package loadbalancer.subject;

public interface SubjectI {
	
	public void registerObservers();
	public void notifyAllObservers(String operation,String hostname,String serviceName, String url);
	public void notifyAllObservers(String operation,String hostname);
}