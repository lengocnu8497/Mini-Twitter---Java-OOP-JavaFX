package A2;

public interface Subject {
    public void register(Observer follower);
    public void unregister(Observer follower);
    public void notifyObserver();  
    public String getUID();
}
