import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Player
{

    int health = 8;
    int HealthBarWidth = 80;
    int HealthBarHeight = 15;
    int PixelsPerHealthPoint = (int)HealthBarWidth/health;
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public HealthBar()
    {
        update(); //runs code when its created automatically
    }
    
    public void act() 
    {
        update();//updates the code from the update code on the screen
    }
    public void update()
    {
        //creates new image
        setImage(new GreenfootImage(HealthBarWidth + 2, HealthBarHeight + 2));
        GreenfootImage myImage = getImage();
        myImage.setColor(Color.BLACK);
        myImage.drawRect(0,0, HealthBarWidth + 1, HealthBarHeight + 1);
        //draws a rectangle
        
        myImage.setColor(Color.RED);
        myImage.fillRect(1,1, health*PixelsPerHealthPoint, HealthBarHeight);
        
    }
    
    public void LoseHealth
    {
        health--;
    }
    }