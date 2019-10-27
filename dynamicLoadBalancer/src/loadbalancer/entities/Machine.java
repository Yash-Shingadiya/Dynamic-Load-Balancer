package loadbalancer.entities;

import java.util.Map;
import loadbalancer.util.FileProcessor;

public class Machine{
	public String hostname;
	// Service name to hosted services.
	public Map<String, Service> hostedServices;
	Service service;
	public Machine(String hostname){
		this.hostname = hostname;
		System.out.println(this.hostname+" "+"added to the cluster");
	}
			
}
