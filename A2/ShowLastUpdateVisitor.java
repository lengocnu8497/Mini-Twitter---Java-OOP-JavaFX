package A2;

import java.util.HashMap;

public class ShowLastUpdateVisitor implements Visitor {

    private HashMap<Integer, String> map = new HashMap<>();
    
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int visit(LastUpdateDetector detector) {
        AdminController admin = AdminController.getInstance();
        
        int latestSoFar = Integer.MIN_VALUE;
        
        for(User user : admin.getUsers().values()) {
            int current = (int) user.getLastUpdatedTime();
            if(current != 0)
                map.put(current, user.getUID());
            latestSoFar = Math.max(latestSoFar, current);
        }
        
        return latestSoFar;
    }
    
    public HashMap<Integer,String> getUpdateMapping() {
        return map;
    }
    
}
