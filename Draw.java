// Sharon Yang
// February, 2014
// Draw.java
// This program is like Microsoft Painter that allows user to paint pictures.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.net.URL;
import java.net.*;
import java.io.*;

public class Draw extends JApplet implements MouseListener, MouseMotionListener{
	private MyPanel drawing;
	private int x1, y1, stroke;
	private boolean clear, swag, wow, hai, doge;
	private int [][] strokestore;
	private int [] color_arr;
	public static final int doge_size = 50;
	public static final int stroke_total = 50000;
	private Image dogePic, albsPic;
	private Color curr_color;
	
	public Draw (){
		clear = false;
		x1 = y1 = stroke = 0;
		swag = hai = wow = false;
		doge = true;
		strokestore=new int [6][stroke_total];
		ClassLoader c1 = Draw.class.getClassLoader();
		URL imageURL1 = c1.getResource("doge.gif");
		URL imageURL2 = c1.getResource("albs.jpg");
		dogePic = Toolkit.getDefaultToolkit().createImage(imageURL1);
		albsPic = Toolkit.getDefaultToolkit().createImage(imageURL2);
		color_arr = new int [3];
		for (int i = 0; i < color_arr.length; i++) {
		  color_arr[i] = (int)(Math.random() * 256);
		}
		curr_color = new Color(color_arr[0], color_arr[1], color_arr[2]);
	}
	
	public void init(){
		drawing = new MyPanel ();
		setContentPane(drawing);
		Color background_color = new Color (234, 227, 255);
		drawing.setBackground(background_color);
		drawing.addMouseListener(this);
		drawing.addMouseMotionListener(this);
	}
	
	class MyPanel extends JPanel {
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			playGame(g);
		}
		public void playGame(Graphics g){
			setUp(g);
			if (!clear){
				draw(g);
				paintOut(g);
			}
			else if (clear){
				stroke = 0;
				strokestore=new int [6][stroke_total];
				clear = false;
			}
		}
		public void setUp(Graphics g){
			g.setColor(Color.black);
			g.fillRect(5,5,94,70);
			g.setColor(Color.red);
			g.fillRect(104,5,94,70);
			g.setColor(Color.blue);
			g.fillRect(203,5,94,70);
			g.setColor(Color.green);
			g.fillRect(302,5,94,70);
			g.setColor(Color.black);
			g.fillRect(401,5,94,70);
			g.drawImage (dogePic, 50, 90, 400, 400, this); // set up background here
			
			g.setColor(Color.white);
			Font font = new Font("Comic Sans MS", Font.BOLD, 24);
			String str = "RESET  SWAG   WOW    HAI    DOGE";
			g.setFont(font);
			g.drawString(str,13,50);
		}
		public void draw(Graphics g){
		
			if (swag == true && y1 >80){
				strokestore[0][stroke] = x1;
				strokestore[1][stroke] = y1;
				strokestore[2][stroke] = color_arr[0];
				strokestore[3][stroke] = color_arr[1];
				strokestore[4][stroke] = color_arr[2];
				strokestore[5][stroke] = 1;
				
			}
			else if(wow == true && y1 >80){
				strokestore[0][stroke] = x1;
				strokestore[1][stroke] = y1;
				strokestore[2][stroke] = color_arr[0];
				strokestore[3][stroke] = color_arr[1];
				strokestore[4][stroke] = color_arr[2];
				strokestore[5][stroke] = 2;
			}
			else if(hai==true && y1 >80){
				strokestore[0][stroke] = x1;
				strokestore[1][stroke] = y1;
				strokestore[2][stroke] = color_arr[0];
				strokestore[3][stroke] = color_arr[1];
				strokestore[4][stroke] = color_arr[2];
				strokestore[5][stroke] = 3;
			}
			else if(doge==true && y1 >80){
				strokestore[0][stroke] = x1;
				strokestore[1][stroke] = y1;
				strokestore[2][stroke] = -1;
				strokestore[3][stroke] = -1;
				strokestore[4][stroke] = -1;
				strokestore[5][stroke] = 4;
			}
		}
		
		public void paintOut(Graphics g){
		  g.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
			for (int i = 0; i < stroke_total; i++){
				if (strokestore[5][i] == 0){}
				else {
				  if (strokestore[5][i] == 1){
					  curr_color = new Color(strokestore[2][i], strokestore[3][i], strokestore[4][i]);
					  g.setColor(curr_color);
					  g.drawString("swag", strokestore[0][i]-5, strokestore[1][i]-5);
					}
					else if (strokestore[5][i] == 2) {
					  curr_color = new Color(strokestore[2][i], strokestore[3][i], strokestore[4][i]);
					  g.setColor(curr_color);
					  g.drawString("wow", strokestore[0][i]-5, strokestore[1][i]-5);
					}
					
					else if (strokestore[5][i] == 3) {
					  curr_color = new Color(strokestore[2][i], strokestore[3][i], strokestore[4][i]);
					  g.setColor(curr_color);
					  g.drawString("hai", strokestore[0][i]-5, strokestore[1][i]-5);
					}
					
					else if (strokestore[5][i] == 4) {
					  g.drawImage(dogePic, strokestore[0][i]-20, strokestore[1][i]-20, doge_size, doge_size, this);
					}
				}
			}
		}
	}
	
	public void mousePressed(MouseEvent evt){
		x1 = evt.getX();
		y1 = evt.getY();
		for (int i = 0; i < color_arr.length; i++) {
		  color_arr[i] = (int)(Math.random() * 256);
		}
		if((x1>5 && x1<99)&& (y1>5 && y1<75)){
			clear = true;
			drawing.repaint();
		}
		else if((x1>104 && x1<198)&&(y1>5&&y1<75)){
			clear = false;
			swag = true;
			wow=hai=doge = false;
			drawing.repaint();
		}
		else if((x1>203 && x1<297)&&(y1>5&&y1<75)){
			clear = false;
			wow = true;
			swag = hai = doge = false;
			drawing.repaint();
		}
		else if((x1>302 && x1<396)&&(y1>5&&y1<75)){
			clear = false;
			hai = true;
			wow = swag = doge = false;
			drawing.repaint();
		}
		else if((x1>401 && x1<495)&&(y1>5&&y1<75)){
			clear = false;
			doge = true;
			wow = hai = swag = false;
			drawing.repaint();
		}
	  else {
	    stroke += 1;
			drawing.repaint();
	  }
	
	}
	
	public void mouseReleased(MouseEvent evt){
	  if (doge) {
      clear = false;
  		x1 = evt.getX();
  		y1 = evt.getY();
  		stroke += 1;
  		if (y1 > 80)
  		  drawing.repaint();
  	}
	}
	public void mouseClicked(MouseEvent evt){
  	if (doge){
  	  clear = false;
  		x1 = evt.getX();
  		y1 = evt.getY();
  		stroke += 1;
  		if (y1 > 80)
  		  drawing.repaint();
  	}
	}
	public void mouseEntered(MouseEvent evt){}
	
	public void mouseExited(MouseEvent evt){}
	
	public void mouseDragged(MouseEvent evt){
	  if (doge) {
  	  clear = false;
  		x1 = evt.getX();
  		y1 = evt.getY();
  		stroke += 1;
  		if (y1 > 80)
  		  drawing.repaint();
  	}
	}
	public void mouseMoved(MouseEvent evt){
	}
	
} // end class