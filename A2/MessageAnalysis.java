package A2;


public class MessageAnalysis implements Visitable {

    public MessageAnalysis() {}
    
    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
