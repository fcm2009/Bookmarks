import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Mohammed Alshehry on 9/12/14.
 */
public class Parser {

    private File db;

    public Parser(File db) {
        this.db = db;
    }

    public ArrayList<File> read() {
        Scanner in;
        ArrayList<File> bookmarks = new ArrayList<File>();
        try{
            in = new Scanner(db);
            while(in.hasNext()) {
                bookmarks.add(new File(in.nextLine()));
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Internal Error. Try again");
        }

        return bookmarks;
    }

    public static ArrayList<File> read(File db) {
        Parser parser = new Parser(db);
        return parser.read();
    }

}
