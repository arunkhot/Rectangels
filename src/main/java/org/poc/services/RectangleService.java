package org.poc.services;

import org.poc.exceptions.InvalidRectangleException;
import org.poc.models.Adjacency;
import org.poc.models.Point;
import org.poc.models.Rectangle;

/**
 * Provides methods to identify Rectangle Adjacency, Intersection, Containment
 */
public class RectangleService {

    /**
     * @param r1
     * @param r2
     * @return true if Rectangle r1 intersect with Rectangle r2
     * @throws InvalidRectangleException
     */
    public boolean getIntersectionPoint(Rectangle r1, Rectangle r2) throws InvalidRectangleException {

        if(!validateReactangle(r1) || !validateReactangle(r2)) {
            throw new InvalidRectangleException();
        }

        // check if r1 top left point is at left side of r2 right bottom point or if r2 left top point is at right side of r1 right bottom point
        if(r1.leftTop.x > r2.rightBottom.x || r2.leftTop.x > r1.rightBottom.x){
            return false;
        }

        if(r1.leftTop.y < r2.rightBottom.y || r1.rightBottom.y > r2.leftTop.y) {
            return false;
        }

        return true;
    }

    /**
     * Checks if Rectangle r2 is contained within Rectangle r1
     * @param r1
     * @param r2
     * @return true is r2 in contained within r1
     * @throws InvalidRectangleException
     */
    public boolean isContainment(Rectangle r1, Rectangle r2) throws InvalidRectangleException {
        if(!validateReactangle(r1) || !validateReactangle(r2)){
            throw new InvalidRectangleException();
        }
        return (r1.leftTop.x < r2.leftTop.x && r1.rightBottom.x > r2.rightBottom.x && r1.leftTop.y > r2.leftTop.y && r1.rightBottom.y < r2.rightBottom.y);
    }

    /**
     * Check all possible Adjacency between two rectangles
     * @param r1
     * @param r2
     * @return Enum representing adjacency - Proper, Subline, Partial or No adjacency.
     * @throws InvalidRectangleException
     */
    public Adjacency getAdjacency(Rectangle r1, Rectangle r2) throws InvalidRectangleException {
        if(!validateReactangle(r1) || !validateReactangle(r2)){
            throw new InvalidRectangleException();
        }
        if (r1.rightBottom.y == r2.leftTop.y || r2.rightBottom.y == r1.leftTop.y) {
            if (r1.rightBottom.x == r2.rightBottom.x && r1.leftTop.x == r2.leftTop.x) {
                return Adjacency.PROPER;
            } else if ((r1.leftTop.x >= r2.leftTop.x && r1.rightBottom.x <= r2.rightBottom.x)
                    || (r1.leftTop.x <= r2.leftTop.x && r1.rightBottom.x >= r2.rightBottom.x)) {
                return Adjacency.SUBLINE;
            } else {
                if((r1.leftTop.x < r2.leftTop.x && r1.rightBottom.x > r2.leftTop.x)
                         ||(r1.leftTop.x < r2.rightBottom.x && r1.rightBottom.x > r2.rightBottom.x)) {
                    return Adjacency.PARTIAL;
                }
            }
        } else if (r1.rightBottom.x == r2.leftTop.x || r2.rightBottom.x == r1.leftTop.x) {
            if (r1.leftTop.y == r2.leftTop.y && r1.rightBottom.y == r2.rightBottom.y) {
                return Adjacency.PROPER;
            } else if ((r1.leftTop.y >= r2.leftTop.y && r1.rightBottom.y <= r2.rightBottom.y)
                    || (r1.leftTop.y <= r2.leftTop.y && r1.rightBottom.y >= r2.rightBottom.y)) {
                return Adjacency.SUBLINE;
            } else {
                if((r1.rightBottom.y > r2.leftTop.y && r1.leftTop.y < r2.leftTop.y)
                        ||(r1.leftTop.y < r2.leftTop.y && r1.rightBottom.y > r2.leftTop.y)) {
                    return Adjacency.PARTIAL;
                }
                return Adjacency.PARTIAL;
            }
        }
        return Adjacency.NO_ADJACENCY;
    }


    private boolean validateReactangle(Rectangle rectangle) {
        if(rectangle.rightBottom.x == rectangle.leftTop.x || rectangle.rightBottom.y == rectangle.leftTop.y){
            return false;
        }
        return true;
    }

}
