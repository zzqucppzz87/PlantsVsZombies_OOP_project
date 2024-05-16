package GUI_Game;


import java.awt.Graphics;
import java.awt.event.MouseEvent;


import Control.AudioPlayer;
import Control.GamePanel;
import GameState.State;

public class AudioOptions extends State{ 

	private VolumeButton volumeButton;
	private SoundButton musicButton, sfxButton;
	
	//private GamePanel panel;
	private AudioPlayer audioPlayer;


	public AudioOptions(AudioPlayer audioPlayer,GamePanel panel) {
		super(panel);
		this.audioPlayer = audioPlayer;

		createSoundButtons();

		createVolumeButton();
	}

	private void createVolumeButton() {
		int vX = (int) (225 * 2);
		int vY = (int) (115 * 2);
		volumeButton = new VolumeButton(vX, vY, 163, 30);
	}

	private void createSoundButtons() {
		int soundX = (int) (270 * 2);
		int musicY = (int) (75 * 2);
		int sfxY = (int) (94 * 2);
		musicButton = new SoundButton(soundX, musicY, 33, 34);
		sfxButton = new SoundButton(soundX, sfxY, 33, 34);
	}

	public void update() {
		musicButton.update();
		sfxButton.update();

		volumeButton.update();

	}

	public void draw(Graphics g) {
		// Sound buttons
		musicButton.draw(g);
		sfxButton.draw(g);

		// Volume Button
		volumeButton.draw(g);
		
	}

	public void mouseDragged(MouseEvent e) {
		if (volumeButton.isMousePressed()) {
			float valueBefore = volumeButton.getFloatValue();
			volumeButton.changeX(e.getX());
			float valueAfter = volumeButton.getFloatValue();
			if (valueBefore != valueAfter){
				audioPlayer.setVolume(valueAfter);
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		if (isIn(e, musicButton))
			musicButton.setMousePressed(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMousePressed(true);
		else if (isIn(e, volumeButton))
			volumeButton.setMousePressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(e, musicButton)) {
			if (musicButton.isMousePressed())
				musicButton.setMuted(!musicButton.isMuted());
				audioPlayer.toggleSongMute();

		} else if (isIn(e, sfxButton)) {
			if (sfxButton.isMousePressed())
				sfxButton.setMuted(!sfxButton.isMuted());
				audioPlayer.toggleEffectMute();
		}

		musicButton.resetBools();
		sfxButton.resetBools();

		volumeButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);

		volumeButton.setMouseOver(false);

		if (isIn(e, musicButton))
			musicButton.setMouseOver(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMouseOver(true);
		else if (isIn(e, volumeButton))
			volumeButton.setMouseOver(true);
	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}


}
