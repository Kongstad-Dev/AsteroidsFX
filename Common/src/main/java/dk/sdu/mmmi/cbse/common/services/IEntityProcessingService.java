package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     *
     *
     *
     * @param gameData
     * @param world
     * @throws
     */


    /*
    void process takes the gameData and world data as arguments for Entities to use looks and world postion?

    pre-condition is none of the data can be null

    post-condition is state(rendering?) and continous placement in the world
     */
    void process(GameData gameData, World world);
}
