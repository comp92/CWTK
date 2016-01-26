package me.daniel.dwtk.widgets;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import me.daniel.dwtk.widgets.events.EventType;
import me.daniel.dwtk.widgets.events.WidgetEvent;
import me.daniel.dwtk.widgets.events.WidgetListener;

public final class RadioButtonList extends Widget implements MouseListener {
	
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
		int tempx = getX() + 2;
		int tempy = getY() + 10;
		for(int i = 0; i < numOptions; i++) {
			if(i == selected) {
				g.setColor(getTextHoveredColor());
			} else {
				g.setColor(getBackgroundColor());
			}
			g.fillOval(tempx, tempy-getHeight()/5, 16, 16);
			tempx+=20;
		}
	}
	
	public int getSelected() {
		return selected;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX()>=getX() && e.getX()<=getX()+getWidth()) {
			if(e.getY()>=getY() && e.getY()<=getY()+getHeight()) {
				WidgetEvent we = new WidgetEvent(EventType.CLICK, this, e);
				for(WidgetListener w : getListeners()) {
					w.run(we);
					if(we.isFinalCancelled()) break;
				}
				if(we.isCancelled()) return;
				int x = -getX()/20 + e.getX()/20;
				if(x > numOptions-1) x = numOptions-1;
				if(x < 0) x = 0;
				selected = x;
			}
		}
	}
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
