package ua.azbest;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LineTest {

    @Test
    public void lineBuilderTest() {

        Point p1 = new Point(4, 7);
        Point p2 = new Point(2, 3);

        Line l = new Line(p1, p2);
        assertEquals(-4.0, l.getA());
        assertEquals(2.0, l.getB());
        assertEquals(2.0, l.getC());

    }

}
