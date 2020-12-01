package A2;

public class LastUpdateDetector implements Visitable{

    public LastUpdateDetector() {}
    
    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }   
}
