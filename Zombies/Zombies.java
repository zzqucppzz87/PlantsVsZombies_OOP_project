package Zombies;

import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

import Control.AudioPlayer;
import Entity.AnimatedImage;
import Entity.Projectile;

public class Zombies extends Projectile implements AnimatedImage{
    private int zombieDamage;
    private int zombieHealth;
    private String zombieName;
    private double zombieFirstSpeed;
    private int effectedZombieDelay = 0;

    private int eatingDelay = 0;
    private boolean zombieEating = false;

    private int deathDelay = 0;
    private int death1 = 50;
    private int death2 = 70;

    private int hit = 0;
    private boolean zombieHit = false;
    private boolean zombieExplore = false;

    private int zombieFirstHealth;

    private int check = 0;

    private boolean eating = false;

    private boolean stopMotion = false;
    protected static boolean gameOver = false;
    private ImageIcon image;

    private AudioPlayer sound;

    public Zombies(double zombieSpeed,int zombieHealth,int zombieDamage,double x,double y,ImageIcon image,int xBackyard,int yBackyard,String name){
        super(x,y,zombieSpeed,image.getIconWidth(),image.getIconHeight(),xBackyard,yBackyard);
        setHitBox(new Rectangle2D.Double((double) x + image.getIconWidth()/2,y,(double) image.getIconWidth()/2,image.getIconHeight()));
        this.zombieHealth = zombieHealth;
        this.zombieDamage = zombieDamage;
        this.image = image;
        this.zombieFirstHealth = zombieHealth;
        this.zombieFirstSpeed = zombieSpeed;
        this.zombieName = name;
        sound = new AudioPlayer("eating",2);
    }

    public void update(){
        updateXCoordinate();
        updateZombieHit();
        animatedImage();
    }

    public void updateZombieHit(){
        if (zombieHit){
            hit++;
            if (hit == 20){
                zombieHit = false;
                hit = 0;
            }
        }
        if (zombieEating){
            eatingDelay++;
            if (eatingDelay > 100){
                eatingDelay = 0;
                zombieEating = false;
            }
        }
    }

    public void updateXCoordinate(){
        if (effectedZombieDelay != 0)
            effectedZombieDelay++;
        if (effectedZombieDelay == 400){
            if (getSpeed() != 0){
                setEffectedZombieDelay(0);
                setSpeed(zombieFirstSpeed);
            }
            else{
                setEffectedZombieDelay(1);
                setSpeed(zombieFirstSpeed/2);                
            }
        }

        if (isImageActive()){
            if (!stopMotion){
                setXCoordinate(getXCoordinate() + getSpeed());
                this.updatePos();
            }
        }
        else
            {
                if (getZombieExplore()){
                    if (getDeathDelay() > getDeath2()){
                        setXCoordinate(3000);
                        setPos(getXCoordinate(), getYCoordinate());
                    }
                }
                else{
                    if (getDeathDelay() > getDeath1()){
                        setXCoordinate(3000);
                        setPos(getXCoordinate(), getYCoordinate());
                    }
                }   
            }
    }

    public void updateAudioEffects(AudioPlayer audioPlayer){
        sound.setVolume(audioPlayer.getVolume());
        if (!audioPlayer.getEffectMute())
            if (isImageActive()){
                if (stopMotion){
                    if (eating == false){
                        sound.playEffect();
                        eating = true;
                    }
                    if (sound.getEffects().getMicrosecondPosition() == sound.getEffects().getMicrosecondLength()){
                        eating = false;
                    }
                }
                else{
                    sound.getEffects().stop();
                    eating = false;
                }
            }
            else{
                sound.getEffects().stop();
                eating = false;            
            }
    }


    public ImageIcon getImage(){
        return image;
    }

    public void setImage(ImageIcon image){
        this.image = image;
    }

    public int getHealth(){
        return zombieHealth;
    }

    public int getDamage(){
        return zombieDamage;
    }

    public void zombieHit(int damage){
        if (isImageActive()){
            zombieHealth -= damage;
        }
    }

    public void setStopMotion(boolean stop){
        this.stopMotion = stop;
    }

    public boolean checkGameOver(){
        if (getHitBox().getX() <= 150)
            return true;
        return false;
    }

    public int getCheck(){
        return check;
    }

    public void setCheck(int check){
        this.check = check;
    }

    public int getFirstHealth(){
        return zombieFirstHealth;
    }

    public void setHealth(int health){
        this.zombieHealth = health;
    }

    public double getFirstSpeed(){
        return zombieFirstSpeed;
    }

    public int getEffectedZombieDelay(){
        return effectedZombieDelay;
    }

    public void setEffectedZombieDelay(int delay){
        this.effectedZombieDelay = delay;
    }

    @Override
    public void animatedImage() {

    }

    public int getDeathDelay(){
        return deathDelay;
    }

    public void setDeathDelay(int delay){
        this.deathDelay = delay;
    }

    public int getDeath1(){
        return death1;
    }

    public void setDeath1(int num){
        this.death1 = num;
    }

    public int getDeath2(){
        return death2;
    }
    
    public void setDeath2(int num){
        this.death2 = num;
    }

    public void setZombieHit(boolean active){
        this.zombieHit = active;
    }

    public boolean getZombieHit(){
        return zombieHit;
    }

    public void setZombieExplore(boolean active){
        this.zombieExplore = active;
    }

    public boolean getZombieExplore(){
        return zombieExplore;
    }

    public void setHit(int num){
        this.hit = num;
    }

    public AudioPlayer getSound(){
        return sound;
    }

    public String getName(){
        return zombieName;
    }

    public void setEatingDelay(Boolean active){
        this.zombieEating = active;
    }

    public boolean getZombieEating(){
        return zombieEating;
    }

    public int getEatingDelay(){
        return eatingDelay;
    }

    public boolean getStopMotion(){
        return stopMotion;
    }

}
