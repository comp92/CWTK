package me.daniel.cwtk.widgets.events;

public enum EventType {
	/*
	 * EventType    | AWTEvent cast
	 * -------------+----------------
	 * CLICK		: MouseEvent
	 * KEYPRESS		: KeyEvent
	 * MOUSEMOVE 	: MouseEvent
	 * ABSTRACT		: Holder event type for default WidgetEvent. Should never occur.
	 */
	CLICK, KEYPRESS, ABSTRACT, MOUSEMOVE;
}