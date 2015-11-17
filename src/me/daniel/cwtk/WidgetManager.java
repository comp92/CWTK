package me.daniel.cwtk;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import me.daniel.cwtk.widgets.Button;
import me.daniel.cwtk.widgets.TextBox;

public class WidgetManager extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private List<Button> buttons = new ArrayList<Button>();
	private List<TextBox> textboxes = new ArrayList<TextBox>();
	
	public Button newButton(String name, int id, boolean enabled, int x, int y, int width, int height) {
		Button b = new Button(name, id, enabled, x, y, width, height);
		buttons.add(b);
		addMouseListener(b);
		addMouseMotionListener(b);
		return b;
	}
	
	public TextBox newTextBox(String title, int id, boolean enabled, int x, int y, int width, int height) {
		TextBox tb = new TextBox(title, id, enabled, x, y, width, height);
		textboxes.add(tb);
		addMouseListener(tb);
		addKeyListener(tb);
		return tb;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		for(Button b : buttons) {
			b.paint(g);
		}
		
		for(TextBox b : textboxes) {
			b.paint(g);
		}
	}
	
	public WidgetManager() {
		Thread t = new Thread(this);
		t.start();
	}
	
	public void update(Graphics g) {
		Graphics offgc;
	    Image offscreen = null;
	    @SuppressWarnings("deprecation")
		Dimension d = size();
	    offscreen = createImage(d.width, d.height);
	    offgc = offscreen.getGraphics();
	    offgc.setColor(getBackground());
	    offgc.fillRect(0, 0, d.width, d.height);
	    offgc.setColor(getForeground());
	    paint(offgc);
	    g.drawImage(offscreen, 0, 0, this);
	}
	
	public void run() {
		while(true) {
			try {
				pollWidgets();
				repaint();
				Thread.sleep(17);
			} catch(InterruptedException e) {
				
			}
		}
	}
	
	public void pollWidgets() {
		//TODO: Poll widgets
//		for(Button b : buttons) {
//			
//		}
	}
	
}