package A2;

public interface Visitor {
    // 6 buttons to implements visitor
    public int visit(User user);
    public int visit(UserGroup userGroup);
    public int visit(MessageAnalysis analysis);
    public int visit(IDVerificator verificator);
    public int visit(LastUpdateDetector detector);
}
