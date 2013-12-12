/* 
 * TCSS 305 – Autumn 2013 
 * Assignment 5 - Power Paint 
 */ 
package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * The Action Listener for the About button in the
 * Media Library GUI.
 * @author jrsto674
 * @version 12/11/2013
 */
@SuppressWarnings("serial")
public class AboutAction extends AbstractAction {

    /**
     * Message that appears in the About JOptionPane
     * in the Help menu.
     */
    private static final String ABOUT_MESSAGE = 
            "Simple media library manager. \n Created by Jonathan Stone";
    
    /**
     * JFrame that this Action uses as a parent component.
     */
    private JFrame myFrame;

    /**
     * Constructor for this Action, sets up the values
     * required by any buttons or menu items that will
     * use this Action.
     * 
     * @param theName Name of this action, in this case "About...".
     * @param theFrame JFrame to which this action is attached.
     */
    public AboutAction(final String theName, final JFrame theFrame) {
        super(theName);
        myFrame = theFrame;

        putValue(Action.SHORT_DESCRIPTION, "Info about this program.");
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
        putValue(Action.ACCELERATOR_KEY,
                               KeyStroke.getKeyStroke('A', 
                                                      java.awt.event.InputEvent.CTRL_MASK));
    }

    /**
     * When this Action is invoked by an event, a JOptionPane
     * will appear giving information about Media Library Manager.
     * 
     * @param theEvent The event generated, unused here.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        JOptionPane.showMessageDialog(myFrame, ABOUT_MESSAGE, "About", 1);
    }

}
