import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Mohammed Alshehry on 9/11/14.
 */
public class Etc {

    private ArrayList<Entry> etcArrayList;

    public Etc() {
        etcArrayList = new ArrayList<Entry>();
    }

    public ArrayList<Entry> getEtc() {
        return etcArrayList;
    }

    public void setEtc(ArrayList<Entry> etc) {
        this.etcArrayList = etc;
    }

    public void addEntry(Entry entry) {
        etcArrayList.add(entry);
    }

    public boolean removeEntry(Entry entry) throws NoSuchElementException {
        return etcArrayList.remove(entry);
    }

    public Entry getEntry(int i) {
        return etcArrayList.get(i);
    }

    public Entry find(Entry entry) throws NoSuchElementException {
        int tmp = etcArrayList.indexOf(entry);
        if(tmp == -1)
            throw new NoSuchElementException();
        else
            return etcArrayList.get(tmp);
    }

    public void clear() {
        etcArrayList.clear();
    }

    public boolean contains(String key) {
        boolean flag = false;
        for(int i = 0; i < etcArrayList.size(); i++) {
            if (etcArrayList.get(i).contains(key)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public int length() {
        return etcArrayList.size();
    }

    @Override
    public String toString() {
        return etcArrayList.toString();
    }
}
