# R2D2 🤖

## Entendendo as funções

```Java
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

```Java
    public void predictiveAiming(ScannedRobotEvent e) {
        double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
        double enemyX = getX() + e.getDistance() * Math.sin(absoluteBearing);
        double enemyY = getY() + e.getDistance() * Math.cos(absoluteBearing);
        double predictedBearing = Math.atan2(enemyX - getX(), enemyY - getY());

        turnGunRightRadians(normalRelativeAngle(predictedBearing - getGunHeadingRadians()));
    }
```

- Esta função calcula a mira para acertar o inimigo em movimento.

```Java
    public double normalRelativeAngle(double angle) {
        if (angle > Math.PI) {
            angle -= 2 * Math.PI;
        } else if (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        return angle;
    }
```

- Esta função ajusta o ângulo para mantê-lo dentro de limites compreensíveis.

```Java
    public void onHitByBullet(HitByBulletEvent e) {
        turnRight(90 - e.getBearing());
        ahead(50);
    }
```

- Esta função é ativada quando o robô é atingido por uma bala.

- O robô desvia e avança após ser atingido.

```Java
    public void onHitWall(HitWallEvent e) {
        turnRight(90);
    }
```

- Esta função é ativada quando o robô atinge uma parede.

- O robô gira para evitar colisão com a parede.
