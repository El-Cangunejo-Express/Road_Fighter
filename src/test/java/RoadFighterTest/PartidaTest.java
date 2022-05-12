package RoadFighterTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import RoadFighter.AutoJugador;
import RoadFighter.Jugador;
import RoadFighter.Partida;
import RoadFighter.Punto;

public class PartidaTest {

	@Test
	public void Agregar_Un_Jugador_A_La_Partida() {
		Partida partida = new Partida();

		Jugador pj = new Jugador("Nahuel");

		assertTrue(partida.agregarJugador(pj));
	}

	@Test
	public void No_Se_Agrega_Jugador_Si_La_Partida_Esta_Llena() {
		Partida partida = new Partida();

		Jugador pj1 = new Jugador("Nahuel");
		Jugador pj2 = new Jugador("Denis");
		Jugador pj3 = new Jugador("Abigail");
		Jugador pj4 = new Jugador("David");
		Jugador pj5 = new Jugador("Nicolas");
		Jugador pj6 = new Jugador("Daniel");

		// La cantidad maxima de jugadores es 5
		partida.agregarJugador(pj1);
		partida.agregarJugador(pj2);
		partida.agregarJugador(pj3);
		partida.agregarJugador(pj4);
		partida.agregarJugador(pj5);

		assertFalse(partida.agregarJugador(pj6)); // No debe permitir agregar otro
	}

	@Test
	public void No_Puedo_Iniciar_La_Partida_Con_Menos_De_Dos_Jugadores() {
		Partida partida = new Partida();

		Jugador pj = new Jugador("Nahuel");

		partida.agregarJugador(pj);
		assertFalse(partida.iniciar());
	}

	@Test
	public void Todos_Los_Vehiculos_De_Los_Jugadores_Inician_En_La_Misma_Linea() {
		Partida partida = new Partida();

		Jugador pj1 = new Jugador("Nahuel");
		Jugador pj2 = new Jugador("Denis");
		Jugador pj3 = new Jugador("Abigail");
		Jugador pj4 = new Jugador("David");
		Jugador pj5 = new Jugador("Nicolas");

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
	public void Mostrar_Puntaje_De_Los_Jugadores_Acorde_Cuanto_Avanzaron() {
		Partida partida = new Partida();

		Jugador pj1 = new Jugador("Nahuel");
		Jugador pj2 = new Jugador("Denis");
		Jugador pj3 = new Jugador("Abigail");
		Jugador pj4 = new Jugador("David");
		Jugador pj5 = new Jugador("Nicolas");

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

		partida.actualizar();

		for (int i = 0; i < 28; i++) {
			autos.get(0).avanzar(); // AUTO DEL PRIMER JUGADOR AVANZA HASTA EL FINAL DE LA CARRETERA
		}

		partida.actualizar(); // AUTO DEL PRIMER JUGADOR GANA

		// Las puntuaciones se calculan como = 10000 * (avanceEnCarrera /
		// largoCarretera)
		assertEquals((int) pj1.getPuntuacion(), 10000);
		assertEquals((int) pj2.getPuntuacion(), 3000);
		assertEquals((int) pj3.getPuntuacion(), 3000);
		assertEquals((int) pj4.getPuntuacion(), 3000);
		assertEquals((int) pj5.getPuntuacion(), 3000);
	}

	@Test
	public void Verificar_Ganador() {
		Partida partida = new Partida();

		partida.agregarJugador(new Jugador("Nahuel"));
		partida.agregarJugador(new Jugador("Denis"));
		partida.iniciar();

		partida.getAutosJugadores().get(0).acelerar();
		partida.getAutosJugadores().get(0).acelerar();
		partida.getAutosJugadores().get(0).acelerar();
		partida.getAutosJugadores().get(0).acelerar();
		partida.getAutosJugadores().get(0).acelerar();
		partida.getAutosJugadores().get(1).acelerar();
		partida.getAutosJugadores().get(0).avanzar();
		partida.getAutosJugadores().get(1).avanzar();

		partida.actualizar();

		for (int i = 0; i < 28; i++) {
			partida.getAutosJugadores().get(0).avanzar();
		}

		partida.actualizar();
		// Nahuel gana
		assertEquals(partida.asignarPuntuaciones().getNombre(), partida.getAutosJugadores().get(0).getNombre());
	}
}
