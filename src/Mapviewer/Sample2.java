package Mapviewer;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Sample2 {


    @FXML Button closeButton;
    //Mapviewer.Control c = new Control();
    public void closeProgram(){

        System.exit(0);
    }
    @FXML
    public void handleCloseButtonAction() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


}
