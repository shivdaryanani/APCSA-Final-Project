package javagame;

import org.lwjgl.input.Mouse; //imports mouse functionality 
import org.newdawn.slick.*;//importing javaSlick
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState { //The class imports the Slick2D BasicGameState to use its methods 
	//The BasicGameState class has an implemented GameState interface. This interface gives me the methods 
	//which make my game possible. These methods are Init, Render, Update, enter and GetID. 
	
	Image playNow; //This creates an image object for the start button
	Image exitGame; //This creates an image object for the exit button
	//int mouse; <--- This is commented because it was only used in my developmental stage
	
	//creates screen for the game using the default constructor from the BasicGameState Class
	public Menu(int state) {		
	}
	
	//This is the first method from the GameState Interface and is used Initialise the state and to 
	//load any resources it needs at this stage
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException {
		playNow = new Image("res/start.png");//This gives the image object for the start button the image it will use
		exitGame = new Image("res/exit.png");//This gives the image object for the exit button the image it will use
		
	}
	//This is the second method used from the GameState Interface
	//It is use for drawing graphics, the screen objects and designs 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString("THINK FAST!!", 100, 50); //This draws the title on on the start screen for the game 
		playNow.draw(250,100);//This draws the start button (the start image from res)
		exitGame.draw(250,333);//This draws the exit button (The exit image from res)
	}
	
	//This is the third method from used from the GameState Interface 
	//This method updates images shown on the game on the screen allowing for animations
	//Update the state's logic based on the amount of time thats passed
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{ 
		//gc holds the game sbg points to the game holding the state 
		//The amount of time thats passed in millisecond since last update
		int posX = Mouse.getX();//get X position of the mouse
		int posY = Mouse.getY();//get Y position of the mouse 
		//mouse = posX + ", " + posY;
		
		//play now button
		if((posX>279 && posX<560) && (posY>317 && posY<420)){//checks if the mouse is the area of the start button
			if(Mouse.isButtonDown(0)) {//checks if the left mouse button is clicked
				sbg.enterState(1);//moves to game state one which is the play screen
			}
		}
		
		//exit game button
		if((posX>303 && posX<539) && (posY>91 && posY<186)){ //checks if the mouse is the area of the exit button 
			if(Mouse.isButtonDown(0)) {//check if the left button is clicked
				System.exit(0);//exits the game 
			}
		}
		
	}
	//This is the fourth method from the BasicState Interface this gets the ID for screen
	public int getID() {//
		return 0;//This means that the ID for this screen is 0 
	}
	
}
