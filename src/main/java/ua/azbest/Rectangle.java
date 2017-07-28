package ua.azbest;

import javafx.scene.canvas.Canvas;

public class Rectangle {

    private Line base;
    private Point farrestPoint;
    private Point point1;
    private Point point2;

    public Rectangle(Line base, Point farrestPoint) {
        this.base = base;
        this.farrestPoint = farrestPoint;
        double height = Math.abs(base.lengthToPoint(farrestPoint));
        Line parallelBase = base.getParallelLineThrowPoint(farrestPoint);
        Line perp1 = base.getPerpendicularThrowPoint(base.getPointA());
        Line perp2 = base.getPerpendicularThrowPoint(base.getPointB());
        point1 = parallelBase.getCrossPoint(perp1);
        point2 = parallelBase.getCrossPoint(perp2);
    }

    public Line getBase() {
        return base;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public Point getFarrestPoint() {
        return farrestPoint;
    }
}
