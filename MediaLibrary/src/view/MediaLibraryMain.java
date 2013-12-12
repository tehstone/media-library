/**
 * 
 */
package view;

/**
 * @author jrsto674
 * @version 12/11/2013
 */
public final class MediaLibraryMain {
    
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
        new MediaLibraryGUI();
    }

}
