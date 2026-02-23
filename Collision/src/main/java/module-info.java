import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.asteroidSplitter.AsteroidSplitterSPI;

module Collision {
    requires Common;
    requires CommonAsteroidSplitter;
    requires Asteroid;
    uses dk.sdu.mmmi.cbse.common.asteroidSplitter.AsteroidSplitterSPI;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.CollisionProcessor;
}
