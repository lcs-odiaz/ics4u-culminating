import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Template for a side-scrolling platform game.
 * 
 * @author R. Gordon
 * @version May 8, 2019
 */
public class GameWorld extends World
{
    /**
     * Instance variables
     * 
     * These are available for use in any method below.
     */    
    // Tile size in pixels for world elements (blocks, clouds, etc)
    // TO STUDENTS: Modify if your game's tiles have different dimensions
    private static final int TILE_SIZE = 32;
    private static final int HALF_TILE_SIZE = TILE_SIZE / 2;

    // World size constants
    // TO STUDENTS: Modify only if you're sure
    //              Should be a resolution that's a multiple of TILE_SIZE
    public static final int VISIBLE_WIDTH = 640;
    private static final int VISIBLE_HEIGHT = 480;

    // Additional useful constants based on world size
    public static final int HALF_VISIBLE_WIDTH = VISIBLE_WIDTH / 2;
    private static final int HALF_VISIBLE_HEIGHT = VISIBLE_HEIGHT / 2;

    // Names of all the players
    // TO STUDENTS: Add to this list of constants if you wish to have additional player types
    public static final String PLAYER_GUILE = "guile";
    public static final String PLAYER_VIGA = "viga";

    // Main player
    Player playerOne;
    Player playerTwo;

    // Track whether game is on
    private boolean isGameOver;

    /**
     * Constructor for objects of class GameWorld.
     */
    public GameWorld()
    {    
        // Create a new world with 640x480 cells with a cell size of 1x1 pixels.
        // Final argument of 'false' means that actors in the world are not restricted to the world boundary.
        // See: https://www.greenfoot.org/files/javadoc/greenfoot/World.html#World-int-int-int-boolean-
        super(VISIBLE_WIDTH, VISIBLE_HEIGHT, 1, false);

        // Set up the starting scene
        setup();

        // Game on
        isGameOver = false;
    }

    
    /**
     * Set up the entire world.
     */
    private void setup()
    {
        // TO STUDENTS: Add, revise, or remove methods as needed to define your own game's world
        // addLeftGround();
        // addFences();
        // addClouds();
        // addPlayerOne();
        // addPlayerTwo();
        
        ericLevel();
    }

    private void ericLevel()
    {
        // How many tiles will cover the bottom of the initial visible area of screen?
        final int tilesToCreate = getWidth() / TILE_SIZE;
        
        //Loop three times to make three sets of blocks
        for (int j = 0; j < 65; j += 32)
        {
            // Loop to create and add the tile objects
            for (int i = 0; i < tilesToCreate; i += 1)
            {
                // Add ground objects at bottom of screen
                // NOTE: Actors are added based on their centrepoint, so the math is a bit trickier.
                int x = i * TILE_SIZE + HALF_TILE_SIZE;
                int y = getHeight() - HALF_TILE_SIZE - j;
        
                // Create a ground tile
                Ground groundTile = new Ground(x, y);
        
                // Add the objects
                addObject(groundTile, x, y);
            }
        }
        
        addPlayerOne ();
        addPlayerTwo();
     
        // How many plates total?
        final int COUNT_OF_METAL_PLATES = 8;
        final int PLATES_PER_GROUP = 4;

         // Add groups of plates
        for (int i = 0; i < 321; i += 320)
        {
            // Group of four metal plates all at same y position
            int y = 272;

            // Add the individual plates in a given group
            for (int j = 0; j < 97; j += 32)
            {
                int x = 112 + i + j;
                MetalPlate plate = new MetalPlate(x, y);
                addObject(plate, x, y);
            }
        }
        
    
    
    }
    
    
    /**
     * Randomly choose and set a background
     */
    private void setBackground()
    {
       int x = Greenfoot.getRandomNumber(7);
       Decoration background;
       if (x == 1)
       {
           background = new BG_1(VISIBLE_WIDTH / 2, VISIBLE_HEIGHT / 2);
       } else if (x == 2) {
           background = new BG_2(VISIBLE_WIDTH / 2, VISIBLE_HEIGHT / 2);
       } else if (x == 3) {
           background = new BG_3(VISIBLE_WIDTH / 2, VISIBLE_HEIGHT / 2);
       }else if (x == 4) {
           background = new BG_4(VISIBLE_WIDTH / 2, VISIBLE_HEIGHT / 2);
       }else if (x == 5) {
           background = new BG_5(VISIBLE_WIDTH / 2, VISIBLE_HEIGHT / 2);
       }else if (x == 6) {
           background = new BG_6(VISIBLE_WIDTH / 2, VISIBLE_HEIGHT / 2);
       } else {
           background = new BG_7(VISIBLE_WIDTH / 2, VISIBLE_HEIGHT / 2);
        }
       
       addObject(background, VISIBLE_WIDTH / 2, VISIBLE_HEIGHT / 2-64);
    }
    
    /**
     * Add blocks to create the ground to walk on at bottom-left of scrollable world.
     */
    private void addLeftGround()
    {
        // How many tiles will cover the bottom of the initial visible area of screen?
        final int tilesToCreate = getWidth() / TILE_SIZE;

        // Loop to create and add the tile objects
        for (int i = 0; i < tilesToCreate; i += 1)
        {
            // Add ground objects at bottom of screen
            // NOTE: Actors are added based on their centrepoint, so the math is a bit trickier.
            int x = i * TILE_SIZE + HALF_TILE_SIZE;
            int y = getHeight() - HALF_TILE_SIZE;

            // Create a ground tile
            Ground groundTile = new Ground(x, y);

            // Add the objects
            addObject(groundTile, x, y);
        }
    }

    /**
     * Add some fences at left and right side.
     */
    private void addFences()
    {
        // Three fences on left side of world
        int x = HALF_TILE_SIZE + TILE_SIZE * 5;
        int y = VISIBLE_HEIGHT - HALF_TILE_SIZE - TILE_SIZE;
        Fence fence1 = new Fence(x, y);
        addObject(fence1, x, y);

        x = HALF_TILE_SIZE + TILE_SIZE * 6;
        y = VISIBLE_HEIGHT - HALF_TILE_SIZE - TILE_SIZE;        
        Fence fence2 = new Fence(x, y);
        addObject(fence2, x, y);

        x = HALF_TILE_SIZE + TILE_SIZE * 7;
        y = VISIBLE_HEIGHT - HALF_TILE_SIZE - TILE_SIZE;
        Fence fence3 = new Fence(x, y);
        addObject(fence3, x, y);
    }

    /**
     * Add a few clouds for the opening scene.
     */
    private void addClouds()
    {
        Cloud cloud1 = new Cloud(170, 125);
        addObject(cloud1, 170, 125);
        Cloud cloud2 = new Cloud(450, 175);
        addObject(cloud2, 450, 175);
    }

    /**
     * Act
     * 
     * This method is called approximately 60 times per second.
     */
    public void act()
    {
    }

    /**
     * Add the main player to the world.
     */
    private void addPlayerOne()
    {
        // Initial horizontal position
        int initialX = TILE_SIZE * 3;

        // Instantiate the main player object object
        playerOne = new Viga(initialX, "a", "d", "w", "f", 100);

        // Add player in bottom left corner of screen
        addObject(playerOne, initialX, getHeight() / 4 * 3);
    }

    /**
     * Add the main player to the world.
     */
    private void addPlayerTwo()
    {
        // Initial horizontal position
        int initialX = VISIBLE_WIDTH - TILE_SIZE * 3;

        // Instantiate the main player object object
        playerTwo = new Guile(initialX, "left", "right", "up", "shift", getWidth() - 100);

        // Add player in bottom left corner of screen
        addObject(playerTwo, initialX, getHeight() / 4 * 3);
    }

    /**
     * Return an object reference to the first player.
     */
    public Player getMainPlayer()
    {
        return playerOne;
    }

    /**
     * Set game over
     */
    public void setGameOver()
    {
        isGameOver = true;
    }

    private void addMetalPlateSteps()
    {
        // How many plates total?
        final int COUNT_OF_METAL_PLATES = 20;
        final int PLATES_PER_GROUP = 4;

        // Add groups of plates
        for (int i = 0; i < COUNT_OF_METAL_PLATES / PLATES_PER_GROUP; i += 1)
        {
            // Group of four metal plates all at same y position
            int y = VISIBLE_HEIGHT - HALF_TILE_SIZE * 3 - i * TILE_SIZE;

            // Add the individual plates in a given group
            for (int j = 0; j < PLATES_PER_GROUP; j += 1)
            {
                int x = VISIBLE_WIDTH + TILE_SIZE * 2 + TILE_SIZE * (i + j) + TILE_SIZE * 5 * i;
                MetalPlate plate = new MetalPlate(x, y);
                addObject(plate, x, y);
            }
        }
    }










}

