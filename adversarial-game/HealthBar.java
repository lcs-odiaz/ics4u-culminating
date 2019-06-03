import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Decoration
{
    //Starting health
    int health = 8;
    
    //Sets the size of the health bar
    int HealthBarWidth = 80;
    int HealthBarHeight = 15;
    int PixelsPerHealthPoint = (int)HealthBarWidth/health;
    
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    HealthBar(int scrollableWorldX, int scrollableWorldY)
    {
        super(scrollableWorldX, scrollableWorldY);
    }

    public void act() 
    {
        //updates the code from the update code on the screen
        update();
    }

    public void update()
    {
        //create a rectangle for empty health bar
        setImage(new GreenfootImage(HealthBarWidth + 2, HealthBarHeight + 2));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.BLACK);
        myImage.drawRect(0,0, HealthBarWidth + 1, HealthBarHeight + 1);
        
        //fill it with red to indicate health
        myImage.setColor(Color.RED);
        myImage.fillRect(1,1, health*PixelsPerHealthPoint, HealthBarHeight);

    }

    public void loseHealth()
    {
        //reduce health
        health--;
        
        // Get an object reference to the world
        GameWorld myWorld = (GameWorld) getWorld();
        
        // Tracks a hit for healthkit spawning
        myWorld.hitLanded();
    }
    
    //function that healthkit calls when picked up; heals the player and play a sound
    public void refillHealth()
    {
        Greenfoot.playSound("heal.mp3");
        health += 2;
    }
    
    //allows the current health of a player to be accessed elsewhere(eg to check for death)
    public int getHealth()
    {
        return health;
    }
}