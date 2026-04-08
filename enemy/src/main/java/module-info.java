import dk.sdu.mmmi.cbse.common.services.IProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPluginService;

module Enemy {
    requires Common;
    requires CommonEnemy;
    requires Player;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IPluginService with dk.sdu.mmmi.cbse.enemy.EnemyPlugin;
    provides IProcessingService with dk.sdu.mmmi.cbse.enemy.EnemyProcessor;
}
