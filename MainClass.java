
import java.util.*;

/**
 * Created by bianca on 12/5/2017.
 */
public class MainClass {

    public static void main(String[] args) {
        //creez o instanta a clasei SubjectF
        IFactory factory = SingletonFactory.getInstance();;
        SubjectF f = factory.create();

        Scanner scan = new Scanner(System.in);
        String buffer = scan.nextLine();

        if (buffer.equals("begin")){
            //citesc de la System.in cate o linie
            buffer = scan.nextLine();
            while (!buffer.equals("end")){
                //separ in string-uri dupa " " pentru a determina comenziile
                String[] tokens = buffer.split(" ");
                switch (tokens[0]) { //comanda
                    case "feed":
                        f.addFeed(new Feed(tokens[1], tokens[2]));
                        break;
                    case "create_obs":
                        int length = tokens.length;
                        for (int i = 3; i< length; i++) {
                            tokens[2] += " " + tokens[i];
                            tokens[i] = null;
                        } //reconstruiesc filtru care este separat dupa " "
                        f.registerObserver(new ObserverStock(Integer.parseInt
                                (tokens[1]), tokens[2]));
                        break;
                    case "delete_obs":
                        f.removeObserver(Integer.parseInt(tokens[1]));
                        break;
                    case "print":
                        f.printObserver(Integer.parseInt(tokens[1]));
                        break;
                    default:
                        break;
                }
                buffer = scan.nextLine();
            }
        }
    }
}
