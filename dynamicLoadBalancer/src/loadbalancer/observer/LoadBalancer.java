package loadbalancer.observer;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import loadbalancer.subject.Cluster;
import loadbalancer.observer.Trie;
import loadbalancer.observer.ServiceManager;

/**
 * Load balancer processes the requests and gives back a response to the Cluster
 */
public class LoadBalancer implements ObserverI{
		
		private Trie ServiceURLAndHostnameFetcher;

		/**
		* Cluster on which the services are hosted.
		*/
		private Cluster cluster;
		/**
		 * Map for storing the mapping between service name and the service manager
		 */
		private Map<String, ServiceManager> serviceMapping;
		ServiceManager servicemanager;
		List<String> processedRequests;

		/**
		 * Constructor for initialization
		 */
		public LoadBalancer(){

			this.serviceMapping = new HashMap<String,ServiceManager>();
			this.servicemanager = new ServiceManager();
			this.processedRequests = new ArrayList<String>();
		}

		/**
		 *Getter
		 */
		public Map getServiceMapping(){

			return this.serviceMapping;
		}

		public ServiceManager getServiceManager(String serviceName){

			if(this.serviceMapping.containsKey(serviceName)){

				this.servicemanager = this.serviceMapping.get(serviceName);
			}
			return this.servicemanager;
		}
		
		/**
		 * Depending on the type of operations observers update their state and they update the mapping of service name and service manager
		 */
		public void updateObservers(String operation,String hosts,String serviceName, String url){
			
			String[] hostList = null;
			hostList = hosts.split(",");
			if(operation.equals("SERVICE_OP__ADD_SERVICE")){
				
				if(this.serviceMapping.containsKey(serviceName)){
	
					this.serviceMapping.put(serviceName,this.servicemanager);
				}
				else{

					this.servicemanager = new ServiceManager();
					this.serviceMapping.put(serviceName,this.servicemanager);	
				}
				

				for(int i = 0;i < hostList.length;i++){

					this.servicemanager.setHosts(url,hostList[i]);
				}
			}
		}

		public void updateObservers(String operation,String hostname){

			if(operation.equals("CLUSTER_OP__SCALE_DOWN")){

				for (Map.Entry<String, ServiceManager> entry : this.serviceMapping.entrySet()) {
					
					String serviceName = entry.getKey();
					ServiceManager servicemanager = entry.getValue();
					if(servicemanager.getHosts().contains(hostname)){
						servicemanager.removeHostsfromService(hostname);
					}

				}
			}

			else if(operation.equals("SERVICE_OP__REMOVE_SERVICE")){

				String serviceName = hostname;
				if(this.serviceMapping.containsKey(serviceName)){
					
					this.serviceMapping.remove(serviceName);
				}			
			}

		}
		public void updateObservers(String operation,String serviceName,String hostname){
			
			if(operation.equals("SERVICE_OP__ADD_INSTANCE")){

				if(this.serviceMapping.containsKey(serviceName)){
					
					this.serviceMapping.get(serviceName).addHostsToService(hostname);
				}				
			}	

			else if(operation.equals("SERVICE_OP__REMOVE_INSTANCE")){

				if(this.serviceMapping.containsKey(serviceName)){

					this.servicemanager = this.serviceMapping.get(serviceName);
					if(this.servicemanager.getHosts().contains(hostname)){

						this.servicemanager.removeHostsfromService(hostname);
					}
					
				}
			}		
		}

		/**
		 * Main algorithm for processing the requests in a round robin fashion
		 */
		public String[] requestService(String serviceName){

			String[] result = new String[2];

			if(this.serviceMapping.containsKey(serviceName)){
				
				this.servicemanager = this.serviceMapping.get(serviceName);
				result[0] = this.servicemanager.getUrl();
				
				if(!this.servicemanager.getHosts().isEmpty()){
					result[1] = this.servicemanager.getHosts().get(0);
					this.servicemanager.getHosts().remove(0);
					
					this.processedRequests.add(result[1]);

				}
				else{
					result[1] = this.processedRequests.get(0);	
					this.processedRequests.remove(0);
				}
				
			}
			return result;
		}
	}
