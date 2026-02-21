package dk.sdu.mmmi.cbse.movement;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.movement.MovementSPI;

public class Movement implements MovementSPI {

    @Override
    public void controlMovement(Entity entity, GameData gameData) {
        if (gameData.getKeys().isDown(GameKeys.LEFT)) {
            entity.setRotation(entity.getRotation() - 3);
        }

        if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
            entity.setRotation(entity.getRotation() + 3);
        }

        if (gameData.getKeys().isDown(GameKeys.UP)) {
            entity.setNormalizedSpeed(entity.getNormalizedSpeed() + 0.01);
        } else {
            entity.setNormalizedSpeed(entity.getNormalizedSpeed() - 0.05);
        }
    }
}