package me.daniel.cwtk;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import me.daniel.cwtk.widgets.Button;
import me.daniel.cwtk.widgets.TextBox;
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
		
		//Sample button
		Button b = wm.newButton("Test", 0, true, 10, 10, 100, 40);
		//WidgetListener has a method run which takes a WidgetEvent.
		b.addListener(new WidgetListener() {
			@Override
			public void run(WidgetEvent e) {
				//getEventType returns an EventType (enum). Use this to detect the type of event fired.
				if(e.getEventType() == EventType.MOUSEMOVE) //If the event is a mouse move event
					System.out.println("Event at button, type = " + e.getEventType());
			}
		});
		
		
		TextBox tb = wm.newTextBox("Textbox", 1, true, 200, 10, 400, 200);
		tb.addListener(new WidgetListener() {
			@Override
			public void run(WidgetEvent e) {
				if(e.getEventType() != EventType.KEYPRESS) return;
				KeyEvent evt = (KeyEvent) e.getParentEvent();
//				e.setCancelled(true); //Cancel the event AFTER this run method is over. All other listeners will run though
				if(!TextBox.isPrintableChar(evt.getKeyChar())) return;
				System.out.println("Key pressed in text box with id of " + e.getAbstractWidget().getId() + " is \"" + evt.getKeyChar() + "\"");
			}
		});
		wm.newCheckbox("", 2, true, 100, 100, 32, 32);
	}
}