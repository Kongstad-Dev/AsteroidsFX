package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;


import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        int fireThreshhold = 100;
        int maxBullets = 5;

        Random random = new Random();
        int randomInt;
        int randomInt2;

        for (Entity enemy : world.getEntities(Enemy.class)) {

//            System.out.println(enemy.getRotation());


            // Increment frame counter
            ((Enemy)enemy).setFrameCounter(((Enemy)enemy).getFrameCounter() + 1);

            if (((Enemy)enemy).getFrameCounter() >= fireThreshhold && ((Enemy)enemy).getBulletsFired() < maxBullets) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(enemy, gameData));}
                );
                // Reset frame counter and increment bullets fired
                ((Enemy)enemy).setFrameCounter(0);
                ((Enemy)enemy).setBulletsFired(((Enemy)enemy).getBulletsFired() + 1);
            }

            randomInt = random.nextInt(2);
            randomInt2 = random.nextInt(10);

            if (randomInt==0) {
                enemy.setRotation(enemy.getRotation() - 5);
            }
            if (randomInt==1) {
                enemy.setRotation(enemy.getRotation() + 5);
            }
            if (true) {
                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX);
                enemy.setY(enemy.getY() + changeY);
            }
//            if(randomInt2==1) {
//                getBulletSPIs().stream().findFirst().ifPresent(
//                        spi -> {world.addEntity(spi.createBullet(enemy, gameData));}
//                );
//
//            }

            if (enemy.getX() < 0) {
                enemy.setX(1);
                enemy.setRotation(enemy.getRotation()+180);

            }

            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(gameData.getDisplayWidth()-1);
                enemy.setRotation(enemy.getRotation()+180);

            }

            if (enemy.getY() < 0) {
                enemy.setY(1);
                enemy.setRotation(enemy.getRotation()+180);

            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(gameData.getDisplayHeight()-1);
                enemy.setRotation(enemy.getRotation()+180);
            }


        }

        if (world.getEntities(Enemy.class).size() < 2)
        {
            createEnemyShip(gameData, world);
        }

    }

    private void createEnemyShip(GameData gameData, World world) {
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
            case 0 -> { // top-left
                enemyShip.setX(buffer);
                enemyShip.setY(buffer);
            }
            case 1 -> { // top-right
                enemyShip.setX(screenWidth - buffer);
                enemyShip.setY(buffer);
            }
            case 2 -> { // bottom-left
                enemyShip.setX(buffer);
                enemyShip.setY(screenHeight - buffer); // Adjust so it doesn't spawn on the edge
            }
            case 3 -> { // bottom-right
                enemyShip.setX(screenWidth - buffer);
                enemyShip.setY(screenHeight - buffer);
            }
        }

        world.addEntity(enemyShip);
    }


    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}