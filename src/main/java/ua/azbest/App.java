package ua.azbest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "../../main.fxml";
        String styleFile = "../../style.css";

        //ResourceBundle bundle = ResourceBundle.getBundle("UIResources.properties", new Locale("ua"));
        FXMLLoader loader = new FXMLLoader();
        //Parent root = (Parent) loader.load(getClass().getResource(fxmlFile), bundle);
        Parent root = (Parent) loader.load(getClass().getResource(fxmlFile));
        primaryStage.setTitle("Measurement");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(App.class.getResource(styleFile).toExternalForm());

        /*Properties lang = new Properties();
        String filename = "lang/ua/lang.properties";
        try (InputStream input = App.class.getClassLoader().getResourceAsStream(filename)) {
            if(input==null){
                System.out.println("No language file " + filename);
            }
            lang.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/



        primaryStage.show();
    }
}
