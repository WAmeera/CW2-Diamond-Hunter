package Mapviewer;

import com.neet.DiamondHunter.Main.Game;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Control {
    int x, y;
    public static int axe_x ; //
    public static int axe_y; //
    public static int boat_x; //
    public static int boat_y ; //
    public static final int BOAT = 0;
    public static final int AXE = 1;
    public static final int TILESIZE = 16;
    public int[][] mapValue;   // the value of each of the map block
    public javafx.scene.image.Image map;
    public javafx.scene.image.Image image;
    private Mapviewer.GameMap gameMap;
    private javafx.scene.canvas.GraphicsContext gContext;
    private java.io.ObjectInputStream objectReader;
    private java.io.ObjectOutputStream objectWriter;

    java.io.File file;
    Mapviewer.Tuple axe;   //store the item's positon
    Mapviewer.Tuple boat;
    java.util.HashMap<Integer, Tuple> items;
    @javafx.fxml.FXML
    private javafx.scene.control.TextArea xPosition, yPosition;
    @javafx.fxml.FXML
    javafx.scene.control.Label axeYLabel,axeXLabel,boatXLabel,boatYLabel,statusLabel;

    @javafx.fxml.FXML
    private javafx.scene.canvas.Canvas canvas;
    /*Confirm closing program and saves any changes have been done by user*/
    public void  closeProgram() throws java.io.IOException {

        javafx.stage.Stage window = new javafx.stage.Stage();
        window.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("sample2.fxml"));


        window.setTitle("Confirm Exit");
        window.setScene(new javafx.scene.Scene(root));
        window.showAndWait();

    }

    public void initialize() {
        //initialize the picture resource
        axe = new Mapviewer.Tuple(37, 26);
        boat = new Mapviewer.Tuple (4,12);
        map = new javafx.scene.image.Image(getClass().getResourceAsStream("/Tilesets/testtileset.gif"));
        image = new javafx.scene.image.Image(getClass().getResourceAsStream("/Sprites/items.gif"));
        gameMap = new Mapviewer.GameMap(TILESIZE, TILESIZE, map);
        gameMap.loadMap("/Maps/testmap.map");
        gContext = canvas.getGraphicsContext2D();
        //draw the map
        gameMap.drawMap(gContext);
        items = new java.util.HashMap<Integer, Tuple>();
        //Draw items
        drawItem(0,boat.x, boat.y);
        drawItem(1, axe.x, axe.y);

        boatXLabel.setText(Integer.toString(boat.x));
        boatYLabel.setText(Integer.toString(boat.y));
        axeXLabel.setText(Integer.toString(axe.x));
        axeYLabel.setText(Integer.toString(axe.y));



    }


    /* this method is used to handle the button action*/
    @javafx.fxml.FXML
    public void axePressed() {
        displayCoordinate(); //////
        statusLabel.setText("Axe button pressed");
        canvas.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                x = (int) event.getX() / TILESIZE;
                y = (int) event.getY() / TILESIZE;
                mapValue = gameMap.getMap();
                int currentPoint = mapValue[y][x];
                checkAvailable(currentPoint, x, y, AXE);

            }
        });

    }

    @javafx.fxml.FXML
    public void boatPressed() {
        displayCoordinate();////
        statusLabel.setText("Boat button pressed");
        canvas.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                x = (int) event.getX() / TILESIZE;
                y = (int) event.getY() / TILESIZE;
                mapValue = gameMap.getMap();
                int currentPoint = mapValue[y][x];
                checkAvailable(currentPoint, x, y, BOAT);

            }
        });

    }

    /*this method is to check if a position is available to set item,
     *if available, draw the item on canvas and put it into a tuple object
     */
    public void checkAvailable(int value, int x, int y, int type) {

        if (value == 20 || value == 22 || value == 21) {        //item can not set on the barrier

            ringAlert(javafx.scene.control.Alert.AlertType.ERROR, "This positon is not available!");
        } else {

            if (type == AXE) {
               if (axe != null) {     //if already have axe, change the positon and draw the axe again
                   drawItem(3, axe.x, axe.y); /////3
                   axe.setPosition(x, y);
                   displayCoordinate();
                   drawItem(1, x, y);///1
               } else {                  //if do not have item, add one to the axe object and draw
                   //drawItem(1, axe_x, axe_y);  ////
                   drawItem(3, axe.x, axe.y);
                   axe = new Mapviewer.Tuple(x, y);
                   displayCoordinate();
               }
                statusLabel.setText("Axe location changed");
            } else {
                if (boat != null) {     //if already have boat, change the positon and draw the axe again
                    drawItem(3, boat.x, boat.y);//3
                    boat.setPosition(x, y);
                    displayCoordinate();
                    drawItem(0, x, y);////0
                } else {                  //if do not have item, add one to the boat object and draw
                    drawItem(0, boat_x, boat_y);
                    boat = new Mapviewer.Tuple(x, y);
                    displayCoordinate();
                }
                statusLabel.setText("Boat location changed");
            }
        }
    }


    public void displayCoordinate() //display the item position on TextArea after set
    {
        axeXLabel.setText(Integer.toString(axe.x));
        axeYLabel.setText(Integer.toString(axe.y));
        boatXLabel.setText(Integer.toString(boat.x));
        boatYLabel.setText(Integer.toString(boat.y));

    }

   /* public void displayCoordinate2() //display the item position on TextArea after set
    {
        xPosition.setText("");
        yPosition.setText("");
    }*/

    public void ringAlert(javafx.scene.control.Alert.AlertType alertType, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(alertType, message);
        alert.showAndWait();
    }

    @javafx.fxml.FXML javafx.scene.control.Button playButton;
    /*start the Main Game*/
    @javafx.fxml.FXML
    public void startGame(){
        com.neet.DiamondHunter.Main.Game.main(null);

        // get a handle to the stage
        javafx.stage.Stage stage = (javafx.stage.Stage) playButton.getScene().getWindow();
        // do what you have to do
        stage.close();

    }

    /* save the change and write items object into the file using a Serializable hashmap.*/
    @javafx.fxml.FXML
    void saveClose() {
        axe_x = axe.x;
        axe_y = axe.y;
        boat_x = boat.x;
        boat_y = boat.y;
        startGame();


    }

    /*draw the item on canvas, using pixelReader to cut the picture*/
    public void drawItem(int type, int x, int y) {
        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();
        javafx.scene.image.PixelReader p = map.getPixelReader();

        if (type == AXE) {
            canvas.getGraphicsContext2D().drawImage(new javafx.scene.image.WritableImage(pixelReader, TILESIZE, TILESIZE, TILESIZE, TILESIZE), x * TILESIZE, y * TILESIZE);
        }
        else if (type == BOAT) {
            canvas.getGraphicsContext2D().drawImage(new javafx.scene.image.WritableImage(pixelReader, 0, TILESIZE, TILESIZE, TILESIZE), x * TILESIZE, y * TILESIZE);
        }
        //draw a grass block to replace when moving the axe or boat
        else {
            canvas.getGraphicsContext2D().drawImage(new javafx.scene.image.WritableImage(p, TILESIZE, 0, TILESIZE, TILESIZE), x * TILESIZE, y * TILESIZE);
        }
    }

    /*implements the object writer*/
    public void objectWriter(java.io.File file) {
        try {
            objectWriter = new java.io.ObjectOutputStream(new java.io.FileOutputStream(file));
            objectWriter.writeObject(items);
            objectWriter.close();
        } catch (java.io.IOException ioe) {
            System.err.println("cannot write Item.data for writing!");
            ioe.printStackTrace();
        }

    }

    @javafx.fxml.FXML
    void resetPosition()
    {
        initialize();
        displayCoordinate();
        statusLabel.setText("Reset pressed");
    }


}
