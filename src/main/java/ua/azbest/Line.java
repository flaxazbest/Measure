package ua.azbest;

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

    public double compare(Point p) {
        double result = a*p.getX() + b*p.getY() + c;
        if (Math.abs(result) < EPS) result = 0.0;
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
}
