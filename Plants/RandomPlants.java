package Plants;

import javax.swing.ImageIcon;

public class RandomPlants extends Plants{
    
    public RandomPlants(int plantDamage, int plantHealth, double x, double y, int xBackyard, int yBackyard) {

        super(plantDamage, plantHealth,x,y,new ImageIcon("Image/Plants/cherry_bomb.gif"),new ImageIcon("Image/Cards/myst_price.png"),"RandomPlants",100,xBackyard,yBackyard,new ImageIcon("Image/Cards/myst_delay.png"),1500);

    }    
}
