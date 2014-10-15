/**
 * Created by Mohammed Alshehry on 9/10/14.
 */
public class Bibtex  implements Comparable {

    private String id;
    private String type;
    private Etc etc;

    public Bibtex(String id, String type, Etc etc) {
        this.id = id;
        this.type = type;
        this.etc = etc;
    }

    public Bibtex(String id, String type) {
        this(id, type, new Etc());
    }

    public Bibtex() {
        this(null, null, new Etc());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

    public Etc getEtc() {
        return etc;
    }

    public void setEtc(Etc etc) {
        this.etc = etc;
    }

    public void addEntry(Entry entry) {
        etc.addEntry(entry);
    }

    public boolean removeEntry(Entry entry) {
        return etc.removeEntry(entry);
    }

    public boolean hasField(String field) {
        if(field.equalsIgnoreCase("id") || field.equalsIgnoreCase("type"))
            return true;
        else {
            for(int i = 0; i < etc.length(); i++)
                if(etc.getEntry(i).getField().equalsIgnoreCase(field))
                    return true;
            return false;
        }
    }

    public Entry getEtcEntry(String field) {
        for(int i = 0; i < etc.length(); i++)
            if(etc.getEntry(i).getField().equalsIgnoreCase(field))
                return etc.getEntry(i);
        return new Entry("", "");
    }

    public boolean contains(String key) {
        if(id.contains(key) || type.contains(key) || etc.contains(key))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public int compareTo(Object obj) {
        if(obj != null) {
            if(obj instanceof Bibtex) {
                Bibtex bibtex = (Bibtex) obj;
                if(this.id.equalsIgnoreCase(bibtex.getId()))
                    return this.type.compareToIgnoreCase(bibtex.gettype());
                else
                    return this.id.compareToIgnoreCase(bibtex.getId());
            }
            else
                throw new ClassCastException();
        }
        else
            throw new NullPointerException();
    }

    @Override
    public boolean equals(Object obj) {
        return this.compareTo(obj) == 0;
    }

}
