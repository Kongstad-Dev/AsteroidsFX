package dk.sdu.mmmi.cbse.asteroidssystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidsPlugin implements IGamePluginService, IEntityProcessingService {

    private Entity asteroids;

    public AsteroidsPlugin()
    {

    }




    @Override
    public void start(GameData gameData, World world) {

        asteroids = createAsteroids(gameData);
        world.addEntity(asteroids);

    }
    private Entity createAsteroids(GameData gameData)
    {
        Entity Asteroids = new Asteroids();
        Asteroids.setPolygonCoordinates(
                0.0, -20.0,    // Vertex 1
                5.0, -15.0,    // Vertex 2
                18.0, -18.0,   // Vertex 3
                20.0, -5.0,    // Vertex 4
                15.0, 0.0,     // Vertex 5
                20.0, 10.0,    // Vertex 6
                10.0, 15.0,    // Vertex 7
                0.0, 20.0,     // Vertex 8
                -10.0, 15.0,   // Vertex 9
                -20.0, 10.0,   // Vertex 10
                -15.0, 5.0,    // Vertex 11
                -18.0, -5.0,   // Vertex 12
                -10.0, -10.0,  // Vertex 13
                -5.0, -15.0);    // Vertex 14

        Random Rand = new Random();

        float screenWidth = gameData.getDisplayWidth();
        float screenHeight = gameData.getDisplayHeight();

        double rotation = Rand.nextDouble()*360;
        Asteroids.setRotation(rotation);
//TODO Make a spawn box away from the center of the screen

       int RandSpawnX = Rand.nextInt((int) screenWidth);
       int RandSpawnY = Rand.nextInt((int) screenHeight);

       Asteroids.setX(RandSpawnX);
       Asteroids.setY(RandSpawnY);


        return Asteroids;
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroids : world.getEntities(Asteroids.class))
        {

            int currentAsteroids = countAsteroids(world);
            int asteroidsNeeded = 4 - currentAsteroids;
            for (int i = 0; i < asteroidsNeeded; i++)
            {
                Entity NewAsteroids = createAsteroids(gameData);
                world.addEntity(NewAsteroids);
            }
            double changeX = Math.cos(Math.toRadians(asteroids.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroids.getRotation()));
            asteroids.setX(asteroids.getX() + changeX);
            asteroids.setY(asteroids.getY() + changeY);

            if (asteroids.getY() > gameData.getDisplayHeight() || asteroids.getY() < 0)
            {
                world.removeEntity(asteroids);
                System.out.println("DEAD");
            }
            if (asteroids.getX() > gameData.getDisplayWidth() || asteroids.getX() < 0)
            {
                world.removeEntity(asteroids);
                System.out.println("DEAD2");
            }





        }


    }

    @Override
    public void stop(GameData gameData, World world) {

        world.removeEntity(asteroids);

    }

    public int countAsteroids(World world) {
        int asteroidCount = 0;
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Asteroids) { // Assuming there's an Asteroid class
                asteroidCount++;
            }
        }
        return asteroidCount;
    }
}
