import java.util.*;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter expression to be evaluated: ");
            String input = sc.nextLine();
            Expression expression = new LogicalExpression(input, new HashMap<>());
            LogicalExpressionSolver solver = new LogicalExpressionSolverImpl();

            expression.setRepresentation(input);
            //add all variables in a set to only consider unique values
            Set<Character> variables = new HashSet<>();
            for(int i = 0; i < input.length(); i++){
                if(Character.isLetter(input.charAt(i)) && input.charAt(i) != 'v'){
                    variables.add(input.charAt(i));
                }
            }
            //scans values of all variables and adds them to a map
            Map<Character, Boolean> vals = new HashMap<>();
            for(Character variable : variables){
                System.out.println(variable + "'s value (true/false): ");
                try {
                    boolean value = sc.nextBoolean();
                    vals.put(variable, value);
                }
                catch (Exception e) {
                    System.out.println("wrong boolean value");
                    return;
                }
            }
            expression.setVals(vals);

            try {
                boolean result = solver.evaluateExpression(expression);
                System.out.println("result: " + result + "\n");
            }
            catch (Exception e) {
                System.out.println("Wrong expression");
            }

        }



    }
}