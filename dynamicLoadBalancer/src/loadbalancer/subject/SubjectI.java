package loadbalancer.subject;

public interface SubjectI {
	
	public void registerObservers();
	public void unregisterObservers();
	public void notifyLoadBalancer(String serviceName);
	public void notifyServiceManager();
}