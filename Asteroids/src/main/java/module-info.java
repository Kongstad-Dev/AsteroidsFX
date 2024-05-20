import dk.sdu.mmmi.cbse.AsteroidsPlugin;
import dk.sdu.mmmi.cbse.EntityProcessor;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroids {
    exports dk.sdu.mmmi.cbse;
    requires Common;

    provides IGamePluginService with AsteroidsPlugin;
    provides IEntityProcessingService with EntityProcessor;
}