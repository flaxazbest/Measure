package ua.azbest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Hord extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "../../hord.fxml";
        String styleFile = "../../style.css";

        ResourceBundle lngBndl = ResourceBundle
                .getBundle("lang/language", new Locale("en"));

        FXMLLoader loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("lang/language", new Locale("en")));

        Parent root = (Parent) loader.load(getClass().getResource(fxmlFile), lngBndl);
        primaryStage.setTitle(lngBndl.getString("program.name"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(App.class.getResource(styleFile).toExternalForm());
        primaryStage.show();
    }
}
