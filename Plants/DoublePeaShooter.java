package Plants;

import javax.swing.ImageIcon;

public class DoublePeaShooter extends Plants{
    //public ArrayList<Pea> peaList;
    private Pea pea1;
    private Pea pea2;

   
    public DoublePeaShooter(int plantDamage, int plantHealth,double x,double y,int xBackyard,int yBackyard) {
        super(plantDamage, plantHealth,x,y,new ImageIcon("Image/Plants/repeater.gif"),new ImageIcon("Image/Cards/repeater_price.png"),"DoublePeaShooter",200,xBackyard,yBackyard,new ImageIcon("Image/Cards/repeater_price_delay.png"),375);
        pea1 = new Pea(plantDamage, x +  3*getImage().getIconWidth()/4, y + getImage().getIconHeight()/4,xBackyard,yBackyard,new ImageIcon("Image/Plants/Pea.png"),"Pea");
        pea2 = new Pea(plantDamage, x +  1*getImage().getIconWidth()/4, y + getImage().getIconHeight()/4,xBackyard,yBackyard,new ImageIcon("Image/Plants/Pea.png"),"Pea");
    }

    public Pea getPea1(){
        return pea1;
    }

    public Pea getPea2(){
        return pea2;
    }    

    public void animatedImage() {
        if (getHealth() >0){
            if (getPlantHit()){
                setImage(new ImageIcon("Image/Plants/repeater_eating.gif"));
            }
            else{
                setImage(new ImageIcon("Image/Plants/repeater.gif"));
            }
        }
        else if (getHealth() <= 0){

            if (getDeathDelay() <= 50)
                setDeathDelay(getDeathDelay() + 1);
        }
    }
}
