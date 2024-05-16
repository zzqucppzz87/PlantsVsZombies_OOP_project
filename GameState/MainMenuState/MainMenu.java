package GameState.MainMenuState;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import Control.GamePanel;

import GUI_Game.PauseButton;
import GUI_Game.UrmButton;
import GameState.State;
import GameState.GameState;
import GameState.StateMethods;
import GameState.LevelState.Level;

public class MainMenu extends State implements StateMethods {
    //class inside the Main menu state
    private Quit quit;
    private Helps helps;
    private Options options;

    private UrmButton playing_button, options_button, helps_button, quit_button;

    private boolean stopState = false; //check the main menu state are active or not
    private int currentState = 0; //the current state are in game (1,2,3) or not (0)
    // it will change depend on the screen that we see
    
    //state class
    private Level level;
    
    private GamePanel panel;


    public MainMenu(GamePanel panel,Level level){
        super(panel);
        this.panel = panel;

        quit = new Quit(this);
        options = new Options(this);
        helps = new Helps(this);

        this.level = level;

        createUrmButtons();
    }

    private void createUrmButtons() {
        //create urm button
		playing_button = new UrmButton(390, 200,285,69,"start_button.png");//,GameState.PLAYING);
		options_button = new UrmButton(390, 285,285,69,"options_button.png");//,GameState.OPTIONS);
		quit_button = new UrmButton(390, 455,285,69,"quit_button.png");//,GameState.QUIT);
        helps_button = new UrmButton(390, 370,285,69,"helps_button.png");//,GameState.QUIT);
	}

    @Override
    public void update() {
        //update urm button in each class
        if (!stopState){
            playing_button.update();
            options_button.update();
            quit_button.update();
            helps_button.update();
        }
        if (options.getOptionsState()){
            options.update();
        }
        if (quit.getQuitState()){
            quit.update();
        }
        if (helps.getHelpsState()){
            helps.update();
        }
    }


    @Override
    public void draw(GamePanel panel, Graphics g) {
        //draw background of main menu state
        g.drawImage(new ImageIcon("Image/GUI/mainmenu.png").getImage(), 0, 0, 1066, 600, null);

        //draw urm button in main menu
        playing_button.draw(g);
        options_button.draw(g);
        quit_button.draw(g);
        helps_button.draw(g);

        //draw small class in main menu
        quit.draw(panel,g);
        options.draw(panel, g);
        helps.draw(panel, g);
        
    }

    public void mouseDragged(MouseEvent e){
        //able to drag a object in bounded area
        if (options.getOptionsState()){
            options.mouseDragged(e);
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
        if (!stopState){
            if (isIn(e, playing_button)){
                playing_button.setMousePressed(true);
            }
            else if (isIn(e, options_button)){
                options_button.setMousePressed(true);
            }
            else if (isIn(e,quit_button)){
                quit_button.setMousePressed(true);
            }
            else if (isIn(e,helps_button)){
                helps_button.setMousePressed(true);
            }
        }
        if (options.getOptionsState()){
            options.mousePressed(e);
        }
        if (quit.getQuitState()){
            quit.mousePressed(e);
        }
        if (helps.getHelpsState()){
            helps.mousePressed(e);
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        //check mouse is pressed in the bounded area 
        //"isIn" is a method to check it
        //also when release the mouse while this area has mousePressed, it will active the function inside
        //also it will change the image         
        if (!stopState){
            if (isIn(e, playing_button)){
                if (playing_button.isMousePressed()){
                    if (currentState == level.getLevel())
                        //it checks that there are no level are remaining to play again
                        //then set gameState to level state to choose level to play
                        setGameState(GameState.LEVEL);
                    else{
                        //it checks that there are level are remaining to play again
                        //then set gameState to the current remaining level to play again
                        //also play song in the level state
                        panel.getAudioPlayer().playSong(1);
                        setGameState(GameState.PLAYING);
                    }
                }
            }
            else if (isIn(e, options_button)){
                if (options_button.isMousePressed()){
                    //enter the options class
                    stopState = true;
                    options.setOptionsState(true);
                }
            }
            else if (isIn(e, quit_button)){
                if (quit_button.isMousePressed()){
                    //enter the quit class
                    stopState = true;
                    quit.setQuitState(true);
                }
            }
            else if (isIn(e, helps_button)){
                if (helps_button.isMousePressed()){
                    //enter the helps state
                    stopState = true;
                    helps.setHelpsState(true);
                }
            }
            
            //after release, it will reset back into a normal button
            playing_button.resetBools();
            options_button.resetBools();
            quit_button.resetBools();
            helps_button.resetBools();
        }

        //mouseReleased method in each small method
        if (options.getOptionsState()){
            options.mouseReleased(e);
        }
        if (quit.getQuitState()){
            quit.mouseReleased(e);
        }
        if (helps.getHelpsState()){
            helps.mouseReleased(e);
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        if (!stopState){
            //at first, set mouse not inside any button
            playing_button.setMouseOver(false);
            options_button.setMouseOver(false);
            quit_button.setMouseOver(false);
            helps_button.setMouseOver(false);

            //after that,check if it is inside any button, it will set true
            if (isIn(e, playing_button)){
                playing_button.setMouseOver(true);
            }
            else if (isIn(e, options_button)){
                options_button.setMouseOver(true);
            }
            else if (isIn(e, quit_button)){
                quit_button.setMouseOver(true);
            }
            else if (isIn(e, helps_button)){
                helps_button.setMouseOver(true);
            }
        }

        //mouseMoved method in each small method
        if (options.getOptionsState()){
            options.mouseMoved(e);
        }
        if (quit.getQuitState()){
            quit.mouseMoved(e);
        }
        if (helps.getHelpsState()){
            helps.mouseMoved(e);
        }
    }

    //method to check mouse in bounded area of the button
    private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

    //getter method
    public boolean getStopState(){
        return stopState;
    }

    public int getCurState(){
        return currentState;
    }

    //setter method
    public void setStopState(boolean active){
        this.stopState = active;
    }

    public void setCurState(int num){
        this.currentState = num;
    }


}
