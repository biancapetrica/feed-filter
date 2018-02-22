/**
 * Created by bianca on 12/2/2017.
 */

public interface IObserver {
    /**
     * Verific daca feed-ul primit ca parametru trece prin filtru.
     * @param feed
     * @param filter
     */
    public void update(Feed feed, String filter);
}
