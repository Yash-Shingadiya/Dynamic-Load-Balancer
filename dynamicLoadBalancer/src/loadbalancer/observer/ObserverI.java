package loadbalancer.observer;

/**
 * Basic methods of Observer method pattern which must be implemented by the Concrete Observers
 */
public interface ObserverI {
	
	public void updateObservers(String operation,String hostname,String serviceName, String url);
	public void updateObservers(String operation,String hostname);
	public void updateObservers(String operation,String serviceName,String hostname);
}