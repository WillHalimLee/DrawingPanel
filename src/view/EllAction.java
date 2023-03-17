/*
 * TCSS 305 - Assignment 5
 */
package view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import tools.PaintTool;

/**
 * Action class for Ellipse tool.
 * 
 * @author leeh10
 * @version 11/19/2022
 */
public class EllAction extends AbstractAction {

    /** A generated serial for the action. */
    private static final long serialVersionUID = 6214926071095120602L;
    
    /** The Drawing Panel.*/
    private final DrawPanel myDrawPanel;
    
    /** The Paint Tool.*/
    private final PaintTool myTool;
    
    /**
     * Overloaded Constructor.
     * 
     * @param theDrawPanel
     * @param theTool
     */
    public EllAction(final DrawPanel theDrawPanel, final PaintTool theTool) {
        super("Ellipse");
        myDrawPanel = theDrawPanel;
        myTool = theTool;
        putValue(Action.SELECTED_KEY, true);
        putValue(Action.SHORT_DESCRIPTION, "ellipse");
        putValue(Action.SMALL_ICON, new ImageIcon("files/ellipse_bw.gif"));
    }
    
    /**
     * Action listener for when this is called.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myDrawPanel.setCurrentTool(myTool);
        myDrawPanel.setFillEnable(true);
    }
}
