package loadbalancer.subject;

import loadbalancer.subject.Cluster;
import loadbalancer.util.FileProcessor;

public class InputProcessor{

	public void inputFileProcessing(String inputFile){

		FileProcessor f = new FileProcessor(inputFile);
		String info = null;
		String[] commands = null; 
		/*List<Service> serviceList = new ArrayList<Service>();*/
		Cluster cluster = new Cluster();

		while((info = f.readLineFromFile()) != null){
			
			commands = info.split(" ");
			if(commands[0].equals("CLUSTER_OP__SCALE_UP")){
				cluster.CLUSTER_OP__SCALE_UP(commands[1]);
			}
			else if(commands[0].equals("CLUSTER_OP__SCALE_DOWN")){
				cluster.CLUSTER_OP__SCALE_DOWN(commands[1]);
			}
			else if(commands[0].equals("SERVICE_OP__ADD_SERVICE")){
				cluster.SERVICE_OP_ADD_SERVICE(commands[1],commands[2],commands[3]);
			}
			/*else if(commands[0].equals("SERVICE_OP__REMOVE_SERVICE")){
				cluster.SERVICE_OP_REMOVE_SERVICE(commands[1],commands[2],commands[3]);
			}
			else if(commands[0].equals("SERVICE_OP__ADD_INSTANCE")){
				cluster.SERVICE_OP_ADD_INSTANCE(commands[1],commands[2]);
			}
			else if(commands[0].equals("SERVICE_OP__REMOVE_INSTANCE")){
				cluster.SERVICE_OP_REMOVE_INSTANCE(commands[1],commands[2]);
			}*/
		}
	}
} 