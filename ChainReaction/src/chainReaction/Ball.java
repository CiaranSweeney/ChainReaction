package chainReaction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ball {
	private double posX;
	private double posY;
	private boolean forward;
	private boolean down;
	private Color colour;
	private double size;
	private double speed;
	
	public Ball(double x, double y, boolean f, boolean d, Color c,double s, double sp){
		posX=x;
		posY=y;
		forward=f;
		down=d;
		colour=c;
		size=s;
		speed=sp;
		posX=posX-(size/2);
		posY=posY-(size/2);
	}
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(colour);
		Ellipse2D.Double circle = new Ellipse2D.Double(posX, posY, size, size);//
		g2d.fill(circle);//
		//g.drawOval(0,0,0,0);//sort this out me
	}
	
	public double getPosX() {
		return posX;
	}
	public double getPosY() {
		return posY;
	}
	public Color getColour() {
		return colour;
	}
	public void setForward(boolean forward) {
		this.forward = forward;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public void updatePostions(){
		if(forward==true){
			posX+=speed;
		}
		else{
			posX-=speed;
		}
		if(down==true){
			posY+=speed;
		}
		else{
			posY-=speed;
		}
	}
}
