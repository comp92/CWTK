package me.daniel.cwtk;

import javax.swing.JFrame;

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
		wm.newButton("Test", 0, true, 10, 10, 100, 40);
		wm.newTextBox("Textbox", 1, true, 200, 10, 400, 200);
	}
}