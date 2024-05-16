package GameState.MainMenuState;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import Control.GamePanel;
import GUI_Game.PauseButton;
import GUI_Game.UrmButton;
import GameState.StateMethods;

public class Quit implements StateMethods{
    private boolean quitState = false; //check the quit state active int the Main menu
    
    private MainMenu mainMenu;
    
    private UrmButton cancel_button,leave_button;

    public Quit (MainMenu mainMenu){
        this.mainMenu = mainMenu;
        
        createUrmButtons();
    }

    private void createUrmButtons(){
        //create urm button
        leave_button = new UrmButton(370,400, 163, 45,"leave.png");
        cancel_button = new UrmButton(540,400, 163, 45,"cancel.png");
    }

    @Override
    public void update() {
        //update urm button
        leave_button.update();
        cancel_button.update();
    }

    @Override
    public void draw(GamePanel panel, Graphics g) {
        // quitState : true -> it will draw quit
        if (quitState){
            //draw background of quit class in main menu 
            g.drawImage(new ImageIcon("Image/GUI/leavegame.png").getImage(), 333, 140, null);

            //draw urm button in helps class
            leave_button.draw(g);
            cancel_button.draw(g);
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
        if (isIn(e,leave_button)){
            leave_button.setMousePressed(true);
        }
        else if (isIn(e,cancel_button)){
            cancel_button.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //check mouse is pressed in the bounded area 
        //"isIn" is a method to check it
        //also when release the mouse while this area has mousePressed, it will active the function inside
        //also it will change the image 
        if (isIn(e,leave_button)){
            if (leave_button.isMousePressed()){
                //exits the game
                System.exit(0);
            }
        }
        else if (isIn(e,cancel_button)){
            if (cancel_button.isMousePressed()){
                //exits the quit class
                quitState = false;
                mainMenu.setStopState(false);
            }
        }

        //after release, it will reset back into a normal button
        leave_button.resetBools();
        cancel_button.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //at first, set mouse not inside any button
        leave_button.setMouseOver(false);
        cancel_button.setMouseOver(false);

        //after that,check if it is inside any button, it will set true
		if (isIn(e, leave_button))
			leave_button.setMouseOver(true);
		if (isIn(e, cancel_button))
			cancel_button.setMouseOver(true);  
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	} 

    //getter method
    public boolean getQuitState(){
        return quitState;
    }

    //setter method
    public void setQuitState(boolean active){
        this.quitState = active;
    }

}
