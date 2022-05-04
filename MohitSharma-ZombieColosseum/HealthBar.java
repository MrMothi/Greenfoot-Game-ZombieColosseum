import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the health bar of the player, works using the counter and substring
 * 
 * Mohit Sharma 
 */
public class HealthBar extends Actor
{
    //String for the health bar (10 bars for the 10 health)
    private String healthBars = "▮▮▮▮▮▮▮▮▮▮";
    
    private String healthBarsAmount = ""; //The string that changes
    
    //String for the spaces in the health bar (30 spaces to compensate for the bar size)
    private String space = "                                        ";
    
    private String spaces = ""; //The string that changes
    
    //variable for the amount of health less than 10 (0 for default as health would be 10 in the begining)
    private int amountLost = 0;
    
    public void act() 
    {
        if(Player1.getHealth() <= 10){ 
             //displaying health 
             setImage(new GreenfootImage(healthBarsAmount + spaces, 24, Color.RED, Color.GRAY));
             
             //Changing the substrings aslong as its not out of range
             if(amountLost < 10){
                 //Setting the health bars, using substring and reducing the size by the value of amount lost
                 healthBarsAmount = healthBars.substring(0,10-amountLost);
                 
                 //Setting space to needed amount, using substring 0,1 and adding places to the value of amountLost (times 4 to make it fit)
                 spaces = space.substring(0, 0+(amountLost*4));
             }
             
             //Setting amount lost to the max health minus current health
             amountLost = 10 - Player1.getHealth();
        }
        //Setting the bar to 0 health if player has no health
        if(Player1.getHealth() <= 0 && EnemyWave.gameOver == 0){
            setImage(new GreenfootImage(space, 24, Color.RED, Color.GRAY));
        }
    } 
    
}
