package dk.sdu.mmmi.cbse.scoreprocesser;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;


/**
 *
 * Should be a SPI
 *
 * */
public class ScoreProcesser implements IPostEntityProcessingService
{
    @Override
    public void process(GameData gameData, World world) {
        gameData.
    }
}