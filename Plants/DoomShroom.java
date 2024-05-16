package Plants;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Control.AudioPlayer;
import Control.GamePanel;
import Zombies.Zombies;

public class DoomShroom extends Plants{

    private int exploreDelay2 = 0;
    private AudioPlayer audioPlayer;

    public DoomShroom(int plantDamage, int plantHealth, double x, double y, int xBackyard, int yBackyard,AudioPlayer audioPlayer) {

        super(plantDamage, plantHealth,x,y,new ImageIcon("Image/Plants/Doom_Shroom.gif"),new ImageIcon("Image/Cards/cherry_bomb_price.png"),"DoomShroom",150,xBackyard,yBackyard,new ImageIcon("Image/Cards/cherry_bomb_price_delay.png"),1500);

        this.audioPlayer = audioPlayer;
        setFinalDeath(50);
    }
    
    public void draw(GamePanel panel,Graphics g){
        if (isImageActive()){
            getImage().paintIcon(panel, g ,(int) getCurrentPoint().getX(),(int) getCurrentPoint().getY());
            if (exploreDelay2 >= getFinalDeath() + 1 && exploreDelay2 <= getFinalDeath() + 35){
                g.drawImage(new ImageIcon("Image/Plants/DoomShroomEffect.gif").getImage(),(int) getCurrentPoint().getX() - 10,(int) getCurrentPoint().getY() - 80, null);
            }
        }
    }

    public void explorePlants(ArrayList<Zombies> zombies, ArrayList<Plants> list){
        if (this.getExploreDelay() == getFinalDeath() + 1){
            explore = true;
            this.setPos(-500, -500);
            exploreDelay2 = getFinalDeath() + 1;
            this.setExploreDelay(getExploreDelay() + 1);
        }
        if (exploreDelay2 >= getFinalDeath() + 1 && exploreDelay2 < 50*15){
            exploreDelay2++;
        }
        if (exploreDelay2 == 50*10){
            setImage(new ImageIcon("Image/Plants/hole2.png"));
        }
        if (this.getExploreDelay() >= getFinalDeath() && !explore){
            setImage(new ImageIcon("Image/Plants/hole1.png"));
            for(Zombies i : zombies){
                if (i.isImageActive())
                    if (i.getXCoordinate() <= 1066){
                        i.setHealth(i.getHealth() - this.getDamage());
                        if (i.getHealth() <= 0)
                            i.setZombieExplore(true);
                    }
            }
            
            AudioPlayer sound = new AudioPlayer("cherry_bomb",2);
            sound.setVolume(audioPlayer.getVolume());
            if (!audioPlayer.getEffectMute()){
                sound.playEffect();
            }
            
        }
        if (exploreDelay2 == 15*50){
            this.setImageActive(false);
            list.add(this);
        }
    }


}

