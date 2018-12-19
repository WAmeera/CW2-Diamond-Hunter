package Mapviewer;

import java.io.Serializable;

/* Tuple object mainly used to store the coordinates of item and implements the Serializable */
public class Tuple implements java.io.Serializable{
    public int x;
    public int y;
    public Tuple(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public void setPosition(int x,int y){
        this.x=x;
        this.y=y;
    }


}
