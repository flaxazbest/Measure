package ua.azbest;

public class IndexedPoint {

    public Point point;
    public int index;

    public IndexedPoint(Point point, int index) {
        this.point = point;
        this.index = index;
    }

    @Override
    public String toString() {
        return point + " - " + index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
