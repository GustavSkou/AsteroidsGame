package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.ServiceLoader;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Random;

public class EnemyProcessor implements IEntityProcessingService { 

    Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));

            enemy.setX(enemy.getX() + changeX * 0.5);
            enemy.setY(enemy.getY() + changeY * 0.5);
            
            for (Entity player : world.getEntities(Player.class)) {
                double a = enemy.getX() - player.getX();
                double b = enemy.getY() - player.getY();

                double radians = Math.atan2 (b, a);
                double degrees = Math.toDegrees(radians) + 180;
                enemy.setRotation(degrees);
                //System.out.println(a + " " + b + " " + radians + " " + degrees);
            }

            int randomInt = random.nextInt(60);
            if (randomInt == 1) {
                getBulletSPIs().stream().findFirst().ifPresent(
                    spi -> {world.addEntity(spi.createBullet(enemy, gameData));}
                );
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}