package Plants;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Control.GamePanel;
import Entity.AnimatedImage;
import Entity.Projectile;
import Zombies.Zombies;

public class Plants extends Projectile implements AnimatedImage{
    private int plantDamage;
    private int plantHealth;
    protected static int plantCount = 0;
    private String name;
    private int plantsValue;

    protected boolean plantHit = false;
    protected int hit = 0;
   
    private boolean checkDelay = false;
    private int constDelay;
    private int delay = 0;
    private ImageIcon imageDelay;

    private int deathDelay = 0;

    private int finalDeath = 50;

    private int exploreDelay = 0;
    protected boolean explore = false;

    private ImageIcon image;
    private ImageIcon cardImage;
    private Point imageCorner;
    private Point imageFirstPoint;
    private Point currentPoint;
    private int check = 0;

    public Plants(int plantDamage,int plantHealth,double x,double y,ImageIcon image,ImageIcon cardImage,String name,int plantsValue,int xBackyard,int yBackyard,ImageIcon imageDelay,int constDelay){
        super(x,y,0,image.getIconWidth(),image.getIconHeight(),xBackyard,yBackyard);
        this.plantDamage = plantDamage;
        this.plantHealth = plantHealth;
        this.image = image;
        this.cardImage = cardImage;
        this.name = name;
        this.imageCorner = new Point((int) x, (int) y);
        this.imageFirstPoint = new Point((int) x, (int) y);
        this.currentPoint = new Point((int) x, (int) y);
        this.plantsValue = plantsValue;
        this.imageDelay = imageDelay;
        this.constDelay = constDelay;
    }

    public void draw(GamePanel panel,Graphics g){
        if (isImageActive()){
            getImage().paintIcon(panel, g ,(int) currentPoint.getX(),(int) currentPoint.getY());
        }
    }

    public void update(){
        if (plantHit){
            hit++;
            if (hit == 20){
                plantHit = false;
                hit = 0;
            }
        }
        if (!getExplore())
            exploreDelay++;
        if (getHealth() <= 0){
            setImageActive(false);
        }
        animatedImage();
    }

    public int getDamage(){
        return plantDamage;
    }

    public int getHealth(){
        return plantHealth;
    }

    public void setHealth(int health){
        this.plantHealth = health;
    }

    public ImageIcon getImage(){
        return image;
    }

    public void setImage(ImageIcon image){
        this.image = image;
    }

    public int getPlantsValue(){
        return plantsValue;
    }

    public Point getImageFirstPoint(){
        return imageFirstPoint;
    }

    public Point getImageCorner(){
        return imageCorner;
    }

    public void setImageCorner(Point point){
        this.imageCorner = point;
    }

    public Point getCurrentPoint(){
        return currentPoint;
    }

    public void setCurrentPoint(Point point){
        this.currentPoint = point;
    }

    public String getName(){
        return name;
    }

    public ImageIcon getCardImage(){
        return cardImage;
    }

    public void plantHit(Zombies zombies,ArrayList<Plants> list){
        if (isImageActive()){
            if (zombies.getName().equals("Gargantuar")){
                zombies.setEatingDelay(true);
                if (zombies.getEatingDelay() == 100){
                    plantHealth -= zombies.getDamage();
                }        
            }
            else{
                setPlantHit(true);
                setHit(0);
                plantHealth -= zombies.getDamage();
            }
        }
    }

    public ImageIcon getImageDelay(){
        return imageDelay;
    }

    public int getConstDelay(){
        return constDelay;
    }

    public void setConstDelay(int delay){
        this.constDelay = delay;
    }

    public int getDelay(){
        return delay;
    }

    public void setDelay(int delay){
        this.delay = delay;
    }

    public boolean getCheckDelay(){
        return checkDelay;
    }

    public void setCheckDelay(boolean active){
        this.checkDelay = active;
    }

    public void explorePlants(ArrayList<Zombies> zombies,ArrayList<Plants> list){

    }

    public int getExploreDelay(){
        return exploreDelay;
    }

    public void setExploreDelay(int delay){
        this.exploreDelay = delay;
    }

    public void animatedImage() {

    }

    public int getDeathDelay(){
        return deathDelay;
    }

    public void setDeathDelay(int delay){
        this.deathDelay = delay;
    }

    public boolean getExplore(){
        return explore;
    }

    public void setPlantHit(boolean active){
        this.plantHit = active;
    }

    public boolean getPlantHit(){
        return plantHit;
    }

    public void setHit(int num){
        this.hit = num;
    }

    public int getFinalDeath(){
        return finalDeath;
    }

    public void setFinalDeath(int num){
        this.finalDeath = num;
    }

    public int getCheck(){
        return check;
    }

    public void setCheck(int num){
        this.check = num;
    }

}
