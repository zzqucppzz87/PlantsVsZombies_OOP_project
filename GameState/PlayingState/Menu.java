package GameState.PlayingState;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import Control.GamePanel;
import GUI_Game.AudioOptions;
import GUI_Game.PauseButton;
import GUI_Game.UrmButton;
import GameState.StateMethods;
import GameState.GameState;

public class Menu implements StateMethods{
    
    private boolean menuState = false; //check the menu state active int the Main menu
    private Playing playing;

    private AudioOptions audioOptions; //class can adjust the volume the audio

    private UrmButton backToGame, mainMenu, restart, quit;


    public Menu(Playing playing){
        this.playing = playing;
        

        this.audioOptions = playing.getGamePanel().getAudioOptions();
        
        createUrmButtons();
    }

    private void createUrmButtons(){
        //create urm button
        quit = new UrmButton(219 * 2, 138 * 2, 192, 33,"quit.png");
        mainMenu = new UrmButton(220 * 2, 178 * 2, 189, 33,"mainMenu_button.png");
		restart = new UrmButton(220 * 2, 158 * 2, 189, 33,"restartLevel_button.png");
        backToGame = new UrmButton((int) 188.5 * 2, 208 * 2, 314, 72,"backToGame_button.png");
    }

    @Override
    public void update() {
        //update urm button menu class
        quit.update();
        mainMenu.update();
		restart.update();
        backToGame.update();

        //update audioOpptions that can adjust the volume of audio
        audioOptions.update();
    }

    @Override
    public void draw(GamePanel panel, Graphics g) {

        if (menuState){
            //draw background of menu class in playing state
            g.drawImage(new ImageIcon("Image/GUI/menu.png").getImage(),333,50, null);

            //draw urm button in menu class
            quit.draw(g);
            mainMenu.draw(g);
            restart.draw(g);
            backToGame.draw(g);

            //draw audio options in menu class
            audioOptions.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e){
        //method that can drag object in the bounded area
        audioOptions.mouseDragged(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //check mouse is pressed in the bounded area 
        //"isIn" is a method to check it
        //if true -> mousePressed is true
        //also when press the mouse, the image in this area change
        if (isIn(e, mainMenu))
            mainMenu.setMousePressed(true);
        else if (isIn(e, quit))
            quit.setMousePressed(true);
        else if (isIn(e, restart))
            restart.setMousePressed(true);
        else if (isIn(e, backToGame))
            backToGame.setMousePressed(true);             
        else
            audioOptions.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //check mouse is pressed in the bounded area 
        //"isIn" is a method to check it
        //also when release the mouse while this area has mousePressed, it will active the function inside
        //also it will change the image 
        if (isIn(e, mainMenu)) {
            if (mainMenu.isMousePressed()) {
                //set gameState to main menu state
                //set menu class false in playing state
                //set the current state as 0 (show that we are in main menu state)
                playing.setGameState(GameState.MAIN_MENU);
                menuState = false;
                playing.setStopState(false);
                playing.getMainMenu().setCurState(0);                
            }
        }
        if (isIn(e,quit)){
            if (quit.isMousePressed()){
                //set gameState into main menu state
                //set game reset as origin
                //set the level as 0 (there are no remaining level)
                //set the current state as 0 (show that we are in main menu state)                
                playing.setGameState(GameState.MAIN_MENU);
                playing.reset();
                playing.getMainMenu().setCurState(0);
                playing.getLevel().setLevel(0);
            }
        }
        else if (isIn(e, restart)) {
            if (restart.isMousePressed()) {
                //replay the game
                //set game reset as origin
                //set time to prepare to play as 0 like the beginning
                playing.reset();
                playing.setStopState(true);
                playing.setPreparePlay(0);
            }
        } 
        else if (isIn(e, backToGame)) {
            if (backToGame.isMousePressed()){
                //exits the menu class
                menuState = false;
                playing.setStopState(false);
            }
        } 
        else{
            audioOptions.mouseReleased(e);
        }

        //after release, it will reset back into a normal button
        quit.resetBools();
        mainMenu.resetBools();
        restart.resetBools();
        backToGame.resetBools();
    }
    

    @Override
    public void mouseMoved(MouseEvent e) {
        //at first, set mouse not inside any button
        quit.setMouseOver(false);
        mainMenu.setMouseOver(false);
        restart.setMouseOver(false);
        backToGame.setMouseOver(false);

        //after that,check if it is inside any button, it will set true
		if (isIn(e, mainMenu))
			mainMenu.setMouseOver(true);
        else if (isIn(e, quit))
			quit.setMouseOver(true);
		else if (isIn(e, restart))
			restart.setMouseOver(true);
		else if (isIn(e, backToGame))
			backToGame.setMouseOver(true);
		else
			audioOptions.mouseMoved(e);
        
    }

    //method to check mouse in bounded area of the button
    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
    
    //getter method
    public boolean getMenuState(){
        return menuState;
    }

    //setter method
    public void setMenuState(boolean active){
        this.menuState = active;
    }
}
