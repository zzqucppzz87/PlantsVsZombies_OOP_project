package GameState.PlayingState;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import Control.AudioPlayer;
import Control.GamePanel;
import Entity.Backyard;
import Entity.Card;
import Entity.ObjectDrag;
import Entity.ObjectStable;
import GUI_Game.PauseButton;

import GUI_Game.UrmButton;
import GameState.State;
import GameState.StateMethods;
import GameState.LevelState.Level;
import GameState.MainMenuState.MainMenu;

public class Playing extends State implements StateMethods{
    
    private ObjectDrag objectDrag;
    private ObjectStable object;
    private Backyard backyard;
    private Card card;

    private int check1 = 0;
    private int check2 = -1;
    private Point prePt;

    private int preparePlay = -1;

    private Menu menu;
    
    private Win win;
    private int prepareWin = 0;
    private GameOver gameOver;
    private int prepareGameOver = 0;

    private boolean stopState = false;

    private UrmButton menuB;

    private Level level;
    private MainMenu mainMenu;

    public Playing(GamePanel panel,Level level,MainMenu mainMenu){
        super(panel);

        objectDrag = new ObjectDrag(panel.getAudioPlayer()); // list of object can be controlled by mouse
        object = new ObjectStable(panel.getAudioPlayer()); // list of object that are stable in game (such as zombie)
        backyard = new Backyard(); // contains the coordinate of every position in the backyard
        card = new Card(); // contains card price of plants
        
        menu = new Menu(this);

        win = new Win(this);
        gameOver = new GameOver(this);

        this.level = level;
        this.mainMenu = mainMenu;

        createUrmButtons();
    }

    private void createUrmButtons(){
        menuB = new UrmButton(430 * 2,0, 189, 33,"mainMenu_button.png");

    }

    @Override
    public void update() {
        if (preparePlay == -1){
            if (level.getLevel() != mainMenu.getCurState()){
                stopState = true;
                preparePlay = 0;
            }
        }
        if (preparePlay >= 0){
            stopState = true;
            preparePlay++;
            if (preparePlay == 150){
                panel.getAudioPlayer().stopSong();
                
                AudioPlayer sound = new AudioPlayer("ready", 2);
                sound.setVolume(panel.getAudioPlayer().getVolume());
                if (!panel.getAudioPlayer().getEffectMute()){
                    sound.playEffect();
                }
            }
            if (preparePlay == 225){
                panel.getAudioPlayer().playSong(2);
                stopState = false;
                preparePlay = -1;
            }
        }
        if (!stopState){
            if (level.getLevel() == 1){
                mainMenu.setCurState(1);
                object.updateTestZombies(1,15,5);
            }
            else if (level.getLevel() == 2){
                mainMenu.setCurState(2);
                object.updateTestZombies(2,30,15);
            }
            else if (level.getLevel() == 3){
                mainMenu.setCurState(3);
                object.updateTestZombies(3,25,15);
            }
            objectDrag.updatePeaList(); //update motion of pea of each peashooter
            object.updateLawnMower();
            object.updateZombies(this); //update motion of each zombies
            object.toggleUnMuteAudioEffects();
            object.checkCollision(objectDrag.getPlantsList(), objectDrag.getPeaUpdateList()); //check collision between objects (zombies vs pea and zombies vs plants)
            objectDrag.updateSunList();
            objectDrag.updateSunFalling();
            objectDrag.updatePlantsList();
            objectDrag.updatePlantsCard();
            object.setAvailableCoordinate(backyard);
            

            if (object.checkGameOver()){
                panel.getAudioPlayer().stopSong();
                AudioPlayer sound = new AudioPlayer("lose", 2);
                sound.setVolume(panel.getAudioPlayer().getVolume());
                if (!panel.getAudioPlayer().getEffectMute()){
                    sound.playEffect();
                }
            
                gameOver.setGameOverState(true);
                stopState = true;
            }
            if (object.checkGameWin()){
                if (prepareWin > 100){
                    win.setWinState(true);
                    stopState = true;
                }
                else{
                    prepareWin++;
                }
            }

            menuB.update();

            object.updateRemove();
   
        }
        else{
            object.toggleMuteAudioEffects();
        }

        if (menu.getMenuState()){
            menu.update();
        }

        if (win.getWinState()){
            if (win.getWinDelay() == 0){
                panel.getAudioPlayer().stopSong();
                AudioPlayer sound = new AudioPlayer("win_game", 2);
                sound.setVolume(panel.getAudioPlayer().getVolume());
                if (!panel.getAudioPlayer().getEffectMute()){
                    sound.playEffect();
                }                
            }
            if (win.getWinDelay() == 250){
                panel.getAudioPlayer().playSong(3);
            }
            win.update();
        }

        if (gameOver.getGameOverState()){
            if (prepareGameOver == 250){
                AudioPlayer sound = new AudioPlayer("lose_state", 2);
                sound.setVolume(panel.getAudioPlayer().getVolume());
                if (!panel.getAudioPlayer().getEffectMute()){
                    sound.playEffect();
                }    
            }
            if (prepareGameOver > 250)
                gameOver.update();
            else
                prepareGameOver++;
        }
    }

    @Override
    public void draw(GamePanel panel, Graphics g) {
        g.drawImage(new ImageIcon("Image/Others/backyard.png").getImage(),0,0, null); // draw backyard
        
        g.setFont(new Font("MV Boli",Font.PLAIN,20));
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(objectDrag.getSunValue()), 18, 78);
       
        menuB.draw(g);
        object.drawPercent(g);

        objectDrag.drawPlantsCard(panel,g); //draw plants card
        objectDrag.drawPlants(panel, g); //draw plants
        objectDrag.drawPeaList(panel,g); //draw pea 

        objectDrag.drawShovel(panel, g);

        object.drawLawnMower(panel, g);
        object.drawZombies(panel, g); //draw zombie

        objectDrag.drawSun(panel, g); //draw sun from sunflower
        objectDrag.drawSunFalling(panel, g);

        if (!stopState){
            object.drawFinal(g);
        }

        menu.draw(panel, g);

        win.draw(panel,g);
        gameOver.draw(panel,g);

        if (preparePlay < 150 && preparePlay != -1){
            g.setColor(new Color(0, 0,0,150));
            g.fillRect(0, 0, 1066, 600);
        }
        else if (preparePlay >= 150 && preparePlay < 175){
            g.drawImage(new ImageIcon("Image/Others/ready.png").getImage(),378,241,null);
        }else if (preparePlay >= 175 && preparePlay < 200){
            g.drawImage(new ImageIcon("Image/Others/set.png").getImage(),378,255,null);
        }else if (preparePlay >= 200 && preparePlay < 225){
            g.drawImage(new ImageIcon("Image/Others/plant.png").getImage(),378,235,null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!stopState){
            objectDrag.updateSunCard(e,objectDrag.getSunList());
            objectDrag.updateSunCard(e,objectDrag.getSunFallingList());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!stopState){
            prePt = e.getPoint();
            if (card.qualifiedPositionCard(e)[1] == 1 && check1 == 0){ //check the mouse can click the card properly
                if (objectDrag.getSunValue() >= objectDrag.getPlantsCardList().get(card.qualifiedPositionCard(e)[0]).getPlantsValue()){
                    if (!objectDrag.getPlantsCardList().get(card.qualifiedPositionCard(e)[0]).getCheckDelay()){
                        check2 = card.qualifiedPositionCard(e)[0]; //get position of the specific card in the card list
                        check1++; //purpose: to make sure to click only 1 card
                    }
                }
            }
            if (objectDrag.getShovel().checkShovel(e)){
                check2 = 10;
            }
            if (isIn(e,menuB))
                menuB.setMousePressed(true);
        }

        if (menu.getMenuState()){
            menu.mousePressed(e);
        }
        if (win.getWinState()){
            win.mousePressed(e);
        }
        if (gameOver.getGameOverState()){
            gameOver.mousePressed(e);
        }
            
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!stopState){
            if (check2 >= 0 && check2 < 10){
                if (backyard.qualifiedPositionBackyard(e)[2] != 1){ //check the mouse are placed in the proper area to place the plants in the backyard
                    objectDrag.getPlantsCardList().get(check2).setImageCorner(new Point((int) objectDrag.getPlantsCardList().get(check2).getImageFirstPoint().getX(),(int) objectDrag.getPlantsCardList().get(check2).getImageFirstPoint().getY()));
                    // a card is forced to move back to card list because the mouse place in area improperly to place plants 
                }
                else{
                    if (backyard.getAvailableCoordinate()[backyard.qualifiedPositionBackyard(e)[3]][backyard.qualifiedPositionBackyard(e)[4]] == 0){
                        backyard.getAvailableCoordinate()[backyard.qualifiedPositionBackyard(e)[3]][backyard.qualifiedPositionBackyard(e)[4]]++;
                        int a = objectDrag.getPlantsCardList().get(check2).getImage().getIconWidth();
                        int b = objectDrag.getPlantsCardList().get(check2).getImage().getIconHeight();
                        // get coordinate to place the plants
                        objectDrag.getPlantsCardList().get(check2).setImageCorner(new Point(backyard.qualifiedPositionBackyard(e)[0]-a/2,backyard.qualifiedPositionBackyard(e)[1]-3*b/4));
                        objectDrag.getPlantsCardList().get(check2).setXBackyard(backyard.qualifiedPositionBackyard(e)[0]);
                        objectDrag.getPlantsCardList().get(check2).setYBackyard(backyard.qualifiedPositionBackyard(e)[1]);
                        objectDrag.getPlantsCardList().get(check2).setCheck(objectDrag.getPlantsCardList().get(check2).getCheck() + 1); //set card are available to create a new plant

                        objectDrag.getPlantsCardList().get(check2).setCheckDelay(true);

                        objectDrag.updateSunValue(objectDrag.getPlantsCardList().get(check2).getPlantsValue(),1);


                        AudioPlayer sound = new AudioPlayer("planting", 2);
                        sound.setVolume(panel.getAudioPlayer().getVolume());
                        if (!panel.getAudioPlayer().getEffectMute())
                            sound.playEffect();
                    }
                    else
                        objectDrag.getPlantsCardList().get(check2).setImageCorner(new Point((int) objectDrag.getPlantsCardList().get(check2).getImageFirstPoint().getX(),(int) objectDrag.getPlantsCardList().get(check2).getImageFirstPoint().getY()));
                }
            }
            else if (check2 == 10){
                if (backyard.qualifiedPositionBackyard(e)[2] == 1){ //check the mouse are placed in the proper area to place the plants in the backyard
                    if (backyard.getAvailableCoordinate()[backyard.qualifiedPositionBackyard(e)[3]][backyard.qualifiedPositionBackyard(e)[4]] == 1){
                        objectDrag.updateShovel(new Point(backyard.qualifiedPositionBackyard(e)[0],backyard.qualifiedPositionBackyard(e)[1]));
                        backyard.getAvailableCoordinate()[backyard.qualifiedPositionBackyard(e)[3]][backyard.qualifiedPositionBackyard(e)[4]]--;

                        
                        AudioPlayer sound = new AudioPlayer("shovel", 2);
                        sound.setVolume(panel.getAudioPlayer().getVolume());
                        if (!panel.getAudioPlayer().getEffectMute())
                            sound.playEffect();
                    }
                    // a card is forced to move back to card list because the mouse place in area improperly to place plants 
                }
                objectDrag.getShovel().setImageCorner(new Point((int) objectDrag.getShovel().getImageFirstPoint().getX(),(int) objectDrag.getShovel().getImageFirstPoint().getY()));    
            }
            check1 = 0;
            check2 = -1;

            if (isIn(e,menuB)){
                if (menuB.isMousePressed()){
                    menu.setMenuState(true);
                    stopState = true;
                }
            }

            menuB.resetBools();
        }
        if (menu.getMenuState())
            menu.mouseReleased(e);
        if (win.getWinState()){
            panel.getAudioPlayer().playSong(1);
            win.mouseReleased(e);
        }
        if (gameOver.getGameOverState()){
            panel.getAudioPlayer().playSong(1);
            gameOver.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!stopState)
            menuB.setMouseOver(false);
        if (isIn(e,menuB))
            menuB.setMouseOver(true);
        
        if (menu.getMenuState())
            menu.mouseMoved(e);
        if (win.getWinState())
            win.mouseMoved(e);
        if (gameOver.getGameOverState())
            gameOver.mouseMoved(e);
    }

    public void mouseDragged(MouseEvent e){
        if (!stopState){
            if (check2 >= 0 && check2 < 10){
                Point currentPt = e.getPoint();
                // get new coordinate by using displacement between the current and the previous mouse
                objectDrag.getPlantsCardList().get(check2).getImageCorner().translate((int)(currentPt.getX()-prePt.getX()),(int)(currentPt.getY()-prePt.getY()));
                prePt = currentPt;
            
            }
            else if (check2 == 10){
                Point currentPt = e.getPoint();
                // get new coordinate by using displacement between the current and the previous mouse
                objectDrag.getShovel().getImageCorner().translate((int)(currentPt.getX()-prePt.getX()),(int)(currentPt.getY()-prePt.getY()));
                prePt = currentPt;
            }
        }

        if (menu.getMenuState())
            menu.mouseDragged(e);
    }

    public void reset(){
        objectDrag.reset();
        object.reset();
        backyard.reset();
        
        stopState = false;
        win.setWinState(false);
        gameOver.setGameOverState(false);
        menu.setMenuState(false);
        prepareWin = 0;
        prepareGameOver = 0;
        //preparePlay = -1;
    }

    public ObjectStable getObject(){
        return object;
    }

    public void setStopState(boolean active){
        this.stopState = active;
    }

    public boolean getStopState(){
        return stopState;
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

    public MainMenu getMainMenu(){
        return mainMenu;
    }

    public Level getLevel(){
        return level;
    }

    public void setPreparePlay(int num){
        this.preparePlay = num;
    }

}
