package loadbalancer.subject;

/**
 * Basic methods of Observer pattern which must be implemented by the Subject(Cluster)
 */
public interface SubjectI {
	
	public void registerObservers();
	public void notifyAllObservers(String operation,String hostname,String serviceName, String url);
	public void notifyAllObservers(String operation,String hostname);
	public void notifyAllObservers(String operation,String hostname,String serviceName);
}