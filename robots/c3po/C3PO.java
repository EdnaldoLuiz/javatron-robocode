package caio;

import robocode.*;
import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class C3PO extends Robot {
    
    private int contador = 0;
    private double girarArma = 10;
    private String nomeAlvo;

    public void run() {
        setColors(Color.blue, Color.green, Color.magenta, Color.white, Color.pink);
        nomeAlvo = null;

        setAdjustGunForRobotTurn(true);

        while (true) {
            girarArmaPeriodicamente();
            contador++;
            scan();
        }
    }

    private void girarArmaPeriodicamente() {
        if (contador > 2 && contador <= 5) {
            girarArma = -10;
        } else if (contador > 5) {
            girarArma = 10;
        }
        turnGunRight(girarArma);
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double distancia = e.getDistance();
        
        if (nomeAlvo == null || !e.getName().equals(nomeAlvo)) {
            nomeAlvo = e.getName();
        }

        girarArma = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
        turnGunRight(girarArma);

        if (distancia > 150) {
            moverParaAlvo(distancia - 140);
        } else {
            fire(3);
            if (distancia < 100) {
                evitarColisao(e.getBearing());
            }
        }
    }

    private void moverParaAlvo(double distancia) {
        ahead(distancia);
    }

    private void evitarColisao(double bearing) {
        if (bearing > -90 && bearing <= 90) {
            back(40);
        } else {
            ahead(40);
        }
    }

    public void onHitRobot(HitRobotEvent e) {
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

