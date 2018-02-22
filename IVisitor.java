/**
 * Created by bianca on 12/15/2017.
 */

public interface IVisitor {
    /**
     * Metoda prin care se evaluat un nod de tip EqualOp.
     * Se verifica daca copiii nodului au valori egale.
     * @param node
     * @param name valoarea ce va fi utilizata in locului nodului cu
     *             informatia "name" pentru efectuarea evaluarii.
     * @param value valoarea ce va fi utilizata in locului nodului cu
     *             informatia "value" pentru efectuarea evaluarii.
     * @return valoarea returnata dupa evaluarea nodului.
     */
    public String visit(EqualOp node, String name, String value);

    /**
     * Metoda prin care se evaluat un nod de tip NotEqualOp.
     * Se verifica daca copiii nodului au valori diferite.
     * @param node
     * @param name valoarea ce va fi utilizata in locului nodului cu
     *             informatia "name" pentru efectuarea evaluarii.
     * @param value valoarea ce va fi utilizata in locului nodului cu
     *             informatia "value" pentru efectuarea evaluarii.
     * @return valoarea returnata dupa evaluarea nodului.
     */
    public String visit(NotEqualOp node, String name, String value);

    /**
     * Metoda prin care se evaluat un nod de tip LesserOp.
     * Se verifica daca copilul din stanga are valoare mai mica sau egala decat
     * copilul din dreapta.
     * @param node
     * @param name valoarea ce va fi utilizata in locului nodului cu
     *             informatia "name" pentru efectuarea evaluarii.
     * @param value valoarea ce va fi utilizata in locului nodului cu
     *             informatia "value" pentru efectuarea evaluarii.
     * @return valoarea returnata dupa evaluarea nodului.
     */
    public String visit(LesserOp node, String name, String value);

    /**
     * Metoda prin care se evaluat un nod de tip GreaterOp.
     * Se verifica daca copilul din stanga are valoare mai mare sau egala decat
     * copilul din dreapta.
     * @param node
     * @param name valoarea ce va fi utilizata in locului nodului cu
     *             informatia "name" pentru efectuarea evaluarii.
     * @param value valoarea ce va fi utilizata in locului nodului cu
     *             informatia "value" pentru efectuarea evaluarii.
     * @return valoarea returnata dupa evaluarea nodului.
     */
    public String visit(GreaterOp node, String name, String value);

    /**
     * Metoda prin care se evaluat un nod de tip LessThanOp.
     * Se verifica daca copilul din stanga are valoare mai mica decat
     * copilul din dreapta.
     * @param node
     * @param name valoarea ce va fi utilizata in locului nodului cu
     *             informatia "name" pentru efectuarea evaluarii.
     * @param value valoarea ce va fi utilizata in locului nodului cu
     *             informatia "value" pentru efectuarea evaluarii.
     * @return valoarea returnata dupa evaluarea nodului.
     */
    public String visit(LessThanOp node, String name, String value);
    /**
     * Metoda prin care se evaluat un nod de tip GreaterThanOp.
     * Se verifica daca copilul din stanga are valoare mai mare decat
     * copilul din dreapta.
     * @param node
     * @param name valoarea ce va fi utilizata in locului nodului cu
     *             informatia "name" pentru efectuarea evaluarii.
     * @param value valoarea ce va fi utilizata in locului nodului cu
     *             informatia "value" pentru efectuarea evaluarii.
     * @return valoarea returnata dupa evaluarea nodului.
     */
    public String visit(GreaterThanOp node, String name, String value);

    /**
     * Metoda prin care se evaluat un nod de tip AndOp.
     * Se verifica daca copiii nodului au valorea "true".
     * @param node
     * @param name valoarea ce va fi utilizata in locului nodului cu
     *             informatia "name" pentru efectuarea evaluarii.
     * @param value valoarea ce va fi utilizata in locului nodului cu
     *             informatia "value" pentru efectuarea evaluarii.
     * @return valoarea returnata dupa evaluarea nodului.
     */
    public String visit(AndOp node, String name, String value);

    /**
     * Metoda prin care se evaluat un nod de tip OrOp.
     * Se verifica unul din copii nodului are este "true"..
     * @param node
     * @param name valoarea ce va fi utilizata in locului nodului cu
     *             informatia "name" pentru efectuarea evaluarii.
     * @param value valoarea ce va fi utilizata in locului nodului cu
     *             informatia "value" pentru efectuarea evaluarii.
     * @return valoarea returnata dupa evaluarea nodului.
     */
    public String visit(OrOp node, String name, String value);

    /**
     * Metoda prin care se evaluat un nod de tip StringOperand.
     * Intoarce informatia stocata in nod.
     * @param node
     * @param name valoarea ce va fi utilizata in locului nodului cu
     *             informatia "name" pentru efectuarea evaluarii.
     * @param value valoarea ce va fi utilizata in locului nodului cu
     *             informatia "value" pentru efectuarea evaluarii.
     * @return valoarea returnata dupa evaluarea nodului.
     */
    public String visit(StringOperand node, String name, String value);

}

class EvaluateVisitor implements IVisitor {
    @Override
    public String visit(LesserOp node, String name, String value) {
        double left = Float.parseFloat(value);
        double right =  Float.parseFloat(node.getRight().accept(this, name,
                value));
        return (left <= right) ? "true" : "false";
    }

    @Override
    public String visit(GreaterOp node, String name, String value) {
        double left = Float.parseFloat(value);
        double right =  Float.parseFloat(node.getRight().accept(this, name,
                value));
        return (left >= right) ? "true" : "false";
    }

    @Override
    public String visit(LessThanOp node, String name, String value) {
        double left = Float.parseFloat(value);
        double right =  Float.parseFloat(node.getRight().accept(this, name,
                value));
        return (left < right) ? "true" : "false";
    }

    @Override
    public String visit(GreaterThanOp node, String name, String value) {
        double left = Float.parseFloat(value);
        double right =  Float.parseFloat(node.getRight().accept(this, name,
                value));
        return (left > right) ? "true" : "false";
    }

    @Override
    public String visit(EqualOp node, String name, String value) {
        String left = node.getLeft().accept(this, name, value);
        if (left.equals("name"))
            left = name;
        else if (left.equals("value"))
            left = value;
        String right =  node.getRight().accept(this, name, value);
        return (left.equals(right)) ? "true" : "false";
    }

    @Override
    public String visit(NotEqualOp node, String name, String value) {
        String left = node.getLeft().accept(this, name, value);
        if (left.equals("name"))
            left = name;
        else if (left.equals("value"))
            left = value;
        String right = node.getRight().accept(this, name, value);
        return (!left.equals(right)) ? "true" : "false";
    }

    @Override
    public String visit(AndOp node, String name, String value) {
        String left = node.getLeft().accept(this, name, value);
        if (left.equals("name"))
            left = name;
        else if (left.equals("value"))
            left = value;
        String right =  node.getRight().accept(this, name, value);
        return (left.equals(right) && left.equals("true")) ? "true" : "false";
    }

    @Override
    public String visit(OrOp node, String name, String value) {
        String left = node.getLeft().accept(this, name, value);
        if (left.equals("name"))
            left = name;
        else if (left.equals("value"))
            left = value;
        String right =  node.getRight().accept(this, name, value);
        return (left.equals("true") || right.equals("true")) ? "true" : "false";
 }

    @Override
    public String visit(StringOperand node, String name, String value) {
        return node.getValue();
    }
}

