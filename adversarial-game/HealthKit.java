import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthKit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthKit extends Decoration
{
    /**
     * Constructor
     * 
     * Called once when object is created.
     */
    HealthKit(int scrollableWorldX, int scrollableWorldY)
    {
        super(scrollableWorldX, scrollableWorldY);
    }
    
    /**
     * Act - do whatever the HealthKit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // when health bar touches a character, their health refills
        if (this.isTouching(Player.class))
        {
          // get the actual object the health kit is touching
          Player thePlayer = (Player) getOneIntersectingObject(Player.class);
          
          thePlayer.getHealthBar().refillHealth();
          
          
          getWorld().removeObject(this);
        }  

}
    
}

