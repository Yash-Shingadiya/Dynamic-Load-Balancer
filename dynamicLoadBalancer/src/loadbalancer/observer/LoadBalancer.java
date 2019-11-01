package loadbalancer.observer;

import java.util.Map;
import java.util.HashMap;
import loadbalancer.subject.Cluster;
import loadbalancer.observer.Trie;
import loadbalancer.observer.ServiceManager;


public class LoadBalancer implements ObserverI{
		// Index to find the URL and hostname for a given service name.
		//
		// Trie is optional for under-graduate students.
		// Graduate students have to use a Trie datastructure.
		private Trie ServiceURLAndHostnameFetcher;

		// Cluster on which the services are hosted.
		private Cluster cluster;
		private Map<String, ServiceManager> serviceMapping;
		ServiceManager servicemanager;

		public LoadBalancer(){

			this.serviceMapping = new HashMap<String,ServiceManager>();
			this.servicemanager = new ServiceManager();
		}

		public Map getServiceMapping(){

			return this.serviceMapping;
		}

		public ServiceManager getServiceManager(String serviceName){

			if(this.serviceMapping.containsKey(serviceName)){

				this.servicemanager = this.serviceMapping.get(serviceName);
			}
			return this.servicemanager;
		}
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
					System.out.println(this.serviceMapping);
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
						System.out.println("host removed"+" "+servicemanager.getHosts());
					}

				}
			}

			else if(operation.equals("SERVICE_OP__REMOVE_SERVICE")){

				String serviceName = hostname;
				if(this.serviceMapping.containsKey(serviceName)){
					this.serviceMapping.remove(serviceName);
					System.out.println(serviceName+" "+"removed"+this.serviceMapping);
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

		public String[] requestService(String serviceName){

			String[] result = new String[2];
			if(this.serviceMapping.containsKey(serviceName)){
				
				this.servicemanager = this.serviceMapping.get(serviceName);
				result[0] = this.servicemanager.getUrl();
				result[1] = this.servicemanager.getHosts().get(0);
				this.servicemanager.getHosts().remove(0);
				this.servicemanager.getHosts().add(result[1]);
			}
			return result;
		}
	}
