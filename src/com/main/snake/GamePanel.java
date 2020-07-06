package com.main.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * 
 * @author zhonghui
 * GamePanel 不是一个面版 但是需要继承一个类JPanel gp就具备面版的能力了
 */
public class GamePanel extends JPanel {
	
	private int lenght;
	//定义一个数组专门存储小蛇的x,y坐标
	int[] snakeX = new int[100];
	int[] snakeY = new int[100];
	
	//设置食物的X，Y坐标
	int FoodX;
	int FoodY;
	
	boolean dead = false;
	
	Dir dir = Dir.R;
	boolean start = false;
	Timer timer;
		
	public void init() {
		lenght = 3;
		snakeX[0] = 150;
		snakeY[0] = 300;
		
		snakeX[1] = 125;
		snakeY[1] = 300;
		
		snakeX[2] = 100;
		snakeY[2] = 300;
		dir = Dir.R;
		for(int i = 3; i <snakeX.length; i++) {
			snakeX[i] = 0;
			snakeY[i] = 0;
		}
		
		FoodX = 325;
		FoodY = 75;
	}
	
	
	public GamePanel() {
		init();
		//聚焦在面版上
		this.setFocusable(true);
		//加入键盘监听
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					start = !start;
					repaint();
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
		//第一个参数设置时间 第二个参数设置方法
		timer = new Timer(100,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				switch(dir){
//					case D:
//						if(dir != dir.U)
//						snakeY[0] -= 25;
//						break;
//					case U:
//						if(dir != dir.D)
//						snakeY[0] += 25;
//						break;
//					case L:
//						if(dir != dir.R)
//						snakeX[0] -= 25;
//						break;
//					case R:
//						if(dir != dir.L)
//						snakeX[0] -= 25;
//						break;
//				}

				if(start) {
					for(int i = lenght-1; i > 0; i--) {
						snakeX[i] = snakeX[i-1];
						snakeY[i] = snakeY[i-1];	
					}
					//防止蛇飞出边界
					if(snakeX[0]>750) snakeX[0] = 0;
					if(snakeX[0]<0) snakeX[0] = 775;
					if(snakeY[0]>750) snakeY[0] = 0;
					if(snakeY[0]<0) snakeY[0] = 775;
					//调整蛇头坐标
					switch(dir) {
					case R:
						snakeX[0] += 25;
						break;
					case L:
						snakeX[0] -= 25;
						break;
					case U:
						snakeY[0] -= 25;
						break;
					case D:
						snakeY[0] += 25;
						break;
					}
					//当蛇头的坐标和食物的坐标相重合是 食物被吃掉
					if(snakeX[0] == FoodX && snakeY[0] == FoodY){
						lenght++;
						FoodX = (int)(Math.random()*31)*25;
						FoodY = (int)(Math.random()*31)*25;
					}
					for(int i=1; i<snakeX.length;i++) {
						if(snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0] ) {
							start = false;
							init();
						}
					}
					
					//蛇的坐标改完后要重绘
					repaint();
				}
				
			}
		});
		//定时器开始
		timer.start();
	}
	//初始化定时器对象


	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * 这个方法就类似main方法，当需要画图的时候，底层就自动调用此方法
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		this.setBackground(new Color(174,204,174));
		
		//画一个图片
		Images.headerImg.paintIcon(this, g, 10, 10);
		
		//调节画笔颜色
		g.setColor(new Color(187,255,255));
		
		g.fillRect(10, 70, 770, 685);
		
		switch(dir) {
		case R:
			Images.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
			break;
		case L:
			Images.leftImg.paintIcon(this, g, snakeX[0], snakeY[0]);
			break;
		case U:
			Images.upImg.paintIcon(this, g, snakeX[0], snakeY[0]);
			break;
		case D:
			Images.downImg.paintIcon(this, g, snakeX[0], snakeY[0]);
			break;
		}
		for(int i=1; i<snakeX.length;i++) {
			if(snakeX[i]!=0 || snakeY[i]!=0)
			Images.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);
		}
		Images.foodImg.paintIcon(this, g, FoodX, FoodY);
		if(start == false ) {
			//面版上写文字游戏暂停
			g.setColor(new Color(133,98,255));
			g.setFont(new Font("consolas",Font.PLAIN, 40));
			g.drawString("点击游戏开始", 250, 300);
		}

	}
}
