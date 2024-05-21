import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires java.desktop;
    requires Player;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.Collision.CollisionDetector;

    exports dk.sdu.mmmi.cbse.Collision;
}