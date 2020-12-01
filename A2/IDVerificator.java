package A2;


public class IDVerificator implements Visitable {

    public IDVerificator() {}
    
    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
    
}
