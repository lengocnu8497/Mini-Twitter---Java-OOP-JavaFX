package A2;

public class ShowGroupTotalVisitor implements Visitor {
    
    @Override
    public int visit(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int visit(UserGroup userGroup) {
        return 1;
    }
    
}
