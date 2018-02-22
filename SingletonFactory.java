/**
 * Created by bianc on 12/2/2017.
 */
public class SingletonFactory implements IFactory {

    private static SubjectF instance = null;
    private static SingletonFactory instanceSF = null;

    private SingletonFactory() {}

    /**
     *
     * @return o instanta SingletonFactory
     */
    public static SingletonFactory getInstance() {
        if(instanceSF == null)
            instanceSF = new SingletonFactory();
        return instanceSF;
    }

    @Override
    public SubjectF create() {
        if(instance == null) {
            instance = new SubjectF();
        }
        return instance;
    }

    /**
     * Creeaza instante pentru nodurile operator.
     * @param operator tipul operatorului
     * @param left nodul din stanga
     * @param right nodul din dreapta
     * @return nodul creat
     */
    public static Node getOperator (String operator, Node left, Node right){
        switch (operator) {
            case "&&" : return new AndOp(left,right);
            case "||" : return new OrOp(left,right);
            case "le" : return new LesserOp(left,right);
            case "ge" : return new GreaterOp(left,right);
            case "lt" : return new LessThanOp(left,right);
            case "gt" : return new GreaterThanOp(left,right);
            case "eq" : return new EqualOp(left,right);
            case "ne" : return new NotEqualOp(left,right);

            default : return null;
        }
    }
    /**
     * Creeaza instante pentru nodurile operand.
     * @param operand
     * @return nodul creat
     */
    public static Node getOperand (String operand){
        return new StringOperand(operand);
    }
}
