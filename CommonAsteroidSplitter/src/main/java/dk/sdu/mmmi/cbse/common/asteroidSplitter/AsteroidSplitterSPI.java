package dk.sdu.mmmi.cbse.common.asteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

public interface AsteroidSplitterSPI {
    void split(Entity asteroid, World world);
}