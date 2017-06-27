package ua.azbest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Properties;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "../../main.fxml";
        String styleFile = "../../style.css";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        primaryStage.setTitle("Measurement");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(App.class.getResource(styleFile).toExternalForm());
        primaryStage.show();
    }
}
