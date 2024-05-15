package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.IllegalFormatCodePointException;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * 3);
            bullet.setY(bullet.getY() + changeY * 3);

            if (bullet.getY() > gameData.getDisplayHeight() || bullet.getY() < 0)
            {
                world.removeEntity(bullet);
            }
            if (bullet.getX() > gameData.getDisplayWidth() || bullet.getX() < 0)
            {
                world.removeEntity(bullet);
            }
        }
    }


    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        // Adjust the starting position based on the shooter's rotation
//        double shooterFrontX = shooter.getX() + Math.cos(Math.toRadians(shooter.getRotation())) * shooter.getRadius();
//        double shooterFrontY = shooter.getY() + Math.sin(Math.toRadians(shooter.getRotation()))* shooter.getRadius();
        double distanceFront = 20; // Adjust this value to ensure the bullet spawns far enough to avoid self-collision
        double shooterFrontX = shooter.getX() + Math.cos(Math.toRadians(shooter.getRotation())) * (shooter.getRadius() + distanceFront);
        double shooterFrontY = shooter.getY() + Math.sin(Math.toRadians(shooter.getRotation())) * (shooter.getRadius() + distanceFront);

        bullet.setX(shooterFrontX);
        bullet.setY(shooterFrontY);
        bullet.setRotation(shooter.getRotation());

        bullet.setDmg(1);
        bullet.setHP(1);

        return bullet;
    }

}
