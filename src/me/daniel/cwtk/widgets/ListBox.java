package me.daniel.cwtk.widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ListBox extends Widget implements KeyListener, MouseListener {
	private List<String> options;
	private int selected;
	private String selectedString = "";
	private String[] visibleoptions;
	
	public ListBox(String title, int id, boolean enabled, int x, int y, int width, int height, String[] options) {
		super(x, y, width, height, enabled, title, id);
		for(String s : options) {
			this.options.add(s);
		}
	}
	
	public String getSelectedString() {
		return this.selectedString;
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
	
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(getX(),getY(),getWidth(),getHeight());
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
		if(e.getX()>=getX() && e.getX()<=getX()+getWidth()) {
			if(e.getY()>=getY() && e.getY()<=getY()+getHeight()) {
				setFocused(true);
			} else {
				setFocused(false);
			}
		} else {
			setFocused(false);
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
}