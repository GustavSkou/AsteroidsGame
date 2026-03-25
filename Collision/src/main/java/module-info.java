import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonAsteroid;
    requires CommonBullet;
    requires CommonEnemy;
    uses dk.sdu.mmmi.cbse.common.asteroid.AsteroidSplitterSPI;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.CollisionProcessor;
}
