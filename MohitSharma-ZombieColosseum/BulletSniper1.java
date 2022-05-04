import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bullet for the sniper, fast and peiercing infinitely until hitting a wall or non enemy object
 * 
 * Mohit Sharma
 * Jan 14th 2020
 */
public class BulletSniper1 extends Sniper{

//Variable for checking if the object has touched a wall object
    private int wallContact = 0;
    
    public void act() 
    { 
        
        //Makes the bullet move at a constant pace and deletes itself when it reaches the edge       CHANGE IT SO IT GETS REMOVED WHEN HITTING ANY SOLID OBJECT OTHER THAN ENEMIES
        move(50);
        if (isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
        
        //if the variable for wallContact is 1 then delete object
        if (wallContact != 0){
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

