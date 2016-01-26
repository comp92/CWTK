package me.daniel.dwtk.widgets;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import me.daniel.dwtk.widgets.events.EventType;
import me.daniel.dwtk.widgets.events.WidgetEvent;
import me.daniel.dwtk.widgets.events.WidgetListener;

public final class Checkbox extends Widget implements MouseListener {
	
	private boolean checked = false;
	
	public Checkbox(String title, int id, boolean enabled, int x, int y, int width, int height) {
		super(x, y, width, height, enabled, title, id);
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean b) {
		this.checked = b;
	}
	
	public void paint(Graphics g) {
		g.setColor(getBackgroundHoveredColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(getBorderNotfocusedColor());
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		if(isChecked()) {
			g.setColor(getTextHoveredColor());
			g.drawLine(getX()+1, getY()+getHeight()-getHeight()/3, getX()+getWidth()/4, getY()+getHeight()-1);
			g.drawLine(getX()+getWidth()/4,getY()+getHeight()-1, getX()+getWidth()-1, getY()+1);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(!isEnabled()) return;
		if(e.getX()>=getX() && e.getX()<=getX()+getWidth()) {
			if(e.getY()>=getY() && e.getY()<=getY()+getHeight()) {
				WidgetEvent we = new WidgetEvent(EventType.CLICK, this, e);
				for(WidgetListener w : getListeners()) {
					w.run(we);
					if(we.isFinalCancelled()) break;
				}
				if(we.isCancelled()) return;
				setChecked(!isChecked());
			}
		}
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
