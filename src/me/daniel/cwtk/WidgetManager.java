package me.daniel.cwtk;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import me.daniel.cwtk.widgets.Button;
import me.daniel.cwtk.widgets.Checkbox;
import me.daniel.cwtk.widgets.Label;
import me.daniel.cwtk.widgets.ListBox;
import me.daniel.cwtk.widgets.RadioButtonList;
import me.daniel.cwtk.widgets.TextBox;
import me.daniel.cwtk.widgets.Widget;

public class WidgetManager extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private static final double VERSION = 0.58d;
	
	private final List<Button> buttons = new ArrayList<>();
	private final List<TextBox> textboxes = new ArrayList<>();
	private final List<Checkbox> checkboxes = new ArrayList<>();
	private final List<ListBox> listboxes = new ArrayList<>();
	private final List<Widget> abstractWidgets = new ArrayList<>();
	private final List<Label> labels = new ArrayList<>();
	private final List<RadioButtonList> radiobuttons = new ArrayList<>();
	
	public RadioButtonList newRadioButtonList(String title, int id, boolean enabled, int x, int y, int width, int height, int numOptions) {
		RadioButtonList rbl = new RadioButtonList(title, id, enabled, x, y, width, height, numOptions);
		radiobuttons.add(rbl);
		return rbl;
	}
	
	public void removeRadioButtonList(RadioButtonList rbl) {
		radiobuttons.remove(rbl);
	}
	
	public Label newLabel(String name, int id, int x, int y) {
		Label l = new Label(x, y, name, id, Color.WHITE);
		labels.add(l);
		return l;
	}
	
	public void removeLabel(Label l) {
		labels.remove(l);
	}
	
	public Button newButton(String name, int id, boolean enabled, int x, int y, int width, int height) {
		Button b = new Button(name, id, enabled, x, y, width, height);
		buttons.add(b);
		addMouseListener(b);
		addMouseMotionListener(b);
		return b;
	}
	
	public void removeButton(Button b) {
		removeMouseListener(b);
		removeMouseMotionListener(b);
		buttons.remove(b);
	}
	
	public void addWidget(Widget w) {
		abstractWidgets.add(w);
		Class<?> widgetClass = w.getClass();
		if(MouseListener.class.isAssignableFrom(widgetClass)) {
			addMouseListener((MouseListener) w);
		}
		if(KeyListener.class.isAssignableFrom(widgetClass)) {
			addKeyListener((KeyListener) w);
		}
		if(MouseMotionListener.class.isAssignableFrom(widgetClass)) {
			addMouseMotionListener((MouseMotionListener) w);
		}
	}
	
	public void removeWidget(Widget w) {
		abstractWidgets.remove(w);
		Class<?> widgetClass = w.getClass();
		if(MouseListener.class.isAssignableFrom(widgetClass)) {
			removeMouseListener((MouseListener) w);
		}
		if(KeyListener.class.isAssignableFrom(widgetClass)) {
			removeKeyListener((KeyListener) w);
		}
		if(MouseMotionListener.class.isAssignableFrom(widgetClass)) {
			removeMouseMotionListener((MouseMotionListener) w);
		}
	}
	
	public ListBox newListBox(String title, int id, boolean enabled, int x, int y, int width, int height, String[] options) {
		ListBox lb = new ListBox(title, id, enabled, x, y, width, height, options);
		listboxes.add(lb);
		addMouseListener(lb);
		addKeyListener(lb);
		return lb;
	}
	
	public void removeListBox(ListBox lb) {
		removeMouseListener(lb);
		removeKeyListener(lb);
		listboxes.remove(lb);
	}
	
	public TextBox newTextBox(String title, int id, boolean enabled, int x, int y, int width, int height) {
		TextBox tb = new TextBox(title, id, enabled, x, y, width, height);
		textboxes.add(tb);
		addMouseListener(tb);
		addKeyListener(tb);
		return tb;
	}
	
	public void removeTextBox(TextBox tb) {
		removeMouseListener(tb);
		removeKeyListener(tb);
		textboxes.remove(tb);
	}
	
	public Checkbox newCheckbox(String title, int id, boolean enabled, int x, int y, int width, int height) {
		Checkbox b = new Checkbox(title, id, enabled, x, y, width, height);
		checkboxes.add(b);
		addMouseListener(b);
		return b;
	}
	
	public void removeCheckbox(Checkbox cb) {
		removeMouseListener(cb);
		checkboxes.remove(cb);
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
		
		for(Checkbox b : checkboxes) {
			b.paint(g);
		}
		
		for(ListBox b : listboxes) {
			b.paint(g);
		}
		
		for(Label b : labels) {
			b.paint(g);
		}
		
		for(RadioButtonList b : radiobuttons) {
			b.paint(g);
		}
		
		for(Widget w : abstractWidgets) {
			w.paint(g);
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
				repaint();
				Thread.sleep(17);
			} catch(InterruptedException e) {
				
			}
		}
	}
	
	public static String getInfo() {
		return "CWTK version " + String.valueOf(VERSION) + " by Daniel Shaw";
	}
	
}