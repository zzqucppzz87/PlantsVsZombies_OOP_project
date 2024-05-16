package Plants;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Control.AudioPlayer;
import Control.GamePanel;
import Zombies.Zombies;

public class Jalapeno extends Plants{
    private int exploreDelay2 = 0;
    private AudioPlayer audioPlayer;

    public Jalapeno(int plantDamage, int plantHealth, double x, double y, int xBackyard, int yBackyard,AudioPlayer audioPlayer) {

        super(plantDamage, plantHealth,x,y,new ImageIcon("Image/Plants/jalapeno.gif"),new ImageIcon("Image/Cards/cherry_bomb_price.png"),"Jalapeno",100,xBackyard,yBackyard,new ImageIcon("Image/Cards/cherry_bomb_price_delay.png"),1500);

        this.audioPlayer = audioPlayer;
        setFinalDeath(25);
    }

    public void draw(GamePanel panel,Graphics g){
        if (isImageActive()){
            getImage().paintIcon(panel, g ,(int) getCurrentPoint().getX(),(int) getCurrentPoint().getY());
        }
        else{
            if (exploreDelay2 >= getFinalDeath() + 1 && exploreDelay2 <= getFinalDeath() + 100){
                g.drawImage(new ImageIcon("Image/Plants/fire.gif").getImage(),270,(int) getCurrentPoint().getY() + 60, null);
            }            
        }
    }

    public void explorePlants(ArrayList<Zombies> zombies, ArrayList<Plants> list){
        if (exploreDelay2 >= getFinalDeath() + 1 && exploreDelay2 <= getFinalDeath() + 100){
            exploreDelay2++;
        }
        if (this.getExploreDelay() == 1){
            setImage(new ImageIcon("Image/Plants/jalapeno_effect.gif"));
        }
        if (this.getExploreDelay() == getFinalDeath() - 10){
            setImage(new ImageIcon("Image/Plants/ja-explode-smoke.gif")); 
        }
        if (this.getExploreDelay() == getFinalDeath() + 1){
            explore = true;
        }  
        if (this.getExploreDelay() >= getFinalDeath() && !explore){
            for(Zombies i : zombies){
                if (i.isImageActive())
                    if (i.getXCoordinate() <= 1066)
                        if (getYBackyard() == i.getYBackyard()){
                            i.setZombieHit(true);
                            i.setHit(0);
                            i.setHealth(i.getHealth() - this.getDamage());
                            if (i.getHealth() <= 0)
                                i.setZombieExplore(true);
                            i.setEffectedZombieDelay(0);
                            i.setSpeed(i.getFirstSpeed());
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

            exploreDelay2 = getFinalDeath() + 1;
            
        }
    }    
}
