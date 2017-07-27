package ua.azbest;

import java.util.ArrayList;
import java.util.Random;

public class InnerFigureFinder {

    private static Random random = new Random();

    public static ArrayList<Point> getInnerHull(ArrayList<Point> points) {
        int n = points.size();
        ArrayList<Point> hull = new ArrayList<>();

        int k = 0;
        int startSegment = (random.nextInt(n));
        do {
//        int startSegment = random.nextInt(n);

            Line l1 = new Line(points.get((startSegment+k)%n), points.get((startSegment+k+1)%n));
            k++;
            Line l2 = new Line(points.get((startSegment+k)%n), points.get((startSegment+k+1)%n));
            k++;

            Point t1 = l1.getRandomPointFromSegment();
            Point t2 = l2.getRandomPointFromSegment();

            hull.add(t1);

            Point basePoint = l1.getCrossPoint(l2);
            Line hord = new Line(t1, t2);
            double baseDirection = hord.compare(basePoint);

            do {
                l2 = new Line(points.get((startSegment+k)%n), points.get((startSegment+k+1)%n));
                t2 = l2.getRandomPointFromSegment();
                hord = new Line(t1, t2);
                k++;
            } while (isSameSign(baseDirection, hord.compare(l1.getCrossPoint(l2))) && k < n);

            if (k > n) break;
            k-=2;

        } while (k < n);

        return hull;
    }

    private static boolean isSameSign(double d1, double d2) {
        return d1*d2 >= 0;
    }


}
