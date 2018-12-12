package Mapviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class Start extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent content = FXMLLoader.load(getClass().getClassLoader().getResource("Mapviewer/editor.fxml"));
            Scene scene = new Scene(content);
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(e->{
                e.consume();
                Mapviewer.Control cp = new Mapviewer.Control();
                try {

                    cp.closeProgram();
                } catch (java.io.IOException e1) {
                    e1.printStackTrace();
                }
            });
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
