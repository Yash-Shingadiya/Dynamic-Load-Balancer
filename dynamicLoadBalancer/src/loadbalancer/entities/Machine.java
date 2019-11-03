package loadbalancer.entities;

import java.util.Map;
import java.util.HashMap;
import loadbalancer.util.FileProcessor;
import loadbalancer.entities.Service;

/**
 * Machine has the mapping of service name and Service. Also, it contains hostnames
 */
public class Machine{
	
	private String hostname;
	/**
	 * Mapping of Service name to hosted services
	 */
	private Map<String, Service> hostedServices;
	Service service;

	/**
	 * Constructor for initialization
	 */
	public Machine(String hostname){
		this.hostname = hostname;
		this.hostedServices = new HashMap<>();
	}
	
	/**
	 * Setters
	 */
	public void setHostedServices(String hostname,Service service){
		
		this.hostname = hostname;
		this.service = service;
		this.hostedServices.put(this.service.getServiceName(),this.service);
	}

	/**
	 * Getters
	 */
	public String getHostName(){
		return this.hostname;
	}
	
	public Map getHostedServices(){
		return this.hostedServices;
	}

	public Service getService(String serviceName){

		if(this.hostedServices.containsKey(serviceName)){

			this.service = this.hostedServices.get(serviceName);
		}
		return this.service;
	}
}
