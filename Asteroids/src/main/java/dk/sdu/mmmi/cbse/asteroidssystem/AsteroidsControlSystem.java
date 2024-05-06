package dk.sdu.mmmi.cbse.asteroidssystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Random;

public class AsteroidsControlSystem implements IEntityProcessingService {
    Random random = new Random();
    @Override
    public void process(GameData gameData, World world) {



        // Chance to spawn 1
        int randomInt = random.nextInt(50);

        for (Entity asteroid : world.getEntities(Asteroids.class)) {
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
            asteroid.setX(asteroid.getX() + changeX * 0.5);
            asteroid.setY(asteroid.getY() + changeY * 0.5);

        }

        if (randomInt==1){
            // Add entities to the world
//            Entity asteroid;
            createAsteroidsLarge(gameData, world);
//            world.addEntity(asteroid);
        }
    }

    public void createAsteroidsLarge(GameData gameData, World world)
    {
        Random rand = new Random();

        // Get player's position
        float playerX = 0;
        float playerY = 0;
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Player) {
                playerX = (float) entity.getX();
                playerY = (float) entity.getY();
                break;
            }
        }
        // Generate a spawn location for the asteroid
        float spawnX;
        float spawnY;
        double safeDistance = 10;
        do {
            spawnX = rand.nextInt(gameData.getDisplayWidth());
            spawnY = rand.nextInt(gameData.getDisplayHeight());
        } while (Math.sqrt(Math.pow(spawnX - playerX, 2) + Math.pow(spawnY - playerY, 2)) < safeDistance);

        Entity asteroid = new Asteroids();
        asteroid.setPolygonCoordinates(
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
        asteroid.setX(spawnX);
        asteroid.setY(spawnY);
        asteroid.setRotation(rand.nextDouble() * 360);

        world.addEntity(asteroid);
    }

    public void createMediumAsteroids(Entity asteroid, World world, int rotation) {
        Entity asteroids = new Asteroids();
        asteroids.setPolygonCoordinates(
                0.0, -10.0,    // Vertex 1
                2.5, -7.5,    // Vertex 2
                9.0, -9.0,   // Vertex 3
                10.0, -2.5,    // Vertex 4
                7.5, 0.0,     // Vertex 5
                10.0, 5.0,    // Vertex 6
                5.0, 7.5,    // Vertex 7
                0.0, 10.0,     // Vertex 8
                -5.0, 7.5,   // Vertex 9
                -10.0, 5.0,   // Vertex 10
                -7.5, 2.5,    // Vertex 11
                -9.0, -2.5,   // Vertex 12
                -5.0, -5.0,  // Vertex 13
                -2.5, -7.5);    // Vertex 14

        asteroids.setX(asteroids.getX());
        asteroids.setY(asteroids.getY());
        asteroids.setRotation(asteroid.getRotation()+rotation);

        world.addEntity(asteroids);
    }

    public void createSmallAsteroids(Entity asteroid, World world, int rotation) {
        Entity asteroids = new Asteroids();
        asteroids.setPolygonCoordinates(
                0.0, -5.0,    // Vertex 1
                1.25, -3.75,    // Vertex 2
                4.5, -4.5,   // Vertex 3
                5.0, -1.25,    // Vertex 4
                3.75, 0.0,     // Vertex 5
                5.0, 2.5,    // Vertex 6
                2.5, 3.75,    // Vertex 7
                0.0, 5.0,     // Vertex 8
                -2.5, 3.75,   // Vertex 9
                -5.0, 2.5,   // Vertex 10
                -3.75, 1.25,    // Vertex 11
                -4.5, -1.25,   // Vertex 12
                -2.5, -2.5,  // Vertex 13
                -1.25, -3.75);    // Vertex 14

        asteroids.setX(asteroids.getX());
        asteroids.setY(asteroids.getY());
        asteroids.setRotation(asteroid.getRotation()+rotation);

        world.addEntity(asteroids);
    }
}