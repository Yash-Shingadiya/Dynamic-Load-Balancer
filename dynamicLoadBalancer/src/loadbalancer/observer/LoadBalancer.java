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
		
		public void requestLoadBalancer(String serviceName){
			System.out.println("hi");
		}		
		public void updateLoadBalancer(String serviceName){

		}
		public void updateServiceManager(){

		}
	}
