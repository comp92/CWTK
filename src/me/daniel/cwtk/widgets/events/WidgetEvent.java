package me.daniel.cwtk.widgets.events;

import java.awt.AWTEvent;

import me.daniel.cwtk.widgets.Widget;

//The parent class of all widget events
public class WidgetEvent {
	
	private boolean cancelled = false;
	private Widget widget;
	private AWTEvent parentEvent;
	private EventType eventType = EventType.ABSTRACT;
	
	public WidgetEvent(EventType type, Widget source, AWTEvent event) {
		this.eventType = type;
		this.widget = source;
		this.parentEvent = event;
	}
	
	public AWTEvent getParentEvent() {
		return this.parentEvent;
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
	
	public EventType getEventType() {
		return this.eventType;
	}
	
}