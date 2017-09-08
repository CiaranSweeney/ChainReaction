package chainReaction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;


public class GameArea extends JPanel{
	
	private ArrayList<Ball> balls=new ArrayList<Ball>();
	private ArrayList<Ball> removingballs=new ArrayList<Ball>();
	private ArrayList<Explosion> explosionList=new ArrayList<Explosion>();
	
	private int maxX;
	private int maxY;
	private int minX=0;
	private int minY=0;
	private double mouseX=0;
	private double mouseY=0;
	private double cMouseX=0;
	private double cMouseY=0;
	private int click=0;
	private int level=0;
	private int ballCount=0;
	private int goal=0;
	private int score=0;
	private boolean enter=true;
	private boolean endLevel=false;
	private String messageA="";
	private String messageB="";
	//private String levelMessage="";
	private Random rand = new Random();
	
	public GameArea(int x, int y){
		maxX=x;
		maxY=y;
		addMouseMotionListener(new MouseAdapter(){
	    	//used to keep track of the cursors position
	    	public void mouseMoved(MouseEvent e) {
	    		cMouseX=e.getX();
            	cMouseY=e.getY();
	    	}
		});
		addMouseListener(new MouseAdapter(){
			//this keeps track of the mouse being pressed
            public void mousePressed(MouseEvent e){
            	mouseX=e.getX();
            	mouseY=e.getY();
            	if(click==0){
            		explosion(new Explosion(mouseX,mouseY,new Color(1f,1f,1f,0.5f),50,1,true));
            		click++;
            	}
            	else if(messageA.length()>1 || messageB.length()>1){
            		click++;
            	}
            }
		 });
		
		Thread gameThread=new Thread(new Runnable(){
			//Thread for Levels. This thread picks a level
			public void run(){
				do{
					//level 0
					if(level==0){
						welcome();
					}
					//level 1
					else if(enter && level==1){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(1,5);
						
					}
					//level 2
					else if(enter && level==2){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(2,10);
					}
					//level 3
					else if(enter && level==3){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(4,15);
					}
					//level 4
					else if(enter && level==4){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(6,20);
					}
					//level 5
					else if(enter && level==5){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(10,25);
					}
					//level 6
					else if(enter && level==6){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(15,30);
					}
					//level 7
					else if(enter && level==7){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(18,35);
					}
					//level 8
					else if(enter && level==8){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(22,40);
					}
					//level 9
					else if(enter && level==9){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(30,45);
					}
					//level 10
					else if(enter && level==10){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(37,50);
					}
					//level 11
					else if(enter && level==11){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(48,55);
					}
					//level 12
					else if(enter && level==12){
						playLevel();
						enter=false;
						click=0;
						gameSetUp(54,60);
					}
					//Game over
					else if(level==13)
						gameOver();
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}while(true);
			}
		});
		gameThread.setDaemon(true);
		gameThread.start();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		//previw
		if(click==0)
			new Explosion(cMouseX,cMouseY,new Color(1f,1f,1f,0.5f),50,1,true).draw(g);
		g.setColor(Color.white);
		//draws messages
		g.drawString(messageA, (maxX/2)-100, (maxY/2)-48);
		g.drawString(messageB, (maxX/2)-100, (maxY/2));
		//draws balls
		for(int i=0 ; i<balls.size(); i++){
			balls.get(i).draw(g);
		}
		//draws explodsions
		for(int i=0 ; i<explosionList.size(); i++)
			explosionList.get(i).draw(g);
	}
	//sets up the game
	public  void gameSetUp(int g, int numBalls){
		goal=g;
		//adds in balls
		setBalls(numBalls);
		repaint();
		Thread ballThread=new Thread(new Runnable(){
			public void run(){
				click=0;
				updateBalls();
				resetAll();
			}
		});
		ballThread.setDaemon(true);
		ballThread.start();
	}
	public void setBalls(int numberBalls){
		//randomly generates the vales ro create ball
		int randomNumX;
		int randomNumY;
		int speed;
		boolean randomBoolX;
		boolean randomBoolY;
		for(int j=0; j<numberBalls; j++){
			randomBoolX=rand.nextBoolean();
			randomBoolY=rand.nextBoolean();
			randomNumX=rand.nextInt((maxX - minX) + 1) + minX;
			randomNumY= rand.nextInt((maxY - minY) + 1) + minY;
			speed= rand.nextInt((4 - 1) + 1) + 1;
			balls.add(new Ball(randomNumX,randomNumY,randomBoolX,randomBoolY,randomColor(),10,speed));
		}
	}
	public Color randomColor(){
		//Generates random colours
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		return new Color(r,g,b,0.5f);
	}
	public void updateBalls(){
		do{
			for(Ball b:balls){
				b.updatePostions();
				wallCollison(b);
				explsionCossion(b);
			}
			for(Ball b:removingballs){
				balls.remove(b);
				ballCount++;
			}
			removingballs.clear();
			this.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(!endLevel);
	}
	//This is where and how the ball bounces off
	public void wallCollison(Ball b){
		if(b.getPosX()>=maxX-25){
			b.setForward(false);
		}
		else if(b.getPosX()<=minX){
			b.setForward(true);
		}
		if(b.getPosY()>=maxY-65){
			
			b.setDown(false);
		}
		else if(b.getPosY()<=minY){
			b.setDown(true);
		}
	}
	public synchronized void explosion(Explosion ex){
		//Creates a thread for creating and removing explosions
		Thread eThread=new Thread(new Runnable(){
			public void run(){
				try {
					explosionList.add(ex);
					//sends the thread to sleep for 3 seconds
					Thread.sleep(3000);
					explosionList.remove(ex);
					scoreCalculation(ex.getChain(),ex);
					if(explosionList.isEmpty()){
						repaint();
						endLevel=true;
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		eThread.setDaemon(true);
		eThread.start();
	}
	// deals with balls hitting with the explodsions then in turn turns into an explosion
	public void explsionCossion(Ball b){
		for(int i=0 ; i<explosionList.size(); i++){
			if(explosionList.get(i).areaOfExplosion(b) && explosionList.get(i).getFirst()){
				removingballs.add(b);
				explosion(new Explosion(b.getPosX(),b.getPosY(),b.getColour(),50,explosionList.get(i).getChain(),false));
				
			}
			else if(explosionList.get(i).areaOfExplosion(b)){
				removingballs.add(b);
				explosion(new Explosion(b.getPosX(),b.getPosY(),b.getColour(),50,explosionList.get(i).getChain()+1,false));
			}
		}
	}
	//Calculates the score
	public void scoreCalculation(int n,Explosion ex){
		//the formula for adding to the score
		if(ex.getFirst())
			n--;
		score+=100*(n*n*n);
		System.out.println(score);
	}
	//Checks to see if the play has beaten the level
	public void winLevel(){
		if(ballCount>=goal){
			messageA="You won! your score was:"+score;
			level++;
		}
		else
			messageA="You lost, try again";
	}
	//resets every thing for next level or for replay level
	public void resetAll(){
		winLevel();
		ballCount=0;
		goal=0;
		score=0;
		balls.clear();
		endLevel=false;
		enter=true;
	}
	//Prints out welcome to the game until clicks the screen
	public void welcome(){
		click=1;
		messageA="Welcome to the Game";
		messageB="click anywhere to play";
		do{
			repaint();
		}while(click<2);
		messageA="";
		messageB="";
		click=0;
		level++;
	}
	//prints a message at the start of each level
	public void playLevel(){
		click=1;
		messageB="click anywhere to play level "+level;
	do{
		repaint();
	}while(click<2);
	messageA="";
	messageB="";
	click=0;
	}
	//prints game over
	public void gameOver(){
		click=1;
		messageB="Gameover";
		do{
			repaint();
		}while(true);
	}
	//picks the level
	public void setLevel(int level) {
		this.level = level;
		endLevel=true;
		balls.clear();
		explosionList.clear();
		messageA="level:"+level;
		messageB="click anywhere to play";
		repaint();

	}
	public int getScore() {
		return score;
	}
	
	
	
}
