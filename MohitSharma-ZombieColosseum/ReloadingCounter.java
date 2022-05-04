import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Reloading counter, shows up when reloading and dissapears when not reloading 
 */
public class ReloadingCounter extends Actor
{   
    //Variable 1 for transparent 2 for visible  //1 default because no reloading at the begining
    private static int state = 1;  
    
    public void act() 
    {
        setImage(new GreenfootImage("Reloading...", 24, Color.WHITE, Color.GRAY));
        if(state == 1){
            getImage().setTransparency(0);
        }
        if(state == 2){
            getImage().setTransparency(255);
        }
    }
    
    //Method for making the reload counter transparent
    public static void transparent(){
        state = 1;
    }
    
    
    //Method for making the reload counter visible
    public static void visible(){
        state = 2;
    }
}
