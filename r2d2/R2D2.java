package campeonado.Bruno;
import robocode.*;
import robocode.util.Utils;
import java.awt.Color;

public class R2D2 extends AdvancedRobot {
	public void run() {
        // Set robot colors
        setColors(Color.darkGray, Color.black, Color.lightGray, Color.white, Color.green);

        while (true) {
            // Move forward, turn the gun in a full circle, move backward, and turn the gun again
            ahead(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double distanceEnemyRobot = e.getDistance();
        double power;

        // Determine the shooting power based on the distance to the enemy
        if (distanceEnemyRobot < 200) {
            power = 3; // Use high power if the enemy is close
        } else if (distanceEnemyRobot < 400) {
            power = 2; // Use medium power if the enemy is at a medium distance
        } else {
            power = 1; // Use low power if the enemy is far away
        }

        // Perform predictive aiming to target the enemy
        predictiveAiming(e);
        fire(power); // Fire using the calculated power
    }

    public void predictiveAiming(ScannedRobotEvent e) {
        // Predict where the enemy will be in the next shot
        double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
        double enemyX = getX() + e.getDistance() * Math.sin(absoluteBearing);
        double enemyY = getY() + e.getDistance() * Math.cos(absoluteBearing);
        double predictedBearing = Math.atan2(enemyX - getX(), enemyY - getY());

        // Turn the gun to aim at the predicted bearing
        turnGunRightRadians(normalRelativeAngle(predictedBearing - getGunHeadingRadians()));
    }

    public double normalRelativeAngle(double angle) {
        // Ensure that the angle is within the -π to π range
        if (angle > Math.PI) {
            angle -= 2 * Math.PI;
        } else if (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    public void onHitRobot(HitRobotEvent e) {
        // If hit by an enemy robot, move away from it
        if (e.getBearing() > -90 && e.getBearing() <= 90) {
            back(100);
        } else {
            ahead(100);
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {
        // When hit by an enemy bullet, turn perpendicular and move
        turnRight(90 - e.getBearing());
        ahead(50);
    }

    public void onHitWall(HitWallEvent e) {
        // When hitting a wall, turn to the right to avoid it
        turnRight(90);
    }
}
