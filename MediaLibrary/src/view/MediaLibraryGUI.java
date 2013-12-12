package view;

import actions.AboutAction;
import actions.ExitAction;
import actions.NewItemAction;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 * 
 * @author jrsto674
 * @version 12/11/2013
 */
public class MediaLibraryGUI {

    /** Default Dimension size of the JFrame. */
    private static final Dimension DEFAULT_SIZE = new Dimension(1000, 800);

    /** String constants containing menu titles. */
    private static final String[] MENU_STRINGS = {"File", "Options", "Help"};

    /** String constants containing menu item names. */
    private static final String[] MENU_ITEM_STRINGS = {"New Item", "Exit", "About"};

    /** String constants containing tab names. */
    private static final String[] TAB_NAMES = {"CDs", "DVDs", "Books", "XBox Games"};

    /** JFrame containing all elements comprising the GUI. */
    private JFrame myFrame;

    /**
     * Generates all the pieces that fit within the JFrame.
     */
    public MediaLibraryGUI() {
        initializeFields();

        makeMenu();

        makeCenter();

        makeSouth();
        startGUI();
    }

    /**
     * Initializes all fields needed in the GUI.
     */
    private void initializeFields() {
        myFrame = new JFrame();
    }
    /**
     * Generates and populates Menus and adds them to MenuBar.
     */
    private void makeMenu() {
        int i = 0;
        int j = 0;
        final JMenuBar bar = new JMenuBar();
        final Action[] menuActions = {new NewItemAction(MENU_ITEM_STRINGS[i++]),
                        new ExitAction(MENU_ITEM_STRINGS[i++], myFrame),
                        new AboutAction(MENU_ITEM_STRINGS[i++], myFrame)};
        i = 0;

        final JMenu fileMenu = new JMenu(MENU_STRINGS[j++]);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(menuActions[i++]);
        fileMenu.addSeparator();
        fileMenu.add(menuActions[i++]);

        final JMenu optionsMenu = new JMenu(MENU_STRINGS[j++]);
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        final JMenu helpMenu = new JMenu(MENU_STRINGS[j++]);
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.add(menuActions[i++]);

        bar.add(fileMenu);
        bar.add(optionsMenu);
        bar.add(helpMenu);

        myFrame.setJMenuBar(bar);
    }

    /**
     * Builds tabbed pane containing several tables in scroll panes.
     */
    private void makeCenter() {
        // JTabbedPane border.center
        // JScrollPane
        // JTable
        final JTabbedPane tabPane = new JTabbedPane();

        final String[][] columnNames = {{"Title", "Artist", "Genre"}, 
                                        {"Title", "Year"}, 
                                        {"Title", "Author"}, 
                                        {"Title"}};
        
        final Object[][][] data = {
            {
                {"Blue", "Third Eye Blind", "Pop-Punk"}, {"Ten", "Pearl Jam", "Grunge"}
            }, {
                {"Pacific Rim", "2013"}
            }, {
                {"Core Java", "Horstmann"}
            }, {
                {"DDR Revolution"}
            }
        };

        final List<JTable> tables = new ArrayList<JTable>();
        for (int i = 0; i < columnNames.length; i++) {
            tables.add(new JTable(data[i], columnNames[i]));
        }

        for (int i = 0; i < TAB_NAMES.length; i++) {
            final JScrollPane scrollPane = new JScrollPane(tables.get(i));
            tabPane.addTab(TAB_NAMES[i], null, scrollPane,
                            "Does nothing");
            final int ascii = (int) TAB_NAMES[i].charAt(0);
            tabPane.setMnemonicAt(0, ascii);
        }
        myFrame.add(tabPane);
    }

    /**
     * Item image and info are displayed in panels within this region.
     */
    private void makeSouth() {
        // JPanel (box layout?) border.south
        // JLabel (left 1/3rd of panel)
        // JTextArea (remainder of panel)
    }

    /**
     * Finishes the creation of the GUI and makes it visible.
     */
    private void startGUI() {
        myFrame.setTitle("Media Library");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationByPlatform(true);
        myFrame.pack();
        myFrame.setSize(DEFAULT_SIZE);
        myFrame.setVisible(true);
    }
}
