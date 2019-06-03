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

    // Track hits
    private int hitsLanded;

    // Main player
    Player playerOne;
    Player playerTwo;
    Decoration POneHB;
    Decoration PTwoHB;
    
    // makes health bar
    HealthBar HealthBar;

    //makes Health Kit
    Decoration HealthKit;

    // Track whether game is on
    private boolean isGameOver;
    
    // Background music
    GreenfootSound backgroundMusic;

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

        // No hits to start game
        hitsLanded = 0;
    }
    
    //
    public HealthBar getHealthBar()
    {
        
        return HealthBar;
    }

    /**
     * This method is called when the world is 'Run'
     */
    public void started()
    {
        //Starts the music when the "Run" button is clicked
        setMusic();
    }
    
    /**
     * This method is called when the world is paused.
     */
    public void stopped()
    {
        //Stops the background music when the game ends
        backgroundMusic.stop();
    }

    /**
     * Set up the entire world.
     */
    private void setup()
    {
        // Generates a random background
        setBackground();
        
        // Sets up the level
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
        
        //Adds Player one and two
        addPlayerOne();
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
        //Sets the background to decoration BG_1
        Decoration background = new BG_1(VISIBLE_WIDTH / 2, VISIBLE_HEIGHT / 2);
        addObject(background, VISIBLE_WIDTH / 2, VISIBLE_HEIGHT / 2-48);
    }

    /**
     * Randomly choose and set background music
     */
    private void setMusic()
    {
        //Generates a random number. Each number corresponds to a different song that is played if the number of the song is chosen
        int x = Greenfoot.getRandomNumber(9);
        backgroundMusic = new GreenfootSound(x + ".mp3");
        backgroundMusic.playLoop();
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
        playerOne = new Viga(initialX, "a", "d", "w", "f");

        // Add player in bottom left corner of screen
        addObject(playerOne, initialX, getHeight() / 4 * 3);

        // Add the player's health bar in left corner the screen
        addObject(playerOne.getHealthBar(), initialX, 50);
    }

    /**
     * Add the main player to the world.
     */
    private void addPlayerTwo()
    {
        // Initial horizontal position
        int initialX = VISIBLE_WIDTH - TILE_SIZE * 3;

        // Instantiate the main player object object
        playerTwo = new Guile(initialX, "left", "right", "up", "shift");

        // Add player in bottom left corner of screen
        addObject(playerTwo, initialX, getHeight() / 4 * 3);

        // Add the player's health bar in left corner the screen
        addObject(playerTwo.getHealthBar(), initialX, 50);
    }

    /**
     * Return an object reference to the first player.
     */
    public Player getPlayerOne()
    {
        return playerOne;
    }

    /**
     * Return an object reference to the second player.
     */
    public Player getPlayerTwo()
    {
        return playerTwo;
    }

    /**
     * Set game over
     */
    public void setGameOver()
    {
        // Makes the game over
        isGameOver = true;
    }

    /**
     * Record a hit.
     */
    public void hitLanded()
    {
        //Adds one to hits added
        hitsLanded += 1;

        // Check to see if we've hit a threshold of hits where we want to add a HealthKit
        if (hitsLanded % 5 == 0)
        {
            HealthKit hk = new HealthKit(VISIBLE_WIDTH/2,VISIBLE_HEIGHT -TILE_SIZE);
            addObject(hk,TILE_SIZE * Greenfoot.getRandomNumber(18) + TILE_SIZE, VISIBLE_HEIGHT - 12 * TILE_SIZE - TILE_SIZE/2);
        }

    }
}

