/*
 * TCSS 305 - Assignment 5
 */
package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * A Class that represents an Ellipse.
 * 
 * @author leeh10
 * @version 10/25/2022
 */
public class EllipseTool extends AbstractPaintTool {

    /**
     * Method that returns an Ellipse.
     * @return an Ellipse.
     */
    @Override
    public Shape getShape() {
        final Point start = getStartPoint();
        final Point end = getEndPoint();

        Ellipse2D ellipse = new Ellipse2D.Double(start.getX(), start.getY(),
                end.getX() - start.getX(),
                end.getY() - start.getY());
        if (start.getX() > end.getX() && start.getY() > end.getY()) {
            ellipse = new Ellipse2D.Double(end.getX(), end.getY(),
                    start.getX() - end.getX(), start.getY() - end.getY());

        } else if (start.getX() > end.getX() && start.getY() < end.getY()) {
            ellipse = new Ellipse2D.Double(end.getX(), start.getY(), start.getX() - end.getX(),
                    end.getY() - start.getY());

        } else if (start.getX() < end.getX() && start.getY() > end.getY()) {
            ellipse = new Ellipse2D.Double(start.getX(), end.getY(), end.getX() - start.getX(),
                    start.getY() - end.getY());
        }
        return ellipse;
    }


}
