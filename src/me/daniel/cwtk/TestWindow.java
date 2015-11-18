package me.daniel.cwtk;

import javax.swing.JFrame;

import me.daniel.cwtk.widgets.Button;
import me.daniel.cwtk.widgets.events.EventType;
import me.daniel.cwtk.widgets.events.WidgetEvent;
import me.daniel.cwtk.widgets.events.WidgetListener;

/*
 * This class implements a sample application using CWTK.
 * It shows off the widgets in a window that allows for 
 * testing.
 * */

public class TestWindow {
	public static void main(String... args) {
		JFrame frame = new JFrame("CWTK test window");
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WidgetManager wm = new WidgetManager(); //WidgetManager extends canvas
		frame.add(wm);
		frame.setVisible(true);
		Button b = wm.newButton("Test", 0, true, 10, 10, 100, 40);
		b.addListener(new WidgetListener() {
			@Override
			public void run(WidgetEvent e) {
				if(e.getEventType() == EventType.MOUSEMOVE)
					System.out.println("Event at button, type = " + e.getEventType());
			}
		});
		wm.newTextBox("Textbox", 1, true, 200, 10, 400, 200);
		wm.newCheckbox("", 2, true, 100, 100, 32, 32);
	}
}