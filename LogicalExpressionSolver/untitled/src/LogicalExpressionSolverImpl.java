import java.util.Map;
import java.util.Stack;

public class LogicalExpressionSolverImpl implements LogicalExpressionSolver {

    @Override
    public boolean evaluateExpression(Expression expression) {
        String infix = expression.getRepresentation();
        String postfix = infixToPostfix(infix);
        return evalPostfix(postfix, expression.getVals());
    }

    private int prec(char ch) {
        if(ch == '~') {
            return 3;
        }
        if(ch == '^' || ch == 'v') {
            return 2;
        }
        if(ch == '>') {
            return 1;
        }
        return -1;
    }

    private String infixToPostfix(String infix) {
        StringBuilder postfix = new  StringBuilder();
        Stack<Character> operators= new Stack<>();
        for(int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            if(Character.isLetter(ch) && ch != 'v') {
                postfix.append(ch);
            }
            else if(ch == '(') {
                operators.push(ch);
            }
            else if(ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    postfix.append(operators.pop());
                }
                operators.pop();
            }
            else if(ch == ' ') {
                continue;
            }
            else {
                while (!operators.isEmpty() && operators.peek() != '(' && (prec(operators.peek()) > prec(ch) ||
                        prec(operators.peek()) == prec(ch))) {
                    postfix.append(operators.pop());
                }
                operators.push(ch);
            }

        }

        while (!operators.isEmpty()) {
            postfix.append(operators.pop());
        }

        return postfix.toString();
    }

    private boolean evalPostfix(String postfix, Map<Character, Boolean> vals) {
        Stack<Boolean> operands= new Stack<>();
        for(int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);
            if(Character.isLetter(ch) && ch!='v') {
                operands.push(vals.get(ch));
            }

            else {
                switch (ch) {
                    case '~':
                        operands.push(!operands.pop());
                        break;
                    case '^':
                        boolean op2 = operands.pop();
                        boolean op1 = operands.pop();
                        operands.push(op1 && op2);
                        break;
                    case 'v':
                        boolean op4 = operands.pop();
                        boolean op3 = operands.pop();
                        operands.push(op3 || op4);
                        break;
                    case '>':
                        boolean op6 = operands.pop();
                        boolean op5 = operands.pop();
                        operands.push(!op5 || op6);
                        break;
                }
            }
        }
        return operands.pop();

    }
}
