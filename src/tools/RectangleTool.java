/*
 * TCSS 305 - Assignment 5
 */
package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
/**
 * Represents a Line.
 * 
 * @author leeh10
 * @version 11/19/2022
 */
public class RectangleTool extends AbstractPaintTool {
    /**
     * Returns a Rectangle.
     * @return a Rectangle.
     */
    @Override
    public Shape getShape() {
        final Point start = getStartPoint();
        final Point end = getEndPoint();

        Rectangle2D rec = new Rectangle2D.Double(start.getX(), start.getY(),
                end.getX() - start.getX(),
                end.getY() - start.getY());
        if (start.getX() > end.getX() && start.getY() > end.getY()) {
            rec = new Rectangle2D.Double(end.getX(), end.getY(),
                    start.getX() - end.getX(), start.getY() - end.getY());

        } else if (start.getX() > end.getX() && start.getY() < end.getY()) {
            rec = new Rectangle2D.Double(end.getX(), start.getY(), start.getX() - end.getX(),
                    end.getY() - start.getY());

        } else if (start.getX() < end.getX() && start.getY() > end.getY()) {
            rec = new Rectangle2D.Double(start.getX(), end.getY(), end.getX() - start.getX(),
                    start.getY() - end.getY());
        }
        return rec;
    }

}
