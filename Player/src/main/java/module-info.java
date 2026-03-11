import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.playersystem.PlayerProcessor;

module Player {
    requires Common;
    requires CommonBullet;
    requires CommonMovement;
    uses dk.sdu.mmmi.cbse.common.movement.MovementSPI;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
    provides IEntityProcessingService with PlayerProcessor;
    provides BulletSPI with dk.sdu.mmmi.cbse.playersystem.PlayerBulletSPI;
    exports dk.sdu.mmmi.cbse.playersystem;
}
