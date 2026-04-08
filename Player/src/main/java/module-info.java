import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.services.IProcessingService;
import dk.sdu.mmmi.cbse.common.services.IPluginService;
import dk.sdu.mmmi.cbse.playersystem.PlayerProcessor;

module Player {
    requires Common;
    requires CommonBullet;
    requires CommonMovement;
    uses dk.sdu.mmmi.cbse.common.movement.MovementSPI;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IPluginService with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
    provides IProcessingService with PlayerProcessor;
    exports dk.sdu.mmmi.cbse.playersystem;
}
