package vt;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import java.awt.Color;

public class Vt extends AdvancedRobot {

    public void run() {
        // Define as cores do robô
        setColors(Color.darkGray, Color.red, Color.red, Color.white, Color.red);

        while (true) {
            // Define a rotação máxima para a direita para girar continuamente
            setTurnRight(10000);
            // Define a velocidade máxima do robô
            setMaxVelocity(5);
            // Move-se para a frente continuamente
            ahead(10000);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        // Obtém o nome do robô inimigo e a distância
        String robotName = e.getName();
        double distance = e.getDistance();
        // Exibe uma mensagem com o nome e a distância do robô inimigo
        System.out.println(robotName + " está a uma distância de " + distance + " unidades.");

        // Ajusta a potência de disparo com base na distância
        if (distance < 135) {
            fire(3); // Dispara com potência 3 se o inimigo estiver próximo
        } else {
            fire(1); // Dispara com potência 1 se o inimigo estiver distante
        }
    }

    public void onHitRobot(HitRobotEvent e) {
        // Verifica se a colisão foi causada pelo próprio robô
        if (e.isMyFault()) {
            turnRight(10); // Vira para a direita se for culpa do próprio robô
        } else {
            turnRight(180); // Vira 180 graus se a colisão foi com outro robô
        }
        ahead(50); // Move-se para frente após uma colisão
    }
}
