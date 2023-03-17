/*
 * TCSS 305 - Assignment 5
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import tools.PaintTool;
import tools.ShapesDrawn;

/**
 * Creates a DrawPanel.
 * 
 * @author leeh10
 * @version 11/19/2022
 */
public class DrawPanel extends JPanel {

    /** Generated serial number for the JPanel.*/
    private static final long serialVersionUID = 1L;
    
    /** A List that stores all the drawn shapes.*/
    private final List<ShapesDrawn> myShapes;
    
    /** The current paint tool selected. */
    private PaintTool myCurrentPaintTool;
    
    /** The current pen color selected.*/
    private Color myCurrentColor;
    
    /** The current fill color selected. */
    private Color myFillColor;
    
    /** Represents if the object is filled.*/
    private boolean myIsFilled;
    
    /** The current size of the pen. */
    private int myStrokeSize;
    
    /** A Clear Button.*/
    private final JMenuItem myClearButton;
    
    /** A check box button for the Fill Button.*/
    private final JCheckBox myFillButton;

    /**
     * Overloaded Constructor for the Drawing Panel.
     * 
     * @param theStrokeSize, Size of the Pen.
     * @param theColor, the Color of the Pen.
     * @param theFillColor, the Color to fill the shape.
     * @param theTool, the tool/shape.
     */
    public DrawPanel(final int theStrokeSize, final Color theColor, final Color theFillColor,
            final PaintTool theTool) {
        super();
        myCurrentColor = theColor;
        myFillColor = theFillColor;
        myIsFilled = false;
        myStrokeSize = theStrokeSize;
        myCurrentPaintTool = theTool;
        myClearButton = createClearButton();
        myFillButton = createFillButton();
        myShapes = new ArrayList<>();
        setUpDrawPanel();
        

    }

    /** Method that sets up the drawing panel.*/
    private void setUpDrawPanel() {
        setBackground(Color.WHITE);
        final MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        final Cursor cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
        this.setCursor(cursor);
    }
    
    /** 
     * Method the returns a JMenuItem for the clear button.
     * 
     * @return A Clear Button in the form of a JMenuItem.
     * */
    public JMenuItem getClearButton() {
        return myClearButton;
    }

    /** 
     * Creates a clear button.
     * @return a JMenuItem as a clear button.
     */
    private JMenuItem createClearButton() {
        final JMenuItem clearButton = new JMenuItem("Clear");
        clearButton.setEnabled(false);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                clear();
            }
        });
        return clearButton;
    }
    
    /** 
     * Creates a fill button button.
     * @return a JMenuItem as a fill button.
     */
    private JCheckBox createFillButton() { 
        final JCheckBox checkBox = new JCheckBox("Fill");
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                if (isFilled()) {
                    setIsFilled(false);
                } else { 
                    setIsFilled(true);
                }
            }
        });
        checkBox.setEnabled(false);
        return checkBox;
    }
    
    /**
     * Returns the objects Fill Button.
     * @return the Fill Button.
     */
    public JCheckBox getFillButton() {
        return myFillButton;
    }
    
    /**
     * A helper method that clears the drawing panel by clearing the myShapes list.
     */
    public void clear() {
        myShapes.clear();
        repaint();
        myClearButton.setEnabled(false);
    }

    /**
     * A setter that sets the paint tool. 
     */
    public void setCurrentTool(final PaintTool theTool) {
        myCurrentPaintTool = theTool;
    }

    /**
     * A setter that sets the color.
     */
    public void setCurrentColor(final Color theColor) {
        myCurrentColor = theColor;
    }
    
    /**
     * A setter that sets the fill color.
     */
    public void setFillColor(final Color theFillColor) {
        myFillColor = theFillColor;
    }

    /** 
     * A setter that sets the pen size. 
     */
    public void setStrokeSize(final int theStrokeSize) {
        myStrokeSize = theStrokeSize;
    }
    
    /**
     * A getter that returns the myShape List size.
     * @return Size of the myShape's List.
     */
    public int getShapeListSize() {
        return myShapes.size();
    }
    
    /**
     *  A getter that returns the color. 
     * @return the Color.
     */
    public Color getColor() {
        return myCurrentColor;
    }
    
    /**
     * A getter that returns the Fill Color.
     * @return the Fill Color.
     */
    public Color getFillColor() {
        return myFillColor;
    }
    
    /**
     * A setter that sets if the Shape is filled.
     * @param theFill, a boolean value.
     */
    public void setIsFilled(final boolean theFill) {
        myIsFilled = theFill;
    }
    
    /**
     * Returns if the shape is filled.
     * @return myIsFilled field's boolean value.
     */
    public boolean isFilled() {
        return myIsFilled;
    }
    
    /**
     * A Setter that enables or disables the fill button.
     * @param theSet, boolean value.
     */
    public void setFillEnable(final boolean theSet) {
        myFillButton.setEnabled(theSet);
    }
    
    /**
     * The Drawing panels paint component. The Method that paints the drawing panel.
     */
    @Override
    public void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        final Graphics2D g2 = (Graphics2D) theG;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        for (final ShapesDrawn s : myShapes) {
            
            if (s.isFill()) {
                g2.setColor(s.getFillColor());
                g2.fill(s.getShape());
            }
            g2.setColor(s.getColor());
            g2.setStroke(new BasicStroke(s.getStrokeSize()));
            g2.draw(s.getShape());

        }
        if (!myShapes.isEmpty() && myStrokeSize != 0) {
            g2.setStroke(new BasicStroke(myStrokeSize));
            g2.setColor(myCurrentColor);
            g2.draw(myCurrentPaintTool.getShape());
        }

    }

    /** A Mouse Listener for the Drawing Panel.*/
    private class MouseHandler extends MouseInputAdapter {
        /** Method when the mouse is pressed.*/
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myCurrentPaintTool.setStartPoint(theEvent.getPoint());
            final ShapesDrawn shapeDrawn = new ShapesDrawn(myCurrentPaintTool.getShape(), 
                    myCurrentColor, myFillColor, myStrokeSize, myIsFilled);
            myShapes.add(shapeDrawn);
            repaint();
        }

        /** Method that listens to when the mouse is dragged.*/
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myCurrentPaintTool.setEndPoint(theEvent.getPoint());
            repaint();
        }

        /** Method that listens to when the mouse click is released. */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            if (myStrokeSize != 0) {
                final ShapesDrawn shapeDrawn = new ShapesDrawn(myCurrentPaintTool.getShape(),
                        myCurrentColor, myFillColor, myStrokeSize, myIsFilled);
                myShapes.add(shapeDrawn);
                myClearButton.setEnabled(true);
                repaint();

            }
        }

    }

}
