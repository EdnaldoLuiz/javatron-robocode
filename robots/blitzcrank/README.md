# Blitzcrank


# Entendendo as funções do robô

```JAVA
public void run() {
        setColors(Color.red, Color.red, Color.red);
```
- A linha de código acima definirá a cor vermelha para o robô

```JAVA
        setAdjustGunForRobotTurn(true);
```
- Já que foi definida como VERDADEIRA (true), determinará que o canhão do robô irá girar em conjunto.       
        
```JAVA        
        setAdjustRadarForGunTurn(true);
```        
- Configura o radar do robô para ajustar automaticamente sua direção quando o canhão é movido. Isso significa que, quando o canhão do robô se move para mirar em uma direção específica, o radar acompanhará esse movimento automaticamente, auxiliando no rastreamento e acerto de alvos com mais precisão.

```JAVA  
        setAdjustRadarForRobotTurn(true);
```  
- Isso configura o radar do robô para ajustar automaticamente sua direção quando o próprio robô gira.O radar se ajustará automaticamente para acompanhar esse movimento. A razão para fazer isso é manter o radar focado na mesma direção em relação ao campo de batalha, isso foi acrescentado já que se o radar não acompanhar o movimento do robô, ele pode ficar desalinhado e não detectar corretamente os inimigos.        

```JAVA          
        while (true) {
            execute();
			
            if (movingForward) {
                setAhead(100);
            } else {
                setBack(100);
            }
            turnRight(90);
// Essa sequência de comandos acima implementará um movimento orbital, evitando colisões com outros robôs e afins. 
            setTurnRadarRight(360);
    }
}
```
- O comando setTurnRadarLight determinará que o radar se mova independente do movimento, já que nesse loop teremos o movimento orbital.

```JAVA
    public void onScannedRobot(ScannedRobotEvent e) {
        double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
		
        double gunTurn = getHeadingRadians() + e.getBearingRadians() - getGunHeadingRadians();
        setTurnGunRightRadians(Utils.normalRelativeAngle(gunTurn));

       double firePower = Math.min(3.0, getEnergy());
        fire(firePower);
    }

```
- Em resumo, esse trecho de código garante que o radar do robô siga e mantenha o inimigo dentro de seu campo de detecção, permitindo que o robô continue a rastrear o inimigo enquanto ele se move pela arena. Utilizando os ângulos em radianos para obter a orientação do robô inimigo, a do próprio robô em relação ao inimigo e etc, auxiliando na detecção de alvos.

```JAVA
    public void onHitByBullet(HitByBulletEvent e) {
        setTurnRight(45);
        setAhead(50);
    }
```
- A função onHitByBullet() é acionada quando o robô é atingido por disparos, e possui apenas uma funcionalidade:
    - Ao ser atingido, faz com que o robô gire para uma direção específica com base na diferença entre a direção atual do robô e a direção atual do robô inimigo.

```JAVA
    public void onHitWall(HitWallEvent e) {
        movingForward = !movingForward;
        setTurnRight(90);
        setAhead(100);
    }
```
- Esta função é ativada quando o robô atinge uma parede.

- O robô gira para evitar colisão com a parede.

```JAVA
    public void onHitRobot(HitRobotEvent e) {
        if (e.getBearing() > -90 && e.getBearing() < 90) {
            setBack(100);
        } else {
            setAhead(100);
        }
    }
```

- Esta função é ativada quando o robô colide com outro robô.

- O robô verifica a direção do robô inimigo muda sua direção buscando não ter uma grave colisão

```JAVA
    public void onWin(WinEvent e) {
        turnLeft(720);
    }
}
```

- O comando acima nada mais é do que uma pequena comemoração em que o robô vai dar 2 giros para a esquerda [apenas para dar um toque simpático]