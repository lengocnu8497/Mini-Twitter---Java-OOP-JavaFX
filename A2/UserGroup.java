package A2;

import java.util.HashSet;

public class UserGroup extends UserComponent implements Visitable {
    private String gid;
    private long creationTime;
    private long lastUpdatedTime;
    private HashSet<UserComponent> userComponents;
    
    public UserGroup(String gid) {
        this.gid = gid;
        this.creationTime = System.currentTimeMillis();
        userComponents = new HashSet();
    }
    
    public boolean addUserComponent(UserComponent newUserComponent) {
        if(!userComponents.contains(newUserComponent)) {
            userComponents.add(newUserComponent);
            return true;
        }
         
        return false;
    }
    
    public String getGID() {
        return gid;
    }
    
     public long getCreationTime() {
        return creationTime;
    }

    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
