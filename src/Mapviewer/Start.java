package Mapviewer;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Start extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent content = FXMLLoader.load(getClass().getClassLoader().getResource("Mapviewer/editor.fxml"));
            Scene scene = new Scene(content);
            primaryStage.setScene(scene);
            //Prevents default closing and opens exit dialog
            primaryStage.setOnCloseRequest(e->{
                e.consume();
                Control cp = new Control();
                //Handle exceptions
                try {

                    cp.closeProgram();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            //Added Icon image
            Image icon = new Image("Mapviewer/diamond.png");
            primaryStage.getIcons().add(icon);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
