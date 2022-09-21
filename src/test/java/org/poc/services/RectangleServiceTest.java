package org.poc.services;

import org.junit.jupiter.api.Test;
import org.poc.exceptions.InvalidRectangleException;
import org.poc.models.Adjacency;
import org.poc.models.Point;
import org.poc.models.Rectangle;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleServiceTest {

    RectangleService rectangleService = new RectangleService();

    @Test
    public void getIntersectionPoint_invalidInput_fail() {
        Rectangle r1 = getRectangle(1,4,1, 1);
        Rectangle r2 = getRectangle(2,4,4, 1);
        assertThrows(InvalidRectangleException.class , () -> rectangleService.getIntersectionPoint(r1,r2));
    }

    @Test
    public void getIntersectionPoint_intersection_success() throws InvalidRectangleException {
        Rectangle r1 = getRectangle(1,4,3, 2);
        Rectangle r2 = getRectangle(2,3,4, 1);
        boolean result = rectangleService.getIntersectionPoint(r1, r2);
        assertTrue(result);
    }

    @Test
    public void getIntersectionPoint_reverserInput_success() throws InvalidRectangleException {
        Rectangle r2 = getRectangle(1,4,3, 2);
        Rectangle r1 = getRectangle(2,3,4, 1);
        boolean result = rectangleService.getIntersectionPoint(r1, r2);
        assertTrue(result);
    }

    @Test
    public void getIntersectionPoint_noIntersection_success() throws InvalidRectangleException {
        Rectangle r1 = getRectangle(1,3,2, 2);
        Rectangle r2 = getRectangle(3,3,4, 1);
        boolean result = rectangleService.getIntersectionPoint(r1, r2);
        assertFalse(result);
    }

    @Test
    public void isContainment_invalidRectangle_fail() {
        Rectangle r1 = getRectangle(1,1,1, 1);
        Rectangle r2 = getRectangle(3,2,3, 1);
        assertThrows(InvalidRectangleException.class, () -> rectangleService.isContainment(r1,r2 ));
    }

    @Test
    public void isContainment_containment_success() throws InvalidRectangleException {
        Rectangle r1 = getRectangle(1,4,4, 1);
        Rectangle r2 = getRectangle(2,3,3, 2);
        boolean result = rectangleService.isContainment(r1,r2 );
        assertTrue(result);
    }

    @Test
    public void isContainment_noContainment_success() throws InvalidRectangleException {
        Rectangle r1 = getRectangle(1,4,3, 3);
        Rectangle r2 = getRectangle(2,3,3, 2);
        boolean result = rectangleService.isContainment(r1,r2 );
        assertFalse(result);

    }

    @Test
    public void getAdjacency_invalidInput_fail() {
        Rectangle r1 = getRectangle(1,1,1, 1);
        Rectangle r2 = getRectangle(3,2,3, 1);
        assertThrows(InvalidRectangleException.class, () -> rectangleService.getAdjacency(r1,r2 ));
    }

    @Test
    public void getAdjacency_topSubline_success() throws InvalidRectangleException{
        Rectangle r1 = getRectangle(1,4, 3,3);
        Rectangle r2 = getRectangle(2,3,3, 2);
        Adjacency adjacency= rectangleService.getAdjacency(r1,r2 );
        assertTrue(adjacency.equals(Adjacency.SUBLINE));
    }

    @Test
    public void getAdjacency_bottomSubline_success() throws InvalidRectangleException{
        Rectangle r2 = getRectangle(1,4, 3,3);
        Rectangle r1 = getRectangle(2,3,3, 2);
        Adjacency adjacency= rectangleService.getAdjacency(r1,r2 );
        assertTrue(adjacency.equals(Adjacency.SUBLINE));
    }

    @Test
    public void getAdjacency_sideSublineEdges_success() throws InvalidRectangleException{
        Rectangle r1 = getRectangle(1,5, 3,2);
        Rectangle r2 = getRectangle(3,3,4, 2);
        Adjacency adjacency= rectangleService.getAdjacency(r1,r2 );
        assertTrue(adjacency.equals(Adjacency.SUBLINE));
    }

    @Test
    public void getAdjacency_sideSubline_success() throws InvalidRectangleException{
        Rectangle r1 = getRectangle(1,5, 3,2);
        Rectangle r2 = getRectangle(3,4,4, 3);
        Adjacency adjacency= rectangleService.getAdjacency(r1,r2 );
        assertTrue(adjacency.equals(Adjacency.SUBLINE));
    }

    @Test
    public void getAdjacency_sideProper_success() throws InvalidRectangleException{
        Rectangle r1 = getRectangle(2,3, 3,2);
        Rectangle r2 = getRectangle(3,3,4, 2);
        Adjacency adjacency= rectangleService.getAdjacency(r1,r2 );
        assertTrue(adjacency.equals(Adjacency.PROPER));
    }

    @Test
    public void getAdjacency_topProper_success() throws InvalidRectangleException{
        Rectangle r1 = getRectangle(2,4, 3,3);
        Rectangle r2 = getRectangle(2,3,3, 2);
        Adjacency adjacency= rectangleService.getAdjacency(r1,r2 );
        assertTrue(adjacency.equals(Adjacency.PROPER));
    }

    @Test
    public void getAdjacency_topPartial_success() throws InvalidRectangleException{
        Rectangle r1 = getRectangle(1,4, 3,3);
        Rectangle r2 = getRectangle(2,3,4, 2);
        Adjacency adjacency= rectangleService.getAdjacency(r1,r2 );
        assertTrue(adjacency.equals(Adjacency.PARTIAL));
    }

    @Test
    public void getAdjacency_sidePartial_success() throws InvalidRectangleException{
        Rectangle r1 = getRectangle(1,4, 2,2);
        Rectangle r2 = getRectangle(2,3,3, 1);
        Adjacency adjacency= rectangleService.getAdjacency(r1,r2 );
        assertTrue(adjacency.equals(Adjacency.PARTIAL));
    }

    @Test
    public void getAdjacency_noAdjacency_success() throws InvalidRectangleException{
        Rectangle r1 = getRectangle(1,4, 2,3);
        Rectangle r2 = getRectangle(3,3,4, 2);
        Adjacency adjacency= rectangleService.getAdjacency(r1,r2 );
        assertTrue(adjacency.equals(Adjacency.NO_ADJACENCY));
    }

    private Rectangle getRectangle(int x1, int y1, int x2, int y2) {
        Point topLeft = new Point(x1, y1);
        Point bottomRight = new Point(x2, y2);
        return new Rectangle(topLeft, bottomRight);
    }
}
