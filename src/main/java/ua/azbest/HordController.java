package ua.azbest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class HordController {

    private final double xShift = 50.0;
    private final double xScale = 1 / 50.0;
    private final double yShift = 50.0;
    private final double yScale = 1 / 50.0;
    private final double gridStep = 1.0;

    private final double DASH_LENGTH = 17;

    private GraphicsContext gc;

    private ArrayList<Point> points;

    @FXML
    Label status;

    @FXML
    Canvas canvas;

    @FXML
    Label sysInfo;

    @FXML
    TableColumn columnX;

    @FXML
    TableColumn columnY;

    @FXML
    TableView<Point> table;

    public void MouseMove(MouseEvent mouseEvent) {

//        double canvasHeight = ((Canvas)(mouseEvent.getSource())).getHeight();
        double canvasHeight = canvas.getHeight();
        double x = (mouseEvent.getX() - xShift) * xScale;
        double y = (canvasHeight - mouseEvent.getY() - yShift) * yScale;
        status.setText(String.format("(%.2f, %.2f)", x, y));

    }

    public void initialize() {
        points = new ArrayList<>();

        gc = canvas.getGraphicsContext2D();

        drawGrid();
    }

    private void drawGrid() {

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int countX = 12;
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(0.5);
        gc.setLineDashOffset(5);

        double h = canvas.getHeight();
        double w = canvas.getWidth();

        for (int i=0; i<countX; i++) {
            vdashed(i/xScale+xShift, 0, h);
            hdashed(i/yScale+yShift, 0, w);
        }
    }

    private double realToVisualX(double x) {
        return (x / xScale) + xShift;
    }

    private double visualToRealX(double x) {
        return (x - xShift) * xScale;
    }

    private double realToVisualY(double y) {
        return canvas.getHeight() - (y / yScale + yShift);
    }

    private double visualToRealY(double y) {
        return (canvas.getHeight() - y - yShift) * yScale;
    }

    private void vdashed(double xx, double y0, double y1)
    {
        for (double yy=y0; yy<=y1; yy+=DASH_LENGTH*2) {
            gc.strokeLine(xx, yy, xx, yy+DASH_LENGTH);
        }
    }

    private void hdashed(double yy, double x0, double x1)
    {
        for (double xx=x0; xx<=x1; xx+=DASH_LENGTH*2) {
            gc.strokeLine(xx, yy, xx+DASH_LENGTH, yy);
        }
    }

    public void MouseClicked(MouseEvent mouseEvent) {

        Point p = new Point(visualToRealX(mouseEvent.getX()), visualToRealY(mouseEvent.getY()));
        drawPoint(p);
        points.add(p);
        if (points.size() > 3) {
            points = Graham.getConvexHull(points);
        }
        drawConvexHull();
        table.getItems().clear();
        for (Point pt: points)
            table.getItems().add(pt);

    }


    private final double pointRadius = 5.0;
    private void drawPoint(Point p) {
        drawPoint(p, Color.RED);
    }

    private void drawPoint(Point p, Color color) {
        gc.setFill(Color.BLACK);
        gc.fillOval(realToVisualX(p.getX())-pointRadius, realToVisualY(p.getY())-pointRadius, pointRadius*2, pointRadius*2);
        gc.setFill(color);
        gc.fillOval(realToVisualX(p.getX())-pointRadius+1, realToVisualY(p.getY())-pointRadius+1, pointRadius*2-2, pointRadius*2-2);

    }

    public void testing(ActionEvent actionEvent) {

        drawConvexHull();

    }

    private void drawConvexHull() {
        if (points.size() > 2) {

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            drawGrid();

            for (int i = 0; i < points.size() - 1; i++) {

                drawLineThrowPoints(points.get(i), points.get(i + 1));
                drawPoint(points.get(i));

            }

            drawLineThrowPoints(points.get(0), points.get(points.size() - 1));
            drawPoint(points.get(points.size() - 1));
            drawPoint(points.get(0));

            //testing ))

            Line l = new Line(points.get(0), points.get(1));
            Point t = l.getRandomPointFromSegment();
            drawPoint(t, Color.DARKSEAGREEN);


        }
    }

    private void drawLineThrowPoints(Point p1, Point p2) {

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);

        gc.strokeLine(
               realToVisualX(p1.getX()),
               realToVisualY(p1.getY()),
                realToVisualX(p2.getX()),
                realToVisualY(p2.getY())
        );

    }

}
