package blitzcrank;
import robocode.*;
import java.awt.Color;
import robocode.util.*;

public class blitzcrank extends AdvancedRobot {
    private boolean movingForward = true;

    public void run() {
        setColors(Color.red, Color.red, Color.red);

        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);

        while (true) {
            execute();
			
            if (movingForward) {
                setAhead(100);
            } else {
                setBack(100);
            }
            turnRight(90);

            setTurnRadarRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
		
        double gunTurn = getHeadingRadians() + e.getBearingRadians() - getGunHeadingRadians();
        setTurnGunRightRadians(Utils.normalRelativeAngle(gunTurn));

       double firePower = Math.min(3.0, getEnergy());
        fire(firePower);
    }

    public void onHitByBullet(HitByBulletEvent e) {
        setTurnRight(45);
        setAhead(50);
    }

    public void onHitWall(HitWallEvent e) {
        movingForward = !movingForward;
        setTurnRight(90);
        setAhead(100);
    }

    public void onHitRobot(HitRobotEvent e) {
        if (e.getBearing() > -90 && e.getBearing() < 90) {
            setBack(100);
        } else {
            setAhead(100);
        }
    }

    public void onWin(WinEvent e) {
        turnLeft(240);
    }
}