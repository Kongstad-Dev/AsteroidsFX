package dk.sdu.mmmi.cbse.Collision;

import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector(){}

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (collides(entity1, entity2) && entity1.getClass() != entity2.getClass()) {
                    // If the entities are colliding, reduce their HP by their respective damage
                        entity1.setHP(entity1.getHP() - entity2.getDmg());
                        entity2.setHP(entity2.getHP() - entity1.getDmg());
                    System.out.println("Entity 1 is:" + entity1.getClass());
                    if (entity1.getHP() < 1) {
                        world.removeEntity(entity1);
                    }
                    if (entity2.getHP() < 1) {
                        world.removeEntity(entity2);
                    }

                }
            }
        }
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        // Get the maximum width and height of each entity
        double maxWidth1 = entity1.getWidth();
        double maxHeight1 = entity1.getHeight();
        double maxWidth2 = entity2.getWidth();
        double maxHeight2 = entity2.getHeight();

        // Calculate the minimum distance required for collision
        double minDistanceX = (maxWidth1 + maxWidth2) / 2;
        double minDistanceY = (maxHeight1 + maxHeight2) / 2;

        // Calculate the distance between the centers of the polygons
        double dx = entity1.getX() - entity2.getX();
        double dy = entity1.getY() - entity2.getY();

        // Check if the distance is less than the minimum required for collision
        return Math.abs(dx) < minDistanceX && Math.abs(dy) < minDistanceY;
    }
}