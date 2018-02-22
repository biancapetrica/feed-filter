import java.util.Stack;

/**
 * Created by bianca on 12/12/2017.
 */
public class FilterTree {

    static Node root = null;

    /**
     * Metoda genereaza arborele de parsare construit din expresia filtrului
     * in forma postfix
     * @param filter filtrul
     * @return radacina arborelui de parsare
     */
    public static Node create(String[] filter){
        SingletonFactory singletonFactory = SingletonFactory.getInstance();

        //Construiesc o stiva din vectorul de string-uri filter
        Stack<String> stackPostfix = new Stack<String>();
        for (int i =  filter.length - 1; i >= 0; i--) {
            stackPostfix.push(filter[i]);
        }

        //Construiesc o stiva unde voi adauga toate nodurile arborelui
        Stack<Node> stack = new Stack<Node>();
        while (!stackPostfix.empty()){
            String postfix = stackPostfix.pop();
            try {
                Node node;
                switch (postfix) {
                    case "&&" : case "||" : case "le" : case "ge" : case "lt"
                        : case "gt" : case "eq" : case "ne" :
                    Node right = stack.pop();
                    Node left = stack.pop();
                    //Creeaza o instanta pentru un nod operator cu design
                        // pattern-ul SingletonFactory
                    node = singletonFactory.getOperator(postfix, left, right);

                    stack.push(node); //adaug nodul in stiva
                    break;
                    default :
                        //Creeaza o instanta pentru un nod operand cu design
                        // pattern-ul SingletonFactory
                        node = singletonFactory.getOperand(postfix);
                        stack.push(node);
                }
            } catch (Exception e) {
                System.out.println("Invalid Expression");
            }
        }
        return stack.pop(); // returneaza varful arborelui
    }

}
