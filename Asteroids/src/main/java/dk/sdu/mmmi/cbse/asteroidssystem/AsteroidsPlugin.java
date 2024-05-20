package dk.sdu.mmmi.cbse.asteroidssystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidsPlugin implements IGamePluginService {
    private final double[] coords = {0.0, -20.0, 5.0, -15.0, 18.0, -18.0, 20.0, -5.0, 15.0, 0.0, 20.0, 10.0, 10.0, 15.0, 0.0, 20.0, -10.0, 15.0, -20.0, 10.0, -15.0, 5.0, -18.0, -5.0, -10.0, -10.0, -5.0, -15.0};
    private Entity asteroids;
    @Override
    public void start(GameData gameData, World world) {
        asteroids = createAsteroids(gameData);
        world.addEntity(asteroids);
    }
    private Entity createAsteroids(GameData gameData)
    {
        Entity Asteroids = new Asteroids();
        Asteroids.setPolygonCoordinates(coords);

        Random Rand = new Random();
        float screenWidth = gameData.getDisplayWidth();
        float screenHeight = gameData.getDisplayHeight();
        double rotation = Rand.nextDouble()*360;
        Asteroids.setRotation(rotation);

       int RandSpawnX = Rand.nextInt((int) screenWidth);
       int RandSpawnY = Rand.nextInt((int) screenHeight);
       Asteroids.setX(RandSpawnX);
       Asteroids.setY(RandSpawnY);

       return Asteroids;
    }


    @Override
    public void stop(GameData gameData, World world) {

        world.removeEntity(asteroids);

    }


}
