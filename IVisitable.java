/**
 * Created by bianca on 12/15/2017.
 */
public interface IVisitable {
    /**
     * Metoda din care nodul accepta vizitatorul si astfel este parcurs si
     * evaluat. Arborele fiind unul generic, compatibil pentru mai
     * multe filtre de evaluare trebuie inlocuite valorile din
     * nodurile care au stocate informatia "name"/"value" cu valorile din
     * parametrii name sau value.
     * @param visitor vizitator
     * @param name
     * @param value
     * @return valoarea pe care o returneaza visitor
     */
    String accept(IVisitor visitor, String name, String value);
}
