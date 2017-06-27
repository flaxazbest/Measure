package ua.azbest;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Properties;

public class MeasureRombus extends Measure {
    public MeasureRombus(GraphicsContext gc, double w, double h, Properties prop) {
        super(gc, w, h, prop);
    }

    public void drawShape() {
        super.drawShape();
        gc.strokeLine(w*0.1, h/2, w/2, h*0.1);
        gc.strokeLine(w/2, h*0.1, w*0.9, h/2);
        gc.strokeLine(w*0.9, h/2, w/2, h*0.9);
        gc.strokeLine(w/2, h*0.9,w*0.1, h/2);
        // one more 3
    }

    protected boolean isInner(double x, double y, double w, double h) {
        boolean lt = -(x) - (y) < 1.0;
        boolean rt = (x+w) - (y) < 1.0;
        boolean rb = (x+w) + (y+h) < 1.0;
        boolean lb = -(x) + (y+h) < 1.0;
        return lt && lb && rt && rb;
    }

    protected boolean isOuter(double x, double y, double w, double h) {
        boolean lt = -(x+w) - (y+h) < 1.0  ;
        boolean rt = (x) - (y+h) < 1.0;
        boolean rb = (x) + (y) < 1.0;
        boolean lb = -(x+w) + (y) < 1.0;
        return lt && lb && rt && rb;
    }

    protected double getInnerPercent() {
        return (2.0-getInnerMeasure()) / 2.0 * 100;
    }

    protected double getOuterPercent() {
        return (getOuterMeasure()-2.0) / 2.0 * 100;
    }
}
