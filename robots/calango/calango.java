package calango;

import robocode.*;

public class CalangoBot extends Robot {
    private int power = 1; 
    @Override
    public void run() {
       
        while (true) {
           
            ahead(150);
            
            turnRight(80);
            
            fire(power);
 
            power = (power % 3) + 1;
           
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
       
        fire(power);
    }
}