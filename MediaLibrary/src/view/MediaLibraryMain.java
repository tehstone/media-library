/**
 * 
 */
package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author jrsto674
 * @version 12/11/2013
 */
public final class MediaLibraryMain {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    static final String USER = "eclipse";
    static final String PASS = "_ifbqGv+3RKg";

    /**
     * 
     */
    private MediaLibraryMain() {
        throw new IllegalStateException();
    }

    /**
     * @param theArgs Possible command line arguments.
     */
    public static void main(final String... theArgs) {        
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            final String sql = "DROP DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
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
        System.out.println("Goodbye!");
        //new MediaLibraryGUI();
        //STEP 1. Import required packages


    }

}
