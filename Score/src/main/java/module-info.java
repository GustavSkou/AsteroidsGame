
import dk.sdu.mmmi.cbse.scoreprocesser.ScoreProcesser;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Score {
    requires Common;
    requires CommonAsteroid;
    requires CommonEnemy;
    provides IPostEntityProcessingService with ScoreProcesser;
}