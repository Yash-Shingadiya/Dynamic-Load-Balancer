package loadbalancer.entities;

import loadbalancer.util.FileProcessor;
import java.util.ArrayList;
import java.util.List;
import loadbalancer.entities.Machine;

public class Service {
		// Service URL.
		private String url;
		// Service name.
		private String serviceName;
		/*public String[] hosts;
		
		public void setUrl(String url){
			this.url = url;
		}
		public void setServiceName(String serviceName){
			this.serviceName = serviceName;
		}
		public String getUrl(){
			return this.url;
		}
		public String getServiceName(){
			return this.serviceName;
		}
		public String getHosts(int index){
			return this.hosts[index];
		}

		public void serviceInfoProcessing(String inputFile){

			FileProcessor f = new FileProcessor(inputFile);
			String info = null;
			String[] commands = null;
			String[] associatedHosts = null;

			while((info = f.readLineFromFile()) != null){
				
				commands = info.split(" ");
		
				if(commands[0].equals("SERVICE_OP__ADD_SERVICE")){
					
					Service service = new Service();
					Machine machine = new Machine();

					service.setServiceName(commands[1]);
					service.setUrl(commands[2]); 
					System.out.println(service);
					machine.machineInfoProcessing(inputFile,service);
				}

			}

		}*/
	}
