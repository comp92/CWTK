package me.daniel.cwtk.widgets;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import me.daniel.cwtk.widgets.events.EventType;
import me.daniel.cwtk.widgets.events.WidgetEvent;
import me.daniel.cwtk.widgets.events.WidgetListener;

public class TextBox extends Widget implements KeyListener, MouseListener {
	private String text = "";
	private int blinkamt = 30;
	private int blink = blinkamt;
	private int origheight = 0;
	public TextBox(String title, int id, boolean enabled, int x, int y, int width, int height) {
		super(x, y, width, height, enabled, title, id);
		origheight = height;
	}
	
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		if(isFocused()) {
			g.setColor(getBackgroundColor());
		} else {
			g.setColor(getBackgroundNotfocusedColor());
		}
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(getBorderNotfocusedColor());
		if(isFocused()) {
			g.setColor(getBorderColor());
		}
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(getTextColor());
		int tempx = getX() + 3;
		int tempy = getY() + 12;
		int maxheight = getHeight() + 10;
		for(char c : text.toCharArray()) {
			if(c == '\n') {
					tempy += 10;
					tempx = getX() + 3;
			} else {
				if(tempx > getX()+getWidth()-14) {
					tempy += 10;
					tempx = getX() + 3;
				}
				g.drawString(String.valueOf(c), tempx, tempy);
				tempx += 7;
			}
			if(tempy > maxheight) {
				setHeight(getHeight() + 10);
				maxheight = getHeight() + 10;
			} else if(tempy < origheight && maxheight > origheight) {
				setHeight(origheight);
				maxheight = getHeight() + 10;
			}
		}
		blink--;
		if(blink > 0) {
			if(isFocused()) g.fillRect(tempx+1, tempy-8, 2, 12);
		}
		if(blink < -blinkamt) blink = blinkamt;
	}
	
	public static boolean isPrintableChar(char c) {
		Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
		return (!Character.isISOControl(c)) && c != KeyEvent.CHAR_UNDEFINED && block != null && block != Character.UnicodeBlock.SPECIALS;
	}

	public void keyPressed(KeyEvent e) {
		if(!isEnabled()) return;
		if(isFocused() && isEnabled()) {
			WidgetEvent we = new WidgetEvent(EventType.KEYPRESS, this, e);
			for(WidgetListener w : getListeners()) {
				w.run(we);
				if(we.isFinalCancelled()) break;
			}
			if(we.isCancelled()) return;
			blink = blinkamt;
			if(isPrintableChar(e.getKeyChar())) {
				this.setText(getText() + e.getKeyChar());
			} else if(e.getKeyChar() == '\n') {
				this.setText(getText() + "\n");
			} else if(e.getKeyChar() == ' ') {
				this.setText(getText() + " ");
			} else if(e.getKeyChar() == '\b') {
				String currentText = getText();
				if(!currentText.equals("")) {
					currentText = currentText.substring(0, currentText.length()-1);
				}
				setText(currentText);
			} else if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
				setText("");
			}
		}
	}
	
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
				setFocused(true);
			} else {
				setFocused(false);
			}
		} else {
			setFocused(false);
		}
	}
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}