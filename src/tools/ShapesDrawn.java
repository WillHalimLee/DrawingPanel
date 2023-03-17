/*
 * TCSS 305 - Assignment 5
 */
package tools;

import java.awt.Color;
import java.awt.Shape;
/**
 * Represents a Shape.
 * 
 * @author leeh10
 * @version 11/19/2022
 */
public class ShapesDrawn {
    /** The Shape's shape.*/
    private final Shape myShape;
    
    /** The Shape's Color.*/
    private final Color myColor;
    
    /** The Shape's Fill Color.*/
    private final Color myFillColor;
    
    /** The Shape's Stroke Size.*/
    private final int myStrokeSize;
    
    /** The Shape's stance on if it is filled or not.*/
    private final boolean myFill;

    /** Constructor for the ShapesDrawn Class.*/
    public ShapesDrawn(final Shape theShapes, final Color theColor, final Color theFillColor,
            final int theStrokeSize, final boolean theFill) {
        myShape = theShapes;
        myColor = theColor;
        myFillColor = theFillColor;
        myStrokeSize = theStrokeSize;
        myFill = theFill;
    }
    
    /**
     * Returns the Shape.
     * @return the Shape.
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * Returns the Color.
     * @return the Color.
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * Returns the Fill Color.
     * @return the Fill Color.
     */
    public Color getFillColor() {
        return myFillColor;
    }
    
    /**
     * Returns the Stroke size.
     * @return the Stroke size.
     */
    public int getStrokeSize() {
        return myStrokeSize;
    }
    
    /**
     * Returns the if the shape is filled.
     * @return the if the shape is filled.
     */
    public boolean isFill() {
        return myFill;
    }
}
