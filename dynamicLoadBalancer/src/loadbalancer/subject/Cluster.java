package loadbalancer.subject;

import java.util.Map;
import java.util.HashMap;
import loadbalancer.entities.Machine;
import loadbalancer.entities.Service;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.observer.ServiceManager;

public class Cluster implements SubjectI{
		
		/**
		 * Mapping of hostnames to their corresponding machine instances.
		 */
		private Map<String, Machine> machines;
		Machine machine;
		Service service;
		LoadBalancer loadbalancer;
		ServiceManager servicemanager;
		private String messages = "";

		/**
		 * Constructor for initialization
		 */
		public Cluster(){
			this.machines = new HashMap<>();
			loadbalancer = new LoadBalancer();
		}

		/**
		 * Overriding the methods of SubjectI and implementing them according to the Observer method pattern 
		 */
		public void notifyAllObservers(String operation,String hostname,String serviceName, String url){

			loadbalancer.updateObservers(operation,hostname,serviceName,url);
		}

		public void notifyAllObservers(String operation,String hostname){

			loadbalancer.updateObservers(operation,hostname);
		}

		public void notifyAllObservers(String operation,String serviceName,String hostname){

			loadbalancer.updateObservers(operation,serviceName,hostname);
		}

		public void registerObservers(){}
		
		/**
		 * Preparing the final output
		 */
		public void setFinalResults(String results){
			
			this.messages = this.messages + results+"\n";
		}

		/**
		 * Getting the final output
		 */
		public String getFinalResults(){

			return this.messages;
		}

		/**
		 * Method for adding machine to the Cluster
		 */
		public void CLUSTER_OP__SCALE_UP(String hostname){

			String results = "";
			/**
			 * There should be only unique hosts in the Cluster.
			 */
			if(machines.containsKey(hostname)){
				results = hostname +" "+"Already Exist In The Cluster";
				setFinalResults(results);
			}
			else{
				/**
				 * Host gets added to the cluster if it was not previously added
				 */
				this.machines.put(hostname,new Machine(hostname));
				results = "Cluster Scaled Up";
				setFinalResults(results);
			}
		}

		/**
		 * Method for removing machine from the Cluster
		 */
		public void CLUSTER_OP__SCALE_DOWN(String hostname){

			String results = "";
			if(machines.containsKey(hostname)){
				
				machines.remove(hostname);
				results = "Cluster Scaled Down";
				setFinalResults(results);
				this.notifyAllObservers("CLUSTER_OP__SCALE_DOWN",hostname);
			}
			else{

				results = hostname+" "+"Does Not Exist In The Cluster";
				setFinalResults(results);
			}
		}

		/**
		 * Method for adding Service to the cluster
		 */
		public int SERVICE_OP__ADD_SERVICE(String serviceName,String url,String hosts){

			String results = "";
			String[] hostList = null;
			Map hostedServices = null;
			hostList = hosts.split(",");
			Service service = new Service(serviceName,url);
			
			/**
			 * Validating the conditions before adding service to the Cluster
			 */
			for(int i = 0;i < hostList.length;i++){
				
				if(machines.containsKey(hostList[i])){
					
					machine = machines.get(hostList[i]);
					hostedServices = machine.getHostedServices();

					if(hostedServices.containsKey(serviceName)){
						results = "Service already exists for"+" "+hostList[i];
						setFinalResults(results);
					}
					else{
						machine.setHostedServices(hostList[i],service);	
					}
				}
				else{
					results = "Service Not Added Because"+" "+hostList[i]+" "+"Does Not Exist In The Cluster";
					setFinalResults(results);
					return 0;
				}
			}		
			/**
			 * On successful addition of the service the registered observers gets notified
			 */
			results = "Service Added";
			setFinalResults(results);
			this.notifyAllObservers("SERVICE_OP__ADD_SERVICE",hosts,serviceName,url);
			return 1;
		}

		/**
		 * Method for removing service from the Cluster
		 */
		public void SERVICE_OP__REMOVE_SERVICE(String serviceName){

			String results = "";
			Map hostedServices = null;
			if(loadbalancer.getServiceMapping().containsKey(serviceName)){
				
				for (Map.Entry<String, Machine> entry : machines.entrySet()) {
					
					String hostname = entry.getKey();
					Machine machine = entry.getValue();
					hostedServices = machine.getHostedServices();
					/**
					 * If service with given name exist in the Cluster then remove it               
					 */
					if(hostedServices.containsKey(serviceName)){
						
						hostedServices.remove(serviceName);
					}
				}
				/**
				 * On successful removal of service registered observers gets notified
				 */
				results = "Service Removed";
				setFinalResults(results);
				this.notifyAllObservers("SERVICE_OP__REMOVE_SERVICE",serviceName);				
			}
			else{
				/**
				 * If the service name is invalid
				 */
				results = "Invalid Service";
				setFinalResults(results);	
			}
				
		}

		/**
		 * Method for adding instance of service that has already been added in the Cluster
		 */
		public void SERVICE_OP__ADD_INSTANCE(String serviceName,String hostname){

			String results = "";
			Map hostedServices = null;
			if(this.machines.containsKey(hostname)){

				if(loadbalancer.getServiceMapping().containsKey(serviceName)){
				
					this.servicemanager = loadbalancer.getServiceManager(serviceName);
					/**
					 * Before adding the instance, need to validate if same instance already exists
					 */
					if(this.servicemanager.getHosts().contains(hostname)){
						
						/**
						 * Only one instance can exists
						 */
						results = "Instance Already Exist for"+" "+hostname;
						setFinalResults(results);
					}
					else{

						for (Map.Entry<String, Machine> entry : machines.entrySet()) {
						
							String host = this.machine.getHostName();
							Machine machine = entry.getValue();
							hostedServices = machine.getHostedServices();

							if((hostedServices.containsKey(serviceName))){
								this.service = machine.getService(serviceName);	
							}
							
						}
						/**
						 * On successful addition of the instance registered observers gets notified
						 */
						machine.setHostedServices(hostname,this.service);
						results = "Instance Added";
						setFinalResults(results);
						this.notifyAllObservers("SERVICE_OP__ADD_INSTANCE",serviceName,hostname);
					}
				}
				else{

					results = serviceName+" "+"Was Not previously Added To The Cluster Using SERVICE_OP__ADD_SERVICE ";
					setFinalResults(results);
				}
			}
			else{

				results = serviceName+" "+"Not Removed Because"+" "+hostname+" "+"Does Not Exist In The Cluster";
				setFinalResults(results);
			}		
		}

		/**
		 * Method for removing the instance of service with given name from the given host
		 */
		public void SERVICE_OP__REMOVE_INSTANCE(String serviceName,String hostname){

			String results = "";
			Map hostedServices = null;
			hostedServices = machine.getHostedServices();
			
			if(machines.containsKey(hostname)){

				/**
				 * On successful removal of the instance all the registered observers gets notified
				 */
				if((hostedServices.containsKey(serviceName))){
					hostedServices.remove(serviceName);	
					results = "Instance Removed";
					setFinalResults(results);
					this.notifyAllObservers("SERVICE_OP__REMOVE_INSTANCE",serviceName,hostname);
				}
				else{
					
					results = "No Instance Of"+" "+serviceName+" "+"Is Present On"+" "+hostname;
					setFinalResults(results);
				}
			}
			else{
				
				results = "Instance of"+" "+serviceName+" "+"Not Removed Because"+" "+hostname+" "+"Does Not Exist In The Cluster";
				setFinalResults(results);
			}
				
		}

		/**
		 * Cluster requests load balancer, where the request is processed and accordingly the response is given back by the load balancer
		 */
		public void REQUEST(String serviceName){
			
			String results = "";
			String[] response = new String[2];
			this.servicemanager = loadbalancer.getServiceManager(serviceName);	
			
			if(loadbalancer.getServiceMapping().containsKey(serviceName)){

				/**
				 * If no hosts are hosting a particular service then marking that service as inactive service
				 */
				if(this.servicemanager.getHosts().isEmpty()){
					results = "Service Inactive - Service::"+serviceName;
					setFinalResults(results);	
				}
				else{
					/**
					 * On successful processing of the request the response is sent by the load balancer
					 */
					response = loadbalancer.requestService(serviceName);
					results = "Processed Request - Service_URL::"+response[0]+" "+"Host::"+response[1];
					setFinalResults(results);	
				}
			}
			else{
				results = "Invalid Service";	
				setFinalResults(results);
			}
		}
	}