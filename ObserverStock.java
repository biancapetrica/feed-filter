import java.util.*;

/**
 * Created by bianca on 12/16/2017.
 */
public class ObserverStock implements IObserver {
    public int obs_id;
    private String filter;
    private Node root;
    private TreeMap<String, Feed> stockTree = new TreeMap<>();
    private ArrayList<Feed> stock = new ArrayList<>(); //lista de feed-uri
    private TreeMap<String, Float> increase = new TreeMap<>();

    /**
     * Constructor
     * @param obs_id id-ul observerului
     * @param filter filtru
     */
    public ObserverStock(int obs_id, String filter){
        this.obs_id = obs_id;
        this.filter = filter;
        this.root = getTree(filter);
    }

    /**
     *
     * @return filter
     */
    public String getFilter(){
        return this.filter;
    }

    /**
     * Creeaza arborele de parsare corespunzator filtrului
     * @param filter filtru
     * @return varful arborelui de parsare
     */
    public Node getTree(String filter){
        if (filter.equals("nil")){
            return null;
        } else {
            //Creez forma postfix a expresiei
            InfixToPostfix p = new InfixToPostfix();
            String[] postfix = p.toPostfix(filter);
            //Creez arborele de parsare
            FilterTree tree = new FilterTree();
            tree.root = tree.create(postfix);
            return tree.root;
        }
    }

    /**
     * Metoda din care arborele de parcurs si evaluat din radacina.
     * Arborele fiind unul generic, compatibil pentru mai multe filtre de
     * evaluare trebuie inlocuite valorile din  nodurile care au stocate
     * informatia "name"/"value" cu valorile din parametrii name sau value.
     * @param root varful arborelui
     * @param name
     * @param value
     * @param filter filtru
     * @return rezultatul obtinut in urma evaluarii arborelui de parsare
     */
    public static String evaluate(Node root, String name, String value,
                                  String filter) {
        if (filter.equals("nil")) {
            //nu exista filtru
            return "true";
        } else {
            EvaluateVisitor eval = new EvaluateVisitor ();
            return root.accept(eval, name, value);
        }
    }

    /**
     * Adauga feed-ul primit ca parametru la lista de stock a observerlui.
     * @param feed
     */
    public void addStock(Feed feed){
        this.stock.add(feed);
    }

    /**
     * Verific daca feed-ul primit ca parametru trece prin filtru, iar in caz
     * afirmativ il adaug in TreeMap-ul de stock-uri.
     * @param feed
     * @param filter
     */
    @Override
    public void update(Feed feed, String filter) {
        if (evaluate(root, feed.getName(), feed.getValue(),
                filter).equals("true")) {
            //feed-ul a  trecut de filtru si il adaug in TreeMap
            this.stockTree.put(feed.getName(), feed);
            //daca este un feed nou ii setez "ultimul" value 0 pentru increase
            if (!this.increase.containsKey(feed.getName()))
                this.increase.put(feed.getName(), (float) 0);
        } else {
            //daca feed-ul nu a trecut de filtru
            if (stockTree.containsKey(feed.getName())) {
                //daca exista un alt feed cu acelasi name in stock il sterg
                stockTree.remove(feed.getName());
            }
        }
    }

    /**
     * Afiseaza toate intrarile din TreeMap-ul observatorului.
     */
    public void print(){
        if (!stockTree.isEmpty()) {
            for (Map.Entry<String, Feed> mentry : stockTree.entrySet()) {
                Feed feed = (Feed) mentry.getValue();

                //calculez fluctuatia pretului de la ultima valoare afisata
                float increase = Float.parseFloat(this.increase.get(mentry.getKey
                                ()).toString());
                if (increase != 0)
                    increase = (Float.parseFloat(feed.getValue()) - increase) /
                            increase * 100;

                //determin nrOfChanges contorizand cate alte feed-uri cu
                //acelasi name sunt in stock
                int nrOfChanges = 0;
                Iterator<Feed> iter = stock.iterator();
                while(iter.hasNext()){
                    Feed f = iter.next();
                    if (f.getName().equals(feed.getName())) {
                        nrOfChanges++;
                        iter.remove(); //sterg din stock feed-urile cu acelasi
                        // name-ul cu al feed-ului curent ce urmeaza afisat
                    }
                }
                //actualizez ultimul value pentru stock
                this.increase.put(mentry.getKey(), Float.parseFloat(feed.getValue
                                ()));

                System.out.print("obs " + obs_id + ": " + mentry.getKey() + " ");
                System.out.printf("%.2f", Float.parseFloat(feed.getValue()));
                System.out.print(" ");
                System.out.printf("%.2f", increase);
                System.out.println("% " + nrOfChanges);
            }
        }
    }
}
