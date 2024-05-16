package Entity;

import java.awt.geom.Rectangle2D;

public class Projectile {
    private Rectangle2D.Double hitBox;
    private boolean imageActive = true;
    
    private double speed;
    
    private int xBackyard;
    private int yBackyard;
    private double xCoordinate;
    private double yCoordinate;

    public Projectile(double x, double y,double speed,int width,int height,int xBackyard,int yBackyard){
        hitBox = new Rectangle2D.Double(x,y,width,height);
        this.speed = speed;
        this.xBackyard = xBackyard;
        this.yBackyard = yBackyard;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }
    public void updatePos(){
        hitBox.x += speed;
    }

    public double getSpeed(){
        return speed;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public void setPos(double x,double y){
        hitBox.x = x;
        hitBox.y = y;
    }

    public void setHitBox(Rectangle2D.Double box){
        this.hitBox = box;
    }

    public Rectangle2D.Double getHitBox(){
        return hitBox;
    }

    public void setImageActive(boolean active){
        this.imageActive = active;
    }

    public boolean isImageActive(){
        return imageActive;
    }
    
    public int getXBackyard(){
        return xBackyard;
    }
    
    public int getYBackyard(){
        return yBackyard;
    }

    public void setXBackyard(int xBackyard){
        this.xBackyard = xBackyard;
    }

    public void setYBackyard(int yBackyard){
        this.yBackyard = yBackyard;
    }

    public double getXCoordinate(){
        return xCoordinate;
    }

    public double getYCoordinate(){
        return yCoordinate;
    }

    public void setXCoordinate(double x){
        this.xCoordinate = x;
    }

}
