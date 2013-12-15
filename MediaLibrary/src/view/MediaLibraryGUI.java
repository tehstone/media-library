package view;

import actions.AboutAction;
import actions.ExitAction;
import actions.NewItemAction;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author jrsto674
 * @version 12/11/2013
 */
public class MediaLibraryGUI {

    /** DB Driver package info. */
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    /** URL for local DB. */
    private static final String DB_URL = "jdbc:mysql://localhost/";
    /** User name to connect to DB. */
    private static final String USER = "eclipse";    
    /** Password to connect to DB. */
    private static final String PASS = "_ifbqGv+3RKg";

    /** Default Dimension size of the JFrame. */
    private static final Dimension DEFAULT_SIZE = new Dimension(1000, 800);

    /** String constants containing menu titles. */
    private static final String[] MENU_STRINGS = {"File", "Options", "Help"};
    /** String constants containing menu item names. */
    private static final String[] MENU_ITEM_STRINGS = {"New Item", "Exit", "About"};
    /** String constants containing tab names. */
    private static final String[] TAB_NAMES = {"CDs", "DVDs", "Books", "XBox Games"};
    /** String constants containing tab names. */
    private static final String[] TABLE_NAMES = {"cds", "dvds", "books", "xbox"};

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
        myFrame.setSize(DEFAULT_SIZE);
    }

    private List<String[]> connectToDB() {
        Connection conn = null;
        Statement stmt = null;
        List<String[]> result = new ArrayList<String[]>();
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            //            final String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'media' AND TABLE_NAME = 'cds';";


            for (int i = 0; i < TABLE_NAMES.length; i++) {
                //ResultSet rs = stmt.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'media' AND TABLE_NAME = '" + TABLE_NAMES[i] + "';");
                ResultSet rs = stmt.executeQuery("SHOW COLUMNS FROM " + TABLE_NAMES[i] + " IN media;");
                int j = 0;
                String[] columns = new String[4];
                try {
                    while (rs.next()) {
                        
                        String s = rs.getString("COLUMN_NAME");
                        if (!"id".equals(s)) {
                            columns[j++] = s;
                        }
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                result.add(columns);

            }


            //            while (rs.next()) {
            //                System.out.println(rs.getString("COLUMN_NAME"));
            //            }
        } catch (final SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (final Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (final SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (final SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
        return result;
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

        List<String[]> columnNamesList = connectToDB();

        //        = {{"Title", "Artist", "Genre"}, 
        //                        {"Title", "Year"}, 
        //                        {"Title", "Author"}, 
        //                        {"Title"}};

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
        for (int i = 0; i < columnNamesList.size(); i++) {
            String[] columnNamesArray = columnNamesList.get(i);
            @SuppressWarnings("serial")
            final DefaultTableModel tableModel = 
                new DefaultTableModel(data[i], columnNamesArray) {
                @Override
                public boolean isCellEditable(final int theRow, final int theColumn) {
                    //all cells false
                    return false;
                }
            };
            final JTable table = new JTable(tableModel);
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(final ListSelectionEvent theEvent) {
                    // do some actions here, for example
                    // print first column value from selected row
                    final StringBuilder string = new StringBuilder();
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        string.append(table.getValueAt(table.getSelectedRow(), i).
                                      toString().
                                      replaceAll("\\s+", ""));
                    }
                    System.out.println(string.toString());
                }
            });
            tables.add(table);
        }

        for (int i = 0; i < TAB_NAMES.length; i++) {
            final JScrollPane scrollPane = new JScrollPane(tables.get(i));
            tabPane.addTab(TAB_NAMES[i], null, scrollPane,
                            "Does nothing");
            final int ascii = (int) TAB_NAMES[i].charAt(0);
            tabPane.setMnemonicAt(0, ascii);
        }
        myFrame.add(tabPane, BorderLayout.NORTH);
    }

    /**
     * Item image and info are displayed in panels within this region.
     */
    private void makeSouth() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        // JPanel (grid layout?) border.south

        final JLabel imageLabel = new JLabel();
        imageLabel.addPropertyChangeListener("selected", null);
        final int size = myFrame.getWidth() / 3; 
        imageLabel.setPreferredSize(new Dimension(size, size));
        Icon icon = new ImageIcon();
        imageLabel.setIcon(icon);

        // JLabel (left 1/3rd of panel)
        final JTextArea infoText = new JTextArea();
        infoText.setPreferredSize(new Dimension(size * 2, size));

        // JTextArea (remainder of panel)
        panel.add(imageLabel, BorderLayout.WEST);
        panel.add(infoText, BorderLayout.EAST);
        myFrame.add(panel, BorderLayout.SOUTH);
    }

    /**
     * Finishes the creation of the GUI and makes it visible.
     */
    private void startGUI() {
        myFrame.setTitle("Media Library");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationByPlatform(true);
        myFrame.pack();

        myFrame.setVisible(true);
    }
}
