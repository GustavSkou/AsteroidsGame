package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author Emil
 */
public class Player extends Entity {
    private double speedProportion;

    public Player () {
        speedProportion = 0;
    }

    public void addToSpeedProportion(double speed) {
        if (speedProportion + speed > 100) {
            speedProportion = 100;
            return;
        } 
        else if (speedProportion + speed < 0) {
            speedProportion = 0;
            return;
        }
        
        if (speedProportion < 100 || speedProportion > 0) {
            speedProportion = speedProportion + speed;   
        }  
    }

    public double getSpeedProportion() {
        return speedProportion;
    }
}
