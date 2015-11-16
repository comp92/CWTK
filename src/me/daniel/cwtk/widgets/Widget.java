package me.daniel.cwtk.widgets;

import java.util.ArrayList;
import java.util.List;

import me.daniel.cwtk.widgets.events.WidgetListener;

//The parent class of all widgets
public abstract class Widget {
	private int x, y, width, height;
	private boolean enabled, focused = false;
	private String title = "";
	private int id;
	//A list containing classes that extend (or implement) the interface WidgetListener.
	private List<Class<? extends WidgetListener>> listeners = new ArrayList<Class<? extends WidgetListener>>();
	
	public Widget(int x, int y, int width, int height, boolean enabled, String text, int id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.enabled = enabled;
		this.title = text;
		this.id = id;
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
		for(Class<? extends WidgetListener> c : getListeners()) {
			
		}
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

	public List<Class<? extends WidgetListener>> getListeners() {
		return this.listeners;
	}
	
	public void addListener(Class<? extends WidgetListener> c) {
		listeners.add(c);
	}
	
	public void removeListener(Class<? extends WidgetListener> c) {
		listeners.remove(c);
	}
}