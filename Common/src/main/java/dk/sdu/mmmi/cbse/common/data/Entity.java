package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;

    private double width;

    private double height;

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {this.radius = radius;}


    public float getRadius() {
        return this.radius;
    }


    public double getWidth() {
        double[] coordinates = polygonCoordinates;
        double max = Arrays.stream(coordinates).max().orElse(-1);
        return max*2;
    }

    public void setWidth(double width) {this.width = width;}

    public double getHeight() {
        double[] coordinatesY = polygonCoordinates;
        double maxY = Arrays.stream(coordinatesY).max().orElse(-1);
        return maxY*2;

    }

    public void setHeight(double height) {this.height = height;}
}
