package Plants;

import javax.swing.ImageIcon;

import Entity.Projectile;

public class Sun extends Projectile {
    private double yFirstCoordinate;
    private double xCoordinate;
    private double yCoordinate;
    private static ImageIcon image = new ImageIcon("Image/Others/sun.gif");

    public Sun(double x, double y){
        super(x,y,0,image.getIconWidth(),image.getIconHeight(),0,0);
        this.xCoordinate = x;
        this.yCoordinate = y + 40;
        this.yFirstCoordinate = y + 40;
    }
    
    public void updateSunCard(){
        setXCoordinate(0);
        setYCoordinate(0);
    }

    public ImageIcon getImage(){
        return image;
    }

    public void setXCoordinate(double x){
        this.xCoordinate = x;
    }

    public void setYCoordinate(double y){
        this.yCoordinate = y;
    }

    public double getXCoordinate(){
        return xCoordinate;
    }

    public double getYCoordinate(){
        return yCoordinate;
    }
    
    public double getYFirstCoordinate(){
        return yFirstCoordinate;
    }




}

