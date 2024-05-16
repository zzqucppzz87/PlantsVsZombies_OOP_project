package GameState.MainMenuState;

import java.awt.Graphics;

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import Control.GamePanel;
import GUI_Game.PauseButton;
import GUI_Game.UrmButton;
import GameState.StateMethods;

public class Helps implements StateMethods{

    private boolean helpsState = false; //check the helps state active int the Main menu
    private MainMenu mainMenu;

    private UrmButton ok_button,next_button;

    private int letter = 0;


    public Helps(MainMenu mainMenu){
        this.mainMenu = mainMenu;

        createUrmButtons();
    }

    private void createUrmButtons(){
        //create urm button
        ok_button = new UrmButton((170 * 2), (270* 2), 156,39,"button_letter2.png");
        next_button = new UrmButton((270 * 2), (270* 2), 156,39,"button_letter1.png");  
    }

    @Override
    public void update() {
        //update urm button
        ok_button.update();
        next_button.update();
    }

    @Override
    public void draw(GamePanel panel, Graphics g) {
        // helpsState : true -> it will draw helps
        if (helpsState){
            //draw background of helps class in main menu             
            if (letter == 0){
                g.drawImage(new ImageIcon("Image/GUI/letter1.png").getImage(), 111, 28, null);    
            }
            if (letter == 1){
                g.drawImage(new ImageIcon("Image/GUI/letter2.png").getImage(), 111, 28, null); 
            }

            //draw urm button in helps class
            ok_button.draw(g);
            next_button.draw(g);
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
        if (isIn(e,ok_button)){
            ok_button.setMousePressed(true);
        }
        if (isIn(e,next_button)){
            next_button.setMousePressed(true);
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
                //exist the helps state
                mainMenu.setStopState(false);
                helpsState = false;
            }
        }
        if (isIn(e,next_button)){
            if (next_button.isMousePressed()){
                //exist the helps state
                letter++;
                if (letter == 2){
                    letter = 0;
                }
            }
        }

        //after release, it will reset back into a normal button
        ok_button.resetBools();
        next_button.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //at first, set mouse not inside any button
        ok_button.setMouseOver(false);
        next_button.setMouseOver(false);

        //after that,check if it is inside any button, it will set true
		if (isIn(e, ok_button))
			ok_button.setMouseOver(true);   
        if (isIn(e, next_button))
			next_button.setMouseOver(true);      
    }

    //method to check mouse in bounded area of the button
    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}    

    //getter method
    public boolean getHelpsState(){
        return helpsState;
    }

    //setter method
    public void setHelpsState(boolean active){
        this.helpsState = active;
    }
   
}
