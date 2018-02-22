import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by bianc on 12/12/2017.
 */
public class InfixToPostfix {

    public String[] result;

    /**
     * Metoda tranforma expresia primit ca paramentru dupa algoritmul
     * postfix intr-un vector de string-uri cu elementele expresiei in
     * oridine postfix.
     * @param infix expresia
     * @return un vector de string-uri
     */
    public String[] toPostfix(String infix){
        //Elimin spatiile de dupa paranteze sau inaintea lor
        infix = infix.replaceAll("\\( ",  "(");
        infix = infix.replaceAll(" \\)", ")");

        //Separ operatorii si operanzii dupa " "
        StringTokenizer tokens = new StringTokenizer(infix," ");

        result = new String[tokens.countTokens()];
        //Creez o stiva
        Stack<String> stack = new Stack<String>();
        int count = 0;

        //System.out.print("BEFORE: ");
        while (tokens.hasMoreTokens()) {
            String temp = tokens.nextToken();
            int i = 0;
            int countParen = 0;
            //verific daca in string exista '(', iar in caz afirmativ le
            // introduc in stiva
            for (i = 0; i < temp.length(); i++){
                if (temp.charAt(i) == '('){
                    stack.push("(");
                } else break;
            }
            if (i > 0) // am descoperit mai multe '(' si le elimin din string
                temp = temp.substring(i, temp.length());
            else {
                //verific daca exista '('
                while (i < temp.length()){
                    if (temp.charAt(i) == ')'){
                        countParen++;
                    }
                    i++;
                }
                if (countParen != 0)
                    // am descoperit mai multe ')' si le elimin din string
                    temp = temp.substring(0, temp.length() - countParen);
            }
            //am eliminat posibile paranteze din string si am ramas doar cu
            // operatorul sau operandul
            switch (temp) {
                case "&&" : case "||" : case "le" : case "ge" : case "lt" :
                case "gt" : case "eq" : case "ne" :
                    //string-ul este un operator
                    while (!stack.isEmpty()) {
                        //scot toate elementele din stiva pana la prima '('
                        // si le adaug in vectorul result
                        String op = stack.peek();
                        if (!op.equals("(")) {
                            stack.pop();
                            result[count] = op;
                            count++;
                        } else break;
                    }
                    stack.push(temp);
                    break;
                default :
                    //string-ul este un operand si il adaug direct in
                    // vectorul result
                    result[count] = temp;
                    count++;
            }
            while (countParen != 0) {
                //pentru fiecare ')' caut in stiva '(', adaugand restul
                // elementelor in vector
                while (!stack.isEmpty()){
                    String op = stack.pop();
                    if (op.equals("(")) {
                        break;
                    }
                    else {
                        result[count] = op;
                        count++;
                    }
                }
                countParen--;
            }
        }

        //In cazul in care stiva nu este goala adaug ce a ramas in vector
        while (!stack.isEmpty()){
            String op = stack.pop();
            result[count] = op;
            count++;
        }
        return result;
    }

}


/*    *//**
 *
 * @param op1
 * @param op2
 * @return 0 - operatorii au aceeasi prioritate
 *         1 -
 *         2 -
 *//*
    public static int checkOrder(String op1, String op2) {
        if (op1.equals(op2))
            return 0; //equal
        else{
            switch (op1) {
                case "&&" : case "||" :
                    System.out.println(op1);
                    switch (op2) {
                        case "le" : case "ge" : case "lt": case "gt":
                        case "eq" : case "ne":
                            return 1;
                        default:
                            return 0;
                    }
                default:
                    switch (op2) {
                        case "||" : case "&&":
                            return 2;
                        default:
                            return 0;
                    }
            }
        }
    }*/