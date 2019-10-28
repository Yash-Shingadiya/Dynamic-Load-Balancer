package loadbalancer.subject;

import java.util.Map;
import java.util.HashMap;
import loadbalancer.entities.Machine;
import loadbalancer.entities.Service;

public class Cluster implements SubjectI{
		// Hostnames to corresponding machine instances.
		private Map<String, Machine> machines;
		Machine machine;
		Service service;
		/*Machine machine;*/
		public Cluster(){
			this.machines = new HashMap<>();
		}
		public void registerObservers(){

		}
		public void unregisterObservers(){

		}
		public void notifyAllObservers(){
			
		}
		public void CLUSTER_OP__SCALE_UP(String hostname){

			if(machines.containsKey(hostname)){
				System.out.println(hostname +" "+"already exists in the cluster");
			}
			else{
				this.machines.put(hostname,new Machine(hostname));
				System.out.println("Cluster scaled up");
			}
		}
		public void CLUSTER_OP__SCALE_DOWN(String hostname){

			if(machines.containsKey(hostname)){
				
				machines.remove(hostname);
				System.out.println("Cluster scaled down");
			}
			else{

				System.out.println(hostname+" "+"does not exist in the Cluster");
			}
		}
		public void SERVICE_OP_ADD_SERVICE(String serviceName,String url,String hosts){

			String[] hostList = null;
			Map hostedServices = null;
			hostList = hosts.split(",");
			for(int i = 0;i < hostList.length;i++){
				
				if(machines.containsKey(hostList[i])){
					
					machine = machines.get(hostList[i]);
					machine.setHostedServices(serviceName,url);
				}
				else{
					System.out.println("Service not added because"+" "+hostList[i]+" "+"does not exist in the cluster");
				}
			}			
		}
	}