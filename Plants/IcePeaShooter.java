package Plants;

import javax.swing.ImageIcon;

public class IcePeaShooter extends Plants{
    //public ArrayList<Pea> peaList;
    private Pea pea;
   
    public IcePeaShooter(int plantDamage, int plantHealth,double x,double y,int xBackyard,int yBackyard) {
        super(plantDamage, plantHealth,x,y,new ImageIcon("Image/Plants/ice_pea_shooter.gif"),new ImageIcon("Image/Cards/ice_pea_shooter_price.png"),"IcePeaShooter",175,xBackyard,yBackyard,new ImageIcon("Image/Cards/ice_pea_shooter_price_delay.png"),375);
        pea = new Pea(plantDamage, x +  3*getImage().getIconWidth()/4, y + getImage().getIconHeight()/4,xBackyard,yBackyard,new ImageIcon("Image/Plants/ice_pea.png"),"IcePea");
    }

    public Pea getPea(){
        return pea;
    }

    public void animatedImage() {
        if (getHealth() >0){
            if (getPlantHit()){
                setImage(new ImageIcon("Image/Plants/ice_pea_shooter_eating.gif"));
            }
            else{
                setImage(new ImageIcon("Image/Plants/ice_pea_shooter.gif"));
            }
        }
        else if (getHealth() <= 0){

            if (getDeathDelay() <= 50)
                setDeathDelay(getDeathDelay() + 1);
        }
    }

}