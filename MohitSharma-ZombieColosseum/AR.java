import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * AR for assualt rifle, fast firing, medium piercing
 * Very simmilar class to all other gun classes
 */
public class AR extends Actor{
    //Initialising the player object here
    Player1 thePlayer = new Player1();
    
    //Setting the variables for player x and y
    private static int playerX; 
    private static int playerY;
    
    //Setting variables for mouse x and y
    private int mouseY;
    private int mouseX;
    
    
    //Setting the variables for AR x and y
    private static int ARX;
    private static int ARY;
    
    
    //Making a static variable for the direction of the gun (for the flipping of the sprite)(Default 1)
    private int directionAR = 1;
    
    
    //Setting the int value of the angle of the sniper (Default 0)
    private static int ARRotation = 0;
    
    
    
    //Bullet variables------
    
    //Setting a variable for the time between shots (1 for currently waiting, 0 for shootable)
    private static int bulletShootable = 0;
   
    
    //Making the variable for the amount of ammo in the gun, max 30 defualt 30
    public static int clipAmmoAR = 30;
   

    //variable for telling if reloading is taking place (0 is no, 1 is yes)
    private int reloadingAR = 0;
   
    
    //variable for temporary bullet amount
    private int tempBullet = 0;
    
    //Variables for timing
    //for within shooting
    long lastAdded = 0;
    long curTime = 0;
    //for reloading
    long lastAdded1 = 0;
    long curTime1 = 0;
    
    
    
    public void act(){
        //deleting this object when the game is over
        if(EnemyWave.gameOver == 0){
           getWorld().removeObject(this);
           return;
        }
        
        //Setting sniper x and y to variables
        ARX = getX();
        ARY = getY();
        
        //Constantly setting the variable to the rotation of the sniper
        ARRotation = this.getRotation();

        //Constantly checking if the gun is in use, and if it is it sets its position and direction, also making it visible
        if (thePlayer.getGunInUse() == 2){
            playerX = Player1.getTheX();
            playerY = Player1.getTheY();
            getImage().setTransparency(255);
            lookMouse();
            setLocation(playerX, playerY);
            //Showing the ammo in the gun
            getWorld().showText("AR: = " + clipAmmoAR + "/30", 820, 750);
        
        }
        //if the gun is not in use the program makes the gun invisible and ends the iteration
        //of the program here
        else{
            getImage().setTransparency(0);
            return;
        }
        
       
        
        
        //Shooting code below
        //Timing Code inspired from:   https://www.greenfoot.org/topics/1291
        
        
        //Checking for reloading or within bullet shoot time and setting it back when done time
        if(bulletShootable == 1){
            //Setting the current time for both situations (Reloading or within shooting)
            curTime  = System.currentTimeMillis();
            curTime1  = System.currentTimeMillis();
            //for within shooting
            if(reloadingAR == 0){
                //Waiting 3 seconds to shoot again
                if(curTime >= lastAdded+400){
                    bulletShootable = 0;
                }
            }
            //for reloading 
            else{
                //Waiting 2.5 seconds to shoot again
                if(curTime1 >= lastAdded+2500){
                    //making the clip 30, player loose 30 (Or however many they have left) bullets, letting the bullet be shot again and setting currently reloading var. to 0
                    //reducing the bullets from the player depending on amount left
                    if (thePlayer.getBulletAmount() > 30){
                        //taking 30 bullets from bullet total
                        thePlayer.reduceBullets(30);
                        //adding the remaining bullets back to the total
                        thePlayer.increaseBullets(clipAmmoAR);
                        //Setting the clip to 30
                        clipAmmoAR = 30;
                        //Making the reloadcounter transparent
                        ReloadingCounter.transparent();
                    }
      
                    // if the bullets left are below the clip amount
                    else{
                        //Setting the temp bullet variable to the current bullet amount, 
                        tempBullet = thePlayer.getBulletAmount();
                        //adding the remaining bullets back to the total
                        thePlayer.increaseBullets(clipAmmoAR);
                        //Reducing the amount of bullets in the inventory by temp bullet
                        thePlayer.reduceBullets(tempBullet);
                        //Making the clip the same as the temp
                        clipAmmoAR = tempBullet;
                        //making temp bullet 0
                        tempBullet = 0;
                        //Making the reloadcounter transparent
                        ReloadingCounter.transparent();
                    }
                    bulletShootable = 0;
                    reloadingAR = 0;
                }
            }
        }
        
        
        
        //Calling the rightclick method, to check when the user wants to fire the weapon (Spawns bullets)
        if(bulletShootable != 1){
            //Calling the method for shooting
            if (clipAmmoAR > 0){
                ((MyWorld)getWorld()).leftClick(ARX, ARY, ARRotation, 2);
                
                //Setting variable to time when shot
                lastAdded = System.currentTimeMillis();
            }
        }
        
        //Calling the reload method if "r" is pressed
        if (Greenfoot.isKeyDown("r")){
            if(thePlayer.getBulletAmount() > 0){
                reloadAR();
            }
        }
        
       
        
        
    }   
    
    
    
    
    //Making the gun look the direction of the mouse
    public void lookMouse(){
        //Making an object for the mouse to get its cords
        //(From Greenfoot API and Greenfoot.org forum)
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        
        //Checking if the mouse is null or not (Prevents error)
        if(mouse!=null){ 
           //Getting x and y of the mouse in order to make it look at it
           mouseX = mouse.getX();
           mouseY = mouse.getY();
           
           //Using the x y to look at that position
           turnTowards(mouseX, mouseY);
           
           //Setting the rotation of the sniper
           //Left
           if (getRotation() < 270 && getRotation() >= 90){
               if (directionAR != 2){
                   getImage().mirrorVertically();
                   directionAR = 2;
               }
           }
           //Right
           if (getRotation() > 270 && getRotation() <= 359 || getRotation() > 0 && getRotation() < 90){
               if (directionAR != 1){
                   getImage().mirrorVertically(); 
                   directionAR = 1;
               }
           }
        }
    }
            
    //Method for decreasing the ammo in the  clip by 1
    public static void changeClipAR(){
        clipAmmoAR--;
    }
    
    
    
    //Getter method for the sniperRotation
    public static int getARRotation(){
        return ARRotation;
    }
    
    
    //Shooting methods

    //Changing the variable which controls if the player could shoot
    public void shotBullet(){
        bulletShootable = 1;
    }
    

    //Method for reloading the sniper (setting time and able to shoot)
    public void reloadAR(){
        //Making the gun unable to shoot while reloading
        bulletShootable = 1;
        //Setting variable to time when reload started
        lastAdded1 = System.currentTimeMillis();
        //Making the reloading variable 1
        reloadingAR = 1;
        //Making the reloadcounter visible
        ReloadingCounter.visible();
    }

}

