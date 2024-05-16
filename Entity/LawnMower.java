package Entity;

import javax.swing.ImageIcon;

import Control.AudioPlayer;

public class LawnMower extends Projectile{

    private AudioPlayer sound;

    private boolean run = false;

    private static ImageIcon image = new ImageIcon("Image/Others/lawn_mower.gif");
    private static final int WIDTH = image.getIconWidth();
    private static final int HEIGHT = image.getIconHeight();

    public LawnMower(double x,double y,int xBackyard,int yBackyard){
        super(x - WIDTH/2,y - HEIGHT/2,10,WIDTH,HEIGHT,xBackyard,yBackyard);
    
        sound = new AudioPlayer("lawn_mower",2);
    }

    public void updateLawnMower(){
        if(!isImageActive()){
            if (getXCoordinate() <= 1000){
                setXCoordinate(getXCoordinate() + getSpeed());
                updatePos();
            }
            else
                setPos(0, getYCoordinate());

        }
    }

    public void updateAudioEffects(AudioPlayer audioPlayer){
        sound.setVolume(audioPlayer.getVolume());
        if (!isImageActive()){
            if (getXCoordinate() <= 1000){
                if (run == false){
                    sound.playEffect();
                    run = true;
                }
                if (sound.getEffects().getMicrosecondPosition() == sound.getEffects().getMicrosecondLength()){
                    run = false;
                }
            }
            else{
                sound.getEffects().stop();
                run = false;                
            }
        }
    }

    public ImageIcon getImage(){
        return image;
    }

    public AudioPlayer getSound(){
        return sound;
    }    

}
