package ua.azbest;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Random;

public class InnerFigure {

    private ArrayList<Point> outer;
    private ArrayList<IndexedPoint> hull;

    public InnerFigure(ArrayList<Point> outer) {
        this.outer = outer;
        getInnerFigure();
    }

    public void addVertex(Point p, Integer index) {
        hull.add(new IndexedPoint(p, index));
    }

    public ArrayList<Point> getPoints() {

        ArrayList<Point> result = new ArrayList<>();
        for (IndexedPoint indexedPoint: hull)
            result.add(indexedPoint.point);
        return result;
    }

    private void getInnerFigure() {
        Random random = new Random();

        int n = outer.size();
        hull = new ArrayList<>();

        int k = 0;
        int startSegment = (random.nextInt(n));
        do {
            Line l1 = new Line(outer.get((startSegment+k)%n), outer.get((startSegment+k+1)%n));
            k++;
            Line l2 = new Line(outer.get((startSegment+k)%n), outer.get((startSegment+k+1)%n));
            k++;

            Point t1 = l1.getRandomPointFromSegment();
            Point t2 = l2.getRandomPointFromSegment();

            hull.add(new IndexedPoint(t1, (k-2)));

            Point basePoint = l1.getCrossPoint(l2);
            Line hord = new Line(t1, t2);
            double baseDirection = hord.compare(basePoint);

            do {
                l2 = new Line(outer.get((startSegment+k)%n), outer.get((startSegment+k+1)%n));
                t2 = l2.getRandomPointFromSegment();
                hord = new Line(t1, t2);
                k++;
            } while (isSameSign(baseDirection, hord.compare(l1.getCrossPoint(l2))) && k < n);

            if (k > n) break;
            k-=2;

        } while (k < n);

    }

    private boolean isSameSign(double d1, double d2) {
        return d1*d2 >= 0;
    }

}
