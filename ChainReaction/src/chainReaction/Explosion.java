package chainReaction;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Explosion {
	//Generating the fields
	private double posX;
	private double posY;
	private Color colour;
	private double size;
	private int chain;
	private boolean first;
	private double xCentre;
	private double yCentre;
	public Explosion(double x, double y,Color c,double s,int ch,boolean b){
		posX=x;
		posY=y;
		colour=c;
		size=s;
		xCentre=posX-(size/2);
		yCentre=posY-(size/2);
		chain=ch;
		first=b;
	}
	//Draws out the explosions =
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(colour);
		Ellipse2D.Double circle = new Ellipse2D.Double(xCentre, yCentre, size, size);
		g2d.fill(circle);
		//If this is not the first explosion in the demo then print the points 
		if(!(first)){
			String s=""+point(chain);
			g.drawString(s,(int)xCentre, (int)yCentre);
		}
	}
	//Use the distance formula to check if the ball hits into the explsion
	public boolean areaOfExplosion(Ball b){
		//distance formaul
		double xBall=b.getPosX();
		double yBall=b.getPosY();
		double x= Math.pow(posX-xBall,2);
		double y= Math.pow(posY-yBall,2);
		double distance=Math.sqrt(x+y);
		//if the distance less than the radius 
		if(distance<=(size/2)){
			return true;
		}
		return false;
	}
	public int getChain() {
		return chain;
	}
	//checks if this is the first expodesion
	public boolean getFirst(){
		return first;
	}
	//calculates the point for this chain
	public int point(int n){
		return 100*(n*n*n);
	}

}
