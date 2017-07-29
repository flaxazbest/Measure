package ua.azbest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class InnerFigure {

    private ArrayList<IndexedPoint> outer;
    private ArrayList<Line> hords;
    private ArrayList<IndexedPoint> hull;

    public InnerFigure(ArrayList<IndexedPoint> outer) {
        this.outer = outer;
        getInnerFigure();
    }

    public void addVertex(Point p, Integer index) {
        hull.add(new IndexedPoint(p, index));
    }

    public ArrayList<IndexedPoint> getPoints() {
        return hull;
    }

    private void getInnerFigure() {
        Random random = new Random();

        int n = outer.size();
        hull = new ArrayList<>();

        int k = 0;
//        int startSegment = (random.nextInt(n));
        int startIndex = 0;
        int currentIndex = startIndex;
        startIndex += n;

        hull.add(outer.get(currentIndex));


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
            tmp.add( outer.get( (start+j)%outer.size() ).point );
        return tmp;
    }

    public int getVertexSize() {
        return hull.size();
    }

}
