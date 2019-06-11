package javagame;

import org.newdawn.slick.*;//Importing the Slick2D Library
import org.newdawn.slick.state.*;

//StateBasedGame is a class in Slick2D that should be used when making a state based game
public class Game extends StateBasedGame{
	
	public static final String gamename = "THINK FAST!!";//The title for the window
	public static final int menu = 0;//giving the value 1 to the variable menu will be used for navigating to the menu page
	public static final int play = 1;//giving the value 0 to the variable play will be used for navigating to the play page
	
	public Game(String gamename) {
		//uses game name title constructor in the StateBasedGame class
		super(gamename);
		this.addState(new Menu(menu)); //This adds the screen menu to the game
		this.addState(new Play(play));//This adds the play screen to the game 
	}
	
	//game container is the back-end of the game and must be implemented
	public void initStatesList(GameContainer gc) throws SlickException{
		//these method initialize states for the game 
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(menu);//uses the enter state method to show the first screen of the game
		
	}
	
	public static void main(String[] args) {//main method
		//this builds the window for the game
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game(gamename));//this puts the game at the top of the window
			appgc.setDisplayMode(840, 560, false); //dimensions for the game window
			appgc.start();//start running the game
		}catch(SlickException e) {//catches exceptions that may happen
			e.printStackTrace();//prints throwable with other details like class name and line number where the exception 
		}
	}

}
