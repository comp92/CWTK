package me.daniel.cwtk.widgets.events;

import java.awt.AWTEvent;

import me.daniel.cwtk.widgets.Widget;

//The parent class of all widget events
/**
 * @author Daniel Shaw
 */
public class WidgetEvent {
	private boolean cancelled = false, finalcancel = false;
	private Widget widget;
	private AWTEvent parentEvent;
	private EventType eventType = EventType.ABSTRACT;
	
	/**
	 * @param type - The event type @see EventType
	 * @param source - The source of the event @see Widget
	 * @param event - The parent AWTEvent
	 */
	public WidgetEvent(EventType type, Widget source, AWTEvent event) {
		this.eventType = type;
		this.widget = source;
		this.parentEvent = event;
	}
	
	public AWTEvent getParentEvent() {
		return this.parentEvent;
	}
	
	/**
	 * @param boolean b : Set whether or not the event should cancel or not
	 */
	public void setCancelled(boolean b) {
		if(finalcancel) return;
		this.cancelled = b;
	}
	
	public Widget getAbstractWidget() {
		return this.widget;
	}
	
	public boolean isCancelled() {
		return this.cancelled;
	}
	
	public void setFinalCancel() {
		if(finalcancel) return;
		//This method will cancel the event entirely. No other listeners will fire, and the event processing will halt.
		finalcancel = true;
	}
	
	public boolean isFinalCancelled() {
		return finalcancel;
	}
	
	public EventType getEventType() {
		return this.eventType;
	}
	
}