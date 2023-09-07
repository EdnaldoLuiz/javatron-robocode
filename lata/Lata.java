package campeonato.EdnaldoLuiz;
import robocode.*;
import java.awt.Color;
/**
 * Lata - a class by (Ednaldo Luiz)
 */
public class Lata extends AdvancedRobot {
  int gunDirection = 1;

  public void run() {
    // Estilos
    setBodyColor(Color.black);
    setRadarColor(Color.green);
    setGunColor(Color.black);
    setBulletColor(Color.orange);

    // Gira procurando por inimigos
    while (true) {
      turnGunRight(360);
    }
  }

  public void onScannedRobot(ScannedRobotEvent e) {
    setTurnRight(e.getBearing());
    setFire(3);
    setAhead(100);
    gunDirection = -gunDirection;
    setTurnGunRight(360 * gunDirection);
    execute();
  }
}
