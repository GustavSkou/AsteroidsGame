package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Random;

public class AsteroidPostProcessor implements IPostEntityProcessingService {
    Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        while (world.getEntities(Asteroid.class).size() < 4) {
            Entity asteroid = new Asteroid();
            asteroid.setX(random.nextInt(gameData.getDisplayWidth()));
            asteroid.setY(1);
            world.addEntity(asteroid);
        }
    }
}
