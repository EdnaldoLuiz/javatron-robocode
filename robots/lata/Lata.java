package luiz;

import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import static robocode.util.Utils.normalRelativeAngle;
import java.awt.*;

/**
 * Lata - a Robot by Ednaldo Luiz (01552573)
 */
public class Lata extends AdvancedRobot {
	
	private boolean mover;
	private boolean pertoDaParede; 
	private int frequenciaMudanca;
	private double statsEnergiaInimigo; 
	private double energiaInimigo = 100; 
	private double dmgTiro; 	
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
	
	private void configurarRobo() {
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setColors(Color.blue, Color.gray, Color.white, Color.cyan, Color.cyan);
	}
	
	private void moverParaFrente() {
		setAhead(100000);
		mover = true;
	}
	
	private void procurarInimigo() {
		setTurnRadarRight(360);
	}
	
	private void evitarParede() {
		if (getX() <= 60 || getY() <= 60 || getBattleFieldWidth() - getX() <= 60 || getBattleFieldHeight() - getY() <= 60) {
			pertoDaParede = true;
		} else {
			pertoDaParede = false;
		}
	
		if (getX() > 60 && getY() > 60 && getBattleFieldWidth() - getX() > 60 && getBattleFieldHeight() - getY() > 60 && pertoDaParede == true) {
			pertoDaParede = false;
		}
	
		if (getX() <= 60 || getY() <= 60 || getBattleFieldWidth() - getX() <= 60 || getBattleFieldHeight() - getY() <= 60) {
			if (pertoDaParede == false) {
				mudarDirecao();
				pertoDaParede = true;
			}
		}
	}
	
	private void zigzag() {
		if (mover == true) {
			frequenciaMudanca = 40;
		} else {
			frequenciaMudanca = 30;
		}
	
		if (getTime() % frequenciaMudanca == 0) {
			mudarDirecao();
		}
	}
	
	private void manterRadarProcurando() {
		if (getRadarTurnRemaining() == 0.0) {
			setTurnRadarRight(360);
		}
	}
	
	private void executarComandos() {
		execute();
	}
	
	public void onScannedRobot(ScannedRobotEvent roboEscaneado) {
		calcularDanoTiro(roboEscaneado);
		calcularEstrategia(roboEscaneado);
		calcularEvitacao(roboEscaneado);
		manterRadarFocado(roboEscaneado);
		dispararSePossivel();
		verificarInimigoDetectado();
		atualizarEnergiaInimigo(roboEscaneado);
	}
	
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
	
	private void calcularEstrategia(ScannedRobotEvent e) {
		double velocidadeDoTiro = 20 - 3 * dmgTiro;
		double areaInimigo = getHeadingRadians() + e.getBearingRadians();
		double velocidadeInimigo = e.getVelocity() * Math.sin(e.getHeadingRadians() - areaInimigo);
		setTurnGunRightRadians(normalRelativeAngle((areaInimigo - getGunHeadingRadians()) + (velocidadeInimigo / velocidadeDoTiro)));
	}
	
	private void calcularEvitacao(ScannedRobotEvent e) {
		if (mover == true) {
			setTurnRight(normalRelativeAngleDegrees(e.getBearing() + 80));
		} else {
			setTurnRight(normalRelativeAngleDegrees(e.getBearing() + 100));
		}
	
		if (e.getDistance() < 100) {
			if (e.getBearing() > -90 && e.getBearing() < 90) {
				setBack(120);
			} else {
				setAhead(120);
			}
		}
	
		statsEnergiaInimigo = energiaInimigo - e.getEnergy();
		if (statsEnergiaInimigo > 0 && statsEnergiaInimigo <= 3) {
			mudarDirecao();
		}
	}
	
	private void manterRadarFocado(ScannedRobotEvent e) {
		double anguloInimigo = getHeading() + e.getBearing();
		double anguloRadar = normalRelativeAngleDegrees(anguloInimigo - getRadarHeading());
		setTurnRadarRight(anguloRadar);
	}
	
	private void dispararSePossivel() {
		if (getGunHeat() == 0 && (getEnergy() - dmgTiro) >= 0.1) {
			fire(dmgTiro);
		}
	}
	
	private void verificarInimigoDetectado() {
		if (getRadarTurnRemaining() == 0) {
			scan();
		}
	}
	
	private void atualizarEnergiaInimigo(ScannedRobotEvent e) {
		energiaInimigo = e.getEnergy();
	}
	
	public void onHitWall(HitWallEvent e) {
		mudarDirecao();
	}	
	public void pertoDoRobo(HitRobotEvent e) {
		if (e.isMyFault()) {
			mudarDirecao();
		}
	}
	
	public void mudarDirecao() {
		if (mover == true) {
			setBack(100000);
			mover = false;
		} else {
			setAhead(100000);
			mover = true;
		}
	}
	