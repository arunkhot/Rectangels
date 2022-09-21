package org.poc.models;

/**
 * Representation of Rectangle with two points
 * First Left Top
 * Second Right bottom
 * With these two points, rectangle area can be covered.
 */
public class Rectangle {
    public Point leftTop;
    public Point rightBottom;

    public Rectangle(Point leftTop, Point rightBottom){
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

}
