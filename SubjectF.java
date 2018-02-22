import java.util.*;

/**
 * Created by bianca on 12/2/2017.
 */

public class SubjectF implements ISubject {

    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    private List<Feed> feeds = new ArrayList<>();
    private TreeMap<String, Feed> feedsToBeProcessed= new TreeMap<>();

    @Override
    public void registerObserver(IObserver observer){
        observers.add(observer);
        if (!this.feeds.isEmpty()) {
            //exista feed creat anterior observatorului
            for (Feed f : feeds) {
                //adauga in TreeMap tot feed-ul anterior pentru a putea fi
                //procesat de noul observator ulterior la comanda de
                //notifyAllObservers
                feedsToBeProcessed.put(f.getName(), f);
            }
        }
    }

    /**
     * Adaug feed la lista de feed-uri a subiectlui si in TreeMap feed-ul
     * pentru a putea fi procesat ulterior de fiecare observator in parte.
     * @param feed
     */
    public void addFeed(Feed feed){
        feeds.add(feed);
        this.updateStockObserver(feed);
        feedsToBeProcessed.put(feed.getName(), feed);
    }

    /**
     * Anunta fiecare observator din lista de observatori cu privire la
     * aparitia unui nou feed pentru ca observatorul sa adauge feed-ul il
     * lista de stock-uri.
     * @param feed
     */
    public void updateStockObserver(Feed feed){
        for (IObserver observer : observers) {
            ((ObserverStock)observer).addStock(feed);
        }
    }

    @Override
    public void removeObserver(int obs_id) {
        Iterator<IObserver> iter = this.observers.iterator();
        while(iter.hasNext()){
            IObserver observer = iter.next();
            if (((ObserverStock)observer).obs_id == obs_id) {
                //am gasit observatorul cu id-ul obs_id si il sterg
                iter.remove();
            }
        }
    }

    @Override
    public void notifyAllObservers(Feed feed){
        for (IObserver observer : observers) {
            observer.update(feed, ((ObserverStock)observer).getFilter());
        }
    }

   @Override
    public void printObserver(int obs_id){
        //anunt observatorii ca feed-urile din TreeMap trebuie sa treaca prin
        //filtru
        for (Map.Entry<String, Feed> mentry : feedsToBeProcessed.entrySet()) {
            Feed feed = (Feed) mentry.getValue();
            notifyAllObservers(feed);
        }
        for (IObserver observer : observers) {
            if (((ObserverStock)observer).obs_id == obs_id) {
                //afisez stock-ul de feed-uri pentru observatorul cu id-ul
                //obs_id
                ((ObserverStock)observer).print();
                break;
            }
        }
        feedsToBeProcessed.clear(); //golesc TreeMap-ul de feed-uri
    }
}
