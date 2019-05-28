import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BG_1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BG_1 extends Decoration
{
    BG_1(int scrollableWorldX, int scrollableWorldY)
    {
        super(scrollableWorldX, scrollableWorldY);
        
            int x = Greenfoot.getRandomNumber(7);
            setImage(x + ".png");
        
    } 
    
    /**
     * Act - do whatever the BG_1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

    }    
}
