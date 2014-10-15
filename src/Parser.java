import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Mohammed Alshehry on 9/12/14.
 */
public class Parser {

    private Scanner in;
    private File file;
    private BibtexList bibtexList = new BibtexList();

    public Parser(String path) throws FileNotFoundException {
        this.file = new File(path);
        in = new Scanner(file);
    }

    public BibtexList parseType() throws IOException {
        in.nextLine();                                          //skip first line
        in.nextLine();                                          //skip second line


        String input;

        do {
            in.nextLine();                                          //skip empty line

            input = in.nextLine();
            if(input.split(":")[0].equalsIgnoreCase("@comment{jabref-meta"))
                break;
            String type = (input.split("\\{")[0]).split("@")[1];
            String id = (input.split("\\{")[1]).split("\\,")[0];
            bibtexList.add(parseString(new Bibtex(id, type)));
        } while(true);
        return bibtexList;
    }

    public Bibtex parseString(Bibtex bibtex) throws IOException {
        String input = read();
        String bibEnd = in.nextLine();
        while(!bibEnd.equalsIgnoreCase("}")) {
            String field = input.split("=")[0].trim();
            String value = input.split("=")[1].trim();
            if(!field.equalsIgnoreCase("month"))
                value = value.split("\\{")[1].trim();
            else
                value = value.split("\\,")[0].trim();
            bibtex.addEntry(new Entry(field, value));
            input = read();
            bibEnd = in.nextLine();
        }
        in.nextLine();
        return bibtex;
    }

    private String read() {
        String input;
        in.useDelimiter("(\\}\\,\\r\\n|\\}\\r\\n\\}|\\,\\r\\n[^\\t])");
        input = in.next();
        in.reset();
        return input;
    }

}
