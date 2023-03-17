/*
 * TCSS 305 - Assignment 5
 */
package tools;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * Represents a Line.
 * 
 * @author leeh10
 * @version 11/19/2022
 */
public class LineTool extends AbstractPaintTool {
    
    /**
     * Returns a Line.
     * @return a Line.
     */
    @Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }
}
