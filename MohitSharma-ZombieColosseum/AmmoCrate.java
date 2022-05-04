import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ammo crate for the game, gives 50 ammo to player, spawns every round in a random position, deletes itself at the end of a wave
 * 
 * Mohit Sharma
 * January 19th 2020
 */
public class AmmoCrate extends Actor
{
    //Variable for the current wave number when the crate was spawned
    private int waveNumberWhenSpawned;
    
    //Variable for setting the when spawned wave number only once
    private boolean waveSet = false;
    
    
    public void act() 
    {
        //Setting the waveNumberWhenSpawned once
        if(waveSet == false){
            waveNumberWhenSpawned = EnemyWave.waveNumber;
            //Setting the boolean to make it so that the variable doesnt get set again
            waveSet = true;
        }   
    
        //Deleting 
        if(isTouching(Player1.class)){
            //Adding 20 bullets to the player when touching Player1
            Player1.bulletAmount += 20;
            
            //Deleting the object
            getWorld().removeObject(this);
            //Stopping further action of this class
            return;
        }
        
        //Deleting the object when the wave passed
        if(waveNumberWhenSpawned != EnemyWave.waveNumber){
            getWorld().removeObject(this);
            return;
        }
        
        //deleting the object when the game ends
        if(EnemyWave.gameOver == 0){
            getWorld().removeObject(this);
            return;
        }
    }    
}
