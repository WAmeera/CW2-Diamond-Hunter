package Mapviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class Start extends javafx.application.Application {
    @Override
    public void start(javafx.stage.Stage primaryStage) {
        try {
            javafx.scene.Parent content = javafx.fxml.FXMLLoader.load(getClass().getClassLoader().getResource("Mapviewer/editor.fxml"));
            javafx.scene.Scene scene = new javafx.scene.Scene(content);
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(e->{
                e.consume();
                Control cp = new Control();
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

    public static void main() {
        launch();
    }
}
