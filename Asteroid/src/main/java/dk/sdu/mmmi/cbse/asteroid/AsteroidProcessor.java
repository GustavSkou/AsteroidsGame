package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for(Entity asteroid : world.getEntities(Asteroid.class)){
            asteroid.setX(asteroid.getX() + asteroid.getXVelocity() * asteroid.getNormalizedSpeed() * 2);
            asteroid.setY(asteroid.getY() + asteroid.getYVelocity() * asteroid.getNormalizedSpeed() * 2);

            if (asteroid.getX() < 0) {
                asteroid.setX(gameData.getDisplayWidth());
            } else if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(1);
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(gameData.getDisplayHeight());
            } else if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(1);
            }
        }
    }
}