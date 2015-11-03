package me.daniel.cwtk.widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ListBox implements KeyListener, MouseListener {
	private int x, y, width, height, id;
	private boolean enabled = true, focused = false;
	private List<String> options;
	private int selected;
	private String selectedString = "";
	private String[] visibleoptions;
	
	public ListBox(String[] options, int x, int y, int width, int height, boolean enabled, int id) {
		for(String s : options) {
			this.options.add(s);
		}
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.enabled = enabled;
		this.id = id;
	}
	
	public String getSelectedString() {
		return this.selectedString;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isFocused() {
		return focused;
	}

	public void setFocused(boolean focused) {
		this.focused = focused;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public String[] getVisibleoptions() {
		return visibleoptions;
	}

	public void setVisibleoptions(String[] visibleoptions) {
		this.visibleoptions = visibleoptions;
	}

	public int getId() {
		return id;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x,y,width,height);
	}

	public void keyPressed(KeyEvent e) {
		if(isFocused()) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				if(getSelected() != 0) {
					selected--;
				}
			} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				if(getSelected() < options.size()) {
					selected++;
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getX()>=x && e.getX()<=x+width) {
			if(e.getY()>=y && e.getY()<=y+height) {
				focused=true;
			} else {
				focused=false;
			}
		} else {
			focused=false;
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
}