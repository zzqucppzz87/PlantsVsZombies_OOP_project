package GUI_Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

public class UrmButton extends PauseButton implements Button{
	private BufferedImage[] imgs;
    private String image;
	private int index;
	private boolean mouseOver, mousePressed;


	public UrmButton(int x, int y, int width, int height,String image) {
		super(x, y, width, height);
        this.image = image;
		loadImgs();
	}

	private void loadImgs(){
		BufferedImage temp = LoadSave.getLoadSave().GetSpriteAtlas(image);
		imgs = new BufferedImage[3];
		for (int i = 0; i < imgs.length; i++){
			imgs[i] = temp.getSubimage(i * width, 0, width, height);
		}
	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;		

	}

	public void draw(Graphics g) {
		g.drawImage(imgs[index], x, y, width, height, null);
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

}
