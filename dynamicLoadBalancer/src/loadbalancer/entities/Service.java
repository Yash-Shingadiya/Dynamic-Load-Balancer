package loadbalancer.entities;

import loadbalancer.util.FileProcessor;
import java.util.ArrayList;
import java.util.List;
import loadbalancer.entities.Machine;

/**
 * Holds all the infor related to services
 */
public class Service {
		/**
		 * Service URL.
		 */
		private String url;
		/**
		 * Service name.
		 */
		private String serviceName;
		
		/**
		 * Constructor for initialization
		 */
		public Service(String serviceName,String url){
			this.serviceName = serviceName;
			this.url = url;
		}

		/**
		 * Getters
		 */
		public String getUrl(){
			return this.url;
		}
		public String getServiceName(){
			return this.serviceName;
		}
	}
