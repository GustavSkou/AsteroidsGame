package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;

import java.util.Random;

public class Asteroid extends Entity {
    public Asteroid() {
        Random rand = new Random();
        double size = rand.nextDouble(7,15);
        double[] polygonCoordinates = polyCoords();

        for (int i = 0; i < polygonCoordinates.length; i++) {
            polygonCoordinates[i] *= size;
        }

        setPolygonCoordinates(polygonCoordinates);

        setXVelocity(rand.nextDouble(1));
        setYVelocity(rand.nextDouble(1));
        setNormalizedSpeed(rand.nextDouble(1));

        setRotation(rand.nextFloat() * 360);
        setRadius((int)size);
    }

    private double[] polyCoords() {
        int pointRes = 36;
        double[] coords = new double[pointRes * 2];
        int index = 0;

        for (int i = 0; i < 360; i += 10) {
            double radians = Math.toRadians(i);
            coords[index++] = Math.cos(radians);
            coords[index++] = Math.sin(radians);
        }
        return coords;
    }
}