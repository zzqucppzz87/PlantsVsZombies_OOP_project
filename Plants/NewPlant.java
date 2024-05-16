package Plants;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Zombies.Zombies;

public class NewPlant extends Plants{
    
    private boolean effect = false;

    public NewPlant(int plantDamage, int plantHealth, double x, double y, int xBackyard, int yBackyard) {

        super(plantDamage, plantHealth,x,y,new ImageIcon("Image/Plants/summer_walnut.gif"),new ImageIcon("Image/Cards/summer_walnut_price.png"),"NewPlant",100,xBackyard,yBackyard,new ImageIcon("Image/Cards/summer_walnut_delay.png"),1500);
    
        setSpeed(2);
    }

 
    public void update(){
        if (isImageActive())
            if(getXCoordinate() <= 1000){
                if (getHealth() > 900){
                    if (plantHit){
                        hit++;
                        if (hit == 20){
                            plantHit = false;
                            hit = 0;
                        }
                    }                    
                }
                else {

                    if (!effect){
                        setImage(new ImageIcon("Image/Plants/test.gif"));
                        effect = true;
                    }
                    setXCoordinate(getXCoordinate() + getSpeed());
                    setCurrentPoint(new Point((int) getXCoordinate(), (int)getYCoordinate()));
                    updatePos();
                }
            }
            else{
                setPos(0,getYCoordinate());
                setImageActive(false);
            }
        animatedImage();
    }

    public void plantHit(Zombies zombies,ArrayList<Plants> list){
        if (isImageActive()){
            if (getHealth() <= 900){
                zombies.setHealth(zombies.getHealth() - getDamage());
                zombies.setZombieHit(true);
                zombies.setHit(0);
                list.add(this);
            }
            else{

                setHealth(getHealth() - zombies.getDamage());
            }
        }
    }

}
