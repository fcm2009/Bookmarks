import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Mohammed Alshehry on 9/15/14.
 */
public class BibtexList {

    private ArrayList<Bibtex> bibtexArrayList;

    public BibtexList() {
        this.bibtexArrayList = new ArrayList<Bibtex>(250);
    }

    public ArrayList<Bibtex> getBibtexArrayList() {
        return bibtexArrayList;
    }

    public void setBibtexArrayList(ArrayList<Bibtex> bibtexArrayList) {
        this.bibtexArrayList = bibtexArrayList;
    }

    public void add(Bibtex bibtex) {
        bibtexArrayList.add(bibtex);
    }

    public void remove(Bibtex bibtex) {
        bibtexArrayList.remove(bibtex);
    }

    public Bibtex find(Bibtex bibtex) {
        int tmp = bibtexArrayList.indexOf(bibtex);
        if(tmp == -1)
            throw new NoSuchElementException();
        else
            return bibtexArrayList.get(tmp);
    }

    public void clear() {
        bibtexArrayList.clear();
    }

    public BibtexList contains(String key) {
        BibtexList result = new BibtexList();
        for(int i = 0; i < bibtexArrayList.size(); i++) {
            if(bibtexArrayList.get(i).contains(key))
                result.add(bibtexArrayList.get(i));
        }
        return result;
    }

    public int length() {
        return bibtexArrayList.size();
    }

    public Bibtex[] toArray() {
        return bibtexArrayList.toArray(new Bibtex[1]);
    }

    @Override
    public String toString() {
        return bibtexArrayList.toString();
    }

}
