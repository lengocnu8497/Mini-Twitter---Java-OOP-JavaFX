package A2;

public interface Visitor {
    // 4 buttons to implements visitor
    public int visit(User user);
    public int visit(UserGroup userGroup);
}
