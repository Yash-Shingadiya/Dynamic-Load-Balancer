package loadbalancer.observer;

public interface ObserverI {
	
	public void updateObservers(String operation,String hostname,String serviceName, String url);
	public void updateObservers(String operation,String hostname);
	public void updateObservers(String operation,String serviceName,String hostname);
}