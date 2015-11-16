package me.daniel.cwtk.widgets.events;

import me.daniel.cwtk.widgets.Widget;

public class WidgetFocusEvent extends WidgetEvent {
	public WidgetFocusEvent(Widget src) {
		super("Widget focus event", src);
	}
}