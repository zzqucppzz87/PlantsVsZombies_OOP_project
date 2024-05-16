package Plants;

import javax.swing.ImageIcon;

import Entity.Projectile;
import Zombies.Zombies;

public class Pea extends Projectile{
    private int damage;
    private double xFirstCoordinate;
    private double yFirstCoordinate;
    private int delay = 850;
    private boolean shootActive = false;
    private boolean stop = false;
    private boolean prepareStop = false;
    private ImageIcon image; 
    private String name;

    public Pea(int damage,double x,double y,int xBackyard,int yBackyard,ImageIcon image,String name){
        super(x,y,850 / (1.425*50),image.getIconWidth(),image.getIconHeight(),xBackyard,yBackyard);
        this.damage = damage;
        this.xFirstCoordinate = x;
        this.yFirstCoordinate = y;
        this.image = image;
        this.name = name;
    }

    public void updatePea() {
        if (!stop){
            if (isShootActive()){
                if (getXCoordinate() < delay + xFirstCoordinate){
                    setXCoordinate(getXCoordinate() + getSpeed());
                    this.updatePos();
                }
                else{
                    setXCoordinate(xFirstCoordinate);
                    setPos(xFirstCoordinate, getYCoordinate());
                    setImageActive(true);
                }
            }
            else{                
                    if (getXCoordinate() > xFirstCoordinate && getXCoordinate() < delay + xFirstCoordinate){
                        setXCoordinate(getXCoordinate() + getSpeed());
                        this.updatePos();
                    }
                    else{
                        setXCoordinate(xFirstCoordinate);
                        setPos(xFirstCoordinate, getYCoordinate());
                        if (!getPrepareStop())
                            setImageActive(true);
                        else
                            setStop(true);
                    }
            }


        }
        else{
            setXFirstCoordinate(-200);
            setPos(-200, getYCoordinate());
            setImageActive(false);
        }
    }

    public void effectOnZombies(Zombies zombies){
        zombies.zombieHit(getDamage());
        zombies.setZombieHit(true);
        zombies.setHit(0);
        setImageActive(false);
        if (getName().equals("IcePea")){
            if (zombies.getSpeed() != 0){
                zombies.setSpeed(zombies.getFirstSpeed()/2);
                zombies.setEffectedZombieDelay(1);
            }
        }

    }

    public String getName(){
        return name;
    }

    public int getDamage(){
        return damage;
    }

    public ImageIcon getImage(){
        return image;
    }

    public double getXFirstCoordinate(){
        return xFirstCoordinate;
    }

    public double getYFirstCoordinate(){
        return yFirstCoordinate;
    }
    public void setXFirstCoordinate(double x){
        this.xFirstCoordinate = x;
    }

    public void setStop(boolean stop){
        this.stop = stop;
    }

    public boolean getStop(){
        return stop;
    }

    public boolean isShootActive(){
        return shootActive;
    }

    public void setShootActive(boolean active){
        this.shootActive = active;
    }

    public void setPrepareStop(boolean active){
        this.prepareStop = active;
    }

    public boolean getPrepareStop(){
        return prepareStop;
    }


}
