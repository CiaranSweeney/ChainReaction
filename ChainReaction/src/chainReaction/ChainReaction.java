package chainReaction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class ChainReaction extends JFrame{
	//Generating the fields
	private int xSize=500;
	private int ySize=500;
	private int count=0;
	private boolean secret=false;
	private ArrayList<String> levelNames=new ArrayList<String>();
	public ChainReaction(){
		super(" JAVA Application!");                    
		setLocation(300,200);                     
		setSize(xSize,ySize);                     
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);                    
		setContentPane(createConP());                     
		setVisible(true);
	}
	private Container createConP(){ 
		Container pane = new JPanel(new BorderLayout());
		GameArea ga=new GameArea(xSize,ySize);
		ga.setBackground(Color.black);
		JMenuBar bar = new JMenuBar();
		//checks to see if the menu will be hidden
		String s="Score:"+ga.getScore();
		bar.add(new JMenu(s));
		if(secret){
			JMenu secretMenu = new JMenu ("Secret Menu");
			fillLevels(ga,secretMenu);
			bar.add(secretMenu);
		}
		pane.add(bar,BorderLayout.NORTH);
		pane.add(ga, BorderLayout.CENTER);
		return pane;           
	}
	//Adds the levels to the hidden menu
	private void fillLevels(GameArea ga,JMenuItem sm){
		JMenuItem lv=new JMenuItem("Level 1");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(1);
		        }
	     });
		sm.add(lv);
		lv=new JMenuItem("Level 2");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(2);
		        }
	     });
		sm.add(lv);
		lv=new JMenuItem("Level 3");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(3);
		        }
	     });
		sm.add(lv);
		lv=new JMenuItem("Level 4");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(4);
		        }
	     });
		sm.add(lv);
		lv=new JMenuItem("Level 5");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(5);
		        }
	     });
		sm.add(lv);
		lv=new JMenuItem("Level 6");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(6);
		        }
	     });
		sm.add(lv);
		lv=new JMenuItem("Level 7");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(7);
		        }
	     });
		sm.add(lv);
		lv=new JMenuItem("Level 8");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(8);
		        }
	     });
		sm.add(lv);
		lv=new JMenuItem("Level 9");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(9);
		        }
	     });
		sm.add(lv);
		lv=new JMenuItem("Level 10");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(10);
		        }
	     });
		sm.add(lv);
		lv=new JMenuItem("Level 11");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(11);
		        }
	     });
		sm.add(lv);
		lv=new JMenuItem("Level 12");
		lv.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {;
		        ga.setLevel(12);
		        }
	     });
		sm.add(lv);
		
	}
	public static void main(String args[]){                     
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){ 
				new ChainReaction();
			} 
		});          
	}
	
}
