import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bullet for the SMG
 * 
 * Mohit Sharma
 * January 19 2020
 */
public class BulletSMG1 extends SMG
{
    //Variable to keep track of how many enemies were pierced (max is 3)
    private int amountPierced = 0;
    
    public void act() 
    { 
        //Makes the bullet move at a constant pace and deletes itself when it reaches the edge       CHANGE IT SO IT GETS REMOVED WHEN HITTING ANY SOLID OBJECT OTHER THAN ENEMIES
        move(20);
        if (isAtEdge()){
            getWorld().removeObject(this);
            return;
        }
        
        //Changing the variable for peirced enemies if touching enemy
        if (amountPierced == 2){
            getWorld().removeObject(this);
            return;
        }
        
        //deleting the object when touching the bottom bar
        if(isTouching(BottomBar.class)){
           getWorld().removeObject(this);
           return;
        }
        
        //Changing the variables if touching the enemy class
        if(isTouching(Enemy.class)){    
           amountPierced++;
        }
    }  
}
