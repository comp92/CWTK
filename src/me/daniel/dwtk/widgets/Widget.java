package me.daniel.dwtk.widgets;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import me.daniel.dwtk.widgets.events.WidgetListener;

//The parent class of all widgets
public abstract class Widget {
	private int x, y, width, height;
	private boolean enabled, focused = false;
	private String title = "";
	private Color backgroundColor = new Color(235,235,235,120), backgroundHoveredColor = new Color(185,185,185,120), borderFocusedColor = Color.BLUE, textDisabledColor = Color.RED, textHoveredColor = Color.ORANGE,
				  textColor = Color.BLACK, backgroundNotFocused = Color.DARK_GRAY, borderNotFocused = Color.GREEN;
	private int id;
	//A list containing classes that extend (or implement) the interface WidgetListener.
	private List<WidgetListener> listeners = new ArrayList<>();
	
	public Widget(int x, int y, int width, int height, boolean enabled, String text, int id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.enabled = enabled;
		this.title = text;
		this.id = id;
	}
	
	public void paint(Graphics g) {}
	
	public void setBorderNotFocusedColor(Color c) {
		borderNotFocused = c;
	}
	
	public Color getBorderNotfocusedColor() {
		return borderNotFocused;
	}
	
	public void setBackgroundNotfocusedColor(Color c) {
		backgroundNotFocused = c;
	}
	
	public Color getBackgroundNotfocusedColor() {
		return backgroundNotFocused;
	}
	
	public Color getTextColor() {
		return textColor;
	}
	
	public void setTextColor(Color c) {
		textColor = c;
	}
	
	public Color getTextHoveredColor() {
		return textHoveredColor;
	}
	
	public void setTextHoveredColor(Color c) {
		textHoveredColor = c;
	}
	
	public Color getTextDisabledColor() {
		return textDisabledColor;
	}
	
	public void setTextDisabledColor(Color c) {
		textDisabledColor = c;
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	public void setBackgroundColor(Color c) {
		backgroundColor = c;
	}
	
	public Color getBackgroundHoveredColor() {
		return backgroundHoveredColor;
	}
	
	public void setBackgroundHoveredColor(Color c) {
		backgroundHoveredColor = c;
	}
	
	public Color getBorderColor() {
		return borderFocusedColor;
	}
	
	public void setBorderColor(Color c) {
		borderFocusedColor = c;
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

	public boolean isFocused() {
		return focused;
	}

	public void setFocused(boolean focused) {
		this.focused = focused;
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

	public List<WidgetListener> getListeners() {
		return this.listeners;
	}
	
	public void addListener(WidgetListener c) {
		listeners.add(c);
	}
	
	public void removeListener(WidgetListener c) {
		listeners.remove(c);
	}
}
