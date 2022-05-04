import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyWave extends Actor
{
    //Variable to keep track of the wave
    public static int waveNumber = 1;
   
    //Amount for enemy limit, starting with 4 for the first round
    private static int enemyLimit = 4;
    
    //Boolean for saying done spawning
    private static boolean doneSpawning = false;
    
    //Variable for game over, 1 for running, 0 for not running
    public static int gameOver = 0;
    
    //Lists for the locations for spawning the zombies
    private static int[] randSpawnX = {1, 900, 900, 1};
    private static int[] randSpawnY = {1, 1, 670, 670};
    
    //Variable for the position the zombies will spawn in(the location in the lists above)
    private static int listPosition;
   
    
    public void act() 
    {
        //Ending the spawning when game has ended
        if(gameOver == 0){
            //Resetting the variables
            doneSpawning = false;
            enemyLimit = 4;
            //Stopping further code in the class
            return;
        }
        //Spawning zombies within limit and controlling the enemy waves
        if(gameOver == 1){
            //Spawning zombies 
            if(doneSpawning == false){  //enemyLimit < Enemy.amountEnemy &&
                for (int x = 0; x < enemyLimit; x++){
                    //increasing enemyAmount
                    Enemy.amountEnemy++;
                    //setting list position
                    listPosition = Greenfoot.getRandomNumber(4);
                    //Adding the enemy into the world
                    getWorld().addObject(new Enemy(), randSpawnX[listPosition],  randSpawnY[listPosition]);  
                 
                }
                //Spawning an ammo crate at a random position
                getWorld().addObject(new AmmoCrate(), Greenfoot.getRandomNumber(900), Greenfoot.getRandomNumber(620));
                //Setting the done spawning to true
                doneSpawning = true;
            }
            //when all enemies are gone, increase the wave number, increase the enemy limit
            if(Enemy.amountEnemy == 0){
                waveNumber++;
                enemyLimit+=6;  //increasing the limit by 6 every time
                //Making the done spawning false so it will spawn again
                doneSpawning = false;
            }
        }
        setImage(new GreenfootImage("Wave : " + waveNumber, 24, Color.RED, Color.GRAY));
    } 
    
    //Method for resetting the wavenumber 
    public static void resetWave(){
        waveNumber = 1;
    }
}
  

