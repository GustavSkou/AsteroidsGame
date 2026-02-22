package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double xVelocity;
    private double yVelocity;
    private double rotation;
    private float radius;
    private double normalizedSpeed;

    public double getXDirection() {
        return Math.cos(Math.toRadians(this.getRotation()));
    }

    public double getYDirection() {
        return Math.sin(Math.toRadians(this.getRotation()));
    }

    /*
    * speed multiplier between 0 and 1
    * */
    public double getNormalizedSpeed() {
        return normalizedSpeed;
    }

    /*
     * speed multiplier between 0 and 1
     * */
    public void setNormalizedSpeed(double normalizedSpeed) {
        if (normalizedSpeed > 1) {
            normalizedSpeed = 1;
        } else if  (normalizedSpeed < 0) {
            normalizedSpeed = 0;
        }
        this.normalizedSpeed = normalizedSpeed;
    }

    public double getXVelocity() {
        return this.xVelocity;
    }

    public double getYVelocity() {
        return this.yVelocity;
    }

    public double setXVelocity(double velocity) {
        return this.xVelocity = velocity;
    }

    public double setYVelocity(double velocity) {
        return this.yVelocity = velocity;
    }

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
        this.x = x;
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

    public void setRadius(float radius) {
        this.radius = radius;
    }
        
    public float getRadius() {
        return this.radius;
    }
}
