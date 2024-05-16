package GUI_Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

public class VolumeButton extends PauseButton implements Button{

	private BufferedImage[] imgs;
	private BufferedImage slider;
	private int index = 0;
	private boolean mouseOver, mousePressed;
	private int buttonX, minX, maxX;
	private float floatValue = 0f;


	public VolumeButton(int x, int y, int width, int height) {
		super(x + width / 2, y, 22, height);
		bounds.x -= 22 / 2;
		buttonX = x + width / 2;
		this.x = x;
		this.width = width;
		minX = x + 22 / 2;
		maxX = x + width - 22 / 2;
		loadImgs();
	}

	private void loadImgs() {
		BufferedImage temp = LoadSave.getLoadSave().GetSpriteAtlas("volume_button1.png");
		imgs = new BufferedImage[3];
		for (int i = 0; i < imgs.length; i++)
			imgs[i] = temp.getSubimage(i * 22, 0, 22, height);

		slider = temp.getSubimage(3 * 22, 0, width, height);	
	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;

	}

	public void draw(Graphics g) {

		g.drawImage(slider, x, y, width, height, null);
		g.drawImage(imgs[index], buttonX - 22 / 2, y, 22, height, null);
	}

	public void changeX(int x) {
		if (x < minX)
			buttonX = minX;
		else if (x > maxX)
			buttonX = maxX;
		else
			buttonX = x;
		updateFloatValue();
		bounds.x = buttonX - 22 / 2;

	}

	private void updateFloatValue(){
		float range = maxX - minX;
		float value =  buttonX - minX;
		floatValue = value/range;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public float getFloatValue(){
		return floatValue;
	}
}
