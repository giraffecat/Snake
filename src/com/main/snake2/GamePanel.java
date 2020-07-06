package com.main.snake2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.main.snake.Dir;
import com.main.snake.Images;

public class GamePanel extends JPanel {
	
	int[] SnakeX = new int[50];
	int[] SnakeY = new int[50];
	Dir dir;
	int length;
	boolean start;
	int FoodX,FoodY;
	
	private void init() {
		length = 3;
		for(int i=0; i<SnakeX.length; i++) {
			SnakeX[i] = 0;
			SnakeY[i] = 0;
		}
		SnakeX[0] = 150;
		SnakeY[0] = 100;
		
		SnakeX[1] = 125;
		SnakeY[1] = 100;
		
		SnakeX[2] = 100;
		SnakeY[2] = 100;
		
		FoodX = 300;
		FoodY = 400;
		
		dir = Dir.R;
		start = false;
		
		
	}


	public GamePanel() {
		init();
		Timer timer;
		this.setFocusable(true);
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					start = !start;
				}
				switch(key) {
				case KeyEvent.VK_W:
					if(dir != dir.D)
					dir = Dir.U;
					break;
				case KeyEvent.VK_S:
					if(dir != dir.U)
					dir = Dir.D;
					break;
				case KeyEvent.VK_A:
					if(dir != dir.R)
					dir = Dir.L;
					break;
				case KeyEvent.VK_D:
					if(dir != dir.L)
					dir = Dir.R;
					break;
				}
			}
			
		});
		
		timer = new Timer(100,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(start != false) {
					for(int i=length-1; i > 0; i--) {
						SnakeX[i] = SnakeX[i-1];
						SnakeY[i] = SnakeY[i-1];
					}
					switch(dir) {
					case U:
						SnakeY[0] -= 25;
						break;
					case D:
						SnakeY[0] += 25;
						break;
					case L:
						SnakeX[0] -= 25;
						break;
					case R:
						SnakeX[0] += 25;
						break;
					}
					
					for(int i=1; i < length; i++) {
						if(SnakeX[i] == SnakeX[0] && SnakeY[i] == SnakeY[0]) {
							init();
							start = false;
						}
					}
					
					if(SnakeX[0] == FoodX && SnakeY[0] == FoodY) {
						length++;
						FoodX = 25 + (int)(Math.random()*29)*25;
						FoodY = 75 + (int)(Math.random()*27)*25;
					}
				
					if(SnakeX[0] > 750 ) SnakeX[0] = 25;
					if(SnakeX[0] < 25 ) SnakeX[0] = 750;
					if(SnakeY[0] > 775 ) SnakeY[0] = 75;
					if(SnakeY[0] < 75 ) SnakeY[0] = 750;
				}
				repaint();
			}
		});
		timer.start();
	}

	@Override
	public void paint(Graphics g) {
		this.setBackground(new Color(174,204,174));
		Images.headerImg.paintIcon(this, g, 10, 10);
		
		Color color = g.getColor();
		g.setColor(new Color(187,255,255));
		g.fillRect(25, 75, 750, 700);
		g.setColor(color);
		
		switch(dir) {
		case U:
			Images.upImg.paintIcon(this, g, SnakeX[0], SnakeY[0]);
			break;
		case D:
			Images.downImg.paintIcon(this, g, SnakeX[0], SnakeY[0]);
			break;
		case L:
			Images.leftImg.paintIcon(this, g, SnakeX[0], SnakeY[0]);
			break;
		case R:
			Images.rightImg.paintIcon(this, g, SnakeX[0], SnakeY[0]);
			break;
		}
		
		for(int i=1; i < length; i++) {
			Images.bodyImg.paintIcon(this, g, SnakeX[i], SnakeY[i]);
		}
		Images.foodImg.paintIcon(this, g, FoodX, FoodY);
		
		if(start == false) {
			g.setColor(new Color(133,98,255));
			g.setFont(new Font("consolas",Font.PLAIN, 40));
			g.drawString("空格开始游戏", 300, 400);
			System.out.println("asd");
		}

	}
	
	
}
