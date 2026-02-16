package dk.sdu.mmmi.cbse;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

public class EmemyPlugin implements IGamePluginService {
    
    @Override
    public void start(GameData gameData, World world) {
        Entity enemy = createEnemy(gameData);
        world.addEntity(enemy);
    }
    
    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : world.getEntities()) {
            if (enemy.getClass() == Enemy.class) {
                world.removeEntity(enemy);
            }
        }
    }

    private Entity createEnemy(GameData gameData){
        Entity enemy = new Enemy();
        enemy.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemy.setX(gameData.getDisplayHeight()/2+100);
        enemy.setY(gameData.getDisplayWidth()/2+100);
        enemy.setRadius(8);
        return enemy;
    }
}
