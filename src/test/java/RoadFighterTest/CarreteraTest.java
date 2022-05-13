package RoadFighterTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import RoadFighter.AutoJugador;
import RoadFighter.AutoObstaculo;
import RoadFighter.CamionObstaculo;
import RoadFighter.Carretera;
import RoadFighter.ManchaDeAceite;
import RoadFighter.Pozo;
import RoadFighter.Punto;

public class CarreteraTest {

	@Test
	public void Carretera_Calcula_Sus_Limites() {
		Carretera carretera = new Carretera(953, 1000);

		assertTrue(carretera.getLimDer() == 476.5);
		assertTrue(carretera.getLimIzq() == -476.5);

		carretera = new Carretera(400, 1000);
		assertTrue(carretera.getLimDer() == 200);
		assertTrue(carretera.getLimIzq() == -200);

		carretera = new Carretera(0, 1000);
		assertTrue(carretera.getLimDer() == 0);
		assertTrue(carretera.getLimIzq() == 0);
	}

	@Test
	public void No_Insertar_Un_Objeto_Fuera_De_Los_Limites() {
		Carretera carretera = new Carretera(300, 1000);// limites -150 y 150
		AutoJugador pj = new AutoJugador(new Punto(150, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(-150, 0));
		CamionObstaculo camion = new CamionObstaculo(new Punto(135, 0));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(-135, 0));
		Pozo pozo = new Pozo(new Punto(0, -1));

		assertFalse(carretera.agregarObjeto(pj));
		assertFalse(carretera.agregarObjeto(bot));
		assertFalse(carretera.agregarObjeto(camion));
		assertFalse(carretera.agregarObjeto(aceite));
		assertFalse(carretera.agregarObjeto(pozo));
	}

	@Test
	public void Insertar_Un_Objeto_Dentro_De_Los_Limites() {
		Carretera carretera = new Carretera(300, 1000);// limites -150 y 150
		AutoJugador pj = new AutoJugador(new Punto(0, 30), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(50, 50));
		CamionObstaculo camion = new CamionObstaculo(new Punto(100, 100));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(-130, 50));
		Pozo pozo = new Pozo(new Punto(7, 50));

		assertTrue(carretera.agregarObjeto(pj));
		assertTrue(carretera.agregarObjeto(bot));
		assertTrue(carretera.agregarObjeto(camion));
		assertTrue(carretera.agregarObjeto(aceite));
		assertTrue(carretera.agregarObjeto(pozo));
	}

	@Test
	public void Carretera_Detecta_Un_Vehiculo_Dentro_De_Los_Limites_Y_No_Hace_Nada() {
		Carretera carretera = new Carretera(300, 1000);
		AutoJugador pj = new AutoJugador(new Punto(134, 50), "Nahuel");

		pj.habilitarDesabilitarJugabilidad();
		carretera.agregarObjeto(pj);

		carretera.actualizar(); // LO DETECTA DENTRO DE LOS LIMITES, NO EXPLOTA

		assertFalse(pj.getExplote());
		assertEquals((int) pj.getCoordenada().getX(), 134);
	}

	@Test
	public void Carretera_Detecta_Que_Un_Vehiculo_Salio_De_Los_Limites() {
		Carretera carretera = new Carretera(300, 1000);
		AutoJugador pj = new AutoJugador(new Punto(134, 50), "Nahuel");

		pj.habilitarDesabilitarJugabilidad();
		carretera.agregarObjeto(pj);

		carretera.actualizar(); // LO DETECTA DENTRO DE LOS LIMITES, NO EXPLOTA
		pj.irADerecha(); // El auto toca el limite derecho
		carretera.actualizar(); // LO DETECTA Y HACE EXPLOTAR EL VEHICULO

		assertTrue(pj.getExplote());
	}

	@Test
	public void Carretera_Detecta_Choque_Con_Objeto() {
		Carretera carretera = new Carretera(300, 1000);
		AutoJugador pj = new AutoJugador(new Punto(50, 95), "Nahuel");
		Pozo pozo = new Pozo(new Punto(50, 130));

		pj.habilitarDesabilitarJugabilidad();
		carretera.agregarObjeto(pj);
		carretera.agregarObjeto(pozo);
		carretera.actualizar(); // LO DETECTA DENTRO DE LOS LIMITES, NO HAY CHOQUES

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
	public void Explotar_Algo_Que_No_Es_Auto_Jugador_Es_Eliminado_De_Los_Objetos_De_La_Carretera() {
		Carretera carretera = new Carretera(300, 1000);
		AutoJugador pj = new AutoJugador(new Punto(50, 95), "Nahuel");
		Pozo pozo = new Pozo(new Punto(50, 130));

		pj.habilitarDesabilitarJugabilidad();
		carretera.agregarObjeto(pj);
		carretera.agregarObjeto(pozo);

		carretera.actualizar(); // LO DETECTA DENTRO DE LOS LIMITES, NO HAY CHOQUES

		pj.acelerar(); // VEL = 1
		pj.acelerar(); // VEL = 2
		pj.acelerar(); // VEL = 3
		pj.acelerar(); // VEL = 4
		pj.acelerar(); // VEL = 5
		pj.avanzar();
		pj.avanzar(); // ACA YA DEBERIAN COLISIONAR

		carretera.actualizar(); // DETECTA EL CHOQUE Y EXPLOSION, Y ELIMINA AL POZO SE SU LISTA DE OBJETOS

		assertEquals(carretera.getCantidadDeObjetos(), 1);
	}
}
