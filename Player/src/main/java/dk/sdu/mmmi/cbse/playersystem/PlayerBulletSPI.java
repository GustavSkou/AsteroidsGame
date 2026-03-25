package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletOwner;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class PlayerBulletSPI implements BulletSPI {
    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));

        // add the shoots velocity to the bullets "normal" speed
        bullet.setXVelocity(shooter.getXVelocity() + changeX * 30);
        bullet.setYVelocity(shooter.getYVelocity() + changeY * 30);

        bullet.setX(shooter.getX() + changeX * shooter.getRadius() + 1);
        bullet.setY(shooter.getY() + changeY * shooter.getRadius() + 1);

        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1);
        ((Bullet) bullet).setOwner(BulletOwner.PLAYER);
        System.out.println("playerBulletSPI.createBullet");
        return bullet;
    }
}