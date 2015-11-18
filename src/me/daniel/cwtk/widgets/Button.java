package me.daniel.cwtk.widgets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import me.daniel.cwtk.widgets.events.EventType;
import me.daniel.cwtk.widgets.events.WidgetEvent;
import me.daniel.cwtk.widgets.events.WidgetListener;

public class Button extends Widget implements MouseListener, MouseMotionListener {
	
	private boolean hovered = false;
	private boolean isPressed = false;
	
	public Button(String title, int id, boolean enabled, int x, int y, int width, int height) {
		super(x, y, width, height, enabled, title, id);
	}
	
	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void paint(Graphics g) {
		if(!hovered) {
			g.setColor(getBackgroundColor());
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.drawRect(getX(),getY(),getWidth(),getHeight());
			g.setFont(new Font(Font.SERIF, Font.BOLD, 12));
		} else {
			g.setColor(getBackgroundHoveredColor());
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(getBorderColor());
			g.drawRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(new Color(50,50,125,50));
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setFont(new Font(Font.SERIF, Font.BOLD, 12));
		}
		if(!isEnabled()) {
			g.setColor(getTextDisabledColor());
		} else {
			if(hovered) {
				g.setColor(getTextHoveredColor());
			} else {
				g.setColor(getTextColor());
			}
		}
		g.drawString(getTitle(), (getX()+getWidth()/2)-25, getY()+getHeight()/2);
	}

	public void mouseMoved(MouseEvent e) {
		if(!isEnabled()) return;
		if(e.getX()>=getX() && e.getX()<=getX()+getWidth()) {
			if(e.getY()>=getY() && e.getY()<=getY()+getHeight()) {
				WidgetEvent we = new WidgetEvent(EventType.MOUSEMOVE, this, e);
				for(WidgetListener w : getListeners()) {
					w.run(we);
					if(we.isFinalCancelled()) break;
				}
				if(we.isCancelled()) return;
				hovered=true;
			} else {
				hovered=false;
			}
		} else {
			hovered=false;
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		if(!isEnabled()) return;
		if(e.getX()>=getX() && e.getX()<=getX()+getWidth()) {
			if(e.getY()>=getY() && e.getY()<=getY()+getHeight()) {
				WidgetEvent we = new WidgetEvent(EventType.CLICK, this, e);
				for(WidgetListener w : getListeners()) {
					w.run(we);
				}
				if(we.isCancelled()) return;
				isPressed=true;
			}
		}
	}

	public void mouseDragged(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}