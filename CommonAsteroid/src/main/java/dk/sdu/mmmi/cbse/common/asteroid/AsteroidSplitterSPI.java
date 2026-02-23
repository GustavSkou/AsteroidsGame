package dk.sdu.mmmi.cbse.common.asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

public interface AsteroidSplitterSPI {
    void split(Entity asteroid, World world);
}