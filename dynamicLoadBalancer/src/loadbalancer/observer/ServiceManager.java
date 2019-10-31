package loadbalancer.observer;

import java.util.List;

public class ServiceManager implements ObserverI{
		private String key;

		// Information pertaining to the service.
		private String URL;
		private List<String> hostnames;

		public ServiceManager(){
			System.out.println("yo");
		}
		public void updateServiceManager(){

		}
		public void updateLoadBalancer(String serviceName){

		}
	}