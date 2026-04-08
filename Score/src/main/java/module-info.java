
import dk.sdu.mmmi.cbse.scoreprocesser.ScoreProcesser;
import dk.sdu.mmmi.cbse.common.services.IPostProcessingService;

module Score {
    requires Common;
    requires CommonAsteroid;
    requires CommonEnemy;
    provides IPostProcessingService with ScoreProcesser;
}