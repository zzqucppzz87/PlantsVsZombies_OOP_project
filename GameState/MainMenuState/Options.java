package GameState.MainMenuState;

import java.awt.Graphics;

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import Control.GamePanel;
import GUI_Game.AudioOptions;
import GUI_Game.PauseButton;
import GUI_Game.UrmButton;
import GameState.StateMethods;

public class Options implements StateMethods{
    private boolean optionsState = false; //check the options state active int the Main menu

    private AudioOptions audioOptions; //class can adjust the volume the audio
    
    private MainMenu mainMenu;

    private UrmButton ok_button;

    public Options(MainMenu mainMenu){
        this.mainMenu = mainMenu;
        this.audioOptions = mainMenu.getGamePanel().getAudioOptions();

        createUrmButtons();
    }

    private void createUrmButtons(){
        //create urm button
        ok_button = new UrmButton((int) (188.5 * 2), (208* 2), 314,72,"backToGame_button.png");
    }

    @Override
    public void update() {
        //update urm button
        ok_button.update();
        audioOptions.update();
    }

    @Override
    public void draw(GamePanel panel, Graphics g) {
        // optionsState : true -> it will draw options
        if (optionsState){
            //draw background of options class in main menu 
            g.drawImage(new ImageIcon("Image/GUI/menu.png").getImage(),333,50, null);
            
            //draw urm button in options class
            ok_button.draw(g);
            audioOptions.draw(g);
        }
    }

    public void mouseDragged(MouseEvent e){
        //method that can drag object in the bounded area
        audioOptions.mouseDragged(e);
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
        if (isIn(e,ok_button)){
            ok_button.setMousePressed(true);
        }
        else {
            audioOptions.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //check mouse is pressed in the bounded area 
        //"isIn" is a method to check it
        //also when release the mouse while this area has mousePressed, it will active the function inside
        //also it will change the image 
        if (isIn(e,ok_button)){
            if (ok_button.isMousePressed()){
                optionsState = false;
                mainMenu.setStopState(false);
            }
        }
        else {
            audioOptions.mouseReleased(e);
        }

        //after release, it will reset back into a normal button
        ok_button.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //at first, set mouse not inside any button
        ok_button.setMouseOver(false);
        
        //after that,check if it is inside any button, it will set true
        if (isIn(e,ok_button))
            ok_button.setMouseOver(true);
        else
            audioOptions.mouseMoved(e);
        
    }

    //method to check mouse in bounded area of the button
    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

    //getter method
    public boolean getOptionsState(){
        return optionsState;
    }

    //setter method
    public void setOptionsState(boolean active){
        this.optionsState = active;
    }
    
}
