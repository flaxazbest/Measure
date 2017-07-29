package ua.azbest;

import java.util.ArrayList;
import java.util.Arrays;

public class Graham {

    private static ArrayList<IndexedPoint> hull;

    public static ArrayList<IndexedPoint> getConvexHull(ArrayList<IndexedPoint> pts) {
        hull = new ArrayList<>();

        int n = pts.size();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = pts.get(i).point;
        }
        Arrays.sort(points);
        Arrays.sort(points, 1, n, points[0].polarOrder());

        int index = 0;
        hull.add(new IndexedPoint(points[index], index));       // p[0] стартова точка

        int k1;
        for (k1 = 1; k1 < n; k1++)
            if (!points[0].equals(points[k1])) break;
        if (k1 == n) return pts;

        int k2;
        for (k2 = k1 + 1; k2 < n; k2++)
            if (Point.ccw(points[0], points[k1], points[k2]) != 0) break;
        hull.add(new IndexedPoint(points[k2 - 1], 0 ));

        // алгоритм Грэхема
        for (int i = k2; i < n; i++) {
            Point top = hull.remove(hull.size()-1).point;
            while (Point.ccw(hull.get(hull.size()-1).point, top, points[i]) <= 0) {
                top = hull.remove(hull.size()-1).point;
            }
            hull.add(new IndexedPoint(top, 0));
            hull.add(new IndexedPoint(points[i], 0));
        }

        for (int i=0; i<hull.size(); i++)
            hull.get(i).setIndex(i);

        return hull;
        //assert isConvex();
    }

    public static Point getCentralPoint(ArrayList<Point> points) {
        double x = 0.0;
        double y = 0.0;

        for (Point p: points) {
            x += p.getX();
            y += p.getY();
        }

        x /= points.size();
        y /= points.size();

        return new Point(x, y);
    }

   /* public Iterable<Point> hull() {
        Stack<Point> s = new Stack<Point>();
        for (Point p : hull) s.push(p);
        return s;
    }*/

    /*public int number(){ //количество точек в готовом многоугольнике
        int  n2 = 0;
        for (Point p : hull) ++n2;
        return n2;
    }
    //проверить, является ли граница многоугольника строго выпуклой
    private boolean isConvex() {
        int n = hull.size();
        if (n <= 2) return true;

        Point[] points = new Point[n];
        int k = 0;
        for (Point p : hull()) {
            points[k++] = p;
        }

        for (int i = 0; i < n; i++) {
            if (Point.ccw(points[i], points[(i + 1) % n], points[(i + 2) % n]) <= 0) {
                return false;
            }
        }
        return true;
    }*/
}
