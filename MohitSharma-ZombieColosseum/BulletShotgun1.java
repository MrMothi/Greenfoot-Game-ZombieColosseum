import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bullet for the shotgun, fast, low range, high damage, unlimited peircing
 * 
 * Mohit Sharma
 * January 19 2020
 */
public class BulletShotgun1 extends Shotgun
{   
    //Setting the variables to the x and y of the bullet spawn
    private int shotgunX;
    private int shotgunY;  
    
    //Variable for checking if the original position was set or not
    private boolean originalPositionSet = false;
    
    public void act() 
    { 
        //Setting the original position if it hasnt been done yet
        if (originalPositionSet == false){
            //Setting the positon variables
            shotgunX = getX();
            shotgunY = getY();
            //setting the variable to true as not to reset the positions again
            originalPositionSet = true;
        }
        
        
        //Makes the bullet move at a constant pace and deletes itself when it reaches the edge       CHANGE IT SO IT GETS REMOVED WHEN HITTING ANY SOLID OBJECT OTHER THAN ENEMIES
        move(45);
        if (isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
        
        //deleting the bullet if 200 pixels away from original position
        //Using length of a line formula (starting pos compared to current)
        if (Math.sqrt(Math.pow(shotgunX - getX(), 2) + Math.pow(shotgunY - getY(), 2)) > 200){
            getWorld().removeObject(this);
            return;
        }
        
        //deleting the object when touching the bottom bar
        if(isTouching(BottomBar.class)){
           getWorld().removeObject(this);
           return;
        }
        
    }      
}
