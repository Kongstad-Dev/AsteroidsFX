package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class EnemyPlugin  implements IGamePluginService {

//    public int corner;

    private Entity enemyShip;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        enemyShip = createEnemyShip(gameData, world);
        world.addEntity(enemyShip);
    }


    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemyShip);
    }

    private Entity createEnemyShip(GameData gameData, World world) {
        Random Rand = new Random();
        int buffer = 100;
        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(
                6.0, -3.0, -6.0, -3.0,
                -6.0, -3.0, -15.0, 3.0,
                -15.0, 3.0, 15.0, 3.0,
                15.0, 3.0, 6.0, 9.0,
                6.0, 9.0, -6.0, 9.0,
                -6.0, 9.0, -15.0, 3.0,
                -15.0, 3.0, -6.0, -3.0,
                -6.0, -3.0, -3.0, -9.0,
                -3.0, -9.0, 3.0, -9.0,
                3.0, -9.0, 6.0, -3.0,
                6.0, -3.0, 15.0, 3.0

        );
        float screenWidth = gameData.getDisplayWidth();
        float screenHeight = gameData.getDisplayHeight();

        int corner = Rand.nextInt(4);

        switch (corner) {
            case 1 -> { // top-left
                enemyShip.setX(buffer);
                enemyShip.setY(buffer);
            }
            case 2 -> { // top-right
                enemyShip.setX(screenWidth - buffer);
                enemyShip.setY(buffer);
            }
            case 3 -> { // bottom-left
                enemyShip.setX(buffer);
                enemyShip.setY(screenHeight - buffer); // Adjust so it doesn't spawn on the edge
            }
            case 4 -> { // bottom-right
                enemyShip.setX(screenWidth - buffer);
                enemyShip.setY(screenHeight - buffer);
            }
        }

        return enemyShip;
    }
}