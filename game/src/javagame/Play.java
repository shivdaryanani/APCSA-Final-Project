package javagame;

import org.lwjgl.input.Mouse; //Importing mouse functionality
import org.newdawn.slick.*; //Importing java slick functions
import org.newdawn.slick.geom.Circle;//importing the circle object from slick
import org.newdawn.slick.state.*;//importing the state methods from slick for the game
import java.util.ArrayList;//importing of the ArrayList library
import java.util.Random;//importing of the random library so that balls can spawn library
public class Play extends BasicGameState {//The class imports the Slick2D BasicGameState to use its methods 
	//The BasicGameState class has an implemented GameState interface. This interface gives me the methods 
	//which make my game possible. These methods are Init, Render, Update, enter and GetID.  
	
	Animation blueman, movingUp, movingDown, movingLeft, movingRight; //This creates animation objects for the character
	Image worldMap;////This creates an image object for the world
	boolean quit = false; //boolean variable used for the menu that pops up when esc is pressed
	int [] duration = {200, 200, 200};//The durations array for the length of each frame
	float guyPositionX = 0; //float variable made to give the guy positionX of 0 to be moved later
	float guyPositionY = 0; //float variable made to give the guy positionY of 0 to be moved later
	float shiftX = guyPositionX + 407; //The shifting of the character to the middle of the screen X movement
	float shiftY = guyPositionY + 276;//The shifting of the character to the middle of the screen Y movement
	int time;//variable created for time
	int points=0; //variable created for points
	private ArrayList<Circle> coins;//ArrayList created for the spawning of the circles
	private Random random; //random object for the use of java.random library
	int timer = 0;//second timer variable
	//String mouse = ""; <-- this was used in the dev phase to see mouse coordinates
	float coorX = 0;//this is coorX for the spawning of the circles
	float coorY = 0;//this the coorY for the spawning of the circles 

	
	//creates screen for the game using the default constructor from BasicGameState
	public Play(int state) {	
		
	}
	
	//This is the first method from the GameState Interface and is used Initialise the state and to 
	//load any resources it needs at this stage
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException {
		worldMap = new Image("res/world.png");//giving the map image to variable 
		//images for walking animation in individual arrays of image objects
		Image[] walkUp = {new Image("res/backOne.png"), new Image("res/backTwo.png"), new Image("res/backThree.png")};
		Image[] walkLeft = {new Image("res/leftOne.png"), new Image("res/leftTwo.png"), new Image("res/leftThree.png")};
		Image[] walkRight = {new Image("res/rightOne.png"), new Image("res/rightTwo.png"), new Image("res/rightThree.png")};
		Image[] walkDown = {new Image("res/frontOne.png"), new Image("res/frontTwo.png"), new Image("res/frontThree.png")};
		
		//making the animations made above in the image arrays attached to animation objects made
		movingUp = new Animation(walkUp, duration, true);//three parameters, images, frame length and the boolean value  
		movingDown = new Animation(walkDown, duration, true);//allowing for the transition from one photo to another
		movingLeft = new Animation(walkLeft, duration, true);
		movingRight = new Animation(walkRight, duration, true);
		blueman = movingDown;//this makes the base image for the character
		coins = new ArrayList<Circle>();//attaches the variable coins to the circles ArrayList
		random = new Random();//this assigns the random object to an instance of random
		
	}
	
	//This is the second method used from the GameState Interface
	//It is use for drawing graphics, the screen objects and designs 
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		worldMap.draw(guyPositionX, guyPositionY);//Draws the map according to the positions of the character
		blueman.draw(shiftX, shiftY);//Draws the character in the center of the screen.
		g.drawString("Posx: "+guyPositionX+"\nPosY: "+guyPositionY, 420, 15);;//printed the coordinates of chracter on screen
		g.drawString("Time : " + time/1000, 300, 15);//prints the time passed on the screen
		g.drawString("Points: "+points,600,15);//prints the points to the screen
		//g.drawString(mouse, 100, 50);
		g.setColor(Color.red);//set the drawing color to red
		for(Circle c: coins) {//accesses the circle ArrayList to draw to screen 
			g.fill(c);
			
		}
		g.setColor(Color.white);//set drawing color to red
		if(quit==true) {//check if quit boolean is true
			g.drawString("Resume (R)", 400, 200);//Places the string "resume" on the screen
			g.drawString("Main Menu (M)", 400, 250);//Places the string "Main Menu" on the screen
			g.drawString("Exit Game (Q)", 400, 300);//Places the string "Exit Game" on the screen
			if(quit==false) {//check if quit boolean is false
				g.clear();//does not draw anything to screen
			}
		}
		
		if(time/1000 > 60) {//check if 60 seconds has passed
			g.drawString("GAME OVER", 250,200);//draw game over to the screen
			g.drawString("Your score: " + points,250,250);//draw the points amount to the screen
			g.drawString("Press(R) to retry and (M) to go to the main menu", 250, 300);//draw the follow String to the screen
			g.drawString("Great Job! Thanks for playing. Press (Q) to exit",250,350);//draw the following String to the screen
		}
		
	}
	
	//this method updates images shown on the game on the screen allowing for animations 
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();//gets the user input 
		time += delta;//game timer
		timer += delta;//timer for the spawning of the circles 
		if(timer>1500) {//checks if the time is passed 1500 ms 
			coorX = random.nextInt((1005-100) + 1)+100;//gets random X for the circle
			coorY = random.nextInt((730-100) + 1)+100;//gets random Y for the circle
			coins.add(new Circle(coorX, coorY,40));//adds the circle to the arraylist of circles
			timer = 0;//makes timer 0 again
		}
	
		for(Circle c:coins) {	//loop runs through array list of circles 		
			c.setCenterX(coorX+guyPositionX);//sets the circles X position in relation to the guy 
			c.setCenterY(coorY+guyPositionY);//sets the circles Y position in relation to the guy 
			if(c.contains(shiftX, shiftY) || c.contains(shiftX-40, shiftY-40) || c.contains(shiftX+40, shiftY+40)){
				points+=1;//adds points if the guys coordinates are contained in the circle 
			}
			
			
		}
		
	
		//int posX = Mouse.getX();
		//int posY = Mouse.getY();
		//mouse = posX + ", " + posY;
		
		//up movement checks if W or UP key is pressed 
		if(input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
			blueman = movingUp;
			guyPositionY += delta * .4f; //moves the character y position up
			if(guyPositionY>218) {
				guyPositionY -= delta * .4f;
			}
		}
		//down movement checks if Down key or S is pressed 
		if(input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
			blueman = movingDown;
			guyPositionY -= delta * .4f; //moves the character y position up
			if(guyPositionY<-565) {
				guyPositionY += delta * .4f;
			}
		}
	
		//left movement checks if left key or A key is pressed 
		if(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
			blueman = movingLeft;
			guyPositionX += delta * .4f; //moves the character y position up
			if(guyPositionX>354) {
				guyPositionX -= delta * .4f;
			}
		}
	
		//right movement checks if the right or D key is pressed
		if(input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
			blueman = movingRight;
			guyPositionX -= delta * .4f; //moves the character y position up
			if(guyPositionX<-775) {
				guyPositionX += delta * .4f;
			}
		}
		
		//escape function checks if esc is pressed to bring the menu up 
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			quit = true;
		}
		//when menu is open
		if(quit==true) {//checks if the esc key is pressed which changed the boolean value quit 
			if(input.isKeyDown(Input.KEY_R)) {//checks if R is pressed 
				quit = false;
			}
			if(input.isKeyDown(Input.KEY_M)) {//checks if M is pressed 
				sbg.enterState(0);//enters the main menu
			}
			if(input.isKeyDown(Input.KEY_Q)) {//check if Q is pressed 
				System.exit(0);//exits the game 
			}
		}
		if(time/1000 > 60) {//check if the time is equal to 60 seconds 
			if(input.isKeyDown(Input.KEY_Q)) {//check if Q is pressed 
				System.exit(0);//exits game 
			}
			if(input.isKeyDown(Input.KEY_M)) {//check if M is pressed
				sbg.enterState(0);//enter main menu
				time=0; //resets game timer
				points=0;//resets points 
			}
			if(input.isKeyDown(Input.KEY_R)) {//check if R is is pressed 
				sbg.enterState(1);//enters the game page to play 
				time=0; //resets the game timer to try again
				points=0; //resets the game points to try again
			}
			
		}
		
			
	}
	
			
	//This is the fourth method from the BasicState Interface this gets the ID for screen
	public int getID() {//
		return 1;//This means that the ID for this screen is 1
	}
	
}
