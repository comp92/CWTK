package me.daniel.cwtk.widgets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Button implements MouseListener, MouseMotionListener {
	
	private int x, y, width, height;
	private String name;
	private int id;
	private boolean hovered = false;
	private boolean enabled = true;
	private boolean isPressed = false;
	
	public Button(String name, int id, boolean enabled, int x, int y, int width, int height) {
		this.name = name;
		this.id = id;
		this.enabled = enabled;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public boolean isPressed() {
		return isPressed;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void paint(Graphics g) {
		if(!hovered) {
			g.setColor(new Color(235,235,235,120));
			g.fillRect(x, y, width, height);
			g.drawRect(x,y,width,height);
			g.setFont(new Font(Font.SERIF, Font.BOLD, 12));
		} else {
			g.setColor(new Color(185,185,185,120));
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLUE);
			g.drawRect(x, y, width, height);
			g.setColor(new Color(50,50,125,50));
			g.fillRect(x, y, width, height);
			g.setFont(new Font(Font.SERIF, Font.BOLD, 12));
		}
		if(!enabled) {
			g.setColor(Color.RED);
		} else {
			if(hovered) {
				g.setColor(Color.ORANGE);
			} else {
				g.setColor(Color.BLACK);
			}
		}
		g.drawString(name, (x+width/2)-25, y+height/2);
	}

	public void mouseMoved(MouseEvent e) {
		if(e.getX()>=x && e.getX()<=x+width) {
			if(e.getY()>=y && e.getY()<=y+height) {
				hovered=true;
			} else {
				hovered=false;
			}
		} else {
			hovered=false;
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getX()>=x && e.getX()<=x+width) {
			if(e.getY()>=y && e.getY()<=y+height) {
				isPressed=true;
			}
		}
		
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	
}