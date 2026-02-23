package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.movement.MovementSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class PlayerProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {

            getMovementSPIs().stream().findFirst().ifPresent(
                movementSPI -> {
                    movementSPI.controlMovement(player, gameData);
                }
            );

            if(gameData.getKeys().isDown(GameKeys.SPACE)) {
                getBulletSPIs().stream().findFirst().ifPresent(
                    bulletSPI -> {
                        world.addEntity(bulletSPI.createBullet(player, gameData));
                    }
                );
            }

            double changeX = Math.cos(Math.toRadians(player.getRotation()));
            double changeY = Math.sin(Math.toRadians(player.getRotation()));

            double speedFactor = Math.pow(player.getNormalizedSpeed(), 2);
            player.setXVelocity(speedFactor * changeX * 5);
            player.setYVelocity(speedFactor * changeY * 5);
            player.setX(player.getX() + player.getXVelocity());
            player.setY(player.getY() + player.getYVelocity());

            if (player.getX() < 0) {
                player.setX(gameData.getDisplayWidth());
            } else if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(1);
            }

            if (player.getY() < 0) {
                player.setY(gameData.getDisplayHeight());
            } else if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(1);
            }                        
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends MovementSPI> getMovementSPIs() {
        return ServiceLoader.load(MovementSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
