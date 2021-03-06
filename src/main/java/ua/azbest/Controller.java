package ua.azbest;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import static ua.azbest.locale.I18N.localeProperty;

public class Controller implements Initializable {

    ResourceBundle resourceBundle;
    Properties prop = null;

    @FXML
    Canvas canvas;

    @FXML
    Pane centralPane;

    @FXML
    Slider sliderWidth;

    @FXML
    Slider sliderHeight;

    @FXML
    CheckBox binder;

    @FXML
    TextField measure;

    @FXML
    TextField innerMeasure;

    @FXML
    TextField outerMeasure;

    @FXML
    TextField innerPercent;

    @FXML
    TextField outerPercent;

    @FXML
    TextField textHH;

    @FXML
    TextField textHW;

    @FXML
    TextField delta;

    @FXML
    ComboBox figure;

    @FXML
    ComboBox comboLanguage;

    private void draw() {

        double w =  canvas.getWidth();
        double h =  canvas.getHeight();

        Measure m = null;

        switch (figure.getSelectionModel().getSelectedIndex()) {
            case 1:  m = new MeasureRombus(canvas.getGraphicsContext2D(), w, h, prop);
                break;
            default: m = new MeasureCircle(canvas.getGraphicsContext2D(), w, h, prop);
        }


        m.clear();

        m.drawGrid(sliderWidth.getValue()/100, sliderHeight.getValue()/100 );
        m.drawFigure();
        m.drawShape();

        measure.setText(String.format("%.4f",m.getMeasure()));
        innerMeasure.setText(String.format("%.4f",m.getInnerMeasure()));
        innerPercent.setText(String.format("%.2f", m.getInnerPercent()) + "%");
        outerMeasure.setText(String.format("%.4f", m.getOuterMeasure()));
        outerPercent.setText(String.format("%.2f", m.getOuterPercent()) + "%");
        delta.setText(String.format("%.4f", m.getOuterMeasure() - m.getInnerMeasure()));
        textHH.setText(String.format("%.3f",sliderHeight.getValue()));
        textHW.setText(String.format("%.3f",sliderWidth.getValue()));


    }

    public void testOperation() {
        double w =  canvas.getWidth();
        double h =  canvas.getHeight();
        Measure measure = new Measure(canvas.getGraphicsContext2D(), w, h, prop);
        measure.clear();

        measure.drawGrid(0.15, 0.15 );
        measure.drawFigure();
        measure.drawShape();
        draw();
        //measure.testDraw();
    }

    public void sliderWMove() {

    }

    public void initialize() {

    }

    public void pick(ActionEvent actionEvent) {

        if (binder.isSelected()) {
            double min = Math.min(sliderHeight.getValue(), sliderWidth.getValue());
            sliderHeight.setValue(min);
            sliderWidth.setValue(min);
        }

    }

    private void setLocaleCombo() {
        ObservableList<Locale> list = FXCollections.observableArrayList(
                new Locale("ru", "RU"),  // Russia
                new Locale("uk", "UA"),  // Ukraine
                new Locale("en", "US")  // United States
        );
        StringConverter<Locale> converter = new StringConverter<Locale>(){
            @Override
            public String toString(Locale object) {
                return String.format("%s(%s)", object.getDisplayCountry(), object.getDisplayLanguage());
            }
            @Override
            public Locale fromString(String string) {
                return null;
            }
        };
        comboLanguage.getItems().addAll(list);
        comboLanguage.setCellFactory(ComboBoxListCell.<Locale>forListView(converter));
        comboLanguage.getSelectionModel().select(1);
    }

    private void changeLoacle(Locale newLocale) {
        Locale.setDefault(newLocale);
        localeProperty().set(newLocale);
        System.out.println(newLocale);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;

        Locale.setDefault(new Locale("en"));

        prop = new Properties();
        String filename = "color.properties";
        try (InputStream input = App.class.getClassLoader().getResourceAsStream(filename)) {
            if(input==null){
                System.out.println("Sorry, unable to find " + filename);
                return;
            }
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setLocaleCombo();

        sliderWidth.setValue(15);
        sliderHeight.setValue(15);

        sliderWidth.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                if (binder.isSelected())
                    sliderHeight.setValue(sliderWidth.getValue());
                draw();
            }
        });

        sliderHeight.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                if (binder.isSelected())
                    sliderWidth.setValue(sliderHeight.getValue());
                draw();
            }
        });

        figure.getItems().addAll(resourceBundle.getString("figure.circle"),resourceBundle.getString("figure.rombus"));
        figure.getSelectionModel().selectFirst();

        figure.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue observable, Object oldValue, Object newValue) -> draw());

        comboLanguage.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue observable, Object oldValue, Object newValue) -> changeLoacle((Locale) newValue));

        testOperation();
    }
}
