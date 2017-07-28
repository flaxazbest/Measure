package ua.azbest;

import java.util.LinkedList;

public class Line {

    public static final double EPS = 1e-7;

    private static double minToFirstPoint = 0.2;
    private static double minToEndPoint = 0.8;

    private Point A;
    private Point B;

    private double a, b, c;

    public Line(Point a, Point b) {
        this.A = a;
        this.B = b;

        this.a = b.getY() - a.getY();
        this.b = - (b.getX() - a.getX());
        this.c = a.getY() * (b.getX() - a.getX()) - a.getX() * (b.getY() - a.getY());
    }

    public Point getCrossPoint(Line line) {

        double delta = a*line.getB() - b*line.getA();
        if (delta == 0)
            return null;
        else {
            double deltaX = -c*line.getB() + b*line.getC();
            double x = deltaX / delta;

            double deltaY = -a*line.getC() + c*line.getA();
            double y = deltaY / delta;;

            return new Point(x, y);
        }
    }

/*
    public Point getRandomPointFromSegment() {

        if (this.A.getX() != this.B.getX()) {
            double lengthX = Math.abs(A.getX() - B.getX());
            double lengthScaledX = lengthX * (minToEndPoint - minToFirstPoint);
            double x = Math.min(A.getX(), B.getX());
            x += (Math.random() * lengthScaledX) + lengthX*minToFirstPoint;
            double y = (-c - a*x) / b;
            return new Point(x, y);
        }
        else {
            double lengthY = Math.abs(A.getY() - B.getY());
            double lengthScaledY = lengthY * (minToEndPoint - minToFirstPoint);
            double y = Math.min(A.getY(), B.getY());
            y += (Math.random() * lengthScaledY) + lengthY*minToFirstPoint;
            double x = A.getX();
            return new Point(x, y);
        }
    }
//*/

    public Point getRandomPointFromSegment() {
        double x = (A.getX() + B.getX()) / 2;
        double y = (A.getY() + B.getY()) / 2;
        return new Point(x, y);
    }

    public double lengthToPoint(Point p) {
        if (p == null)
            return 0.0;
        double result = a*p.getX() + b*p.getY() + c;
        if (Math.abs(result) < EPS) result = 0.0;
        return result;
    }

    public Point getFarrestPoint(LinkedList<Point> setOfPoints) {
        Point result = setOfPoints.get(0);
        double length = Math.abs(this.lengthToPoint(result));

        for (Point a: setOfPoints) {
            double tmp = Math.abs(this.lengthToPoint(a));
            if (tmp > length) {
                length = tmp;
                result = a;
            }
        }
        return result;
    }

    public Line getParallelLineThrowPoint(Point p) {

        double cc = -(a*p.getX() + b*p.getY());
        Line result = new Line(a, b, cc);
        result.A = p;

        Point second;
        if (b != 0) {
            double yy = (-cc -a*(p.getX() + 5)) / b;
            second = new Point(p.getX()+5, yy);
        } else {
            second = new Point(p.getX(), p.getY()+5);
        }
        result.B = second;

        return result;
    }

    public Line getPerpendicularThrowPoint(Point p) {

        double newA = -b;
        double newB = a;
        double newC = -(newA*p.getX() + newB*p.getY());
        Line result = new Line(newA, newB, newC);

        result.A = p;
        Point second;
        if (newB!=0) {
            double yy = (-newC -newA*(p.getX() + 5)) / newB;
            second = new Point(p.getX()+5, yy);
        } else {
            second = new Point(p.getX(), p.getY()+5);
        }
        result.B = second;
        return result;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    private Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point getPointA() {
        return this.A;
    }

    public Point getPointB() {
        return this.B;
    }

}
