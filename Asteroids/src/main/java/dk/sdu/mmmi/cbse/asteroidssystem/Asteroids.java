package dk.sdu.mmmi.cbse.asteroidssystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

import java.util.Random;

public class Asteroids extends Entity{


        public Entity asteroidSplit(Entity entity, int size) {
            Random r = new Random();

            Entity asteroid = new Asteroids();
            asteroid.setX(entity.getX());
            asteroid.setY(entity.getY());
            asteroid.setRotation(entity.getRotation() + r.nextInt(360));
            //asteriod.setHP(5);


            switch (size){
                case 1:
                    asteroid.setPolygonCoordinates(
                            0.0, -20.0,    // Vertex 1
                            5.0, -15.0,    // Vertex 2
                            18.0, -18.0,   // Vertex 3
                            20.0, -5.0,    // Vertex 4
                            15.0, 0.0,     // Vertex 5
                            20.0, 10.0,    // Vertex 6
                            10.0, 15.0,    // Vertex 7
                            0.0, 20.0,     // Vertex 8
                            -10.0, 15.0,   // Vertex 9
                            -20.0, 10.0,   // Vertex 10
                            -15.0, 5.0,    // Vertex 11
                            -18.0, -5.0,   // Vertex 12
                            -10.0, -10.0,  // Vertex 13
                            -5.0, -15.0);    // Vertex 14

                    break;
                case 2:
                    asteroid.setPolygonCoordinates(
                            0.0, -10.0,    // Vertex 1
                            2.5, -7.5,    // Vertex 2
                            9.0, -9.0,   // Vertex 3
                            10.0, -2.5,    // Vertex 4
                            7.5, 0.0,     // Vertex 5
                            10.0, 5.0,    // Vertex 6
                            5.0, 7.5,    // Vertex 7
                            0.0, 10.0,     // Vertex 8
                            -5.0, 7.5,   // Vertex 9
                            -10.0, 5.0,   // Vertex 10
                            -7.5, 2.5,    // Vertex 11
                            -9.0, -2.5,   // Vertex 12
                            -5.0, -5.0,  // Vertex 13
                            -2.5, -7.5);    // Vertex 14
                    break;
            }
            return asteroid;
        }
}

