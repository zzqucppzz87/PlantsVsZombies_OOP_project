package GameState;

import Control.GamePanel;

public class State {
    protected GamePanel panel;

    public State(GamePanel panel){
        this.panel = panel;
    }

    public void setGameState(GameState state){
        switch(state){
            case MAIN_MENU:
                panel.getAudioPlayer().playSong(0);
                break;
            case LEVEL:
                panel.getAudioPlayer().playSong(1);
                break;
            case PLAYING:
                break;
        }
        GameState.state = state;
    }

    public GameState getGameState(){
        return GameState.state;
    }

    public GamePanel getGamePanel(){
        return panel;
    }
}
