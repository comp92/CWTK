package me.daniel.cwtk.widgets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextBox implements KeyListener, MouseListener {
	private String text = "";
	private int x, y, width, height;
	private boolean enabled, focused = false;
	private String title = "";
	private int id;
//	private int curposX = 0, curposY = 0;
	private int blinkamt = 30;
	private int blink = blinkamt;
	public TextBox(String title, int id, boolean enabled, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.enabled = enabled;
		this.title = title;
		this.id = id;
	}
	
	public boolean isFocused() {
		return focused;
	}
	
	public void setFocused(boolean b) {
		this.focused = b;
	}
	
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
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


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getId() {
		return id;
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		if(isFocused()) {
			g.setColor(Color.LIGHT_GRAY);
		} else {
			g.setColor(Color.DARK_GRAY);
		}
		g.fillRect(x, y, width, height);
		g.setColor(Color.GREEN);
		if(isFocused()) {
			g.setColor(Color.CYAN);
		}
		g.drawRect(x,y,width,height);
		g.setColor(Color.BLACK);
		int tempx = x + 3;
		int tempy = y + 12;
		for(char c : text.toCharArray()) {
			if(c == '\n') {
					tempy += 10;
					tempx = x + 3;
			} else {
				if(tempx > x+width-14) {
					tempy += 10;
					tempx = x + 3;
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
//					curposX--;
//					if(currentText.contains("\n")) {
//						if(currentText.substring(currentText.length()-1).equals("\n")) {
//							curposY--;
//						}
//					}
				}
				setText(currentText);
			}
			//TODO
//			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
//				if(curposX!=0) curposX--;
//			} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
//				if(curposX<getText().length()) {
//					curposX++;
//				}
//			}
			//if(tempx > x+width-14) {
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
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}