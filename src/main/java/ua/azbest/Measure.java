package ua.azbest;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.LinkedList;
import java.util.Properties;

public class Measure {

    protected Properties prop;

    protected GraphicsContext gc;
    protected double w;
    protected double h;

    protected double hw;
    protected double hh;

    protected LinkedList<Pair> listX;
    protected LinkedList<Pair> listY;

    protected int innerCounter;
    protected int outerCounter;


    public Measure(GraphicsContext gc, double w, double h, Properties prop) {
        this.gc = gc;
        this.w = w;
        this.h = h;
        this.prop = prop;

        hw = 0.1;
        hh = 0.1;

    }

    public void clear() {
        gc.clearRect(0, 0, w, h);
    }

    public void drawShape() {
        gc.setStroke(Paint.valueOf(prop.getProperty("figureColor")));
        gc.setLineWidth(2);
    }

    public double getInnerMeasure() {
        return getMeasure() * innerCounter;
    }

    public double getOuterMeasure() {
        return getMeasure() * outerCounter;
    }

    public void drawGrid(double hw, double hh) {
        gc.setStroke(Paint.valueOf(prop.getProperty("gridColor")));
        this.hw = hw;
        this.hh = hh;

        int p = (int)(0.8*3 / hw);
        int t = (int)(0.8*3 / hh);

        listX = new LinkedList<Pair>();
        listY = new LinkedList<Pair>();

        for (int i=0; i<p; i++) {
            gc.strokeLine(w / 2 + w / 2 * i * hw * 0.8, 0, w / 2 + w / 2 * i * hw * 0.8, h);
            listX.add(new Pair(i*hw, w / 2 + w / 2 * i * hw * 0.8));
        }
        for (int i=-1; i>-p; i--) {
            gc.strokeLine(w / 2 + w / 2 * i * hw * 0.8, 0, w / 2 + w / 2 * i * hw * 0.8, h);
            listX.add(new Pair(i*hw, w / 2 + w / 2 * i * hw * 0.8));
        }

        for (int i=0; i<t; i++) {
            gc.strokeLine(0, h / 2 + h / 2 * i * hh * 0.8, w, h / 2 + h / 2 * i * hh * 0.8);
            listY.add(new Pair(i*hh, h / 2 + h / 2 * i * hh * 0.8));
        }
        for (int i=-1; i>-t; i--) {
            gc.strokeLine(0,h/2 + h/2*i*hh*0.8, w, h/2 + h/2*i*hh*0.8);
            listY.add(new Pair(i*hh, h / 2 + h / 2 * i * hh * 0.8));
        }
    }

    public void drawFigure() {
        innerCounter = 0;
        outerCounter = 0;

        for (int i=0; i<listX.size(); i++) {
            for (int j=0; j<listY.size(); j++) {
                if (isInner(listX.get(i).realPoint, listY.get(j).realPoint, hw, hh)) {
                    gc.setFill(Paint.valueOf(prop.getProperty("innerColor")));
                    gc.fillRect(listX.get(i).visualPoint, listY.get(j).visualPoint,w / 2 *  hw * 0.8-1,  h / 2  * hh * 0.8-1);
                    innerCounter++;
                    outerCounter++;
                } else
                if (isOuter(listX.get(i).realPoint, listY.get(j).realPoint, hw, hh)) {
                    gc.setFill(Paint.valueOf(prop.getProperty("outerColor")));
                    gc.fillRect(listX.get(i).visualPoint, listY.get(j).visualPoint,w / 2 *  hw * 0.8-1,  h / 2  * hh * 0.8-1);
                    outerCounter++;
                }

            }
        }
    }

    protected double getInnerPercent() {
        return 0.0;
    }

    protected double getOuterPercent() {
        return 0.0;
    }

    protected boolean isInner(double x, double y, double w, double h) {
        return false;
    }

    protected boolean isOuter(double x, double y, double w, double h) {
        return true;
    }

    public double getMeasure() {
        return hw * hh;
    }
}
