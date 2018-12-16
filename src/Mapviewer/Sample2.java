package Mapviewer;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Sample2 {


    @FXML Button closeButton;
    //Exit program fully when click confirm button in dialog
    public void closeProgram(){

        System.exit(0);
    }
    //When click cancel only confirm exit dialog will close and return to mapview
    @FXML
    public void handleCloseButtonAction() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


}
