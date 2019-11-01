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

			if(operation.equals("SERVICE_OP__REMOVE_SERVICE")){

				String serviceName = hostname;
				for(int i = 0;i < this.serviceMapping.size(); i++){

					if(this.serviceMapping.containsKey(serviceName)){
						this.serviceMapping.remove(serviceName);
						System.out.println(serviceName+" "+"removed"+this.serviceMapping);
					}
				}		
			}
			
			
		}
	}
