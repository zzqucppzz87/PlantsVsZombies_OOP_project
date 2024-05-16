package Plants;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Control.AudioPlayer;
import Control.GamePanel;
import Zombies.Zombies;

public class IceShroom extends Plants{

    private int exploreDelay2 = -1;
    private AudioPlayer audioPlayer;

    public IceShroom(int plantDamage, int plantHealth, double x, double y, int xBackyard, int yBackyard,AudioPlayer audioPlayer) {

        super(plantDamage, plantHealth,x,y,new ImageIcon("Image/Plants/Ice_Shroom.gif"),new ImageIcon("Image/Cards/cherry_bomb_price.png"),"IceShroom",150,xBackyard,yBackyard,new ImageIcon("Image/Cards/cherry_bomb_price_delay.png"),1500);

        this.audioPlayer = audioPlayer;
        setFinalDeath(60);
    }

    public void draw(GamePanel panel,Graphics g){
        if (isImageActive()){
            getImage().paintIcon(panel, g ,(int) getCurrentPoint().getX(),(int) getCurrentPoint().getY());
        }
        else{
            if (exploreDelay2 >= 0 && exploreDelay2 <= 150){
                g.setColor(new Color(215, 255, 255, exploreDelay2));
                g.fillRect(0, 0, 1066, 600);
            }
        }
    }

    public void explorePlants(ArrayList<Zombies> zombies, ArrayList<Plants> list){
        if (exploreDelay2 >= 0 && exploreDelay2 <= 150){
            exploreDelay2 += 30;
        }
        if (this.getExploreDelay() == getFinalDeath() + 1){
            explore = true;
        }
        if (this.getExploreDelay() >= getFinalDeath() && !explore){
            for(Zombies i : zombies){
                if (i.isImageActive())
                    if (i.getXCoordinate() <= 1066){
                        i.setHealth(i.getHealth() - this.getDamage());
                        i.setSpeed(0);
                        i.setEffectedZombieDelay(1);
                }
            }
            
            this.setImageActive(false);
            
            list.add(this);
            this.setPos(-500, -500);
            //explore = true;
            
            AudioPlayer sound = new AudioPlayer("cherry_bomb",2);
            sound.setVolume(audioPlayer.getVolume());
            if (!audioPlayer.getEffectMute()){
                sound.playEffect();
            }

            exploreDelay2 = 0;
            
        }

    }
}
