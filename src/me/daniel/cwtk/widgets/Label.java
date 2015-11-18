package me.daniel.cwtk.widgets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public final class Label extends Widget {
	
	private Font font = new Font(Font.SERIF, Font.PLAIN, 12);
	private Color color;

	private Label(int x, int y, int width, int height, boolean enabled, String text, int id) {
		super(x, y, width, height, enabled, text, id);
	}
	
	public Label(int x, int y, String text, int id, Color color) {
		this(x,y,0,0,true,text,id);
		this.color = color;
	}
	
	public void setFont(Font f) {
		this.font = f;
	}
	
	public Font getFont() {
		return this.font;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(getTitle(), getX(), getY());
	}
}