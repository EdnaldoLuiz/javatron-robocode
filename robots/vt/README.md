Package VT - Robocode Robot
Este repositório contém um robô desenvolvido para o jogo Robocode. O Robocode é uma plataforma de programação de robôs de combate em Java, onde os robôs competem uns contra os outros em uma arena virtual. O objetivo deste README é explicar o funcionamento e o propósito deste robô chamado "Vt".

Sobre o Robô
Descrição Geral
O robô "Vt" é um exemplo simples de um robô para iniciantes no Robocode. Ele possui comportamento básico de movimento e combate. Este robô gira continuamente para a direita, avançando de forma constante pela arena, enquanto detecta inimigos e toma decisões de disparo com base na distância. Além disso, ele lida com colisões, virando para evitar colidir com outros robôs.

Funcionalidades Principais
Configuração de Cores: O robô é configurado com cores escuras com detalhes em vermelho e branco usando a função setColors.

Movimento Constante: O robô move-se para a frente continuamente com velocidade máxima, mantendo uma rotação constante para a direita.

Detecção de Inimigos: Quando detecta um inimigo usando o evento onScannedRobot, o robô exibe o nome e a distância do inimigo no console.

Estratégia de Disparo: Com base na distância do inimigo, o robô ajusta a potência de disparo. Se o inimigo estiver próximo (a menos de 135 unidades), ele dispara com potência 3; caso contrário, dispara com potência 1.

Gestão de Colisões: Quando o robô colide com outro robô, ele verifica se a colisão foi causada por si mesmo ou por outro robô. Se for culpa própria, ele vira para a direita em 10 graus. Se a colisão foi com outro robô, ele vira 180 graus para a direita e move-se para frente para evitar a colisão.

Como Usar
Certifique-se de ter o ambiente de desenvolvimento do Robocode configurado em seu sistema.

Clone este repositório para sua máquina local.

Abra o Robocode e adicione o pacote "vt" (o diretório onde você clonou este repositório) à lista de pacotes disponíveis no jogo.

Crie ou carregue uma arena de batalha no Robocode e adicione o robô "vt" à arena.

Inicie a batalha e observe o comportamento do robô "Vt" em ação.

Contribuições
Este robô é um exemplo simples e educacional destinado a iniciantes no Robocode. Se você deseja contribuir com melhorias ou criar robôs mais avançados, sinta-se à vontade para forkar este repositório e fazer suas modificações.
