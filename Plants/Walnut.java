package Plants;

import javax.swing.ImageIcon;

public class Walnut extends Plants{
   
    public Walnut(int plantDamage, int plantHealth,double x,double y,int xBackyard,int yBackyard) {
        super(plantDamage, plantHealth,x,y,new ImageIcon("Image/Plants/walnut_full_life.gif"),new ImageIcon("Image/Cards/walnut_price.png"),"Walnut",50,xBackyard,yBackyard,new ImageIcon("Image/Cards/walnut_price_delay.png"),1500);
    }

    public void animatedImage() {
        if (getHealth() >0){
            if (getPlantHit()){
                setImage(new ImageIcon("Image/Plants/walnut_eating.gif"));
            }
            else{
                setImage(new ImageIcon("Image/Plants/walnut_full_life.gif"));
            }
        }
        else if (getHealth() <= 0){

            if (getDeathDelay() <= 50)
                setDeathDelay(getDeathDelay() + 1);
        }
    }

}