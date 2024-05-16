package Zombies;

import javax.swing.ImageIcon;

public class Zombie_normal extends Zombies{
    
    public Zombie_normal(double zombieSpeed, int zombieHealth, int zombieDamage ,double xCoordinate, double yCoordinate,int xBackyard,int yBackyard) {
        super(zombieSpeed, zombieHealth, zombieDamage ,xCoordinate, yCoordinate,new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_1.gif"),xBackyard,yBackyard,"Normal");
    }

    public void animatedImage() {
        if (getEffectedZombieDelay() == 0){       
            if (getHealth() <= 181 && getHealth() > 100){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_eating_light_1.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_light_1.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_eating_1.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_1.gif"));
            }
            else if(getHealth() <= 100 && getHealth() > 0){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_eating_light_2.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_light_2.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_eating_2.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_2.gif"));
            }
        }
        else{
            if (getHealth() <= 181 && getHealth() > 100){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_blue_eating_light_1.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_blue_light_1.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_blue_eating_1.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_blue_1.gif"));
            }
            else if(getHealth() <= 100 && getHealth() > 0){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_blue_eating_light_2.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_blue_light_2.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_blue_eating_2.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_blue_2.gif"));
            }
        }
        if (getHealth() <= 0){
            if (getZombieExplore()){
                setImage(new ImageIcon("Image/Zombies/zombie_Boomed.gif"));
                if (getDeathDelay() <= getDeath2()){
                    setDeathDelay(getDeathDelay() + 1);
                }
            }
            else{
                setImage(new ImageIcon("Image/Zombies/Zombie_normal/zombie_normal_die.gif"));
                if (getDeathDelay() <= getDeath1())
                    setDeathDelay(getDeathDelay() + 1);
            }
        }



    }


}

