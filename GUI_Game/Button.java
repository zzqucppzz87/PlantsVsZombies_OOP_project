package GUI_Game;

import java.awt.Graphics;

public interface Button {
    public void update();

    public void resetBools();

    public void draw(Graphics g);

	public boolean isMouseOver();

	public void setMouseOver(boolean mouseOver);

	public boolean isMousePressed();

	public void setMousePressed(boolean mousePressed);
}
