package ua.azbest;

import java.util.Comparator;

public class Point implements Comparable<Point>{

    private final double x;    // x координата
    private final double y;    // y координата

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }
    /**
     * Повертає 1, якщо a→b→c - поворот проти годинникової стрілки
     */
    public static int ccw(Point a, Point b, Point c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area2 < 0) return -1;
        else if (area2 > 0) return +1;
        else return 0;
    }

    public Comparator<Point> polarOrder() {
        return new PolarOrder();
    }

    private class PolarOrder implements Comparator<Point> {
        public int compare(Point q1, Point q2) {
            double dx1 = q1.x - x;
            double dy1 = q1.y - y;
            double dx2 = q2.x - x;
            double dy2 = q2.y - y;
            if      (dy1 >= 0 && dy2 < 0) return -1;    // q1 сверху; q2 снизу
            else if (dy2 >= 0 && dy1 < 0) return +1;    // q1 снизу; q2 сверху
            else if (dy1 == 0 && dy2 == 0) {            // коллинеарные
                if      (dx1 >= 0 && dx2 < 0) return -1;
                else if (dx2 >= 0 && dx1 < 0) return +1;
                else                          return  0;
            }
            else return -ccw(Point.this, q1, q2);     // обе сверху или снизу
        }
    }
}