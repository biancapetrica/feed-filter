/**
 * Created by bianca on 12/16/2017.
 */
public interface ISubject {
    /**
     * Adauga un nou observator in lista de observatori.
     * @param observer
     */
    public void registerObserver(IObserver observer);

    /**
     * Sterge observatorul cu id-ul primit ca parametru din lista de observatori
     * @param obs_id
     */
    public void removeObserver(int obs_id);

    /**
     * Anunta fiecare observator din lista de observatori cu privire la
     * aparitia unui nou feed.
     * @param feed
     */
    public void notifyAllObservers(Feed feed);

    /**
     * Afiseaza informatia din stock-ul observatorului cu id-ul obs_id.
     * @param obs_id
     */
    public void printObserver(int obs_id);
}
