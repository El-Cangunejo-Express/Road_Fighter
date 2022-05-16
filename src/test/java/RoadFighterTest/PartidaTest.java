package RoadFighterTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import RoadFighter.AutoJugador;
import RoadFighter.Jugador;
import RoadFighter.Partida;
import RoadFighter.Punto;

public class PartidaTest {

	private Partida partida;
	private Jugador pj1;
	private Jugador pj2;
	private Jugador pj3;
	private Jugador pj4;
	private Jugador pj5;
	private Jugador pj6;

	@Before
	public void inicializaciones() {
		partida = new Partida();
		pj1 = new Jugador("Nahuel");
		pj2 = new Jugador("Denis");
		pj3 = new Jugador("Abigail");
		pj4 = new Jugador("David");
		pj5 = new Jugador("Nicolas");
		pj6 = new Jugador("Daniel");
	}

	@Test
	public void AgregarUnJugadorALaPartida() {
		assertTrue(partida.agregarJugador(pj1));
	}

	@Test
	public void NoSeAgregaJugadorSiLaPartidaEstaLlena() {
		assertEquals(partida.getJugadoresMaximos(), 5);

		assertTrue(partida.agregarJugador(pj1));
		assertTrue(partida.agregarJugador(pj2));
		assertTrue(partida.agregarJugador(pj3));
		assertTrue(partida.agregarJugador(pj4));
		assertTrue(partida.agregarJugador(pj5));

		assertFalse(partida.agregarJugador(pj6)); // No debe permitir agregar otro
	}

	@Test
	public void NoPuedoIniciarLaPartidaConMenosDeDosJugadores() {
		partida.agregarJugador(pj1);
		assertFalse(partida.iniciar());
	}

	@Test
	public void PuedoIniciarLaPartidaConDosOMasJugadores() {
		partida.agregarJugador(pj1);
		partida.agregarJugador(pj2);
		assertTrue(partida.iniciar());
	}

	@Test
	public void TodosLosVehiculosDeLosJugadoresInicianEnLaMismaLinea() {
		partida.agregarJugador(pj1);
		partida.agregarJugador(pj2);
		partida.agregarJugador(pj3);
		partida.agregarJugador(pj4);
		partida.agregarJugador(pj5);

		partida.iniciar();

		ArrayList<AutoJugador> autos = partida.getAutosJugadores();

		assertEquals(autos.get(0).getCoordenada(), new Punto(0, 50));
		assertEquals(autos.get(1).getCoordenada(), new Punto(60, 50));
		assertEquals(autos.get(2).getCoordenada(), new Punto(-60, 50));
		assertEquals(autos.get(3).getCoordenada(), new Punto(120, 50));
		assertEquals(autos.get(4).getCoordenada(), new Punto(-120, 50));
	}

	@Test
	public void MostrarPuntajeDeLosJugadoresAcordeCuantoAvanzaron() {
		partida.agregarJugador(pj1);
		partida.agregarJugador(pj2);
		partida.agregarJugador(pj3);
		partida.agregarJugador(pj4);
		partida.agregarJugador(pj5);

		partida.iniciar();

		ArrayList<AutoJugador> autos = partida.getAutosJugadores();

		for (int i = 0; i < 5; i++) {
			autos.get(0).acelerar();
			autos.get(1).acelerar();
			autos.get(2).acelerar();
			autos.get(3).acelerar();
			autos.get(4).acelerar();
		}

		autos.get(0).avanzar();
		autos.get(0).avanzar();
		autos.get(1).avanzar();
		autos.get(1).avanzar();
		autos.get(2).avanzar();
		autos.get(2).avanzar();
		autos.get(3).avanzar();
		autos.get(3).avanzar();
		autos.get(4).avanzar();
		autos.get(4).avanzar();

		for (int i = 0; i < 28; i++) {
			autos.get(0).avanzar(); // AUTO DEL PRIMER JUGADOR AVANZA HASTA EL FINAL DE LA CARRETERA
		}

		partida.actualizar(); // AUTO DEL PRIMER JUGADOR GANA

		assertEquals((int) pj1.getPuntuacion(), 10000); // LAS PUNTUACIONES COMO = 10000 * (avanceEnCarrera /
														// largoCarretera)
		assertEquals((int) pj2.getPuntuacion(), 3000);
		assertEquals((int) pj3.getPuntuacion(), 3000);
		assertEquals((int) pj4.getPuntuacion(), 3000);
		assertEquals((int) pj5.getPuntuacion(), 3000);
	}

	@Test
	public void VerificarGanador() {
		partida.agregarJugador(pj1);
		partida.agregarJugador(pj2);
		partida.iniciar();

		partida.getAutosJugadores().get(0).acelerar();
		partida.getAutosJugadores().get(0).acelerar();
		partida.getAutosJugadores().get(0).acelerar();
		partida.getAutosJugadores().get(0).acelerar();
		partida.getAutosJugadores().get(0).acelerar();

		partida.actualizar();

		for (int i = 0; i < 30; i++) {
			partida.getAutosJugadores().get(0).avanzar();
		}

		partida.actualizar();
		// Nahuel gana
		assertEquals(partida.getGanador().getNombre(), "Nahuel");
	}
}
