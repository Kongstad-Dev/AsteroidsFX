//import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires javafx.graphics;
    provides IGamePluginService with dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin.EnemyPlugin;
}