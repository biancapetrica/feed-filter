/**
 * Created by bianca on 12/17/2017.
 */

/**
 * Clasa de baza pentru feed.
 */
public class Feed {

    private String name;
    private String value;

    /**
     * Constructor
     * @param name
     * @param value
     */
    public Feed(String name, String value){
        this.name = name;
        this.value = value;
    }

    /**
     *
     * @return numele feed-ului
     */
    public String getName(){
        return this.name;
    }

    /**
     *
     * @return valoarea feed-ului
     */
    public String getValue(){
        return this.value;
    }

}
