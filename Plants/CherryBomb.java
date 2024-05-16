package Plants;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import Control.AudioPlayer;
import Zombies.Zombies;

public class CherryBomb extends Plants {

    private boolean audioActive = false;

    private AudioPlayer audioPlayer;


    public CherryBomb(int plantDamage, int plantHealth,double x,double y,int xBackyard,int yBackyard, AudioPlayer audioPlayer) {
        super(plantDamage, plantHealth,x,y,new ImageIcon("Image/Plants/cherry_bomb.gif"),new ImageIcon("Image/Cards/cherry_bomb_price.png"),"CherryBomb",150,xBackyard,yBackyard,new ImageIcon("Image/Cards/cherry_bomb_price_delay.png"),2500);
        
        this.audioPlayer = audioPlayer;
        setFinalDeath(40);
    }

    public void explorePlants(ArrayList<Zombies> zombies, ArrayList<Plants> list){
        if (this.getExploreDelay() == getFinalDeath() + 1){
            explore = true;
        }
        if (this.getExploreDelay() >= getFinalDeath() && !explore){
            for(Zombies i : zombies){
                if (checkEffect(i)){
                    i.setHealth(i.getHealth() - this.getDamage());
                    if (i.getHealth() <= 0)
                        i.setZombieExplore(true);
                }
            }
            this.setImageActive(false);
            list.add(this);
            this.setPos(-500, -500);
            
            AudioPlayer sound = new AudioPlayer("cherry_bomb",2);
            sound.setVolume(audioPlayer.getVolume());
            if (!audioPlayer.getEffectMute() && !audioActive){
                sound.playEffect();
                audioActive = true;
            }  
        }
    }

    public boolean checkEffect(Zombies zombies){
        if (zombies.isImageActive())
            if (zombies.getYBackyard() <= this.getYBackyard() + 100 && zombies.getYBackyard() >= this.getYBackyard() - 100)
                if (zombies.getXCoordinate() <= this.getXBackyard() + 80 && zombies.getXCoordinate() + zombies.getImage().getIconWidth() >= this.getXBackyard() - 80)
                    return true;
        return false;
    }
}
