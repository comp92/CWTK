package me.daniel.cwtk.widgets;

import java.awt.Graphics;

public final class RadioButtonList extends Widget {
	
	private int numOptions = 0;
	private int selected = 0;
	
	public RadioButtonList(String title, int id, boolean enabled, int x, int y, int width, int height, int numOptions) {
		super(x, y, width, height, enabled, title, id);
		this.numOptions = numOptions;
	}
	
	public void paint(Graphics g) {
		g.setColor(getBackgroundNotfocusedColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(getBorderNotfocusedColor());
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(getBackgroundColor());
		int tempx = getX() + 2;
		int tempy = getY() + 10;
		for(int i = 0; i < numOptions; i++) {
			g.fillOval(tempx, tempy, getWidth()/numOptions/2, getWidth()/numOptions/2);
			tempx+=getWidth()/numOptions/2+10;
		}
	}
	
}