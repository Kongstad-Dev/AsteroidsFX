package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

    /*
    Void start contains the gamedata and the world data on start of the engine running

    Pre-condition is None of the data can be null

    Post-condition is that the data has been applied to engine
     */

    void start(GameData gameData, World world);


    void stop(GameData gameData, World world);
}
