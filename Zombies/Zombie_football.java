package Zombies;

import javax.swing.ImageIcon;

public class Zombie_football extends Zombies {

    
    public Zombie_football(double zombieSpeed, int zombieHealth, int zombieDamage ,double xCoordinate, double yCoordinate,int xBackyard,int yBackyard) {
        super(zombieSpeed, zombieHealth, zombieDamage ,xCoordinate, yCoordinate,new ImageIcon("Image/Zombies/Zombie_football/zombie_football_1.gif"),xBackyard,yBackyard,"Football");
    }
    
    public void animatedImage() {
        if (getEffectedZombieDelay() == 0){
            if (getHealth() > 700 + 181){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_eating_light.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_light_1.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_eating.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_1.gif"));

            }
            else if (getHealth() <= 700 + 181 && getHealth() > 181 ){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_eating_light.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_light_2.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_eating.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_2.gif"));
            }
            else if(getHealth() <= 181 && getHealth() > 0){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_eating_light.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_light_3.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_eating.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_3.gif"));
            }
        }
        else {
            if (getHealth() > 700 + 181){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_eating_light.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_light_1.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_eating.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_1.gif"));
            }
            else if (getHealth() <= 700 + 181 && getHealth() > 181 ){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_eating_light.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_light_2.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_eating.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_2.gif"));
            }
            else if(getHealth() <= 181 && getHealth() > 0){
                if (getZombieHit())
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_eating_light.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_light_3.gif"));
                else
                    if (getStopMotion())
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_eating.gif"));
                    else
                        setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_blue_3.gif"));
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
                setImage(new ImageIcon("Image/Zombies/Zombie_football/zombie_football_die.gif"));
                if (getDeathDelay() <= getDeath1())
                    setDeathDelay(getDeathDelay() + 1);
            }
        }
    }
}
