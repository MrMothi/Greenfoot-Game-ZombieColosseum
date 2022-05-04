import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * This is the class for the enemy
 * 
 * Mohit Sharma
 */
public class Enemy extends Actor
{
    //Variable to track how many total enemies are in play
    public static int amountEnemy = 0;
    
    //random variable for the zombie health (from 1-5)
    private int enemyHealth = Greenfoot.getRandomNumber(20)+1;
    
    //random variable for speed of the actor(from 1 to 8)
    private int speed = Greenfoot.getRandomNumber(6)+1;
    
    //random variable for random zombie sprite (3 sprite types)
    private int sprite = Greenfoot.getRandomNumber(2)+1;
    
    //Random damage variable for the zombie (1 or 2)
    private int damage = Greenfoot.getRandomNumber(1)+1;
    
    
    //Variables for timing
    //for player damage
    long lastAdded = 0;
    long curTime = 0;
    
    public void act() 
    {  
       //Constantly move towards the player first by looking at the player then moving towards it
       turnTowards(Player1.getTheX(), Player1.getTheY());
       move(speed);
       
       //Setting the current time constantly
       curTime  = System.currentTimeMillis();
       
       
       setImage("zombie" + sprite + "-" + sprite + ".png");
      

       
      
        
       //Causing the player damage when touching it
       if(isTouching(Player1.class)){
           //Waiting 3 seconds between every attack
           if(curTime > lastAdded+3000){
               //Damaging the player by the random assigned damage
               Player1.changeHealth(-damage);
               //Setting the time for last attacked
               lastAdded = System.currentTimeMillis(); 
            }   
       }
       
       //Reducing health based on what bullet hits it
       //If touching the sniper bullet
       if(isTouching(BulletSniper1.class)){
           enemyHealth -= 15;
           //Removing the enemy when health is 0
           if(enemyHealth <= 0){
               //removing the object
               getWorld().removeObject(this);
               //increasing the score of the player randomly from (1-10)
               Player1.score += (Greenfoot.getRandomNumber(9)+1);
               //Reducing the enemyAmount
               amountEnemy--;
               return;
           }
       }
       //If touching the ar bullet
       if(isTouching(BulletAR1.class)){
           enemyHealth = enemyHealth - 5;
           //Removing the enemy when health is 0
           if(enemyHealth <= 0){
               //removing the object
               getWorld().removeObject(this);
               //increasing the score of the player randomly from (1-10)
               Player1.score += (Greenfoot.getRandomNumber(9)+1);
               //Reducing the enemyAmount
               amountEnemy--;
               return;
            }
       }
       
       //If touching the shotgun bullet
       if(isTouching(BulletShotgun1.class)){
           enemyHealth = enemyHealth - 20;
           //Removing the enemy when health is 0
           if(enemyHealth <= 0){
               //removing the object
               getWorld().removeObject(this);
               //increasing the score of the player randomly from (1-10)
               Player1.score += (Greenfoot.getRandomNumber(9)+1);
               //Reducing the enemyAmount
               amountEnemy--;
               return;
            }
       }
       
       //If touching the SMG bullet
       if(isTouching(BulletSMG1.class)){
           enemyHealth = enemyHealth - 3;
           //Removing the enemy when health is 0
           if(enemyHealth <= 0){
               //removing the object
               getWorld().removeObject(this);
               //increasing the score of the player randomly from (1-10)
               Player1.score += (Greenfoot.getRandomNumber(9)+1);
               //Reducing the enemyAmount
               amountEnemy--;
               return;
            }
       }
       
       //stopping the enemy form moving when the game 
       if(EnemyWave.gameOver == 0){
           getWorld().removeObject(this);
           return;
       }

      
    }    
    
    
    //method for decreasing the zombie health    NOT USED
    public void reduceHealth(int amount){
        enemyHealth -= amount;
    }
}


//Code for switching sprites, cannot use this because it makes the game lag
/*
 *  INSTANCE VARIABLES
 * 
 *  //Lists for sprites
    
    //List for the sprites for running
    private static GreenfootImage[] zombie1 = new GreenfootImage[3];
    private static GreenfootImage[] zombie2 = new GreenfootImage[3];
    private static GreenfootImage[] zombie3 = new GreenfootImage[3];
    
   //Variable for the position in the list
    private int num = 0;
    
    //public variable for checking if the lists has been set
    public static boolean settingImages = false; //False is default
    
    //Variable for timing
    //for sprite image
    long lastAdded1 = 0;
    
    //IN ACT METHOD
    
 *        //setting the images in the list once
       if(settingImages == false){
           for (int i = 0; i < 3; i++) {
               zombie1[i] = new GreenfootImage("zombie1-" + (i+1) + ".png");  
               zombie2[i] = new GreenfootImage("zombie2-" + (i+1) + ".png");    
               zombie3[i] = new GreenfootImage("zombie3-" + (i+1) + ".png");    
           }
           settingImages = true;
       }
       
       //Setting the images of the sprites, based on what they randomly select   also waits 1.5 seconds after every switch
       if(sprite == 1 && curTime > lastAdded+1500){
           //Setting the lastTime variable
           lastAdded = System.currentTimeMillis();
           
           //Setting the player sprite and changing it every iteration
           setImage(zombie1[num]);
           num++;
           //Resting the position variable for the list
           if (num == 3) {
               num = 0;
           }
       }
       if(sprite == 2 && curTime > lastAdded+2000){
           //Setting the lastTime variable
           lastAdded = System.currentTimeMillis();
           
           //Setting the player sprite and changing it every iteration
           setImage(zombie2[num]);
           num++;
           //Resting the position variable for the list
           if (num == 3) {
               num = 0;
           }
       }
       if(sprite == 3 && curTime > lastAdded+2000){
           //Setting the lastTime variable
           lastAdded = System.currentTimeMillis();
           
           //Setting the player sprite and changing it every iteration
           setImage(zombie3[num]);
           num++;
           //Resting the position variable for the list
           if (num == 3) {
               num = 0;
           }
       }
       */
      