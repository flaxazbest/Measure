package ua.azbest;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Properties;

public class MeasureCircle extends Measure {
    public MeasureCircle(GraphicsContext gc, double w, double h, Properties prop) {
        super(gc, w, h, prop);
    }

    public void drawShape() {
        super.drawShape();
        gc.strokeOval(w*0.1, h*0.1, w*0.8, h*0.8);
    }

    protected boolean isInner(double x, double y, double w, double h) {
        boolean lt = x*x + y*y < 1.0;
        boolean lb = x*x + (y+h)*(y+h) < 1.0;
        boolean rt = (x+w)*(x+w) + y*y < 1.0;
        boolean rb = (x+w)*(x+w) + (y+h)*(y+h) < 1.0;
        return lt && lb && rt && rb;
    }

    protected boolean isOuter(double x, double y, double w, double h) {
        boolean lt = x*x + y*y < 1.0;
        boolean lb = x*x + (y+h)*(y+h) < 1.0;
        boolean rt = (x+w)*(x+w) + y*y < 1.0;
        boolean rb = (x+w)*(x+w) + (y+h)*(y+h) < 1.0;
        return lt || lb || rt || rb;
    }

    protected double getInnerPercent() {
        return (Math.PI-getInnerMeasure()) / Math.PI * 100;
    }

    protected double getOuterPercent() {
        return (getOuterMeasure()-Math.PI) / Math.PI * 100;
    }

}
