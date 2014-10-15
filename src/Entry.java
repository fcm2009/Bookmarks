/**
 * Created by Mohammed Alshehry on 9/11/14.
 */
public class Entry implements Comparable {

    String field;
    String value;

    public Entry(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public Entry(String field) {
        this.field = field;
    }

    public Entry() {
        this(null);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean contains(String key) {
        if(value.contains(key))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return field + ": " + value;
    }

    @Override
    public int compareTo(Object obj) {
        if(obj != null) {
            if(obj instanceof Entry) {
                Entry entry = (Entry) obj;
                if(this.field.equalsIgnoreCase(entry.getField()))
                    return this.value.compareToIgnoreCase(entry.getValue());
                else
                    return this.field.compareToIgnoreCase(entry.getField());
            }
            else
                throw new ClassCastException();
        }
        else
            throw new NullPointerException();
    }

    @Override
    public boolean equals(Object obj) {
        return compareTo(obj) == 0;
    }
}
