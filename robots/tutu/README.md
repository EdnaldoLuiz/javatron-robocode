# Tutu

## Entendendo as funções

```Java
    int contador = 0;
	double girarArma;
	String nomeAlvo;
```

- Antes dos métodos, são declaradas três variáveis:
    - **contador**, que irá participar da lógica do *turn* do robô;
    - **girarArma**, que irá ser passado como parâmetro de graus em que o robô irá mover sua arma para a direita;
    - **nomeAlvo**, que vai receber o nome do alvo do robô.

```Java
	public void run() {
		setColors(Color.orange, Color.red, Color.yellow, Color.black, Color.orange);
		nomeAlvo = null;
		setAdjustGunForRobotTurn(true);
		girarArma = 10;
	
		while (true) {
			turnGunRight(girarArma);
			contador++;
			ahead(10);
			
			if (contador>2) {
				girarArma = -10;
			}
			if (contador>5) {
				girarArma = 10;
			}
			if (contador>11) {
				nomeAlvo = null;
			}
		}
	}
```

- Na função run(), são feitas:
    - A distribuição de cores do robô;
    - Definição de nomeAlvo como nulo;
    - girarArma = 10, definindo que começará com 10 graus;
    - Um loop infinito, onde enquanto o robô estiver vivo, uma lógica será aplicada. Para entender melhor a lógica dentro do while, ela possui:
        - turnGunRight(girarArma), passa como parâmetro o girarArma na quantidade de grau que está guardada na variável girarArma;
        - Incrementação do contador, com o contador++;
        - ahead(10), que diz que o robô vai sempre andar 10px para frente;
        - Lógica condicional onde, dependendo de quanto esteja o contador, ele irá realizar alguma atividade determinada.

```Java
public void onScannedRobot(ScannedRobotEvent e) {
		double distancia = e.getDistance();
	
		if (nomeAlvo != null && !e.getName().equals(nomeAlvo)) {
			return;
		}		
		if (nomeAlvo == null) {
			nomeAlvo = e.getName();
		}

		if (distancia > 150) {
			girarArma = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
			ahead(distancia - 140);
			return;
		}
		
		girarArma = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		fire(3);
		
		if (distancia < 100) {
			if (e.getBearing() > -90 && e.getBearing() <= 90) {
				back(40);
			} else {
				ahead(40);
			}
		}
		scan();		
	}
```
- Na função onScannedRobot() são feitas:
    - Declaração da variável distancia, que recebe getDistance(), função que retorna a distância do robô que foi escaneado;
    - Lógica condicional onde caso o nome do alvo **não** seja nulo, e o nome do robô escaneado seja diferente do nome do robô armazenado na variável nomeAlvo, ele irá retornar nada, pois queremos continuar com o mesmo alvo;
    - Lógica condicional onde caso o nome do alvo seja nulo, o nome do alvo escaneado seja atribuída à variável nomeAlvo;
    - Lógica condicional onde caso a distância do alvo seja maior que 150px, será atribuído ao girarArma um ângulo em graus específico, e determina que o robô deve seguir em frente, usando a distancia entre o alvo - 140px como parâmetro;
    - Faz um calculo do ângulo relativo entre a direção do alvo inimigo e a direção em que o radar do seu robô está apontando;
    - Faz uma lógica para se o alvo estiver a menos de 100px de distância e estiver dentro do campo de visão frontal, o robô recuará 40px. Caso contrário, ele avança 40px. 
    - Chama o método scan(), para detectar inimigos.
    

	```Java
    	public void onHitRobot(HitRobotEvent e) {
		nomeAlvo = e.getName();
		
		girarArma = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(girarArma);
		fire(3);
		back(50);
	}
    ```
- A função onHitRobot() é acionada quando colidimos com um robô inimigo, e tem a seguinte lógica:
    - Iremos pegar o nome do alvo que colidimos, e atribuir ao nomeAlvo;
    - Faz um calculo do ângulo relativo entre a direção do alvo inimigo e a direção em que o radar do seu robô está apontando;
    - Atira com força máxima;
    - Volta 50px.

```Java
	
		public void onHitWall(HitWallEvent e) {
			back(50);
			turnRight(90);
			ahead(35);
		}
```
- A função onHitWall() é acionada quando o robô atinge a parede, e tem a seguinte lógica:
    - O robô volta 50px;
    - Vira para a direita em 90 graus;
    - Anda para frente 35px.
```Java
		public void onHitByBullet(HitByBulletEvent e) {
			turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));
		}
```
- A função onHitByBullet() é acionada quando o robô é atingido por disparos, e possui apenas uma funcionalidade:
    - Ao ser atingido, faz com que o robô gire para uma direção específica com base na diferença entre a direção atual do robô e a direção atual do robô inimigo.