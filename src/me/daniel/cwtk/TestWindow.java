package me.daniel.cwtk;

import java.awt.Font;

import javax.swing.JFrame;

import me.daniel.cwtk.widgets.Label;
import me.daniel.cwtk.widgets.ListBox;
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
		WidgetManager wm = new WidgetManager();
		frame.add(wm);
		frame.setVisible(true);
		wm.newButton("Click me!", 0, true, 10, 10, 150, 40);
		wm.newTextBox("Textbox", 1, true, 200, 10, 540, 290);
		wm.newCheckbox("Checkbox", 2, true, 10, 60, 32, 32);
		String[] options = new String[15];
		for(int i = 0; i<options.length;i++) {
			options[i] = "Option " + (i+1);
		}
		final ListBox lb = wm.newListBox("ListBox", 3, true, 10, 100, 150, 200, options);
		lb.addListener(new WidgetListener() {
			
			@Override
			public void run(WidgetEvent e) {
				if(e.getEventType() == EventType.KEYPRESS) {
					lb.addOption("Test");
				}
			}
		});
		Label l = wm.newLabel("Label", 4, 55, 90);
		l.setFont(new Font(Font.SERIF, Font.PLAIN, 48));
		wm.newRadioButtonList("", 5, true, 10, 320, 400, 30, 10);
	}
}