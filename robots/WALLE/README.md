# Robô de Batalha - Projeto WALLE

- Este é um projeto de robô de batalha desenvolvido para competições no jogo Robocode. O robô WALLE é uma criação que foi projetado para lutar contra outros robôs autônomos em uma arena virtual.

# Funções Principais:

- Quando o robô detecta um inimigo, ele mede a distância e decide a potência do tiro. O robô usa uma função de mira preditiva para acertar o inimigo em movimento e, em seguida, dispara a arma com a potência escolhida.

- `onScannedRobot(ScannedRobotEvent e)`

```java
public void onScannedRobot(ScannedRobotEvent e) {
    double distanceEnemyRobot = e.getDistance();
    double power;

    if (distanceEnemyRobot < 200) {
        power = 3;
    } else if (distanceEnemyRobot < 400) {
        power = 2;
    } else {
        power = 1;
    }

    predictiveAiming(e);
    fire(power);
}
```

   
 - Quando o robô detecta um inimigo, ele mede a distância e decide a potência do tiro.
 - O robô usa uma função de mira preditiva para acertar o inimigo em movimento.
 - Em seguida, dispara a arma com a potência escolhida.

# FunçãopredictiveAiming(ScannedRobotEvent e)
```
public void predictiveAiming(ScannedRobotEvent e) {
    double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
    double enemyX = getX() + e.getDistance() * Math.sin(absoluteBearing);
    double enemyY = getY() + e.getDistance() * Math.cos(absoluteBearing);
    double predictedBearing = Math.atan2(enemyX - getX(), enemyY - getY());

    turnGunRightRadians(normalRelativeAngle(predictedBearing - getGunHeadingRadians()));
}
```

- Esta função calcula a mira para acertar o inimigo em movimento.

# FunçãonormalRelativeAngle(double angle)

```public double normalRelativeAngle(double angle) {
    if (angle > Math.PI) {
        angle -= 2 * Math.PI;
    } else if (angle < -Math.PI) {
        angle += 2 * Math.PI;
    }
    return angle;
}
```

- Esta função ajusta o ângulo para mantê-lo dentro de limites compreensíveis.

# FunçãoonHitByBullet(HitByBulletEvent e)
```
public void onHitByBullet(HitByBulletEvent e) {
    turnRight(90 - e.getBearing());
    ahead(50);
}
```

- Esta função é ativada quando o robô é atingido por uma bala.
- O robô desvia e avança após ser atingido.


# FunçãoonHitWall(HitWallEvent e)
```
public void onHitWall(HitWallEvent e) {
    turnRight(90);
}
```

- Esta função é ativada quando o robô atinge uma parede.
- O robô gira para evitar danos à parede.

## Contribuições

- Contribuições são bem-vindas! Se você deseja melhorar o robô WALLE ou adicionar novos recursos, sinta-se à vontade para abrir um problema ou enviar um pedido de pull request.
