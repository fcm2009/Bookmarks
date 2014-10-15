import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by st201138010 on 9/14/2014.
 */
public class GUI extends JFrame implements KeyListener, DocumentListener, ListSelectionListener {

    protected BibtexList bibtexList;

    protected JTextField searchTextField;

    protected JLabel searchLabelFirstLine;
    protected JLabel searchLabelSecondLine;
    protected JLabel searchLabelThirdLine;
    protected JLabel searchLabelFourthLine;
    protected JLabel searchLabelFifthLine;

    protected JTextArea logoArea;
    protected JTextArea infoArea;

    protected final Color BACKGROUND_COLOR = Color.BLACK;
    protected final Color FIELD_COLOR = Color.BLACK;
    protected final Color TEXT_COLOR = new Color(254, 248, 130);
    protected final Color BORDER_COLOR = Color.BLACK;

    protected final Font TEXT_FONT = new Font(Font.MONOSPACED, Font.PLAIN ,14);

    protected int hAlignStart = 10;
    protected int hAlignEnd = 427;
    protected int vAlign = 10;
    protected int vGap = 20;

    JScrollPane resultListScrollPane;
    JScrollPane infoLabelScrollPane;

    protected JList resultList;

    public GUI() {
        super("OpenCitation");
        this.setSize(450, 860);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        this.setResizable(false);
        this.addKeyListener(this);

        try{
            bibtexList = new Parser("library.bib").parseType();
        }
        catch(FileNotFoundException e) {
            JOptionPane error = new JOptionPane("Bibtex Library File Can't Be Found. Make Sure That It's in The Same" +
                    "Directory and It's Name Is Library.bib");
        }
        catch(IOException e) {
            JOptionPane error = new JOptionPane("This Bibtex Library File Is Corrupted. Make Sure to Use The" +
                    "Correct File");
        }

        int currentVPosition = vAlign;

        logoArea = new JTextArea();
        logoArea.setText("                                                 \n" +
                "   _____             _____ _ _       _   _         \n" +
                "  |     |___ ___ ___|     |_| |_ ___| |_|_|___ ___ \n" +
                "  |  |  | . | -_|   |   --| |  _| .'|  _| | . |   |\n" +
                "  |_____|  _|___|_|_|_____|_|_| |__,|_| |_|___|_|_|\n" +
                "        |_|                                        ");
        logoArea.setBounds(hAlignStart, vAlign, hAlignEnd, 110);
        logoArea.setBackground(BACKGROUND_COLOR);
        logoArea.setForeground(TEXT_COLOR);
        logoArea.setFont(TEXT_FONT);
        logoArea.setLineWrap(true);
        logoArea.setWrapStyleWord(true);
        logoArea.setEditable(false);
        this.add(logoArea);


        currentVPosition = vAlign + 110;


        int searchLabelHeight = 20;

        searchLabelFirstLine = new JLabel("Functions and shortcuts: ");
        searchLabelFirstLine.setBounds(hAlignStart - 4, currentVPosition, hAlignEnd, searchLabelHeight);
        searchLabelFirstLine.setBackground(BACKGROUND_COLOR);
        searchLabelFirstLine.setForeground(TEXT_COLOR);
        searchLabelFirstLine.setFont(TEXT_FONT);
        this.add(searchLabelFirstLine);
        currentVPosition += searchLabelHeight + 5;

        searchLabelSecondLine = new JLabel("insert Bibkey.................................: ENTER");
        searchLabelSecondLine.setBounds(hAlignStart, currentVPosition, hAlignEnd, searchLabelHeight);
        searchLabelSecondLine.setBackground(BACKGROUND_COLOR);
        searchLabelSecondLine.setForeground(TEXT_COLOR);
        searchLabelSecondLine.setFont(TEXT_FONT);
        this.add(searchLabelSecondLine);
        currentVPosition += searchLabelHeight + 5;

        searchLabelThirdLine = new JLabel("inset Title...................................:   1");
        searchLabelThirdLine.setBounds(hAlignStart, currentVPosition, hAlignEnd, searchLabelHeight);
        searchLabelThirdLine.setBackground(BACKGROUND_COLOR);
        searchLabelThirdLine.setForeground(TEXT_COLOR);
        searchLabelThirdLine.setFont(TEXT_FONT);
        this.add(searchLabelThirdLine);
        currentVPosition += searchLabelHeight + 5;

        searchLabelFourthLine = new JLabel("insert Author.................................:   2");
        searchLabelFourthLine.setBounds(hAlignStart, currentVPosition, hAlignEnd, searchLabelHeight);
        searchLabelFourthLine.setBackground(BACKGROUND_COLOR);
        searchLabelFourthLine.setForeground(TEXT_COLOR);
        searchLabelFourthLine.setFont(TEXT_FONT);
        this.add(searchLabelFourthLine);
        currentVPosition += searchLabelHeight + 5;

        searchLabelFifthLine = new JLabel("insert Title, Author, and Year................:   0");
        searchLabelFifthLine.setBounds(hAlignStart, currentVPosition, hAlignEnd, searchLabelHeight);
        searchLabelFifthLine.setBackground(BACKGROUND_COLOR);
        searchLabelFifthLine.setForeground(TEXT_COLOR);
        searchLabelFifthLine.setFont(TEXT_FONT);
        this.add(searchLabelFifthLine);
        currentVPosition += searchLabelHeight + vGap;


        searchTextField = new JTextField(20);
        searchTextField.setBounds(hAlignStart, currentVPosition, hAlignEnd, 30);
        searchTextField.setBackground(FIELD_COLOR);
        searchTextField.setForeground(TEXT_COLOR);
        searchTextField.setFont(TEXT_FONT);
        searchTextField.setCaretColor(TEXT_COLOR);
        searchTextField.setBorder(BorderFactory.createLineBorder(TEXT_COLOR, 1, true));
        searchTextField.getDocument().addDocumentListener(this);
        searchTextField.addKeyListener(this);
        this.add(searchTextField);
        currentVPosition += 30 + vGap;

        resultList = new JList(bibtexList.toArray());
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultList.setBackground(FIELD_COLOR);
        resultList.setForeground(TEXT_COLOR);
        resultList.setFont(TEXT_FONT);
        resultList.setSelectionBackground(new Color(33, 66, 131));
        resultList.setSelectionForeground(Color.white);
        resultListScrollPane = new JScrollPane(resultList);
        resultListScrollPane.setBounds(hAlignStart, currentVPosition, hAlignEnd, 290);
        resultListScrollPane.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        resultList.addKeyListener(this);
        resultList.addListSelectionListener(this);
        this.add(resultListScrollPane);
        currentVPosition += 290 + vGap;

        infoArea = new JTextArea();
        infoArea.setEditable(true);
        infoArea.setBackground(BACKGROUND_COLOR);
        infoArea.setForeground(TEXT_COLOR);
        infoArea.setFont(TEXT_FONT);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoLabelScrollPane = new JScrollPane(infoArea);
        infoLabelScrollPane.setBounds(hAlignStart, currentVPosition, hAlignEnd, 190);
        infoLabelScrollPane.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        infoArea.addKeyListener(this);
        this.add(infoLabelScrollPane);

        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        String text;
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
            text = ((Bibtex) resultList.getSelectedValue()).getId();
        else if(keyEvent.getKeyCode() == KeyEvent.VK_1)
            text = "" + resultList.getSelectedValue();
        else if(keyEvent.getKeyCode() == KeyEvent.VK_2)
            text = "" + resultList.getSelectedValue();
        else if(keyEvent.getKeyCode() == KeyEvent.VK_0)
            text = "" + resultList.getSelectedValue();
        else if(keyEvent.getKeyCode() == KeyEvent.VK_F11 && searchTextField.hasFocus()) {
            resultList.requestFocusInWindow();
            return;
        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_F11 && resultList.hasFocus()) {
            searchTextField.requestFocusInWindow();
            return;
        }
        else if(keyEvent.getKeyCode() == KeyEvent.VK_F11) {
            searchTextField.requestFocusInWindow();
            return;
        }
        else
            return;

        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selectedBibtexInfo = new StringSelection(text);
        systemClipboard.setContents(selectedBibtexInfo, selectedBibtexInfo);

        try {
            Robot robot = new Robot();
            robot.setAutoDelay(50);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_ALT);
        }
        catch (AWTException e) {
            JOptionPane error = new JOptionPane("You Don't Have Enough Permissions To Run OpenCitation. Contact Your System " +
                    "Administrator");
        }
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        String key = searchTextField.getText().trim();
        if(key.equalsIgnoreCase(""))
            resultList.setListData(bibtexList.toArray());
        else
            resultList.setListData(bibtexList.contains(key).toArray());
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        String key = searchTextField.getText().trim();
        if(key.equalsIgnoreCase(""))
            resultList.setListData(bibtexList.toArray());
        else
            resultList.setListData(bibtexList.contains(key).toArray());
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        String key = searchTextField.getText().trim();
        if(key.equalsIgnoreCase(""))
            resultList.setListData(bibtexList.toArray());
        else
            resultList.setListData(bibtexList.contains(key).toArray());
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        Bibtex selectedBibtex = (Bibtex) resultList.getSelectedValue();
        infoArea.setText("\n\nBibKey:\t" + selectedBibtex.getId() + "\n" + "Type:\t" + selectedBibtex.gettype() + "\n" +
                "Author:\t" + selectedBibtex.getEtcEntry("author").getValue() + "\n" + "Year:\t" +
                selectedBibtex.getEtcEntry("Year").getValue());
    }

}