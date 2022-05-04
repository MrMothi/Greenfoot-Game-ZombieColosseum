import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a weapon class, the sniper
 * Slow Shooting, High Piercing, Fast Projectile
 * Mohit Sharma
 * January 8th 2020
 */
public class Sniper extends Actor
{
    //MAKE THE THING WHERE IT GETS PICKED UP
  

    //Initialising the player object here
    Player1 thePlayer = new Player1();
    
    //Setting the variables for player x and y
    private static int playerX; 
    private static int playerY;
    
    //Setting variables for mouse x and y
    private int mouseY;
    private int mouseX;
    
    
    //Setting the variables for sniper x and y
    private static int sniperX;
    private static int sniperY;
    
    
    //Making a static variable for the direction of the gun (for the flipping of the sprite)(Default 1)
    private int directionSniper = 1;
    
    
    //Setting the int value of the angle of the sniper (Default 0)
    private static int sniperRotation = 0;
    
    
    
    //Bullet variables------

    //Setting a variable for the time between shots (1 for currently waiting, 0 for shootable)
    private static int bulletShootable = 0;
   
    
    //Making the variable for the amount of ammo in the gun, max 5 defualt 5
    public static int clipAmmo = 5;
   

    //variable for telling if reloading is taking place (0 is no, 1 is yes)
    private int reloading = 0;
   
    
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
        sniperX = getX();
        sniperY = getY();
        
       
        //Constantly setting the variable to the rotation of the sniper
        sniperRotation = this.getRotation();

        //Constantly checking if the gun is in use, and if it is it sets its position and direction, also making it visible
        if (thePlayer.getGunInUse() == 1){
            playerX = Player1.getTheX();
            playerY = Player1.getTheY();
            getImage().setTransparency(255);
            lookMouse();
            setLocation(playerX, playerY);
            //Showing the ammo in the gun
            getWorld().showText("Sniper: = " + clipAmmo + "/5", 820, 750);
        
        }
        //if the gun is not in use the program makes the gun invisible and ends the iteration
        //of the program here
        else if (thePlayer.getGunInUse() != 1){
            getImage().setTransparency(0);
            return;
        }
        
       
        
        
        //Shooting code below
        //Code inspired from:   https://www.greenfoot.org/topics/1291
        
        
        //Checking for reloading or within bullet shoot time and setting it back when done time
        if(bulletShootable == 1){
            //Setting the current time for both situations (Reloading or within shooting)
            curTime  = System.currentTimeMillis();
            curTime1  = System.currentTimeMillis();
            //for within shooting
            if(reloading == 0){
                //Waiting 3 seconds to shoot again
                if(curTime >= lastAdded+3000){
                    bulletShootable = 0;
                }
            }
            //for reloading 
            else{
                //Waiting 4 seconds to shoot again
                if(curTime1 >= lastAdded+4000){
                    if (thePlayer.getBulletAmount() > 5){
                        thePlayer.increaseBullets(clipAmmo);
                        thePlayer.reduceBullets(5);
                        clipAmmo = 5;
                        bulletShootable = 0;
                        reloading = 0;
                        //Making the reloadcounter transparent
                        ReloadingCounter.transparent();
                    }
  
                    // if the bullets left are below the clip amount
                    else{
                        tempBullet = thePlayer.getBulletAmount();
                        thePlayer.increaseBullets(clipAmmo);
                        thePlayer.reduceBullets(tempBullet);
                        clipAmmo = tempBullet;
                        bulletShootable = 0;
                        reloading = 0;
                        //Making the reloadcounter transparent
                        ReloadingCounter.transparent();
                    }
                    
                }
                
            }
        }
        
            
        
        
        //Calling the rightclick method, to check when the user wants to fire the weapon (Spawns bullets)
        if(bulletShootable != 1){
            //Calling the method for shooting
            if (clipAmmo > 0){
                ((MyWorld)getWorld()).leftClick(sniperX, sniperY, sniperRotation, 1);
                
                //Setting variable to time when shot
                lastAdded = System.currentTimeMillis();
            }
        }
        
        //Calling the reload method if "r" is pressed and only if the player has bullets
        if (Greenfoot.isKeyDown("r")){
            if(thePlayer.getBulletAmount() > 0){
                reloadSniper();
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
               if (directionSniper != 2){
                   getImage().mirrorVertically();
                   directionSniper = 2;
               }
           }
           //Right
           if (getRotation() > 270 && getRotation() <= 359 || getRotation() > 0 && getRotation() < 90){
               if (directionSniper != 1){
                   getImage().mirrorVertically(); 
                   directionSniper = 1;
               }
           }
        }
    }
            
    
    public void changeClip(){
        clipAmmo--;
    }
    
    
    
    //Getter method for the sniperRotation
    public static int getSniperRotation(){
        return sniperRotation;
    }
    
    
    //Shooting methods

    //Changing the variable which controls if the player could shoot
    public void shotBullet(){
        bulletShootable = 1;
    }
    

    //Method for reloading the sniper (setting time and able to shoot)
    public void reloadSniper(){
        //Making the gun unable to shoot while reloading
        bulletShootable = 1;
        //Setting variable to time when reload started
        lastAdded1 = System.currentTimeMillis();
        //Making the reloading variable 1
        reloading = 1;
        //Making the reloadcounter visible
        ReloadingCounter.visible();
    }
    
  
    
    
    //UNUSED METHODS__________
    
    //if the player has 0 bullets    
    //else if(thePlayer.getBulletAmount() == 0 && clipAmmo == 0){
           //return;
    //}
    
    
    /*public int getX(){
        return sniperX;   
    }
    
    public int getY(){
        return sniperY;
    }
    */
    
    
    
    
   /* public void shootBullet(){
        if (bulletShootable == 0){
            BulletSniper1 bullet = new BulletSniper1();
            
          
            getWorld().addObject(bullet, getX(), getY()); // adds the bullet into the world
            
        }
    }*/
    

}         
