# Lata 🤖

## Entendendo as funções

```Java
public void run() {

    setColors(Color.blue, Color.gray, Color.white,Color.cyan, Color.cyan);

    moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());

    peek = false;

    turnLeft(getHeading() % 90);
    ahead(moveAmount);

    peek = true;
    turnGunRight(90);
    turnRight(90);

    while (true) {
        peek = true;
        ahead(moveAmount);
        peek = false;
        turnRight(90);
    }
}
```

- Nesta função, o robô configura suas cores, calcula a quantidade máxima de movimento com base no tamanho do campo de batalha e começa a se mover. Ele alterna entre as posições de "peek" (olhar) e "não peek" para explorar o campo de batalha.

```Java
public void onHitRobot(HitRobotEvent e) {
    if (e.getBearing() > -90 && e.getBearing() < 90) {
        back(100);
    } else {
        ahead(100);
    }
}
```

- Esta função é ativada quando o robô colide com outro robô.

- O robô verifica a direção do robô inimigo que atingiu e age de acordo para evitar uma colisão mais séria, recuando ou avançando.

```Java
public void onScannedRobot(ScannedRobotEvent e) {

    fire(2);

    if (peek) {
        scan();
    }
}
```

- Esta função é ativada quando o robô detecta um inimigo.

- O robô dispara balas com potência 2 e, se a variável peek estiver habilitada, realiza uma varredura de radar para atualizar as informações sobre inimigos.
