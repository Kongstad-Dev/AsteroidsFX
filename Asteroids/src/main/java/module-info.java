import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroids {
    requires Common;

    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroidssystem.AsteroidsPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroidssystem.AsteroidsControlSystem;

    exports dk.sdu.mmmi.cbse.asteroidssystem;
}