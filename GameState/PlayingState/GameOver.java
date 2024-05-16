package GameState.PlayingState;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;

import Control.GamePanel;
import GUI_Game.PauseButton;
import GUI_Game.UrmButton;
import GameState.StateMethods;
import GameState.GameState;

public class GameOver implements StateMethods{
    private boolean gameOverState = false; //check the helps state active int the Playing
    
    private Playing playing; 
    
    private int gameOverDelay = 0; //delay time to appear effect of game over

    private UrmButton menuB,replayB;

    public GameOver(Playing playing){
        this.playing = playing;

        createUrmButtons();
    }

    private void createUrmButtons(){
        //create urm button
        menuB = new UrmButton(580, 500, 189, 33,"mainMenu_button.png");
		replayB = new UrmButton(325, 500, 189, 33,"restartLevel_button.png");   
    }

    @Override
    public void update() {
        //if gameOver are true => the time will delay for 300 (6s) to show the effect of game over
        if (gameOverDelay <= 300)
            gameOverDelay++;

        //update urm button
        menuB.update();
        replayB.update();
    }

    @Override
    public void draw(GamePanel panel, Graphics g) {
        // gameOverState : true -> it will draw gameOver class
        if (gameOverState){
            if (gameOverDelay > 200){
            //draw background of gameOver class in playing 
            g.setColor(Color.black);
            g.fillRect(0, 0, 1066, 600);
            g.drawImage(new ImageIcon("Image/Others/game_over.jpg").getImage(),112,0,null);

            //draw urm button in gameOver class
            menuB.draw(g);
            replayB.draw(g);
            }
            else if (gameOverDelay >= 1 && gameOverDelay <= 200){
                //draw background of gameOver class in playing
                g.drawImage(new ImageIcon("Image/Others/over.png").getImage(),251,66,null);
            }
        }   
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //check mouse is pressed in the bounded area 
        //"isIn" is a method to check it
        //if true -> mousePressed is true
        //also when press the mouse, the image in this area change
        if (isIn(e, menuB))
            menuB.setMousePressed(true);
        else if (isIn(e, replayB))
            replayB.setMousePressed(true);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //check mouse is pressed in the bounded area 
        //"isIn" is a method to check it
        //also when release the mouse while this area has mousePressed, it will active the function inside
        //also it will change the image 
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
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
        else if (isIn(e, replayB)){
            if (replayB.isMousePressed()) {
                //replay the game
                //set game reset as origin
                //set time to prepare to play as 0 like the beginning
                playing.reset();
                playing.setPreparePlay(0);
            }
        }

        //after release, it will reset as origin
        gameOverDelay = 0;

        //after release, it will reset back into a normal button
        menuB.resetBools();
        replayB.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //at first, set mouse not inside any button
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);

        //after that,check if it is inside any button, it will set true
		if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else if (isIn(e, replayB))
			replayB.setMouseOver(true);
  
    }

    //method to check mouse in bounded area of the button
    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

    //getter method
    public boolean getGameOverState(){
        return gameOverState;
    }

    //setter method
    public void setGameOverState(boolean active){
        this.gameOverState = active;
    }

}
