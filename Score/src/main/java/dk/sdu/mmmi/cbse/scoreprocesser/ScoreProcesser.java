package dk.sdu.mmmi.cbse.scoreprocesser;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.List;


public class ScoreProcesser implements IPostEntityProcessingService
{
    private static final int ASTEROID_POINTS = 1;
    private static final int ENEMY_POINTS = 5;

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> playerHits = gameData.consumePendingPlayerEntityHits();
        if (playerHits.isEmpty()) {
            return;
        }

        int pointsToAdd = 0;
        for (Entity hitEntity : playerHits) {
            if (hitEntity.getClass() == Asteroid.class) {
                pointsToAdd += ASTEROID_POINTS;
            } else if (hitEntity.getClass() == Enemy.class) {
                pointsToAdd += ENEMY_POINTS;
            }
        }

        if (pointsToAdd > 0) {
            gameData.setScore(gameData.getScore() + pointsToAdd);
        }
    }
}