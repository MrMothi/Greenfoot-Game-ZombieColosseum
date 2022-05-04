import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The counter for the score, prints the player score
 * 
 * Mohit Sharma
 */
public class ScoreCounter extends Actor
{
    public void act() 
    {
        setImage(new GreenfootImage("Score: " + Player1.score, 20, Color.YELLOW, Color.GRAY));
    }    
}
