
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.EntityProcessor;

module Player {
    requires Common;
    requires CommonBullet;   
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;

    provides IGamePluginService with dk.sdu.mmmi.cbse.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.PlayerControlSystem, EntityProcessor;
    
}
