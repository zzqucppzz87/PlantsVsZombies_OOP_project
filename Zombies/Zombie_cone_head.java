package Zombies;

import javax.swing.ImageIcon;

public class Zombie_cone_head extends Zombies{

    public Zombie_cone_head(double zombieSpeed, int zombieHealth, int zombieDamage ,double xCoordinate, double yCoordinate,int xBackyard,int yBackyard) {
        super(zombieSpeed, zombieHealth, zombieDamage ,xCoordinate, yCoordinate,new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_1.gif"),xBackyard,yBackyard,"Cone_head");
    
        setDeath1(35);
    }

    public void animatedImage() {
        if (getEffectedZombieDelay() == 0){
            if (getHealth() > 185 + 181){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_eating_light_1.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_light_1.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_eating_1.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_1.gif"));
           
            }
            else if (getHealth() <= 185 + 181 && getHealth() > 181 ){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_eating_light_2.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_light_2.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_eating_2.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_2.gif"));
            }
            else if(getHealth() <= 181 && getHealth() > 0){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_eating_light_3.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_light_3.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_eating_3.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_3.gif"));
            }
        }
        else{
            if (getHealth() > 370){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_eating_light_1.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_light_1.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_eating_1.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_1.gif"));

            }
            else if (getHealth() <= 370 && getHealth() > 181 ){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_eating_light_2.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_light_2.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_eating_2.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_2.gif"));
            }
            else if(getHealth() <= 181 && getHealth() > 0){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_eating_light_3.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_light_3.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_eating_3.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_blue_3.gif"));
            }
        }
        if (getHealth() <= 0){
            if (getZombieExplore()){
                setImage(new ImageIcon("Image/Zombies/zombie_Boomed.gif"));
                if (getDeathDelay() <= getDeath2())
                    setDeathDelay(getDeathDelay() + 1);
            }
            else{
                setImage(new ImageIcon("Image/Zombies/Zombie_conehead/zombie_conehead_die.gif"));
                if (getDeathDelay() <= getDeath1())
                    setDeathDelay(getDeathDelay() + 1);
            }
        }
    }


}
