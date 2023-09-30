# Lata 🤖

## Entendendo as Funções

### Método `run()`

```java
/**
 * Configura o robô e inicia seu comportamento principal.
 */
public void run() {
    configurarRobo();
    moverParaFrente();
    procurarInimigo();

    while (true) {
        evitarParede();
        zigzag();
        manterRadarProcurando();
        executarComandos();
    }
}
```

- **`configurarRobo()`**: Configura as propriedades iniciais do robô, como cores e ajustes de radar e arma.

- **`moverParaFrente()`**: Move o robô para frente em uma distância considerável.

- **`procurarInimigo()`**: Inicia a busca por inimigos girando o radar.

- **`evitarParede()`**: Avalia se o robô está perto da parede e ajusta seu comportamento para evitar colisões.

- **`zigzag()`**: Implementa um padrão de movimento zigzag, mudando de direção em intervalos específicos.

- **`manterRadarProcurando()`**: Garante que o radar continue procurando inimigos.

- **`executarComandos()`**: Executa os comandos do robô.

### Método `onScannedRobot(ScannedRobotEvent roboEscaneado)`

```java
/**
 * Chamado quando um inimigo é escaneado.
 * @param roboEscaneado O evento contendo informações sobre o inimigo escaneado.
 */
public void onScannedRobot(ScannedRobotEvent roboEscaneado) {
    calcularDanoTiro(roboEscaneado);
    calcularEstrategia(roboEscaneado);
    calcularEvitacao(roboEscaneado);
    manterRadarFocado(roboEscaneado);
    dispararSePossivel();
    verificarInimigoDetectado();
    atualizarEnergiaInimigo(roboEscaneado);
}
```

- **`calcularDanoTiro(ScannedRobotEvent roboEscaneado)`**: Calcula a potência de tiro com base na distância do inimigo.

- **`calcularEstrategia(ScannedRobotEvent e)`**: Calcula a estratégia de mira, prevendo o movimento do inimigo.

- **`calcularEvitacao(ScannedRobotEvent e)`**: Calcula a evitação para evitar colisões com o inimigo.

- **`manterRadarFocado(ScannedRobotEvent e)`**: Mantém o radar focado no inimigo.

- **`dispararSePossivel()`**: Dispara a arma se estiver pronta e houver energia suficiente.

- **`verificarInimigoDetectado()`**: Verifica se o radar concluiu sua varredura e, se necessário, inicia uma nova.

- **`atualizarEnergiaInimigo(ScannedRobotEvent e)`**: Atualiza a energia do inimigo para fins de cálculos.

### Método `onHitWall(HitWallEvent e)`

```java
/**
 * Chamado quando o robô atinge a parede.
 * @param e O evento contendo informações sobre a colisão com a parede.
 */
public void onHitWall(HitWallEvent e) {
    mudarDirecao();
}
```

- **`mudarDirecao()`**: Inverte a direção do robô para evitar a parede.

### Método `pertoDoRobo(HitRobotEvent e)`

```java
/**
 * Chamado quando o robô está perto de outro robô.
 * @param e O evento contendo informações sobre a colisão com outro robô.
 */
public void pertoDoRobo(HitRobotEvent e) {
    if (e.isMyFault()) {
        mudarDirecao();
    }
}
```

### Método `calcularDanoTiro(ScannedRobotEvent roboEscaneado)`

```java

/**
 * Calcula a potência do tiro com base na distância do inimigo.
 * @param roboEscaneado O evento contendo informações sobre o inimigo escaneado.
 */
private void calcularDanoTiro(ScannedRobotEvent roboEscaneado) {
    double distancia = roboEscaneado.getDistance();
    
    if(distancia > 350) {
        dmgTiro = 0.5;
    } else if (distancia > 300) {
        dmgTiro = 1;
    } else if (distancia > 250) {
        dmgTiro = 1.5;
    } else if (distancia > 150) {
        dmgTiro = 2;
    } else if (distancia > 100) {
        dmgTiro = 2.5;
    } else {
        dmgTiro = 3;
    }
}
```

### Método `calcularEstrategia(ScannedRobotEvent e)`

```java

/**
 * Calcula a estratégia de mira, prevendo o movimento do inimigo.
 * @param e O evento contendo informações sobre o inimigo escaneado.
 */
private void calcularEstrategia(ScannedRobotEvent e) {
    double velocidadeDoTiro = 20 - 3 * dmgTiro;
    double areaInimigo = getHeadingRadians() + e.getBearingRadians();
    double velocidadeInimigo = e.getVelocity() * Math.sin(e.getHeadingRadians() - areaInimigo);
    
    alvoRelativoY = Math.cos(normalRelativeAngle(e.getBearingRadians() + getHeadingRadians())) * e.getDistance();
    alvoRelativoX = Math.sin(normalRelativeAngle(e.getBearingRadians() + getHeadingRadians())) * e.getDistance();

    alvoPreditoRelativoY = alvoRelativoY + Math.cos(e.getHeadingRadians()) * e.getVelocity() * distanciaAlvoPredita
            / Rules.getBulletSpeed(getPotenciaDoTiroPara(e));
    alvoPreditoRelativoX = alvoRelativoX + Math.sin(e.getHeadingRadians()) * e.getVelocity() * distanciaAlvoPredita
            / Rules.getBulletSpeed(getPotenciaDoTiroPara(e));

    distanciaAlvoPredita = Math.sqrt(alvoPreditoRelativoX * alvoPreditoRelativoX
            + alvoPreditoRelativoY * alvoPreditoRelativoY);

    setTurnGunRightRadians(
            normalRelativeAngle((areaInimigo - getGunHeadingRadians()) + (velocidadeInimigo / velocidadeDoTiro)));
}
```

Este método calcula a estratégia de mira, prevendo o movimento do inimigo. Ele utiliza a posiçã

- **`mudarDirecao()`**: Inverte a direção do robô se a colisão for causada pelo próprio robô.

### Método `mudarDirecao()`

```java
/**
 * Inverte a direção do robô.
 */
private void mudarDirecao() {
    if (mover == true) {
        setBack(100000);
        mover = false;
    } else {
        setAhead(100000);
        mover = true;
    }
}
```

- Inverte a direção do robô, alternando entre se movimentar para frente e para trás.

---
