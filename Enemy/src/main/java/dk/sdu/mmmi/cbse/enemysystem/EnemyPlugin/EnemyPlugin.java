package dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import javafx.scene.text.Text;

import java.util.Random;

public class EnemyPlugin  implements IGamePluginService {

    public int corner;

        private Entity enemy;

        public EnemyPlugin() {
        }

        @Override
        public void start(GameData gameData, World world) {

            // Add entities to the world

                enemy = createEnemyShip(gameData);
                world.addEntity(enemy);



        }

        private Entity createEnemyShip(GameData gameData) {
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

            corner = Rand.nextInt(4);

            switch (corner)  {
            case 0: // top-left
                enemyShip.setX(buffer );
            enemyShip.setY(buffer);
            break;
            case 1: // top-right
            enemyShip.setX(screenWidth - buffer );
            enemyShip.setY(buffer);
            break;
            case 2: // bottom-left
            enemyShip.setX(buffer );
            enemyShip.setY(screenHeight - buffer); // Adjust so it doesn't spawn on the edge
            break;
            case 3: // bottom-right
            enemyShip.setX(screenWidth - buffer);
            enemyShip.setY(screenHeight - buffer);
            break;
        }

            return enemyShip;
        }

        @Override
        public void stop(GameData gameData, World world) {
            // Remove entities
            world.removeEntity(enemy);
        }


}