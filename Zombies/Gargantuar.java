package Zombies;

import javax.swing.ImageIcon;

public class Gargantuar extends Zombies{

    public Gargantuar(double zombieSpeed, int zombieHealth, int zombieDamage ,double xCoordinate, double yCoordinate,int xBackyard,int yBackyard) {
        super(zombieSpeed, zombieHealth, zombieDamage ,xCoordinate, yCoordinate,new ImageIcon("Image/Zombies/Gargantuar/Gargantuar.gif"),xBackyard,yBackyard,"Gargantuar");
    
        setDeath1(130);
        setDeath2(55);
    }

    public void animatedImage() {
        if (getEffectedZombieDelay() == 0){
            if (!getZombieEating()){
                if (getZombieHit())
                    setImage(new ImageIcon("Image/Zombies/Gargantuar/Gargantuar_light.gif"));
                else{
                    setImage(new ImageIcon("Image/Zombies/Gargantuar/Gargantuar.gif"));
                }
            }
            else{
                if (getZombieHit())
                    setImage(new ImageIcon("Image/Zombies/Gargantuar/Gargantuar_eating_light.gif"));
                else
                    setImage(new ImageIcon("Image/Zombies/Gargantuar/Gargantuar_eating.gif"));        
            }
        }
        else{
            if (!getZombieEating()){
                if (getZombieHit())
                    setImage(new ImageIcon("Image/Zombies/Gargantuar/Gargantuar_blue_light.gif"));
                else
                    setImage(new ImageIcon("Image/Zombies/Gargantuar/Gargantuar_blue.gif"));
            }
            else{
                if (getZombieHit())
                    setImage(new ImageIcon("Image/Zombies/Gargantuar/Gargantuar_blue_eating_light.gif"));
                else
                    setImage(new ImageIcon("Image/Zombies/Gargantuar/Gargantuar_blue_eating.gif"));                
            }
        }
        if (getHealth() <= 0){
            if (getZombieExplore()){
                setImage(new ImageIcon("Image/Zombies/Gargantuar/Gargantuar_Boomed.gif"));
                if (getDeathDelay() <= getDeath2()){
                    setDeathDelay(getDeathDelay() + 1);
                }
            }
            else{
                setImage(new ImageIcon("Image/Zombies/Gargantuar/Gargantuar_die.gif"));
                if (getDeathDelay() <= getDeath1())
                    setDeathDelay(getDeathDelay() + 1);
            }
        }
    

    }
}
