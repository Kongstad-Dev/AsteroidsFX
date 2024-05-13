package dk.sdu.mmmi.cbse.asteroidssystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidsControlSystem implements IEntityProcessingService {
    Random random = new Random();

    private int asteroidsCount = 0;
    private int maxAsteroids = 10;

    @Override
    public void process(GameData gameData, World world) {



//        // Chance to spawn 1
//        int randomInt = random.nextInt(50);
//
//        for (Entity asteroid : world.getEntities(Asteroids.class)) {
//            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
//            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
//            asteroid.setX(asteroid.getX() + changeX * 0.5);
//            asteroid.setY(asteroid.getY() + changeY * 0.5);
//
//
//            if (asteroid.getY() > gameData.getDisplayHeight() || asteroid.getY() < 0) {world.removeEntity(asteroid);}
//            if (asteroid.getX() > gameData.getDisplayWidth() || asteroid.getX() < 0) {world.removeEntity(asteroid);}
//
//
//            if (asteroid.getHP() == 5)
//            {
//                createAsteroid(gameData, world, AsteroidSize.MEDIUM);
//                createAsteroid(gameData, world, AsteroidSize.MEDIUM);
//                world.removeEntity(asteroid);
//            }
//
//            if (asteroid.getHP() == 2)
//            {
//                createAsteroid(gameData, world, AsteroidSize.SMALL);
//                createAsteroid(gameData, world, AsteroidSize.SMALL);
//                world.removeEntity(asteroid);
//            }
//        }
//
//        if (randomInt==1 && asteroidsCount < maxAsteroids) {
//            // Add entities to the world
//            createAsteroid(gameData, world, AsteroidSize.LARGE);
//            asteroidsCount++;
//        }



    }

    public enum AsteroidSize {
        LARGE,
        MEDIUM,
        SMALL
    }

    public void createAsteroid(GameData gameData, World world, AsteroidSize size) {
        Random rand = new Random();



        Entity asteroid = new Asteroids();
        asteroid.setHP(10);
        double[] coordinates;

        switch (size) {
            case LARGE:
                coordinates = new double[]{
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
                        -5.0, -15.0    // Vertex 14
                };
                break;
            case MEDIUM:
                coordinates = new double[]{
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
                        -2.5, -7.5    // Vertex 14
                };
                break;
            case SMALL:
                coordinates = new double[]{
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
                        -1.25, -3.75    // Vertex 14
                };
                break;
            default:
                throw new IllegalArgumentException("Invalid asteroid size");
        }

        asteroid.setPolygonCoordinates(coordinates);
        asteroid.setRotation(rand.nextDouble() * 360);

        world.addEntity(asteroid);
    }
}
