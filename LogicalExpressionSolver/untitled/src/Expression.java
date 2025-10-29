import java.util.Map;

public interface Expression {
    String getRepresentation();

    void setRepresentation(String representation);

    Map<Character, Boolean> getVals();

    void setVals(Map<Character, Boolean> vals);


}
