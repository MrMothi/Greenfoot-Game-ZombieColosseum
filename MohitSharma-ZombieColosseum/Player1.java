import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;

/**
 * This is the player class, all the commands for the player will be here 
 * such as for movement, sprite changes, health and armour etc.
 * 
 * Mohit 
 * January 8th 2020
 */
public class Player1 extends Actor
{
    //Making a direction variable 2 is left and 1 is right, default is 1
    private static int direction = 1;
    
    //Variables for x and y 
    public static int theX;
    public static int theY;
    
    
    //List for the sprites for running
    private GreenfootImage[] running = new GreenfootImage[8];

    //Variable for the position in the list
    private int num = 0;
    
    //Gun VARIABLES______________________

    //Gun in use method variable (0 is no gun, 1 is sniper, 2 is AR, 3 is Shotgun, 4 is SMG)
    private static int gunInUse = 1;   // for now default is 1
    
    
    //Bullet amount variable
    public static int bulletAmount = 50;
    
    //OTHER variables
    
    //Health and Sheild Variable
    //The player would have a max of 10 sheild and health
    private static int health = 0; //Default 10
    private static int sheild = 0; //Default 10
    
    //Score variable
    public static int score = 0; //0 is default due to no score in the begining
    


    //Act method for the player class
    public void act() 
    {
        //Making mouse info object to get the mouse information
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        //Showing the bullet amount 
        getWorld().showText("Total Bullets: " + bulletAmount, 815, 720);
        
        
        //Checking if the player has 0 health, if it does then ends the game
        if (health <= 0){
            //Setting the location to the top middle
            setLocation(450, 200);
            
            //Setting the gameover variable to 0
            EnemyWave.gameOver = 0;
            
            
            
            //Setting the game over backround
            getWorld().setBackground(new GreenfootImage("GameOver.png"));
            
            //deleting the player
            getWorld().removeObject(this);
            
            //Stopping all actions in this class
            return;
        }
            

        //Image setting code from     https://www.greenfoot.org/topics/1842
        //Constantly changing the images for the running sprite (setting the images in the list)
        for (int i = 0; i < running.length; i++) {
            running[i] = new GreenfootImage("running" + i + ".png");       
        }
        
        //Movement if statments  Controls WASD
        //"w" for up movement
        if (Greenfoot.isKeyDown("w")){
             setLocation(getX(),getY()-10);
             setImage(running[num]);
             //Setting the player sprite and changing it every iteration
             num++;
             if (num == 8) {
                num = 0;
             }
        }
        //"s" for down movement     not allowed when beyond the zone 
        if(theY < 675){
            if (Greenfoot.isKeyDown("s")){
                setLocation(getX(),getY()+10);
                setImage(running[num]);
                num++;
                if (num == 8) {
                   num = 0;
                }
            }
        }
        //"a" for left movement
        if (Greenfoot.isKeyDown("a")){
             setLocation(getX()-10,getY());
             turnTowards(getX()-1, getY());
             direction = 2;
             //setting the image to the running one
             setImage(running[num]);
             //reversing the image if going left
             getImage().mirrorVertically();
             //increasing the num variable to move to the next pic
             num++;
             if (num == 8) {
                 num = 0;
             }
        }
        //"d" for right movement
        if (Greenfoot.isKeyDown("d")){
            
             setLocation(getX()+10,getY());
             turnTowards(getX()+1, getY());

             direction = 1;
             setImage(running[num]);
             
             num++;
             if (num == 8) {
                   num = 0;
             }
        }  
        
        
        
        //Changing the weapon using "1"   Sniper
        if (Greenfoot.isKeyDown("1")){
            gunInUse = 1;
        }
        
        //Changing the weapon using "2"   AR
        if (Greenfoot.isKeyDown("2")){
            gunInUse = 2;
        }
        
        //Changing the weapon using "3"   Shotgun
        if (Greenfoot.isKeyDown("3")){
            gunInUse = 3;
        }
        
        //Changing the weapon using "4"   SMG
        if (Greenfoot.isKeyDown("4")){
            gunInUse = 4;
        }
        
        
        
        //Adding the melee attack
        
        //Setting x and y
        theX = getX();
        theY = getY();
        
    }
    
    
    //PLAYER LOOKS AND LOCATION METHODS 
    
    
    //Method for getting this player object
    public Player1 getPlayerObject(){
        return this;
    }
    
    //Get method for direction variable
    public static int getDirection(){
        return  direction;
    }
    
    
    //Get Methods for x and y cords of the player
    public static int getTheX(){
        return  theX;
    }
    public static int getTheY(){
        return  theY;
    }
    
    
    //HEALTH AND SHEILD METHODS
    
    //Decreasing the health and sheild method
    public static void changeHealth(int amount){
        if(sheild > 0){
            if(sheild < 10 || sheild >= 0){
                sheild = sheild+amount;
            }
        }   
        else{
            if(health < 10 || health >= 0){
                health = health+amount;
            }
        }
    }
    
    //Increasing the health
    public static void increaseHealth(int amount){
        if(health < 10 || health >= 0){
            health = health+amount;
        }
    }
    
    //Increasing the sheild
    public static void increaseSheild(int amount){
        if(sheild < 10 || sheild >= 0){
            sheild = sheild+amount;
        }
    }
    
    
    //Get method for health
    public static int getHealth(){
        return health;
    }
    
    //Get method for sheild
    public static int getSheild(){
        return sheild;
    }
    
    
    
    
    //WEAPON AND OTHER INVENTORY METHODS
  
    //Get method for checking what gun is in use  
    public static int getGunInUse(){
        return gunInUse;
    }
    
    //Getter method for the amount of ammo
    public static int getBulletAmount(){
        return bulletAmount;
    }
    
    //mutator methods for the amount ammo
    public static void reduceBullets(int amount){
        bulletAmount -= amount;
    }
    public static void increaseBullets(int amount){
        bulletAmount += amount;
    }
    
    
    
    
    //initialising the gifimage class from   https://www.youtube.com/watch?v=pOCE1NynA7o
    //GifImage gifImage = new GifImage("idle.gif");

    
    
    //for letting the image be a gif
    //setImage(gifImage.getCurrentImage());
       
    
    //Methods for the direction of the sprite
    /*
    public void facingLeft(){
        if(direction != 2){
            //getImage().mirrorVertically();
        }
    }
    public void facingRight(){
        if(direction != 1){
            //getImage().mirrorVertically();  
        }
    }
    */
   
       /*Sheild
    //Decreasing or increasing the sheild method
    public static void changeSheild(int amount){
        if(sheild < 10 || sheild >= 0){
            sheild = sheild+amount;
        }
    }
    
    */ 
}
