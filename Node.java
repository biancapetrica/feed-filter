
/**
 * Created by bianca on 12/5/2017.
 */

/**
 * Clasa Node reprezinta nodurile arborelui de pasare.
 */
public abstract class Node implements IVisitable {
    private Node left;
    private Node right;

    /**
     * Constructor
     * @param left nodul din stanga
     * @param right nodul din dreapta
     */
    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String accept(IVisitor visitor, String name, String value) {
        throw new java.lang.UnsupportedOperationException("Not implemented " +
                "yet.");
    }

    /**
     *
     * @return nodul din stanga
     */
    public Node getLeft() {
        return left;
    }

    /**
     *
     * @return nodul din dreapta
     */
    public Node getRight() {
        return right;
    }

}

/**
 * Clasa pentru constante, de tip String.
 */
class StringOperand extends Node implements IVisitable {
    private String operand;

    /**
     * Constructor
     * @param operand valoarea operandului
     */
    public StringOperand(String operand){
        super(null, null);
        this.operand = operand;
    }

    /**
     *
     * @return valoarea
     */
    public String getValue() {
        return operand;
    }

    @Override
    public String accept(IVisitor visitor, String name, String value) {
        return visitor.visit(this, name, value);
    }
}

/**
 * Clasa pentru operatorul "le".
 */
class LesserOp extends Node implements IVisitable {

    public LesserOp(Node left, Node right) {
        super(left, right);
    }

    @Override
    public String accept(IVisitor visitor, String name, String value) {
        return visitor.visit(this, name, value);
    }
}

/**
 * Clasa pentru operatorul "lt".
 */
class LessThanOp extends Node implements IVisitable {

    public LessThanOp(Node left, Node right) {
        super(left, right);
    }

    @Override
    public String accept(IVisitor visitor, String name, String value) {
        return visitor.visit(this, name, value);
    }
}

/**
 * Clasa pentru operatorul "ge".
 */
class GreaterOp extends Node implements IVisitable {

    public GreaterOp(Node left, Node right) {
        super(left, right);
    }

    @Override
    public String accept(IVisitor visitor, String name, String value) {
        return visitor.visit(this, name, value);
    }
}

/**
 * Clasa pentru operatorul "gt".
 */
class GreaterThanOp extends Node implements IVisitable {

    public GreaterThanOp(Node left, Node right) {
        super(left, right);
    }

    @Override
    public String accept(IVisitor visitor, String name, String value) {
        return visitor.visit(this, name, value);
    }
}

/**
 * Clasa pentru operatorul "eq".
 */
class EqualOp extends Node implements IVisitable {

    public EqualOp(Node left, Node right) {
        super(left, right);
    }

    @Override
    public String accept(IVisitor visitor, String name, String value) {
        return visitor.visit(this, name, value);
    }
}

/**
 * Clasa pentru operatorul "ne".
 */
class NotEqualOp extends Node implements IVisitable {

    public NotEqualOp(Node left, Node right) {
        super(left, right);
    }

    @Override
    public String accept(IVisitor visitor, String name, String value) {
        return visitor.visit(this, name, value);
    }
}

/**
 * Clasa pentru operatorul "&&".
 */
class AndOp extends Node implements IVisitable {

    public AndOp(Node left, Node right) {
        super(left, right);
    }

    @Override
    public String accept(IVisitor visitor, String name, String value) {
        return visitor.visit(this, name, value);
    }
}

/**
 * Clasa pentru operatorul "||".
 */
class OrOp extends Node implements IVisitable {

    public OrOp(Node left, Node right) {
        super(left, right);
    }

    @Override
    public String accept(IVisitor visitor, String name, String value) {
        return visitor.visit(this, name, value);
    }
}
