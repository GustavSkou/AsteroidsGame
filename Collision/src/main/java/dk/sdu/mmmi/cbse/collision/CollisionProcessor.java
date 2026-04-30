package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSplitterSPI;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletOwner;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IPostProcessingService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class CollisionProcessor implements IPostProcessingService {
    public void process(GameData gameData, World world) {
        Entity[] entities = world.getEntitiesAsArray();
        for (int i = 0; i < entities.length - 1; i++) {
            for (int j = i + 1; j < entities.length; j++) {
                Entity currentEntity = entities[i];
                Entity nextEntity = entities[j];
                double distanceBetweenEntities = distanceBetweenPoints(currentEntity.getX(), currentEntity.getY(), nextEntity.getX(), nextEntity.getY());
                if (hasCollided(distanceBetweenEntities, currentEntity, nextEntity)) {
                    registerPlayerBulletHitEntity(gameData, currentEntity, nextEntity);
                    splitAsteroid(world, currentEntity, nextEntity);
                    world.removeEntity(currentEntity);
                    world.removeEntity(nextEntity);
                }
            }
        }
    }

    private void removeEntities(Entity entity1, Entity entity2) { }

    private void splitAsteroid(World world, Entity currentEntity, Entity nextEntity) {
        if (currentEntity.getClass() == Asteroid.class && nextEntity.getClass() == Asteroid.class) {
            return;
        }

        if (currentEntity.getClass() == Asteroid.class) {
            getAsteroidSplitterSPIs().stream().findFirst().ifPresent(
                splitterSPI -> splitterSPI.split( (Asteroid) currentEntity, world)
            );
            return;
        }
        if (nextEntity.getClass() == Asteroid.class) {
            getAsteroidSplitterSPIs().stream().findFirst().ifPresent(
                splitterSPI -> splitterSPI.split( (Asteroid) nextEntity, world)
            );
            return;
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

    private void registerPlayerBulletHitEntity(GameData gameData, Entity entity1, Entity entity2) {
        if (!isBullet(entity1) && !isBullet(entity2)) {
            return;
        }

        Bullet bullet = isBullet(entity1) ? (Bullet) entity1 : (Bullet) entity2;
        Entity hitEntity = isBullet(entity1) ? entity2 : entity1;

        if (bullet.getOwner() != BulletOwner.PLAYER) {
            return;
        }

        addScore( pointsToGive( hitEntity ) );
    }

    private int pointsToGive(Entity hitEntity) {
        if (isAsteroid(hitEntity)) {
            return 1;
        }
        else if (isEnemy(hitEntity)){
            return 5;
        }
        else  {
            return 0;
        }
    }

    private boolean isAsteroid(Entity entity) {
        return entity.getClass() == Asteroid.class;
    }

    private boolean isBullet(Entity entity) {
        return entity.getClass() == Bullet.class;
    }

    private boolean isEnemy(Entity entity) {
        return entity.getClass() == Enemy.class;
    }

    private static Collection<? extends AsteroidSplitterSPI> getAsteroidSplitterSPIs() {
        return ServiceLoader.load(AsteroidSplitterSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private void addScore(long points) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/score?point=" + points))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
            
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}