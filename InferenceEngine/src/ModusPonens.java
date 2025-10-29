public class ModusPonens implements InferenceRule{
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        String e1 = exp1.getRepresentation();
        String e2 = exp2.getRepresentation();
        return (e1.contains(">") && e2.charAt(0) == e1.charAt(0) && e2.length() == 1)
                || (e2.contains(">") && e1.charAt(0) == e2.charAt(0) && e1.length() == 1);
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        String e1 = exp1.getRepresentation();
        String e2 = exp2.getRepresentation();
        if(matches(exp1, exp2)) {
            return new Expression(Character.toString(e1.charAt(0)));
        }
        return null;
    }

    @Override
    public String name() {
        return "Modus Ponens";
    }
}
