package GameState.LevelState;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import Control.GamePanel;
import GUI_Game.PauseButton;
import GUI_Game.UrmButton;
import GameState.GameState;
import GameState.State;
import GameState.StateMethods;

public class Level extends State implements StateMethods{

    private UrmButton level_1, level_2, level_3;
    private UrmButton menu;

    private int level; // the state level are playing (1,2,3) or not (0)
    //it can be stable if the level are still remaining 

    public Level(GamePanel panel) {
        super(panel);
        
        createUrmButtons();
    }

    private void createUrmButtons(){
        //create urm button
        level_1 = new UrmButton(96, 80 * 2,227, 318, "easy_button.png");
        level_2 = new UrmButton(419, 50 * 2,227, 318, "normal_button.png");
        level_3 = new UrmButton(743, 80 * 2,227, 318, "hard_button.png");
        menu = new UrmButton(438, 250 * 2,189, 33, "mainMenu_button.png");
    }

    @Override
    public void update() {
        //update urm button
        level_1.update();
        level_2.update();
        level_3.update();
        menu.update();
    }

    @Override
    public void draw(GamePanel panel, Graphics g) {
        //draw background of level state 
        g.drawImage(new ImageIcon("Image/GUI/level_bg.png").getImage(), 0, 0, null);
        
        //draw urm button
        level_1.draw(g);
        level_2.draw(g);
        level_3.draw(g);
        menu.draw(g);
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
        if (isIn(e,level_1)){
            level_1.setMousePressed(true);
        }
        else if (isIn(e,level_2)){
            level_2.setMousePressed(true);
        }
        else if (isIn(e,level_3)){
            level_3.setMousePressed(true);
        }
        else if (isIn(e,menu)){
            menu.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //check mouse is pressed in the bounded area 
        //"isIn" is a method to check it
        //also when release the mouse while this area has mousePressed, it will active the function inside
        //also it will change the image 
        if (isIn(e,level_1)){
            if (level_1.isMousePressed()){
                //change gameState into playing with level1
                level = 1;
                setGameState(GameState.PLAYING);
            }
        }
        else if (isIn(e,level_2)){
            if (level_2.isMousePressed()){
                //change gameState into playing with level2
                level = 2;
                setGameState(GameState.PLAYING);
            }
        }
        else if (isIn(e,level_3)){
            if (level_3.isMousePressed()){
                //change gameState into playing with level3
                level = 3;
                setGameState(GameState.PLAYING);
            }
        }
        else if (isIn(e,menu)){
            if (menu.isMousePressed()){
                //change gameState back to main menu
                setGameState(GameState.MAIN_MENU);
            }
        }

        //after release, it will reset back into a normal button
        level_1.resetBools();
        level_2.resetBools();
        level_3.resetBools();
        menu.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //at first, set mouse not inside any button
        level_1.setMouseOver(false);
        level_2.setMouseOver(false);
        level_3.setMouseOver(false);
        menu.setMouseOver(false);

        //after that,check if it is inside any button, it will set true
        if (isIn(e,level_1))
            level_1.setMouseOver(true);
        else if (isIn(e,level_2))
            level_2.setMouseOver(true);
        else if (isIn(e,level_3))
            level_3.setMouseOver(true);
        else if (isIn(e,menu))
            menu.setMouseOver(true);
    }

    //method to check mouse in bounded area of the button
    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

    //getter method
    public int getLevel(){
        return level;
    }

    //setter method
    public void setLevel(int num){
        this.level = num;
    }
}
