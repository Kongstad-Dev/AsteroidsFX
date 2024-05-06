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

    private int HP;

    private int dmg;


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
        double[] xCoordinates = new double[polygonCoordinates.length / 2];
        for (int i = 0; i < polygonCoordinates.length; i += 2) {
            xCoordinates[i / 2] = polygonCoordinates[i];
        }
        double minX = Arrays.stream(xCoordinates).min().orElse(-1);
        double maxX = Arrays.stream(xCoordinates).max().orElse(-1);
        return maxX - minX;
    }

    public void setWidth(double width) {this.width = width;}

    public double getHeight() {
        double[] yCoordinates = new double[polygonCoordinates.length / 2];
        for (int i = 1; i < polygonCoordinates.length; i += 2) {
            yCoordinates[i / 2] = polygonCoordinates[i];
        }
        double minY = Arrays.stream(yCoordinates).min().orElse(-1);
        double maxY = Arrays.stream(yCoordinates).max().orElse(-1);
        return maxY - minY;
    }

    public void setHeight(double height) {this.height = height;}

    public void setHP(int HP) {this.HP = HP;}

    public int getHP() {return HP;}

    public int getDmg() {return dmg;}

    public void setDmg(int dmg) {this.dmg = dmg;}
}
