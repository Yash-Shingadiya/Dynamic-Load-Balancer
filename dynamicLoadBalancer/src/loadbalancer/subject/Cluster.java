package loadbalancer.subject;

import java.util.Map;
import java.util.HashMap;
import loadbalancer.entities.Machine;

public class Cluster implements SubjectI{
		// Hostnames to corresponding machine instances.
		private Map<String, Machine> machines;
		Machine machine;
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
				System.out.println(this.machines);
				System.out.println("Cluster scaled up");
			}
		}
		public void CLUSTER_OP__SCALE_DOWN(String hostname){

			if(machines.containsKey(hostname)){
				
				machines.remove(hostname);
				System.out.println(this.machines);
				System.out.println("Cluster scaled down");
			}
			else{

				System.out.println(hostname+" "+"does not exists in the Cluster");
			}
		}
	}