package me.daniel.cwtk.widgets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextBox extends Widget implements KeyListener, MouseListener {
	private String text = "";
	private int blinkamt = 30;
	private int blink = blinkamt;
	public TextBox(String title, int id, boolean enabled, int x, int y, int width, int height) {
		super(x, y, width, height, enabled, title, id);
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
			g.setColor(Color.LIGHT_GRAY);
		} else {
			g.setColor(Color.DARK_GRAY);
		}
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.GREEN);
		if(isFocused()) {
			g.setColor(Color.CYAN);
		}
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.BLACK);
		int tempx = getX() + 3;
		int tempy = getY() + 12;
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
		}
		blink--;
		if(blink > 0) {
			if(isFocused()) g.fillRect(tempx+1, tempy-8, 2, 12);
		}
		if(blink < -blinkamt) blink = blinkamt;
	}
	
	private boolean isPrintableChar(char c) {
		Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
		return (!Character.isISOControl(c)) && c != KeyEvent.CHAR_UNDEFINED && block != null && block != Character.UnicodeBlock.SPECIALS;
	}

	public void keyPressed(KeyEvent e) {
		if(isFocused() && isEnabled()) {
			blink = blinkamt;
			if(isPrintableChar(e.getKeyChar())) {
				this.setText(getText() + e.getKeyChar());
//				curposX++;
			} else if(e.getKeyChar() == '\n') {
				this.setText(getText() + "\n");
//				curposX=0;
//				curposY++;
			} else if(e.getKeyChar() == ' ') {
				this.setText(getText() + " ");
			} else if(e.getKeyChar() == '\b') {
				String currentText = getText();
				if(!currentText.equals("")) {
					currentText = currentText.substring(0, currentText.length()-1);
				}
				setText(currentText);
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
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}