package me.daniel.dwtk.widgets;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import me.daniel.dwtk.widgets.events.EventType;
import me.daniel.dwtk.widgets.events.WidgetEvent;
import me.daniel.dwtk.widgets.events.WidgetListener;

public final class ListBox extends Widget implements KeyListener, MouseListener {
	private List<String> options = new ArrayList<>();
	private int selected = 0;
	
	public ListBox(String title, int id, boolean enabled, int x, int y, int width, int height, String[] options) {
		super(x, y, width, height, enabled, title, id);
		for(String s : options) {
			this.options.add(s);
		}
	}
	
	public void addOption(String s) {
		options.add(s);
	}
	
	public void removeOption(String s) {
		if(options.get(selected).equals(s)) {
			if(selected > 0) selected--;
			else selected = options.size()-1;
		}
		options.remove(s);
	}
	
	public String getSelectedString() {
		return this.options.get(selected);
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
	
	public void paint(Graphics g) {
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		if(isFocused()) {
			g.setColor(getBackgroundColor());
		} else {
			g.setColor(getBackgroundNotfocusedColor());
		}
		g.fillRect(getX(),getY(),getWidth(),getHeight());
		if(isFocused()) {
			g.setColor(getBorderColor());
		} else {
			g.setColor(getBorderNotfocusedColor());
		}
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		int tempy = getY() + 10;
		for(int i = 0; i < options.size(); i++) {
			if(i == selected) {
				if(isFocused()) {
					g.setColor(getTextHoveredColor());
				} else {
					g.setColor(getBorderColor());
				}
				g.fillRect(getX()+1,tempy-9,getX()+getWidth()-11,11);
			}
			if(isEnabled()) {
				g.setColor(getTextColor());
			} else {
				g.setColor(getTextDisabledColor());
			}
			g.drawString(options.get(i), getX()+1, tempy);
			tempy+=10;
		}
	}

	public void keyPressed(KeyEvent e) {
		if(isFocused()) {
			WidgetEvent we = new WidgetEvent(EventType.KEYPRESS, this, e);
			for(WidgetListener w : getListeners()) {
				w.run(we);
				if(we.isFinalCancelled()) break;
			}
			if(we.isCancelled()) return;
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				if(getSelected() != 0) {
					selected--;
				} else {
					selected = options.size()-1;
				}
			} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				if(getSelected() < options.size()-1) {
					selected++;
				} else {
					selected=0;
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getX()>=getX() && e.getX()<=getX()+getWidth()) {
			if(e.getY()>=getY() && e.getY()<=getY()+getHeight()) {
				WidgetEvent we = new WidgetEvent(EventType.CLICK, this, e);
				for(WidgetListener w : getListeners()) {
					w.run(we);
					if(we.isFinalCancelled()) break;
				}
				if(we.isCancelled()) return;
				if(isFocused()) {
					int y = getY()/10 + -getHeight()/10 + e.getY()/10; //This is actual sorcery, I have only a basic understanding of why this works.
					if(y > options.size()-1) selected = options.size()-1;
					else selected = y;
					if(selected < 0) selected = 0;
				}
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
