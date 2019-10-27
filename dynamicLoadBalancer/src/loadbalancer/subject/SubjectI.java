package loadbalancer.subject;

public interface SubjectI {
	
	public void registerObservers();
	public void unregisterObservers();
	public void notifyAllObservers();
}