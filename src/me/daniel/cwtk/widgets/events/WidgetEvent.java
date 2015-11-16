package me.daniel.cwtk.widgets.events;

import me.daniel.cwtk.widgets.Widget;

//The parent class of all widget events
public abstract class WidgetEvent {
	
	private boolean cancelled = false;
	private Widget widget;
	private String eventType = "Abstract Widget Event";
	
	public WidgetEvent(String type, Widget source) {
		this.eventType = type;
		this.widget = source;
	}
	
	public void setCancelled(boolean b) {
		this.cancelled = b;
	}
	
	public Widget getAbstractWidget() {
		return this.widget;
	}
	
	public boolean isCancelled() {
		return this.cancelled;
	}
	
	public String getEventType() {
		return this.eventType;
	}
}