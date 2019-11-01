package loadbalancer.observer;

import java.util.List;
import java.util.ArrayList;

public class ServiceManager implements ObserverI{
		private String key;

		// Information pertaining to the service.
		private String url;
		private List<String> hostnames;

		public ServiceManager(){
			
			this.hostnames = new ArrayList<String>();
		}
		public void setHosts(String url,String hosts){
			
			this.url = url;
			this.hostnames.add(hosts);
			System.out.println(this.url+" "+this.hostnames);
		}
		public List<String> getHosts(){
			return this.hostnames;
		}
		public String getUrl(){
			
			return this.url;
		}
		public void addHostsToService(String hosts){
			
			this.hostnames.add(hosts);
			System.out.println(this.url+" "+this.hostnames);
		}
		public void removeHostsfromService(String hosts){
			
			this.hostnames.remove(hosts);
		}
		public void updateObservers(String operation,String hostname,String serviceName, String url){}
		public void updateObservers(String operation,String hostname){}
		public void updateObservers(String operation,String serviceName,String hostname){}
	}