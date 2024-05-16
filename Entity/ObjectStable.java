package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import Control.AudioPlayer;
import Control.GamePanel;
import GameState.PlayingState.Playing;
import Plants.Pea;
import Plants.Plants;
import Zombies.Gargantuar;
import Zombies.Zombie_bucket_head;
import Zombies.Zombie_cone_head;
import Zombies.Zombie_football;
import Zombies.Zombie_normal;
import Zombies.Zombies;

public class ObjectStable {
    private static ArrayList<Zombies> zombiesList;
    private static ArrayList<LawnMower> lawnMowersList;
    private static ArrayList<Plants> removeList;

    private static ArrayList<AudioPlayer> audioList;

    private boolean gameOver = false;
    private boolean checkFinal = false;
    private int finalDelay = 0;
    private int delayZombies = 0;
    private int numTurn;
    private int numFinal;
    private int[] list1 = new int[3];
    private int percent = 0;
    private double percent1 = 0;

    private Random random;

    private int n = 0;

    private AudioPlayer audioPlayer;

    
    public ObjectStable(AudioPlayer audioPlayer){ 
        zombiesList = new ArrayList<Zombies>();
        lawnMowersList = new ArrayList<LawnMower>();
        removeList = new ArrayList<>();

        audioList = new ArrayList<>();

        random = new Random();
        updateLawnMowerBackyard();

        this.audioPlayer = audioPlayer;

    }
    
    public void zombiesListName(int i,int j,int z){
        switch(j){
            case 0:{
                Zombies zombies = new Zombie_normal(- 80 / (4.7 * 50), 181,2, 1200 + z*50, i*100 + 20,1000, 130 + i*100);
                zombiesList.add(zombies);
                audioList.add(zombies.getSound());
                break;
            }
            case 1:{
                Zombies zombies = new Zombie_cone_head(- 80 / (4.7 * 50), 551, 2,1200 + z*50, i*100 + 20,1000, 130 + i*100);
                zombiesList.add(zombies);
                audioList.add(zombies.getSound());
                break;
            }
            case 2:{
                Zombies zombies = new Zombie_bucket_head(- 80 / (4.7 * 50), 1281, 2,1200 + z*50, i*100 + 20,1000, 130 + i*100);
                zombiesList.add(zombies);
                audioList.add(zombies.getSound());
                break;
            }
            case 3:{
                Zombies zombies = new Zombie_football(- 80 / (2.5 * 50), 1581, 2,1200 + z*50, i*100 + 20,1000, 130 + i*100);
                zombiesList.add(zombies);
                audioList.add(zombies.getSound());
                break;                
            }
            case 4:{
                Zombies zombies = new Gargantuar(- 80 / (2.5 * 50), 3000, 5000,1200 + z*50, i*100 - 20,1000, 130 + i*100);
                zombiesList.add(zombies);
                audioList.add(zombies.getSound());
                break; 
            }                
        }
    }

    public void addTurnZombies(int numBegin,int numEnd,int typeBegin,int typeEnd){
        int k = numBegin + random.nextInt(numEnd - numBegin + 1);
        int z = 0;
        while (z < k){
            int i = random.nextInt(5);
            int j = typeBegin + random.nextInt(typeEnd - typeBegin + 1);
            zombiesListName(i, j, 0);
            z++;
        }
    }
    public void addFinalZombies(int num,int limitType){      
        int a = num/5;
        int b = num % 5;
        int[] list1 = {a,a,a,a,a};

        for(int i = 0; i < b;i++)
            list1[i]++;
        
        int[] list2 = {-1,-1,-1,-1,-1};
        for(int i = 0;i < 5;i++){
            while(true){
                int j = random.nextInt(5);
                if (list2[j] == -1){
                    list2[j] = list1[i];
                    break;
                }
            }
        }
        int limit = limitType;
        int count = 0;
        int test;
        if (b == 0)
            test = a;
        else
            test = a + 1;
        for(int k = 0; k < test;k++){
            for(int i = 0; i < 5;i++){
                if (k < list2[i]){                    
                    if (count < limit){
                        zombiesListName(i, count,k);
                        count++;
                    }
                    else{
                        int j = random.nextInt(limit);
                        zombiesListName(i,j,k);
                    }
                }
            }
        }
    }



    public boolean readyToAddZombies(){        
        for(Zombies i : zombiesList){
            if (i.getHealth() <= (int) i.getFirstHealth()/2 && i.getCheck() == 0){
                for(Zombies j : zombiesList){
                    j.setCheck(1);
                }
                return true; 
            }
        }
        return false;
    }

    public boolean checkFinal(){
        for(Zombies i : zombiesList){
            if (i.getHealth() > 0){
                return false;
            }
        }
        return true;
    }

    public void updateTestZombies(int level,int numTurn,int numFinal){
        int[] list = new int[3];
        for(int i = 1; i < 3 - 1;i++){
            list[i] = numTurn / 3;
            this.list1[i] = numTurn / 3;
        }
        list[3 - 1] = numTurn / (3 + 1);
        list1[3-1] = numTurn / (3 + 1);
        list[0] = numTurn;
        list1[0] = numTurn;
        for(int i = 1; i < 3;i++){
            list[0] -= list[i];
        }
        this.numTurn = numTurn;
        this.numFinal = numFinal;
        if (zombiesList.size() + n < numTurn){
            if (delayZombies < 200){
                delayZombies += 1;
            }
            else{
                if (zombiesList.size() + n == 0){
                    addTurnZombies(1,1, 0, 0);
                }
                else if (zombiesList.size() + n < 3){
                    if (readyToAddZombies())
                        addTurnZombies(1,2, 0, 1);                   
                }
                else if (zombiesList.size() + n >= 3 && zombiesList.size() + n < list[0]){
                    if (readyToAddZombies()){
                        if (level == 1)
                            addTurnZombies(1,3, 0, 2);
                        if (level == 2)
                            addTurnZombies(1,4, 0, 2);
                        if (level == 3)
                            addTurnZombies(2,4, 0, 2);
                    }
                }
                else if (zombiesList.size() + n >= list[0] && zombiesList.size() + n < list[1] + list[0]){
                    if (readyToAddZombies()){
                        if (level == 1)
                            addTurnZombies(1,3, 1, 2);
                        if (level == 2)
                            addTurnZombies(1,4, 1, 3);
                        if (level == 3)
                            addTurnZombies(3,5, 0, 3);
                    }
                }
                else if (zombiesList.size() + n >= list[1] + list[0] && zombiesList.size()  + n< numTurn){
                    if (readyToAddZombies()){
                        if (level == 1)
                            addTurnZombies(2,4, 0, 2);
                        if (level == 2)
                            addTurnZombies(2,5, 0, 3);
                        if (level == 3)
                            addTurnZombies(3,5, 1, 4);
                    }
                }

            }
        }
        else{
            if (checkFinal() && checkFinal == false){
                if (level == 1)
                    addFinalZombies(numFinal,3);
                if (level == 2)
                    addFinalZombies(numFinal, 4);
                if (level == 3)
                    addFinalZombies(numFinal, 5); 
                checkFinal = true;
            }
        }
    }

    public void updateRemove(){
        ArrayList<Zombies> list = new ArrayList<>();
        for(Zombies i : zombiesList){
            if (!i.isImageActive()){
                if (i.getZombieExplore()){
                    if  (i.getDeathDelay() <= i.getDeath2()){
                        list.add(i);
                    }
                }
                else{
                    if  (i.getDeathDelay() <= i.getDeath1()){
                        list.add(i);
                    }
                }
            }
            else {
                list.add(i);
            }
        }
        n += zombiesList.size() - list.size();

        zombiesList.clear();
        for(Zombies i : list){
            zombiesList.add(i);
        }
        
    }

    public void drawPercent(Graphics g){
        if (delayZombies >= 200 || checkFinal){
            if (zombiesList.size() + n <= numTurn){
                percent = (int) ((double) 210 * (zombiesList.size() + n) / numTurn);
                if (percent < 210)
                    if (percent1 <= percent)
                        percent1 += (double) 1 / 50;
                else
                    percent = 210;
                g.drawImage(new ImageIcon("Image/GUI/bar.png").getImage(),1000-210,565,null);
                g.setColor(Color.green);
                g.fillRect(1000 - (int) percent1  + 5, 570,(int) percent1 - 10, 15);
                g.drawImage(new ImageIcon("Image/GUI/flag.png").getImage(),1000-70,545,null);
                g.drawImage(new ImageIcon("Image/GUI/flag.png").getImage(),1000-140,545,null);
                g.drawImage(new ImageIcon("Image/GUI/flag.png").getImage(),1000-210,545,null);
                g.drawImage(new ImageIcon("Image/GUI/zom_head.png").getImage(),1000 - (int) percent1 - 20,555,null);
            }
            else{
                percent = 210;
                if (percent1 <= percent)
                    percent1 += (double) 1 / 50;
                else
                    percent1 = 210;
                g.fillRect(1000 - (int) percent1, 565, (int) percent1, 25);
                g.drawImage(new ImageIcon("Image/GUI/bar.png").getImage(),1000-210,565,null);
                g.setColor(Color.green);
                g.fillRect(1000 - (int) percent1  + 5, 570,(int) percent1 - 10, 15);
                g.drawImage(new ImageIcon("Image/GUI/flag.png").getImage(),1000-70,545,null);
                g.drawImage(new ImageIcon("Image/GUI/flag.png").getImage(),1000-140,545,null);
                g.drawImage(new ImageIcon("Image/GUI/flag.png").getImage(),1000-210,545,null);
                g.drawImage(new ImageIcon("Image/GUI/zom_head.png").getImage(),1000 - (int) percent1 - 20,555,null);
            }        
        }

    }

    public void drawFinal(Graphics g){
        if (checkFinal){
            finalDelay++;
            if (finalDelay <= 500 && finalDelay >= 200){
                g.drawImage(new ImageIcon("Image/Others/text.png").getImage(), 123, 280, null);
            }
            if (finalDelay == 200){
                AudioPlayer sound = new AudioPlayer("wave", 2);
                sound.setVolume(0.9f);
                if (!audioPlayer.getEffectMute()){
                    sound.playEffect();
                } 
            }

        }        
    }

    public void updateLawnMowerBackyard(){
        for(int i = 0; i < 5;i++){
            LawnMower lawnMower = new LawnMower(210, 130 + i*100,210,130 + i*100);
            lawnMowersList.add(lawnMower);
            audioList.add(lawnMower.getSound());
        }
    }

    public void drawLawnMower(GamePanel panel,Graphics g){
        for(LawnMower i : lawnMowersList)
            if (i.getXCoordinate() <= 1000)
                i.getImage().paintIcon(panel, g,(int) i.getXCoordinate(),(int) i.getYCoordinate());
    }

    //draw zombie in the screen
    public void drawZombies(GamePanel panel,Graphics g){
        for(Zombies i : zombiesList){
            if (i.isImageActive()){
                i.getImage().paintIcon(panel, g,(int) i.getXCoordinate(),(int) i.getYCoordinate());
            }
            else{
                if (i.getZombieExplore()){
                    if (i.getDeathDelay() <= i.getDeath2()){
                        i.getImage().paintIcon(panel, g,(int) i.getXCoordinate(),(int) i.getYCoordinate());
                    }
                }
                else{
                    if (i.getDeathDelay() <= i.getDeath1()){
                        i.getImage().paintIcon(panel, g,(int) i.getXCoordinate(),(int) i.getYCoordinate());
                    }
                }
            }
        }
    }

    public void updateZombies(Playing playing){
        for(Zombies i : zombiesList){
            i.update();
            if (i.getHealth() <= 0){
                i.setImageActive(false);
            }
        }
    }

    public void updateLawnMower(){
        for(LawnMower i : lawnMowersList){
            i.updateLawnMower();
            i.updateAudioEffects(audioPlayer);
        }
    }

    public void checkCollision(ArrayList<Plants> plantsList, ArrayList<Pea> peaUpdateList){
        for(Pea i : peaUpdateList){
            if (checkZombiesLine(zombiesList, i.getXFirstCoordinate() ,i.getYBackyard()) && !i.getPrepareStop()){
                i.setShootActive(true);
            }            
            else
                i.setShootActive(false);
        }

        for(Zombies i : zombiesList){
            if (checkInteract(plantsList, i))
                i.setStopMotion(true);
            else
                i.setStopMotion(false);
            i.updateAudioEffects(audioPlayer);
        }
        
        for(Zombies i : zombiesList){
            if (i.isImageActive()){
                if (i.getXCoordinate() <= 1066){
                    for(Plants j : plantsList){
                        if (i.getHitBox().intersects(j.getHitBox())){
                            if (j.getYBackyard() == i.getYBackyard()){
                                if (j.isImageActive()){
                                    j.plantHit(i,removeList);
                                }
                                else{
                                    j.setPos(-500, -500);
                                    removeList.add(j);
                                }
                            }
                        }
                    }
                    for(Pea k : peaUpdateList){
                        for(Plants m : removeList){
                            if (m.getXBackyard() == k.getXBackyard() && m.getYBackyard() == k.getYBackyard()){ 
                                k.setStop(true);
                            }
                        }
                        if (i.getHitBox().intersects(k.getHitBox())){
                            if (k.isImageActive()){
                                k.effectOnZombies(i);
                                

                                AudioPlayer sound = new AudioPlayer("hitting", 2);
                                sound.setVolume(audioPlayer.getVolume());
                                if (!audioPlayer.getEffectMute()){
                                    sound.playEffect();
                                }
                            }
                        }
                    }
                    for(LawnMower n: lawnMowersList){
                        if (n.getYBackyard() == i.getYBackyard())
                            if (i.getHitBox().intersects(n.getHitBox())){
                                i.setHealth(0);
                                n.setImageActive(false);
                            }
                    }
                    if (i.checkGameOver())
                        gameOver = true;
                }
            }
        }

        for(Plants i : plantsList){
            i.explorePlants(zombiesList, removeList);
        }
    }

    public boolean checkZombiesLine(ArrayList<Zombies> zombiesList,double x,int y){
        for(Zombies i : zombiesList){
            if (i.isImageActive())
                if (i.getYBackyard() == y && i.getXCoordinate() <= 1000){ 
                    if (x < i.getHitBox().getX() + i.getHitBox().getWidth())
                        return true;
                }
        }
        return false;
    }
    
    public void reset(){
        zombiesList.clear();
        lawnMowersList.clear();
        updateLawnMowerBackyard();
        delayZombies = 0;

        gameOver = false;
        checkFinal = false;
        finalDelay = 0;
        n = 0;
        percent1 = 0;
        percent = 0;
    }

    public boolean checkGameWin(){
        if (zombiesList.size() + n >= numFinal + numTurn){
            for(Zombies i : zombiesList){
                if (i.getHealth() > 0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean checkGameOver(){
        return gameOver;
    }

    public int[] getAvailableCoordinate(Backyard a ,int x,int y){
        int[] b = {0,0};
        for(int i = 0; i < a.getRows().length;i++)
            if (a.getRows()[i] == y){
                b[0] = i;
            }
        for(int j = 0; j < a.getColumns().length;j++)
            if (a.getColumns()[j] == x){
                b[1] = j;
            }

        return b;
    }

    public void setAvailableCoordinate(Backyard a){
        int[] b;
        for(Plants i : removeList){
            b = getAvailableCoordinate(a, i.getXBackyard(), i.getYBackyard());
            if (a.getAvailableCoordinate()[b[0]][b[1]] == 1)
                a.getAvailableCoordinate()[b[0]][b[1]] = 0;
        }
        removeList.clear();
    }

    public boolean checkInteract(ArrayList<Plants> plantsList,Zombies j){
        for(Plants i : plantsList){
            if (i.getYBackyard() == j.getYBackyard())
                if (j.getHitBox().intersects(i.getHitBox()))
                    return true;
        }
        return false;
    }

    public void toggleMuteAudioEffects(){
        for(AudioPlayer i : audioList){
            if (!i.getEffectMute())
                i.toggleEffectMute();
        }
    }

    public void toggleUnMuteAudioEffects(){
        for(AudioPlayer i : audioList){
            if (i.getEffectMute()){
                i.toggleEffectMute();
            }
        }
    }

}    
