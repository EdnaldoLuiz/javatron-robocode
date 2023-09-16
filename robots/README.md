# Robô de Batalha - Projeto WALLE

Este é um projeto de robô de batalha desenvolvido para competições no jogo Robocode. O robô WALLE é uma criação que foi projetado para lutar contra outros robôs autônomos em uma arena virtual.

## Funções Principais:

## Quando o robô detecta um inimigo, ele mede a distância e decide a potência do tiro. O robô usa uma função de mira preditiva para acertar o inimigo em movimento e, em seguida, dispara a arma com a potência escolhida.

### `onScannedRobot(ScannedRobotEvent e)`

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
<li>
   
 <ul>Quando o robô detecta um inimigo, ele mede a distância e decide a potência do tiro.</ul>
 <ul>O robô usa uma função de mira preditiva para acertar o inimigo em movimento.</ul>
 <ul>Em seguida, dispara a arma com a potência escolhida.</ul>

</li>

## FunçãopredictiveAiming(ScannedRobotEvent e)

public void predictiveAiming(ScannedRobotEvent e) {
    double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
    double enemyX = getX() + e.getDistance() * Math.sin(absoluteBearing);
    double enemyY = getY() + e.getDistance() * Math.cos(absoluteBearing);
    double predictedBearing = Math.atan2(enemyX - getX(), enemyY - getY());

    turnGunRightRadians(normalRelativeAngle(predictedBearing - getGunHeadingRadians()));
}

<li>
<ul>Esta função calcula a mira para acertar o inimigo em movimento.</ul>
</li>

##FunçãonormalRelativeAngle(double angle)

public double normalRelativeAngle(double angle) {
    if (angle > Math.PI) {
        angle -= 2 * Math.PI;
    } else if (angle < -Math.PI) {
        angle += 2 * Math.PI;
    }
    return angle;
}

<li>
<ul>Esta função ajusta o ângulo para mantê-lo dentro de limites compreensíveis. 
</ul>
</li>

##FunçãoonHitByBullet(HitByBulletEvent e)

public void onHitByBullet(HitByBulletEvent e) {
    turnRight(90 - e.getBearing());
    ahead(50);
}

<li>
<ul>Esta função é ativada quando o robô é atingido por uma bala.</ul>
<ul>O robô desvia e avança após ser atingido.</ul>
</li>

## FunçãoonHitWall(HitWallEvent e)

public void onHitWall(HitWallEvent e) {
    turnRight(90);
}

<li>
<ul>Esta função é ativada quando o robô atinge uma parede.</ul>
<ul>O robô gira para evitar danos à parede.</ul>
</li>

##Contribuições

- Contribuições são bem-vindas! Se você deseja melhorar o robô WALLE ou adicionar novos recursos, sinta-se à vontade para abrir um problema ou enviar um pedido de pull request.