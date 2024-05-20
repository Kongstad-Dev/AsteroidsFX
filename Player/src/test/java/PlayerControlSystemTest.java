

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.playersystem.Player;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControlSystemTest {

    private PlayerControlSystem playerControlSystem;
    private GameData gameData;
    private World world;
    private Player player;

    @BeforeEach
    void setUp() {
        playerControlSystem = new PlayerControlSystem();
        gameData = new GameData();
        world = new World();
        player = new Player();
        player.setPolygonCoordinates(-5,-5,10,0,-5,5);
        player.setX(100);
        player.setY(100);
        world.addEntity(player);
    }
    @AfterEach
    void tearDown() {
        playerControlSystem = null;
        gameData = null;
        world = null;
        player = null;
    }


    @Test
    void testPlayerRotationOnRightKeyPress() {
        gameData.getKeys().setKey(GameKeys.D, true);
        gameData.getKeys().update();
        playerControlSystem.process(gameData, world);
        assertEquals(3, player.getRotation());
    }

    @Test
    void testPlayerPositionOnUpKeyPress() {
        player.setRotation(0);
        gameData.getKeys().setKey(GameKeys.W, true);
        gameData.getKeys().update();

        playerControlSystem.process(gameData, world);
        assertEquals(101, player.getX());
        assertEquals(100, player.getY());
    }

    @Test
    void testPlayerPositionAdjustmentWhenOutOfDisplayArea() {
        player.setX(gameData.getDisplayWidth() + 1);
        player.setY(gameData.getDisplayHeight() + 1);
        playerControlSystem.process(gameData, world);
        assertEquals(gameData.getDisplayWidth() - 1, player.getX());
        assertEquals(gameData.getDisplayHeight() - 1, player.getY());
    }


}