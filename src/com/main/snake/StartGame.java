package com.main.snake;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class StartGame {
	public static void main(String[] args) {
		JFrame jf  = new JFrame();
		GamePanel gp = new GamePanel();
		
		jf.setTitle("snake game");
		//获取屏幕高和宽
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;

		jf.setBounds((width-800)/2, (height-800)/2, 800, 800);		
		//关闭窗体的时候，程序关闭
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//设置窗体大小不可变
		jf.setResizable(false);
		
		jf.add(gp);
		//

		jf.setVisible(true);

	}
}
