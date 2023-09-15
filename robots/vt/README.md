# Pacote VT - Robô para Robocode

Este repositório contém um robô desenvolvido para o jogo Robocode. O Robocode é uma plataforma de programação de robôs de combate em Java, onde os robôs competem uns contra os outros em uma arena virtual. O objetivo deste README é explicar o funcionamento e o propósito deste robô chamado "Vt."

## Sobre o Robô

### Descrição Geral
O robô "Vt" é um exemplo simples destinado a iniciantes no Robocode. Ele exibe comportamento básico de movimento e combate. Este robô gira continuamente para a direita, avançando constantemente pela arena, enquanto detecta inimigos e toma decisões de disparo com base na distância. Além disso, ele lida com colisões, virando para evitar colidir com outros robôs.

### Principais Funcionalidades
- **Configuração de Cores:** O robô é configurado com cores escuras, detalhes em vermelho e branco usando a função `setColors`.

- **Movimento Constante:** O robô move-se continuamente para a frente com velocidade máxima, mantendo uma rotação constante para a direita.

- **Detecção de Inimigos:** Quando detecta um inimigo usando o evento `onScannedRobot`, o robô exibe o nome e a distância do inimigo no console.

- **Estratégia de Disparo:** Com base na distância do inimigo, o robô ajusta a potência do disparo. Ele dispara com potência 3 se o inimigo estiver próximo (a menos de 135 unidades) ou com potência 1 se o inimigo estiver mais distante.

- **Gestão de Colisões:** Em caso de colisão com outro robô, o robô verifica se a colisão foi causada por si mesmo ou por outro robô. Se a culpa for do próprio robô, ele vira 10 graus para a direita. Se a colisão foi com outro robô, ele vira 180 graus para a direita e move-se para frente para evitar a colisão.

## Como Usar
1. Certifique-se de ter o ambiente de desenvolvimento do Robocode configurado em seu sistema.

2. Clone este repositório para a sua máquina local.

3. Abra o Robocode e adicione o pacote "vt" (o diretório onde você clonou este repositório) à lista de pacotes disponíveis no jogo.

4. Crie ou carregue uma arena de batalha no Robocode e adicione o robô "vt" à arena.

5. Inicie a batalha e observe o comportamento do robô "Vt" em ação.

## Contribuições
Este robô é um exemplo simples e educacional destinado a iniciantes no Robocode. Se você deseja contribuir com melhorias ou criar robôs mais avançados, sinta-se à vontade para fazer um fork deste repositório e realizar suas modificações.
