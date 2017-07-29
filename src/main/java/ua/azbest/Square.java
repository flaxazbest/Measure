package ua.azbest;

import java.util.ArrayList;

public class Square {

    public static double getSqyareBy3Points(Point p1, Point p2, Point p3) {
        double delta = (p1.getX() * p2.getY() + p2.getX() * p3.getY() + p3.getX() * p1.getY())
                     - (p3.getX() * p2.getY() + p1.getX() * p3.getY() + p2.getX() * p1.getY());
        return Math.abs(0.5 * delta);
    }

    public static double getSquareHull(ArrayList<IndexedPoint> points) {
        double s = 0.0;
        if (points == null)
            return s;

        if (points.size()>2) {
            for (int i=2; i<points.size(); i++) {
                s += getSqyareBy3Points(points.get(0).point, points.get(i-1).point, points.get(i).point);
            }
        }

        return s;
    }

}
