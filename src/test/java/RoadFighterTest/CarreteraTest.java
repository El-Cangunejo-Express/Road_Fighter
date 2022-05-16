package RoadFighterTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import RoadFighter.AutoJugador;
import RoadFighter.AutoObstaculo;
import RoadFighter.CamionObstaculo;
import RoadFighter.Carretera;
import RoadFighter.Jugador;
import RoadFighter.ManchaDeAceite;
import RoadFighter.Pozo;
import RoadFighter.Punto;

public class CarreteraTest {

	private Carretera carretera;
	private double ancho;
	private double largo;
	private Jugador jugador;

	@Before
	public void inicializacion() {
		ancho = 300;
		largo = 1000;
		carretera = new Carretera(ancho, largo);
		jugador = new Jugador("Nahuel");
	}

	@Test
	public void CarreteraCalculaSusLimites() {
		double limite = ancho / 2;

		assertTrue(carretera.getLimDer() == limite);
		assertTrue(carretera.getLimIzq() == -limite);
	}

	@Test
	public void NoInsertarUnObjetoFueraDeLosLimites() {
		AutoJugador pj = new AutoJugador(new Punto(150, 0), jugador);
		AutoObstaculo bot = new AutoObstaculo(new Punto(-150, 0));
		CamionObstaculo bot2 = new CamionObstaculo(new Punto(135, 0));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(-135, 0));
		Pozo pozo = new Pozo(new Punto(0, -1));

		assertFalse(carretera.agregarObjeto(pj));
		assertFalse(carretera.agregarObjeto(bot));
		assertFalse(carretera.agregarObjeto(bot2));
		assertFalse(carretera.agregarObjeto(aceite));
		assertFalse(carretera.agregarObjeto(pozo));
	}

	@Test
	public void InsertarUnObjetoDentroDeLosLimites() {
		AutoJugador pj = new AutoJugador(new Punto(0, 30), jugador);
		AutoObstaculo bot = new AutoObstaculo(new Punto(50, 50));
		CamionObstaculo bot2 = new CamionObstaculo(new Punto(100, 100));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(-130, 50));
		Pozo pozo = new Pozo(new Punto(7, 50));

		assertTrue(carretera.agregarObjeto(pj));
		assertTrue(carretera.agregarObjeto(bot));
		assertTrue(carretera.agregarObjeto(bot2));
		assertTrue(carretera.agregarObjeto(aceite));
		assertTrue(carretera.agregarObjeto(pozo));
	}

	@Test
	public void CarreteraDetectaUnVehiculoDentroDeLosLimitesYNoHaceNada() {
		AutoJugador pj = new AutoJugador(new Punto(134, 50), jugador);
		pj.habilitarDesabilitarJugabilidad();

		assertTrue(carretera.agregarObjeto(pj));

		carretera.actualizar(); // LO DETECTA DENTRO DE LOS LIMITES, NO EXPLOTA

		assertFalse(pj.getExplote());
		assertEquals((int) pj.getCoordenada().getX(), 134);
	}

	@Test
	public void CarreteraDetectaQueUnVehiculoSalioDeLosLimites() {
		AutoJugador pj = new AutoJugador(new Punto(134, 50), jugador);
		carretera.agregarObjeto(pj);
		pj.habilitarDesabilitarJugabilidad();

		carretera.actualizar(); // LO DETECTA DENTRO DE LOS LIMITES, NO EXPLOTA
		pj.irADerecha(); // AL IRME UN LUGAR A LA DERECHA EL CUERPO DEL VEHICULO TOCA EL LIMITE DERECHO
		carretera.actualizar(); // LO DETECTA Y HACE EXPLOTAR EL VEHICULO

		assertTrue(pj.getExplote());
	}

	@Test
	public void CarreteraDetectaChoqueConObjeto() {
		AutoJugador pj = new AutoJugador(new Punto(50, 95), jugador);
		Pozo pozo = new Pozo(new Punto(50, 130));

		carretera.agregarObjeto(pj);
		carretera.agregarObjeto(pozo);

		carretera.actualizar(); // LO DETECTA DENTRO DE LOS LIMITES, NO HAY CHOQUES

		pj.habilitarDesabilitarJugabilidad();
		pj.acelerar(); // VEL = 1
		pj.acelerar(); // VEL = 2
		pj.acelerar(); // VEL = 3
		pj.acelerar(); // VEL = 4
		pj.acelerar(); // VEL = 5

		pj.avanzar();
		pj.avanzar(); // ACA YA DEBERIAN COLISIONAR

		carretera.actualizar(); // DETECTA EL CHOQUE, Y LOS HACE EXPLOTAR

		assertTrue(pj.getExplote());
		assertTrue(pozo.getExplote());
	}

	@Test
	public void ExplotarAlgoQueNoEsAutoJugadorEsEliminadoDeLosObjetosDeLaCarretera() {
		AutoJugador pj = new AutoJugador(new Punto(50, 95), jugador);
		Pozo pozo = new Pozo(new Punto(50, 130));

		carretera.agregarObjeto(pj);
		carretera.agregarObjeto(pozo);

		carretera.actualizar(); // LO DETECTA DENTRO DE LOS LIMITES, NO HAY CHOQUES

		pj.habilitarDesabilitarJugabilidad();
		pj.acelerar(); // VEL = 1
		pj.acelerar(); // VEL = 2
		pj.acelerar(); // VEL = 3
		pj.acelerar(); // VEL = 4
		pj.acelerar(); // VEL = 5
		pj.avanzar();
		pj.avanzar(); // ACA YA DEBERIAN COLISIONAR

		assertEquals(carretera.getCantidadDeObjetos(), 2);

		carretera.actualizar(); // DETECTA EL CHOQUE Y EXPLOSION, Y ELIMINA AL POZO SE SU LISTA DE OBJETOS

		assertEquals(carretera.getCantidadDeObjetos(), 1); // PASO DE TENER DOS OPBJETOS A TENER UNO SOLO, ELIMINO EL
															// POZO EXPLOTADO
	}
}
