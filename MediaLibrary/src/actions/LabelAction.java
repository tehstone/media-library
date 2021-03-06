/* 
 * TCSS 305 � Autumn 2013 
 * Assignment 5 - Power Paint 
 */ 
package actions;

import java.awt.event.ActionEvent;
//import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
//import javax.swing.Action;
//import javax.swing.KeyStroke;

/**
 * The Action Listener for the Exit button in the
 * Media Library GUI.
 * 
 * @author jrsto674
 * @version 11/06/2013
 */
@SuppressWarnings("serial")
public class LabelAction extends AbstractAction {

    /**
     * Constructor for this Action, sets up the values
     * required by any buttons or menu items that will
     * use this Action.
     * 
     * @param theName Name of this action, in this case "Exit".
     */
    public LabelAction(final String theName) {
        super();

//        putValue(Action.SHORT_DESCRIPTION, "Add new media Item.");
//        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
//        putValue(Action.ACCELERATOR_KEY,
//                              KeyStroke.getKeyStroke('N', 
//                                                     java.awt.event.InputEvent.CTRL_MASK));
    }

    /**
     * When this Action is invoked by an event, the JFrame
     * is disposed and the program ends.
     * 
     * @param theEvent The event that triggers this Action, unused here.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        
    }

}
