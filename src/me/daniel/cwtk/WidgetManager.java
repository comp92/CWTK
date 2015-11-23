package me.daniel.cwtk;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
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

public class WidgetManager {
	private static final double VERSION = 1.1d;
	
	private Canvas canvas;
	
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
		canvas.addMouseListener(rbl);
		return rbl;
	}
	
	public void removeRadioButtonList(RadioButtonList rbl) {
		radiobuttons.remove(rbl);
		canvas.removeMouseListener(rbl);
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
		canvas.addMouseListener(b);
		canvas.addMouseMotionListener(b);
		return b;
	}
	
	public void removeButton(Button b) {
		canvas.removeMouseListener(b);
		canvas.removeMouseMotionListener(b);
		buttons.remove(b);
	}
	
	public void addWidget(Widget w) {
		abstractWidgets.add(w);
		Class<?> widgetClass = w.getClass();
		if(MouseListener.class.isAssignableFrom(widgetClass)) {
			canvas.addMouseListener((MouseListener) w);
		}
		if(KeyListener.class.isAssignableFrom(widgetClass)) {
			canvas.addKeyListener((KeyListener) w);
		}
		if(MouseMotionListener.class.isAssignableFrom(widgetClass)) {
			canvas.addMouseMotionListener((MouseMotionListener) w);
		}
	}
	
	public void removeWidget(Widget w) {
		abstractWidgets.remove(w);
		Class<?> widgetClass = w.getClass();
		if(MouseListener.class.isAssignableFrom(widgetClass)) {
			canvas.removeMouseListener((MouseListener) w);
		}
		if(KeyListener.class.isAssignableFrom(widgetClass)) {
			canvas.removeKeyListener((KeyListener) w);
		}
		if(MouseMotionListener.class.isAssignableFrom(widgetClass)) {
			canvas.removeMouseMotionListener((MouseMotionListener) w);
		}
	}
	
	public ListBox newListBox(String title, int id, boolean enabled, int x, int y, int width, int height, String[] options) {
		ListBox lb = new ListBox(title, id, enabled, x, y, width, height, options);
		listboxes.add(lb);
		canvas.addMouseListener(lb);
		canvas.addKeyListener(lb);
		return lb;
	}
	
	public void removeListBox(ListBox lb) {
		canvas.removeMouseListener(lb);
		canvas.removeKeyListener(lb);
		listboxes.remove(lb);
	}
	
	public TextBox newTextBox(String title, int id, boolean enabled, int x, int y, int width, int height) {
		TextBox tb = new TextBox(title, id, enabled, x, y, width, height);
		textboxes.add(tb);
		canvas.addMouseListener(tb);
		canvas.addKeyListener(tb);
		return tb;
	}
	
	public void removeTextBox(TextBox tb) {
		canvas.removeMouseListener(tb);
		canvas.removeKeyListener(tb);
		textboxes.remove(tb);
	}
	
	public Checkbox newCheckbox(String title, int id, boolean enabled, int x, int y, int width, int height) {
		Checkbox b = new Checkbox(title, id, enabled, x, y, width, height);
		checkboxes.add(b);
		canvas.addMouseListener(b);
		return b;
	}
	
	public void removeCheckbox(Checkbox cb) {
		canvas.removeMouseListener(cb);
		checkboxes.remove(cb);
	}
	
	public void paint(Graphics g) {
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
	
	public WidgetManager(Canvas c) {
		this.canvas = c;
	}
	
	public static String getInfo() {
		return "CWTK version " + String.valueOf(VERSION) + " by Daniel Shaw";
	}
	
}