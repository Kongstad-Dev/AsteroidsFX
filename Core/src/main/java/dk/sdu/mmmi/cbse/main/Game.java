package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

class Game {


        private final GameData gameData = new GameData();
        private final World world = new World();
        private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
        private Map<Entity, Rectangle> hitboxes = new ConcurrentHashMap<>();
        private final Pane gameWindow = new Pane();

        public Text textEntityCount;
    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServiceList;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;

    Game(List<IGamePluginService> gamePluginServices, List<IEntityProcessingService> entityProcessingServiceList, List<IPostEntityProcessingService> postEntityProcessingServices) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServiceList = entityProcessingServiceList;
        this.postEntityProcessingServices = postEntityProcessingServices;
    }


        public void start(Stage window) throws Exception {
            Text text = new Text(10, 20, "Destroyed asteroids: 0");
            gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());

            textEntityCount = new Text(10, 20, "Entity count: 0");
            textEntityCount.setFill(Color.WHEAT);
            gameWindow.getChildren().add(textEntityCount);


            Scene scene = new Scene(gameWindow);
            scene.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.A)) {
                    gameData.getKeys().setKey(GameKeys.A, true);
                }
                if (event.getCode().equals(KeyCode.D)) {
                    gameData.getKeys().setKey(GameKeys.D, true);
                }
                if (event.getCode().equals(KeyCode.W)) {
                    gameData.getKeys().setKey(GameKeys.W, true);
                }
                if (event.getCode().equals(KeyCode.Z)) {
                    gameData.getKeys().setKey(GameKeys.Z, true);
                }
                if (event.getCode().equals(KeyCode.SPACE)) {
                        gameData.getKeys().setKey(GameKeys.SPACE, true);
                }
            });
            scene.setOnKeyReleased(event -> {
                if (event.getCode().equals(KeyCode.A)) {
                    gameData.getKeys().setKey(GameKeys.A, false);
                }
                if (event.getCode().equals(KeyCode.D)) {
                    gameData.getKeys().setKey(GameKeys.D, false);
                }
                if (event.getCode().equals(KeyCode.W)) {
                    gameData.getKeys().setKey(GameKeys.W, false);
                }
                if (event.getCode().equals(KeyCode.SPACE)) {
                    gameData.getKeys().setKey(GameKeys.SPACE, false);
                }
                if (event.getCode().equals(KeyCode.Z)) {
                    gameData.getKeys().setKey(GameKeys.Z, false);
                }

            });

            // Lookup all Game Plugins using ServiceLoader
            for (IGamePluginService iGamePlugin : getPluginServices()) {
                iGamePlugin.start(gameData, world);
            }
            for (Entity entity : world.getEntities()) {
                Polygon polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);

            }
            gameWindow.setBackground(Background.fill(Color.BLACK));
            render();

            window.setScene(scene);
            window.setTitle("ASTEROIDS");
            window.show();

        }

    private void initializeHitboxesForNewEntities() {
        for (Entity entity : world.getEntities()) {
            if (!hitboxes.containsKey(entity)) { // Check if the entity already has a hitbox
                Rectangle hitbox = new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
                hitbox.setStroke(Color.RED);
                hitbox.setFill(Color.TRANSPARENT);
                gameWindow.getChildren().add(hitbox);
                hitboxes.put(entity, hitbox); // Add the new hitbox to the map
            }
        }
    }

    private void updateHitboxes() {
        initializeHitboxesForNewEntities();

        Iterator<Map.Entry<Entity, Rectangle>> iterator = hitboxes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Entity, Rectangle> entry = iterator.next();
            Entity entity = entry.getKey();
            Rectangle hitbox = entry.getValue();

            if (world.getEntities().contains(entity)) {
                // Update the hitbox's position and size to match the entity
                //hitbox.setX(entity.getMinWidth());
                //hitbox.setX(entity.getminHeight());
                hitbox.setX(entity.getX());
                hitbox.setY(entity.getY());
                hitbox.setWidth(entity.getWidth());
                hitbox.setHeight(entity.getHeight());
            } else {
                // If the entity no longer exists, remove its hitbox
                gameWindow.getChildren().remove(hitbox);
                iterator.remove(); // It's important to use iterator.remove() to avoid ConcurrentModificationException
            }
        }

        // Toggle hitboxes visibility
        if (gameData.getKeys().isPressed(GameKeys.Z)) {
            for (Rectangle hitbox : hitboxes.values()) {
                hitbox.setVisible(!hitbox.isVisible());
            }
        }
    }

        public void render() {
            new AnimationTimer() {
                private long then = 0;

                @Override
                public void handle(long now) {
                    update();
                    draw();
                    gameData.getKeys().update();
                    textEntityCount.setText("Entity count: " + polygons.size());

                }

            }.start();
        }

        private void update() {

            // Update
            for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
                entityProcessorService.process(gameData, world);
            }
            for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
                postEntityProcessorService.process(gameData, world);
            }

        }

        private void draw() {
            for (Entity polygonEntity : polygons.keySet()) {

                if(!world.getEntities().contains(polygonEntity)){
                    Polygon removedPolygon = polygons.get(polygonEntity);
                    polygons.remove(polygonEntity);
                    gameWindow.getChildren().remove(removedPolygon);
                }
                updateHitboxes();
            }


            for (Entity entity : world.getEntities()) {
                Polygon polygon = polygons.get(entity);
                if (polygon == null) {
                    polygon = new Polygon(entity.getPolygonCoordinates());
                    polygons.put(entity, polygon);
                    gameWindow.getChildren().add(polygon);
                }
                polygon.setTranslateX(entity.getX());
                polygon.setTranslateY(entity.getY());
                polygon.setRotate(entity.getRotation());

                polygon.setFill(Color.rgb(159,211,68));

            }

        }

        private Collection<? extends IGamePluginService> getPluginServices() {
            return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
        }

        private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
            return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
        }

        private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
            return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
        }
    }


