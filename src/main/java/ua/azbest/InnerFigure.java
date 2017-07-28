package ua.azbest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class InnerFigure {

    private ArrayList<Point> outer;
    private ArrayList<Line> hords;
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
//        int startSegment = (random.nextInt(n));
        int startSegment = 0;
        do {
            Line l1 = new Line(outer.get((startSegment+k)%n), outer.get((startSegment+k+1)%n));
            k++;
            Line l2 = new Line(outer.get((startSegment+k)%n), outer.get((startSegment+k+1)%n));
            k++;

            Point t1 = l1.getRandomPointFromSegment();
            Point t2 = l2.getRandomPointFromSegment();

            hull.add(new IndexedPoint(t1, (startSegment+k-1)));

            Point basePoint = l1.getCrossPoint(l2);
            Line hord = new Line(t1, t2);
            double baseDirection = hord.lengthToPoint(basePoint);

            do {
                l2 = new Line(outer.get((startSegment+k)%n), outer.get((startSegment+k+1)%n));
                t2 = l2.getRandomPointFromSegment();
                hord = new Line(t1, t2);
                k++;
            } while (isSameSign(baseDirection, hord.lengthToPoint(l1.getCrossPoint(l2))) && k < n);

            if (k > n) break;
            k-=2;

        } while (k < n);

        int sz = hull.size();
        hords = new ArrayList<>();
        for (int i=0; i<sz; i++) {
            hords.add(new Line(hull.get(i).point, hull.get((i+1)%sz).point));
        }

    }

    private boolean isSameSign(double d1, double d2) {
        return d1*d2 >= 0;
    }

    public void printPoints() {
        System.out.println("\n================================");
        for (int i=0; i<hull.size(); i++) {
            System.out.println(i);
            int start = hull.get(i).index;
            int end = hull.get((i+1) % hull.size()).index;

            int count;
            if (start < end)
                count = end - start;
            else
                count = outer.size()-start + end;

            for (int j=0; j<count; j++)
                System.out.println("   " + outer.get( (start+j)%outer.size() ));

        }
    }

    public Point getPointByIndex(int index) {
        index = index % hull.size();
        return hull.get(index).point;
    }

    Line getHordByIndex(int index) {
        return hords.get(index);
    }

    public LinkedList<Point> getPointSideByHord(int index) {
        LinkedList<Point> tmp = new LinkedList<>();

        int start = hull.get(index).index;
        int end = hull.get((index+1) % hull.size()).index;

        int count;
        if (start < end)
            count = end - start;
        else
            count = outer.size()-start + end;

        for (int j=0; j<count; j++)
            tmp.add( outer.get( (start+j)%outer.size() ) );
        return tmp;
    }

    public int getVertexSize() {
        return hull.size();
    }

}
