package org.poc.services;

import org.poc.exceptions.InvalidRectangleException;
import org.poc.models.Adjacency;
import org.poc.models.Point;
import org.poc.models.Rectangle;

public class RectangleService {
    public boolean getIntersectionPoint(Rectangle r1, Rectangle r2) throws InvalidRectangleException {

        if(!validateReactangle(r1) || !validateReactangle(r2)){
            throw new InvalidRectangleException();
        }

        if(r1.leftTop.x > r2.rightBottom.x || r2.leftTop.x > r1.rightBottom.x){
            return false;
        }

        if(r1.leftTop.y < r2.rightBottom.y || r1.rightBottom.y > r2.leftTop.y) {
            return false;
        }

        return true;
    }


    public boolean isContainment(Rectangle r1, Rectangle r2) throws InvalidRectangleException {
        if(!validateReactangle(r1) || !validateReactangle(r2)){
            throw new InvalidRectangleException();
        }
        return (r1.leftTop.x < r2.leftTop.x && r1.rightBottom.x > r2.rightBottom.x && r1.leftTop.y > r2.leftTop.y && r1.rightBottom.y < r2.rightBottom.y);
    }


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
