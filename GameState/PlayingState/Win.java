package GameState.PlayingState;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import Control.GamePanel;
import GUI_Game.PauseButton;

import GUI_Game.UrmButton;
import GameState.StateMethods;
import GameState.GameState;


public class Win implements StateMethods{
    private boolean winState = false; //check the helps state active int the Playing

    private int winDelay = 0;//delay time to appear effect of win

    private Playing playing;

    private UrmButton menuB, nextLevel;

    public Win(Playing playing){
        this.playing = playing;

        createUrmButtons();
    }

    private void createUrmButtons(){
        //create urm button
        menuB = new UrmButton( 325, 250 * 2, 156, 39,"menu_button_2.png");
        nextLevel = new UrmButton( 605, 250 * 2, 156, 39,"next_level_button_2.png");
    }

    public void update() {
        //if win are true => the time will delay for 300 (6s) to show the effect of win
        if (winDelay <= 500)
            winDelay++;

        //update urm button
        menuB.update();
        nextLevel.update();
    }


    public void draw1(GamePanel panel,Graphics g){
        //draw turning white
        g.setColor(new Color(255,255,255,winDelay));
        g.fillRect(0, 0, 1066, 600);
    }

    public void draw2(GamePanel panel,Graphics g){
        
        g.setColor(Color.black);
        g.fillRect(0, 0, 1066, 600);

        g.drawImage(new ImageIcon("Image/GUI/win_bg.gif").getImage(),0,0,null);

        //draw urm button
        menuB.draw(g);
        nextLevel.draw(g);

        if (winDelay <= 500){
            g.setColor(new Color(255,255,255,250*2 - winDelay));
            g.fillRect(0, 0, 1066, 600);
        }
    }

    @Override
    public void draw(GamePanel panel, Graphics g) {
        if (winState){
            if (winDelay >= 0 && winDelay <= 250){
                draw1(panel, g);
            }
            else if (winDelay > 250){
                draw2(panel,g);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e,menuB)){
            menuB.setMousePressed(true);
        }
        else if (isIn(e,nextLevel)){
            nextLevel.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e,menuB)){
            if(menuB.isMousePressed()){
                playing.setGameState(GameState.MAIN_MENU);
                playing.reset();
                playing.getMainMenu().setCurState(0);
                playing.getLevel().setLevel(0);
                winDelay = 0;
            }
        }
        else if (isIn(e,nextLevel)){
            if (nextLevel.isMousePressed()){
                if (playing.getLevel().getLevel() == 1){
                    playing.getLevel().setLevel(2);
                    playing.reset();
                }
                else if (playing.getLevel().getLevel() == 2){
                    playing.getLevel().setLevel(3);
                    playing.reset();
                }                
                else if (playing.getLevel().getLevel() == 3){
                    playing.setGameState(GameState.MAIN_MENU);
                    playing.reset();
                    playing.getMainMenu().setCurState(0);
                    playing.getLevel().setLevel(0);
                }
                winDelay =0;               
            }
        }

        menuB.resetBools();
        nextLevel.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false);
        nextLevel.setMouseOver(false);

        if (isIn(e,menuB)){
            menuB.setMouseOver(true);
        }
        else if (isIn(e,nextLevel)){
            nextLevel.setMouseOver(true);
        }
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
    
    public void setWinDelay(int delay){
        this.winDelay = delay;
    }

    public int getWinDelay(){
        return winDelay;
    }

    public boolean getWinState(){
        return winState;
    }

    public void setWinState(boolean active){
        this.winState = active;
    }
}
