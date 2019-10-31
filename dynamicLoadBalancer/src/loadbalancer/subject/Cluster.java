package loadbalancer.subject;

import java.util.Map;
import java.util.HashMap;
import loadbalancer.entities.Machine;
import loadbalancer.entities.Service;
import loadbalancer.observer.LoadBalancer;

public class Cluster implements SubjectI{
		// Hostnames to corresponding machine instances.
		private Map<String, Machine> machines;
		Machine machine;
		Service service;
		LoadBalancer loadbalancer;
		/*Machine machine;*/
		public Cluster(){
			this.machines = new HashMap<>();
			loadbalancer = new LoadBalancer();
		}
		public void registerObservers(){

		}
		public void unregisterObservers(){

		}
		public void notifyServiceManager(){
			
		}
		public void notifyLoadBalancer(String serviceName){
			System.out.println(serviceName);
			loadbalancer.updateLoadBalancer(serviceName);
		}
		public void CLUSTER_OP__SCALE_UP(String hostname){

			if(machines.containsKey(hostname)){
				System.out.println(hostname +" "+"already exists in the cluster");
			}
			else{
				this.machines.put(hostname,new Machine(hostname));
				System.out.println(machines);
				System.out.println("Cluster Scaled Up");
			}
		}
		public void CLUSTER_OP__SCALE_DOWN(String hostname){

			if(machines.containsKey(hostname)){
				
				machines.remove(hostname);
				System.out.println(machines);
				System.out.println("Cluster Scaled Down");
			}
			else{

				System.out.println(hostname+" "+"Does Not Exist In The Cluster");
			}
		}
		public void SERVICE_OP__ADD_SERVICE(String serviceName,String url,String hosts){

			String[] hostList = null;
			Map hostedServices = null;
			hostList = hosts.split(",");
			Service service = new Service(serviceName,url);
			
			for(int i = 0;i < hostList.length;i++){
				
				if(machines.containsKey(hostList[i])){
					
					machine = machines.get(hostList[i]);
					hostedServices = machine.getHostedServices();

					if(hostedServices.containsKey(serviceName)){
						System.out.println("Service already exists for"+" "+hostList[i]);
					}
					else{
						machine.setHostedServices(hostList[i],service);
						System.out.println("Service Added to"+" "+hostList[i]);	
					}
					
					//notifyLoadBalancer(serviceName);
				}
				else{
					System.out.println("Service Not Added Because"+" "+hostList[i]+" "+"Does Not Exist In The Cluster");
				}
			}			
		}

		public void SERVICE_OP__REMOVE_SERVICE(String serviceName){

			Map hostedServices = null;
			
			for (Map.Entry<String, Machine> entry : machines.entrySet()) {
				String hostname = entry.getKey();
				Machine machine = entry.getValue();
				hostedServices = machine.getHostedServices();

				if(hostedServices.containsKey(serviceName)){
					
					hostedServices.remove(serviceName);
					System.out.println(serviceName+" "+"removed from"+" "+hostname);
				}
			}

			//notifyLoadBalancer(serviceName);
			System.out.println("Service Removed");
				
		}
		public void SERVICE_OP__ADD_INSTANCE(String serviceName,String hostname){

			Map hostedServices = null;

			for (Map.Entry<String, Machine> entry : machines.entrySet()) {
				
				String host = this.machine.getHostName();
				Machine machine = entry.getValue();
				hostedServices = machine.getHostedServices();

				if((hostedServices.containsKey(serviceName))){
					this.service = machine.getService(serviceName);	
				}
				
			}
			
			machine.setHostedServices(hostname,this.service);
			System.out.println("Instance Added");
		}

		public void SERVICE_OP__REMOVE_INSTANCE(String serviceName,String hostname){

			Map hostedServices = null;
			hostedServices = machine.getHostedServices();
			
			if(machines.containsKey(hostname)){

				if((hostedServices.containsKey(serviceName))){
					hostedServices.remove(serviceName);	
					System.out.println("Instance Removed"+" "+"from"+" "+hostname);
				}
				else{
					System.out.println("Instance of"+" "+serviceName+" "+"does not exist for"+" "+hostname);
				}
			}
			else{
				System.out.println(hostname+" "+"Does Not Exist In The Cluster");
			}
				
		}
		/*public void REQUEST(String serviceName){
			
			loadbalancer.requestLoadBalancer(serviceName);
		}*/
	}