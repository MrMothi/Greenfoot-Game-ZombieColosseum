import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Sheild bars for the player, changes accordingly
 * 
 * Mohit Sharma
 * @version (a version number or a date)
 */
public class SheildBar extends Actor
{
    //String for the sheild bar (10 bars for the 10 health)
    private String sheildBars = "▮▮▮▮▮▮▮▮▮▮";
    
    private String sheildBarsAmount = ""; //The string that changes
    
    //String for the spaces in the health bar (30 spaces to compensate for the bar size)
    private String space = "                                        ";
    
    private String spaces = ""; //The string that changes
    
    //variable for the amount of health less than 10 (0 for default as health would be 10 in the begining)
    private int amountLost = 0;
    
    public void act() 
    {
        if(Player1.getSheild() <= 10){
             //displaying health 
             setImage(new GreenfootImage(sheildBarsAmount + spaces, 24, Color.BLUE, Color.GRAY));
             
             //Setting the health bars, using substring and reducing the size by the value of amount lost
             sheildBarsAmount = sheildBars.substring(0,10-amountLost);
             
             //Setting space to needed amount, using substring 0,1 and adding places to the value of amountLost (times 4 to make it fit)
             spaces = space.substring(0, 0+(amountLost*4));
             
             //Setting amount lost to the max health minus current health
             amountLost = 10 - Player1.getSheild();
        }
    }  
}
