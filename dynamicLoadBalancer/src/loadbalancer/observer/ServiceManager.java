package loadbalancer.observer;

import java.util.List;
import java.util.ArrayList;

/**
 * The Service Manager returns the host name and url corresponding to a requested service name to the load balancer
 */
public class ServiceManager implements ObserverI{
		private String key;

		/**
		 * Information pertaining to the service.
		 */
		private String url;
		private List<String> hostnames;

		/**
		 * Constructor for intitializing
		 */
		public ServiceManager(){
			
			this.hostnames = new ArrayList<String>();
		}
		public void setHosts(String url,String hosts){
			
			this.url = url;
			this.hostnames.add(hosts);
		}
		/**
		 * Getters
		 */
		public List<String> getHosts(){
			return this.hostnames;
		}
		public String getUrl(){
			
			return this.url;
		}
		/**
		 * Adding hosts associated to the service
		 */
		public void addHostsToService(String hosts){
			
			this.hostnames.add(hosts);
		}
		public void removeHostsfromService(String hosts){
			
			this.hostnames.remove(hosts);
		}
		
		public void updateObservers(String operation,String hostname,String serviceName, String url){}
		public void updateObservers(String operation,String hostname){}
		public void updateObservers(String operation,String serviceName,String hostname){}
	}