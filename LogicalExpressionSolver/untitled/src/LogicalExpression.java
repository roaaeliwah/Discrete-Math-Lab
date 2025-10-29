import java.util.Map;

public class LogicalExpression implements Expression {
    private String representation;
    private Map<Character, Boolean> vals;

    public LogicalExpression(String representation,  Map<Character, Boolean> vals) {
        this.representation = representation;
        this.vals = vals;
    }

    @Override
    public Map<Character, Boolean> getVals() {
        return vals;
    }

    @Override
    public void setVals(Map<Character, Boolean> vals) {
        this.vals = vals;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }

    @Override
    public void setRepresentation(String representation) {
        this.representation = representation;
    }
}
