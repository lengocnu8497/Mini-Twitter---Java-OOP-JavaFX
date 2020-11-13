package A2;

public class ShowUserTotalVisitor implements Visitor {
   
    public ShowUserTotalVisitor() {};
    
    @Override
    public int visit(User user) {
        return 1;
    }

    @Override
    public int visit(UserGroup userGroup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
