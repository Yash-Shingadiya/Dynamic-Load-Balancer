package loadbalancer.subject;

import loadbalancer.subject.Cluster;
import loadbalancer.util.FileProcessor;

/**
 * Preprocessing the input file and calling appropriate operations on the Cluster
 */
public class InputProcessor{

	public String inputFileProcessing(String inputFile){

		/**
		 * Reading input file
		 */
		FileProcessor fileprocessor = new FileProcessor(inputFile);
		String info = null;
		String[] commands = null; 
		Cluster cluster = new Cluster();
		try{
			while((info = fileprocessor.readLineFromFile()) != null){
			
				commands = info.split(" ");
				if(commands[0].equals("CLUSTER_OP__SCALE_UP")){
					cluster.CLUSTER_OP__SCALE_UP(commands[1]);
				}
				else if(commands[0].equals("CLUSTER_OP__SCALE_DOWN")){
					cluster.CLUSTER_OP__SCALE_DOWN(commands[1]);
				}
				else if(commands[0].equals("SERVICE_OP__ADD_SERVICE")){
					cluster.SERVICE_OP__ADD_SERVICE(commands[1],commands[2],commands[3]);
				}
				else if(commands[0].equals("SERVICE_OP__REMOVE_SERVICE")){
					cluster.SERVICE_OP__REMOVE_SERVICE(commands[1]);
				}
				else if(commands[0].equals("SERVICE_OP__ADD_INSTANCE")){
					cluster.SERVICE_OP__ADD_INSTANCE(commands[1],commands[2]);
				}
				else if(commands[0].equals("SERVICE_OP__REMOVE_INSTANCE")){
					cluster.SERVICE_OP__REMOVE_INSTANCE(commands[1],commands[2]);
				}
				else if(commands[0].equals("REQUEST")){
					cluster.REQUEST(commands[1]);
				}
			}

			String finalResults = cluster.getFinalResults();
			return finalResults;
		}

		catch(Exception e){

			System.err.println("Please check the formatting of input file");
			System.exit(0);
		}
		
		finally{}
		
		return null;	
	}
} 