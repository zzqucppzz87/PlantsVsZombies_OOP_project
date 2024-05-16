package Plants;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Control.AudioPlayer;
import Control.GamePanel;
import Zombies.Zombies;

public class PotatoMine extends Plants{

    private int exploreDelay2 = -1;

    private AudioPlayer audioPlayer;

    public PotatoMine(int plantDamage, int plantHealth,double x,double y,int xBackyard,int yBackyard,AudioPlayer audioPlayer) {
        super(plantDamage, plantHealth,x,y,new ImageIcon("Image/Plants/potato_mine.gif"),new ImageIcon("Image/Cards/potato_mine_price.png"),"PotatoMine",25,xBackyard,yBackyard,new ImageIcon("Image/Cards/potato_mine_price_delay.png"),1500);
        this.audioPlayer = audioPlayer;
    }

    public void draw(GamePanel panel,Graphics g){
        if (isImageActive()){
            getImage().paintIcon(panel, g ,(int) getCurrentPoint().getX(),(int) getCurrentPoint().getY());
        }
        else{
            if (exploreDelay2 >= 0 && exploreDelay2 <= 20){
                getImage().paintIcon(panel, g ,(int) getCurrentPoint().getX() - 20,(int) getCurrentPoint().getY());
            }            
        }
    }

    public void explorePlants(ArrayList<Zombies> zombies,ArrayList<Plants> list){
        if (exploreDelay2 >= 0 && exploreDelay2 <= 20){
            exploreDelay2++;
        }
        if (this.getExploreDelay() >= 5*50 && !explore){
            for(Zombies i : zombies){
                if (i.isImageActive())
                    if (i.getXCoordinate() <= 1066)
                        if (checkEffect(i)){
                            i.setHealth(i.getHealth() - this.getDamage());
                            i.setZombieExplore(true);
                            this.setImageActive(false);
                            explore = true;
                            list.add(this);
                            this.setPos(-500, -500);
                            exploreDelay2 = 0;
                            AudioPlayer sound = new AudioPlayer("potato_mine",2);
                            sound.setVolume(audioPlayer.getVolume());
                            if (!audioPlayer.getEffectMute()){
                                sound.playEffect();
                            }
                            break;
                        }
            }
        }
    }    

    public boolean checkEffect(Zombies zombies){
        if (this.getHitBox().intersects(zombies.getHitBox())){
            if (this.getYBackyard() == zombies.getYBackyard()){
                return true;
            }
        }
        return false;
    }

    public void animatedImage() {
        if (getExploreDelay() < 5*50){
            if (getHealth() > 0 )
                if (getPlantHit()){
                    setImage(new ImageIcon("Image/Plants/potato_mine_eating.gif"));
                }
                else{
                    setImage(new ImageIcon("Image/Plants/potato_mine.gif"));
                }
            else{
                
                setDeathDelay(getDeathDelay() + 1);
            }
        }
        else{
            if (explore){
                setImage(new ImageIcon("Image/Plants/potato_mine_bomb.gif"));
            }
            else
                setImage(new ImageIcon("Image/Plants/potato_mine_red.gif"));
        }
    }
}
