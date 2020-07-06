package com.main.snake2;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

public class SnakeFrame extends Frame {
	private final static int Block = 25;
	private final static int Width = Block * 32;
	private final static int Hight = Block * 32;
	public static void main(String[] args) {
		
		
		JFrame  jf  = new JFrame();
		GamePanel gm = new GamePanel();
		jf.setTitle("重构Snake小游戏");
		jf.setSize(Width, Hight);
		jf.setLocation(500, 300);
		jf.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		jf.add(gm);
		jf.setVisible(true);
	}
	
}
