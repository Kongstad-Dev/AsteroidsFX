import dk.sdu.mmmi.cbse.asteroidssystem.Asteroids;
import dk.sdu.mmmi.cbse.asteroidssystem.AsteroidsControlSystem;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AsteroidsControlSystemTest {

    private AsteroidsControlSystem asteroidsControlSystem;
    private GameData gameData;
    private World world;

    @BeforeEach
    void setUp() {
        asteroidsControlSystem = new AsteroidsControlSystem();
        gameData = new GameData();
        world = new World();
    }

    @AfterEach
    void tearDown() {
        asteroidsControlSystem = null;
        gameData = null;
        world = null;
    }

    @Test
    void testAsteroidMovement() {
        Entity asteroid = new Asteroids();
        asteroid.setX(100);
        asteroid.setY(100);
        asteroid.setRotation(0); // Moving right
        world.addEntity(asteroid);

        asteroidsControlSystem.process(gameData, world);
        assertTrue(asteroid.getX() > 100 && asteroid.getY() == 100);
    }

    @Test
    void testAsteroidCreation() {
        asteroidsControlSystem.createAsteroid(gameData, world, AsteroidsControlSystem.AsteroidSize.LARGE);
        assertEquals(1, world.getEntities(Asteroids.class).size());
        Entity asteroid = world.getEntities(Asteroids.class).iterator().next();
        assertNotNull(asteroid.getPolygonCoordinates());
        // Further assertions can be made on the coordinates based on the expected shape
    }

    @Test
    void testAsteroidRemovalWhenOutOfDisplay() {
        Entity asteroid = new Asteroids();
        asteroid.setX(-1); // Position outside the display area
        asteroid.setY(-1);
        world.addEntity(asteroid);

        asteroidsControlSystem.process(gameData, world);
        assertTrue(world.getEntities(Asteroids.class).isEmpty());
    }
}