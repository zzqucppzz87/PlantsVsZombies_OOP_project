package Plants;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class SunFlower extends Plants{
    private int delay = 1000;
    private int count = 0;
   
    public SunFlower(int plantDamage, int plantHealth,double x,double y,int xBackyard,int yBackyard) {
        super(plantDamage, plantHealth,x,y,new ImageIcon("Image/Plants/sun_flower.gif"),new ImageIcon("Image/Cards/sun_flower_price.png"),"SunFlower",50,xBackyard,yBackyard,new ImageIcon("Image/Cards/sun_flower_price_delay.png"),375);
    }


    public void updateSun(ArrayList<Sun> sunList){
        if (isImageActive()){
            if (delay <= 50*24.25){
                delay ++;
            }
            else{
                delay = 0;
                this.setXCoordinate(this.getXCoordinate()+count);
                sunList.add(new Sun(this.getXCoordinate(),this.getYCoordinate()));
                this.setXCoordinate(this.getXCoordinate()-count);
                if (count < 20)
                    count += 10;
                else
                    count = 0;
            }
        }
    }

    public void animatedImage() {
        if (getHealth() >0){
            if (getPlantHit()){
                setImage(new ImageIcon("Image/Plants/sun_flower_eating.gif"));
            }
            else{
                setImage(new ImageIcon("Image/Plants/sun_flower.gif"));
            }
        }
        else if (getHealth() <= 0){

            if (getDeathDelay() <= 50)
                setDeathDelay(getDeathDelay() + 1);
        }
    }

}
