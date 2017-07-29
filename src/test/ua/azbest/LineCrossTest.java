package ua.azbest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineCrossTest {

    private Line l;
    private Line l2;
    private Line l3;
    private Line l4;

    @Before
    public void init() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        l = new Line(p1, p2);

        Point p11 = new Point(2, 3);
        Point p12 = new Point(-1, 0);
        l2 = new Line(p11, p12);

        Point p21 = new Point(5, 1);
        Point p22 = new Point(4, 2);
        l3 = new Line(p21, p22);

        Point p31 = new Point(4, 7);
        Point p32 = new Point(2, 3);
        l4 = new Line(p31, p32);
    }

    @Test
    public void getCrossPoint() throws Exception {

        Point p = l.getCrossPoint(l3);
        assertEquals(3, p.getX(), Line.EPS);
        assertEquals(3, p.getY(), Line.EPS);

        p = l.getCrossPoint(l4);
        assertEquals(4.5, p.getX(), Line.EPS);
        assertEquals(4.5, p.getY(), Line.EPS);

    }

    @Test
    public void compare() throws Exception {
    }

    @Test
    public void getA() throws Exception {

        assertEquals(1, l.getA(), Line.EPS);
        assertEquals(-3, l2.getA(), Line.EPS);

    }

    @Test
    public void getB() throws Exception {

        assertEquals(-1, l.getB(), Line.EPS);
        assertEquals(3, l2.getB(), Line.EPS);

    }

    @Test
    public void getC() throws Exception {

        assertEquals(0, l.getC(), Line.EPS);
        assertEquals(-3, l2.getC(), Line.EPS);

    }

}