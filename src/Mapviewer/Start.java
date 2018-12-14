package Mapviewer;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
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
                Control cp = new Control();
                try {

                    cp.closeProgram();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            Image icon = new Image("Mapviewer/diamond.png");
            primaryStage.getIcons().add(icon);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main() {
        launch();
    }
}
