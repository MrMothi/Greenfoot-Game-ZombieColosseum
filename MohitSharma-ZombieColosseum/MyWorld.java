import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the myworld class of Zombie Coloseum
 * 
 * Mohit Sharma 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{


    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 800, 1); 
        
        //Setting the backround to the title page
        setBackground(new GreenfootImage("TitlePage.png"));
    }
    
    public void act(){
        //changing to the controls screen if pressing n
        if(Greenfoot.isKeyDown("n") && EnemyWave.gameOver == 0){
            setBackground(new GreenfootImage("ControlsImage.png"));
        }
        //Starting the game when pressing b
        if(Greenfoot.isKeyDown("b") && EnemyWave.gameOver == 0){
            //Calling the prepare method to spawn objects
            prepare();
            //Starting the game by making gameOver = 1
            EnemyWave.gameOver = 1;
            
            //Setting the backround
            setBackground(new GreenfootImage("floor.png"));
            
            //SETTING VARIABLES TO DEFAULT
            
            //resetting the player's health to max
            Player1.increaseSheild(10);
            Player1.increaseHealth(10);
            
            //Setting the score to 0  
            Player1.score = 0;
            
            //Setting the ammo of the player to default (200)
            Player1.bulletAmount = 50;
            
            //Setting the wave number to 0
            EnemyWave.resetWave();
            
            //Setting enemy count to 0
            Enemy.amountEnemy = 0;
            
            //Setting the ammo in all the weapons to max
            Sniper.clipAmmo = 5;
            AR.clipAmmoAR = 30;
            Shotgun.clipAmmoShotgun = 8;
            SMG.clipAmmoSMG = 25;
        }
    }
    
    
    //Prepare method for the objects in the game
    private void prepare()
    {
        //Adding the player object
        Player1 thePlayer = new Player1();
        addObject(thePlayer, 450, 300);


        //Adding the Sniper object
        Sniper snip = new Sniper();
        addObject(snip, 100,100);
        
        //Adding the AR object
        AR ar = new AR();
        addObject(ar, 100,120);
        
        //Adding the Shotgun object
        Shotgun shot = new Shotgun();
        addObject(shot, 100,140);
        
        //Adding the SMG object
        SMG smg = new SMG();
        addObject(smg, 100, 160);
        
        //BOTTOM INFORMATION COUNTERS AND IMAGES--------
        
        //Making the bottom bar object
        BottomBar bar = new BottomBar();
        addObject(bar, 450, 750);
        
        //Spawning the wave number counter
        EnemyWave enemyWave = new EnemyWave();
        addObject(enemyWave, 60, 775);
        
        //Spawning the image for the wave counter
        SkullIcon skull = new SkullIcon();
        addObject(skull, 60, 730);
        
        //Spawning the image for the bullet stats
        BulletIcon bulletImage = new BulletIcon();
        addObject(bulletImage, 725, 750);
        bulletImage.rotation();  //setting the rotation for the image
        
        //Reloading counter
        ReloadingCounter reloading = new ReloadingCounter();
        addObject(reloading, 823, 775);
        
        //Health icon
        HealthIcon healthImage = new HealthIcon();
        addObject(healthImage, 150, 728);
        
        //Health bar
        HealthBar healthBar = new HealthBar();
        addObject(healthBar, 280, 728);
        
        //Sheild icon
        SheildIcon sheildImage = new SheildIcon();
        addObject(sheildImage, 160, 785);
        
        //Sheild bar
        SheildBar sheildBar = new SheildBar();
        addObject(sheildBar, 280, 775);
        
        //Coin Icon
        CoinIcon coinImage = new CoinIcon();
        addObject(coinImage, 460, 730);
        
        //Score counter
        ScoreCounter scoreCount = new ScoreCounter();
        addObject(scoreCount, 460, 780);
        
        //Gun Controls Image 
        GunControlsImage gunControlsImage = new GunControlsImage();
        addObject(gunControlsImage, 595, 752);
    }
    
    
    //Spawning for bullets    (The bullet will depend on the bullet type: (1)Sniper, (2)Assualt Rifle, (3)Shotgun, (4)Sub-Machine Gun)
    public void leftClick(int x, int y, int rotation, int bulletType){
        //Making mouse info object to get the mouse information
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        //Initializing objects
        Sniper snip = new Sniper();
        AR ar = new AR();
        Shotgun shot = new Shotgun();
        SMG smg = new SMG();
        
        //Sniper Bullet
        if (bulletType==1){
            if (mouse!= null){
                if (mouse.getButton() == 1){ 
                    //Spawning the object at position and rotation
                    BulletSniper1 bullet = new BulletSniper1();
                    addObject(bullet, x, y);
                    bullet.setRotation(rotation);
                    
                    
                    //calling the method to set the bullet timer on
                    snip.shotBullet();
                    
                    //Reducing the bullet in the clip by calling the method
                    //snip.changeBulletInClip();
                    snip.changeClip();
                }
            }
        }
        
        
        //AR Bullet
        if (bulletType==2){
            if (mouse!= null){
                if (mouse.getButton() == 1){ 
                    BulletAR1 bullet2 = new BulletAR1();
                    addObject(bullet2, x, y);
                    bullet2.setRotation(rotation);
                    
                    
                    //calling the method to set the bullet timer on
                    ar.shotBullet();
                    
                    //Reducing the bullet in the clip by calling the method
                    //snip.changeBulletInClip();
                    ar.changeClipAR();
                }
            }
        }
        
        
        //Shotgun Bullet
        if (bulletType==3){
            if (mouse!= null){
                if (mouse.getButton() == 1){ 
                    BulletShotgun1 bullet3 = new BulletShotgun1();
                    addObject(bullet3, x, y);
                    bullet3.setRotation(rotation);
                    
                    
                    //calling the method to set the bullet timer on
                    shot.shotBullet();
                    
                    //Reducing the bullet in the clip by calling the method
                    //snip.changeBulletInClip();
                    shot.changeClipShotgun();
                }
            }
        }
        
        
        //SMG Bullet
        if (bulletType==4){
            if (mouse!= null){
                if (mouse.getButton() == 1){ 
                    BulletSMG1 bullet4 = new BulletSMG1();
                    addObject(bullet4, x, y);
                    bullet4.setRotation(rotation);
                    
                    
                    //calling the method to set the bullet timer on
                    smg.shotBullet();
                    
                    //Reducing the bullet in the clip by calling the method
                    //snip.changeBulletInClip();
                    smg.changeClipSMG();
                }
            }
        }
    }
}
