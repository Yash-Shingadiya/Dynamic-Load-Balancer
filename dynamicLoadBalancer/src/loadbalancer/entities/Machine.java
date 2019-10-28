package loadbalancer.entities;

import java.util.Map;
import java.util.HashMap;
import loadbalancer.util.FileProcessor;
import loadbalancer.entities.Service;

public class Machine{
	
	private String hostname;
	// Service name to hosted services.
	private Map<String, Service> hostedServices;
	Service service;

	public Machine(String hostname){
		this.hostname = hostname;
		this.hostedServices = new HashMap<>();
	}
	
	public void setHostedServices(String serviceName, String url){
		
		if(this.hostedServices.containsKey(serviceName)){
			System.out.println("Service already exists for"+" "+this.hostname);
		}
		else{
			this.hostedServices.put(serviceName,new Service(serviceName,url));
			System.out.println("Service Added to"+" "+this.hostname);
		}
	}

	public Service getService(String serviceName){
		service = this.hostedServices.get(serviceName);
		return service;
	}

	public String getHostName(){
		return this.hostname;
	}
	
	public Map getHostedServices(){
		return this.hostedServices;
	}
	
}
