package loadbalancer.observer;

public interface ObserverI {
	public void updateLoadBalancer(String serviceName);
	public void updateServiceManager();		
}