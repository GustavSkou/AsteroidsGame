import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Enemy {
    requires Common;
    provides IGamePluginService with dk.sdu.mmmi.cbse.enemy.EmemyPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.enemy.EnemyProcessor;
}
