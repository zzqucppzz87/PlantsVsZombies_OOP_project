package Entity;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class Shovel {
    private double xCoordinate;
    private double yCoordinate;
    private boolean imageActive = true;
    private Point imageFirstPoint;
    private Point imageCorner;
    

    ImageIcon image = new ImageIcon("Image/Others/shovel.png");

    public Shovel(double x,double y){
        this.xCoordinate = x;
        this.yCoordinate = y;
        imageCorner = new Point((int) x, (int)y);
        imageFirstPoint = new Point((int) x,(int) y);
    }

    public ImageIcon getImage(){
        return image;
    }

    public double getXCoordinate(){
        return xCoordinate;
    }

    public double getYCoordinate(){
        return yCoordinate;
    }

    public boolean isImageActive(){
        return imageActive;
    }

    public void setImageActive(boolean active){
        this.imageActive = active;
    }

    public Point getImageFirstPoint(){
        return imageFirstPoint;
    }

    public Point getImageCorner(){
        return imageCorner;
    }

    public void setImageCorner(Point point){
        this.imageCorner = point;
    }

    public boolean checkShovel(MouseEvent e){
        if (e.getX() >= imageFirstPoint.getX() && e.getX() <= imageFirstPoint.getX() + image.getIconWidth()){
            if (e.getY() >= imageFirstPoint.getY() && e.getY() <= imageFirstPoint.getY() + image.getIconHeight()){
                return true;
            }
        }
        return false;
    }
}