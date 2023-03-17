/*
 * TCSS 305 - Assignment 5
 */
package tools;

import java.awt.Point;
import java.awt.Shape;
/**
 * An Interface for objects that contain a Shape with their x and y coordinates.
 * 
 * @author leeh10
 * @version 12/01/2022 
 */
public interface PaintTool {

    /**
     * Returns the Shape that this tools draws.
     * 
     * @return the Shape to draw
     */
    Shape getShape();

    /**
     * Sets the initial point for the Shape drawn by this tool.
     * 
     * @param thePoint the start point to set
     */
    void setStartPoint(Point thePoint);
    
    /**
     * Sets the end point for the Shape drawn by this tool.
     * 
     * @param thePoint the end point to set
     */
    void setEndPoint(Point thePoint);

   
}

