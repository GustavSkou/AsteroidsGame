package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IPluginService {

    void start(GameData gameData, World world);

    void cleanUp(GameData gameData, World world);
}
