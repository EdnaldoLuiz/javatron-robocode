package tutu;
import robocode.*;
import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;

/**
 * Tutu	 - a class by Arthur Morais de Moura
 */
public class Tutu extends Robot {
	int contador = 0;
	double girarArma;
	String nomeAlvo;

	public void run() {
		setColors(Color.orange, Color.red, Color.yellow, Color.black, Color.orange);
		nomeAlvo = null;
		setAdjustGunForRobotTurn(true);
		girarArma = 10;
	
		while (true) {
			turnGunRight(girarArma);
			contador++;
			ahead(10);
			
			if (contador>2) {
				girarArma = -10;
			}
			if (contador>5) {
				girarArma = 10;
			}
			if (contador>11) {
				nomeAlvo = null;
			}
		}
	}
			
	public void onScannedRobot(ScannedRobotEvent e) {
		double distancia = e.getDistance();
	
		if (nomeAlvo != null && !e.getName().equals(nomeAlvo)) {
			return;
		}		
		if (nomeAlvo == null) {
			nomeAlvo = e.getName();
		}

		if (distancia > 150) {
			girarArma = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
			ahead(distancia - 140);
			return;
		}
		
		girarArma = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		fire(3);
		
		if (distancia < 100) {
			if (e.getBearing() > -90 && e.getBearing() <= 90) {
				back(40);
			} else {
				ahead(40);
			}
		}
		scan();		

	}
		
	public void onHitRobot(HitRobotEvent e) {
		nomeAlvo = e.getName();
		
		girarArma = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(girarArma);
		fire(3);
		back(50);
	}
	
		public void onHitWall(HitWallEvent e) {
			back(50);
			turnRight(90);
			ahead(35);
		}
	
		public void onHitByBullet(HitByBulletEvent e) {
			turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));
		}
}