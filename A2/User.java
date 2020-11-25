package A2;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User extends UserComponent implements Subject, Observer, Visitable {
    
    private ObservableList<Observer> followers;
    private ObservableList<Subject> following;
    private ObservableList<String> followingMessage;
    private ObservableList<String> messages;
    private int mid;
    private String uid;
    
    public User(String uid){
        this.followers = FXCollections.observableArrayList();
        this.following = FXCollections.observableArrayList();
        this.messages = FXCollections.observableArrayList();
        this.followingMessage = FXCollections.observableArrayList();
        this.uid = uid;
    }
    
    public void register(Observer follower) {
        // Not the same user and not an existing follower
        if(!this.uid.equals(follower.getUID()) ) {
           followers.add(follower);
        }
    }

    
    public void unregister(Observer follower) {
        followers.remove(follower.getUID());
       
    }

    public void notifyObserver() { 
        String latestMessage = messages.get(messages.size() -1);
        
        for(Observer follower : followers) {
            follower.update(latestMessage, this.uid);
            System.out.println(this.uid + " : " + latestMessage);
        }
    }

    public void update(String message, String subjectID) {
        followingMessage.add("New Tweet by @" + subjectID + " : " + message);
        System.out.println(subjectID + " : " + message);
    }
    
    public boolean follow(Subject subjectUser) {
        // Not the same user and not alrd following
        if(!subjectUser.equals(this) ) {
            subjectUser.register(this);
            following.add(subjectUser);
            return true;
        }
           
        return false;
    }
   
    public void addMessage(String message) {
       this.messages.add(message);
    }
    
    public String getUID() {
        return uid;
    }
    
    public int getFollowersNum() {
        return followers.size();
    }
    
    public int getFollowingNum() {
        return following.size();
    }
    
    public ObservableList<String> getUserMessages() {
        return messages;
    }
    
    public ObservableList<String> getFollowingMessages() {
       return followingMessage;
    }

    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
