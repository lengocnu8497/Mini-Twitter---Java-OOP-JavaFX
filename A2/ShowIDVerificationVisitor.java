package A2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ShowIDVerificationVisitor implements Visitor {

    private Set<String> idSet = new HashSet<>();
    
    @Override
    public int visit(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int visit(UserGroup userGroup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int visit(MessageAnalysis analysis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int visit(IDVerificator verificator) {
        AdminController admin = AdminController.getInstance();
        
        for(User user : admin.getUsers().values()) {
            String uid = user.getUID();
            if(!idSet.add(uid) || uid.contains(" "))
                return 1;
        }
        
        Iterator iterator = admin.getGroups().iterator();
        while(iterator.hasNext()) {
            UserGroup group = (UserGroup) iterator.next();
            String gid = group.getGID();
            if(!idSet.add(gid) || gid.contains(" "))
                return 1;
        }
        
        return 0;
    }

    @Override
    public int visit(LastUpdateDetector detector) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
