package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroidSplitter.AsteroidSplitterSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class CollisionProcessor implements IPostEntityProcessingService {
    public void process(GameData gameData, World world) {
        Entity[] entities = world.getEntitiesAsArray();
        for (int i = 0; i < entities.length - 1; i++) {
            for (int j = i + 1; j < entities.length; j++) {
                Entity currentEntity = entities[i];
                Entity nextEntity = entities[j];
                double distanceBetweenEntities = distanceBetweenPoints(currentEntity.getX(), currentEntity.getY(), nextEntity.getX(), nextEntity.getY());
                if (hasCollided(distanceBetweenEntities, currentEntity, nextEntity)) {

                    if (currentEntity.getClass() == Asteroid.class) {
                        getAsteroidSplitterSPIs().stream().findFirst().ifPresent(
                            splitterSPI -> {
                                splitterSPI.split(currentEntity, world);
                            }
                        );
                        System.out.println(getAsteroidSplitterSPIs());
                    }
                    if (currentEntity.getClass() == Asteroid.class) {
                        getAsteroidSplitterSPIs().stream().findFirst().ifPresent(
                                splitterSPI -> {
                                    splitterSPI.split(nextEntity, world);
                                }
                        );
                    }
                    world.removeEntity(currentEntity);
                    world.removeEntity(nextEntity);
                }
            }
        }
    }

    private double distanceBetweenPoints(double x1, double y1, double x2, double y2) {
        double length = x2 - x1;
        double height = y2 - y1;
        return Math.sqrt(Math.pow(length, 2) + Math.pow(height, 2));
    }

    /*
     * Check if the entities are close enough, that their hitbox radius would touch
     * */
    private boolean hasCollided(double distanceBetween, Entity entity1, Entity entity2) {
        return distanceBetween <= entity1.getRadius() + entity2.getRadius();
    }

    private Collection<? extends AsteroidSplitterSPI> getAsteroidSplitterSPIs() {
        return ServiceLoader.load(AsteroidSplitterSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}