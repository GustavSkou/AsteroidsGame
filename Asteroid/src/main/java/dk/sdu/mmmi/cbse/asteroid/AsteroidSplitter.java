package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSplitterSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;

public class AsteroidSplitter implements AsteroidSplitterSPI {
    @Override
    public void split(Entity asteroid, World world) {
        Random random = new Random();
        Entity[] babyAsteroids = getBabyAsteroids(asteroid);
        for (Entity babyAsteroid : babyAsteroids) {
            if (random.nextInt(2) == 0) {
                world.addEntity(babyAsteroid);
            }
        }
    }

    private Entity[] getBabyAsteroids(Entity asteroid) {
        Entity[] babyAsteroids = new dk.sdu.mmmi.cbse.common.asteroid.Asteroid[2];
        double[][] velocities = getBabyVelocityVectors(asteroid);

        for (int i = 0; i < 2; i++) {
            Entity babyAsteroid = new dk.sdu.mmmi.cbse.common.asteroid.Asteroid();

            babyAsteroid.setXVelocity(velocities[i][0]);
            babyAsteroid.setYVelocity(velocities[i][1]);

            babyAsteroid.setX(asteroid.getX() + babyAsteroid.getXVelocity() * babyAsteroid.getRadius() * 2);
            babyAsteroid.setY(asteroid.getY() + babyAsteroid.getYVelocity() * babyAsteroid.getRadius() * 2);

            babyAsteroids[i] = babyAsteroid;
        }

        return babyAsteroids;
    }
    /*
    * Get the velocity for the new asteroids based on the mothers
    * velocity and then rotated to 45 and -45 degrees
    * */
    private double[][] getBabyVelocityVectors(Entity asteroid) {
        double x = asteroid.getXVelocity();
        double y = asteroid.getYVelocity();
        double currentRadians = Math.atan2(y,x);

        double radians = Math.toRadians(45);
        double x1 = x * Math.cos(currentRadians+radians) - y * Math.sin(currentRadians+radians);
        double y1 = x * Math.sin(currentRadians+radians) + y * Math.cos(currentRadians+radians);
        double x2 = x * Math.cos(currentRadians-radians) - y * Math.sin(currentRadians-radians);
        double y2 = x * Math.sin(currentRadians-radians) + y * Math.cos(currentRadians-radians);
        return new double[][] { {x1,y1}, {x2,y2} };
    }
}
