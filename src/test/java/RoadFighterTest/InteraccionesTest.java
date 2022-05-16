package RoadFighterTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import RoadFighter.AceleradorExtremo;
import RoadFighter.AutoJugador;
import RoadFighter.AutoObstaculo;
import RoadFighter.CamionObstaculo;
import RoadFighter.Escudo;
import RoadFighter.Jugador;
import RoadFighter.ManchaDeAceite;
import RoadFighter.Miniaturizador;
import RoadFighter.Pozo;
import RoadFighter.Punto;

public class InteraccionesTest {

	private Jugador jugador1;
	private Jugador jugador2;
	private AutoJugador pj1;
	private AutoJugador pj2;
	private AutoObstaculo autoBot;
	private AutoObstaculo autoBot2;
	private CamionObstaculo camionBot;
	private ManchaDeAceite aceite;
	private Pozo pozo;
	private Escudo escudo;
	private Escudo escudo2;
	private AceleradorExtremo acelerador;
	private Miniaturizador miniaturizacion;

	@Before
	public void inicializaciones() {
		jugador1 = new Jugador("Nahuel");
		jugador2 = new Jugador("Denis");
		pj1 = new AutoJugador(new Punto(0, 0), jugador1);
		pj2 = new AutoJugador(new Punto(0, 0), jugador2);
		autoBot = new AutoObstaculo(new Punto(0, 0));
		autoBot2 = new AutoObstaculo(new Punto(0, 0));
		camionBot = new CamionObstaculo(new Punto(0, 0));
		aceite = new ManchaDeAceite(new Punto(0, 0));
		pozo = new Pozo(new Punto(0, 0));
		escudo = new Escudo(new Punto(0, 0));
		escudo2 = new Escudo(new Punto(0, 0));
		acelerador = new AceleradorExtremo(new Punto(0, 0));
		miniaturizacion = new Miniaturizador(new Punto(0, 0));
	}

	@Test
	public void AutoJugadorChocaAutoJugadorNoExplotanSiPierdenElControl() {
		pj1.choqueConObjeto(pj2);

		assertFalse(pj1.getExplote());
		assertFalse(pj2.getExplote());

		assertTrue(pj1.getPerdiControl());
		assertTrue(pj2.getPerdiControl());
	}

	@Test
	public void AutoJugadorChocaAutoObstaculoNoExplotanSiPierdenElControl() {
		pj1.choqueConObjeto(autoBot);

		assertFalse(pj1.getExplote());
		assertFalse(autoBot.getExplote());

		assertTrue(pj1.getPerdiControl());
		assertTrue(autoBot.getPerdiControl());
	}

	@Test
	public void AutoObstaculoChocaAutoObstaculoNoExplotanSiPierdenElControl() {
		autoBot.choqueConObjeto(autoBot2);

		assertFalse(autoBot.getExplote());
		assertFalse(autoBot2.getExplote());

		assertTrue(autoBot.getPerdiControl());
		assertTrue(autoBot2.getPerdiControl());
	}

	@Test
	public void AutoJugadorChocaCamionObstaculoYExplotaSolo_AutoJugador() {
		pj1.choqueConObjeto(camionBot);

		assertTrue(pj1.getExplote());
		assertFalse(camionBot.getExplote());
	}

	@Test
	public void AutoObstaculoChocaCamionObstaculoYExplotaSoloAutoObstaculo() {
		autoBot.choqueConObjeto(camionBot);

		assertTrue(autoBot.getExplote());
		assertFalse(camionBot.getExplote());
	}

	@Test
	public void JugadorChocaConAceiteYPierdeControl() {
		pj1.choqueConObjeto(aceite);
		assertTrue(pj1.getPerdiControl());
	}

	@Test
	public void AutoObstaculoChocaConAceiteYPierdeControl() {
		autoBot.choqueConObjeto(aceite);
		assertTrue(autoBot.getPerdiControl());
	}

	@Test
	public void CamionObstaculoChocaConAceiteYNoPierdeControl() {
		camionBot.choqueConObjeto(aceite);
		assertFalse(camionBot.getPerdiControl());
	}

	@Test
	public void AutoJugadorChocaConPozoYExplotan() {
		pj1.choqueConObjeto(pozo);
		assertTrue(pj1.getExplote());
		assertTrue(pozo.getExplote());
	}

	@Test
	public void AutoObstaculoChocaConPozoYExplotan() {
		autoBot.choqueConObjeto(pozo);
		assertTrue(autoBot.getExplote());
		assertTrue(pozo.getExplote());
	}

	@Test
	public void CamionObstaculoChocaConPozoYExplotanSoloElPozo() {
		camionBot.choqueConObjeto(pozo);
		assertFalse(camionBot.getExplote());
		assertTrue(pozo.getExplote());
	}

	@Test
	public void AutoJugadorSeEquipaConEscudoAlChocarlo() {
		pj1.choqueConObjeto(escudo);
		assertTrue(pj1.getEscudo());
	}

	@Test
	public void AutoJugadorChocaPozoConEscudoNoExplotaYPierde_Escudo() {
		pj1.choqueConObjeto(escudo);
		pj1.choqueConObjeto(pozo);
		assertFalse(pj1.getExplote());
		assertFalse(pj1.getEscudo());
	}

	@Test
	public void AutoJugadorChocaManchaDeAceiteConEscudoNoPierdeElControlYPierdeEscudo() {
		pj1.choqueConObjeto(escudo);
		pj1.choqueConObjeto(aceite);
		assertFalse(pj1.getPerdiControl());
		assertFalse(pj1.getEscudo());
	}

	@Test
	public void AutoJugadorChocaPozoConEscudoDosVecesYExplota() {
		pj1.choqueConObjeto(escudo);
		pj1.choqueConObjeto(pozo);
		assertFalse(pj1.getExplote());
		assertFalse(pj1.getEscudo());
		pj1.choqueConObjeto(pozo);
		assertTrue(pj1.getExplote());
	}

	@Test
	public void AutoObstaculoNoSePuedenEquiparConEscudo() {
		autoBot.choqueConObjeto(escudo);
		assertFalse(autoBot.getExplote()); // NO EXPLOTA AL CHOCARLO PERO TYAMPOCO ADQUIERE LA INMUNIDAD

		autoBot.choqueConObjeto(pozo);
		assertTrue(autoBot.getExplote());
	}

	@Test
	public void CamionObstaculoNoSePuedenEquiparCon_Escudo() {
		camionBot.choqueConObjeto(escudo);
		assertFalse(camionBot.getExplote()); // NO EXPLOTA AL CHOCARLO PERO TYAMPOCO ADQUIERE LA INMNIDAD
	}

	@Test
	public void AutoObstaculoExplotaCuandoEsChocadoPorAutoJugadorConEscudo() {
		pj1.choqueConObjeto(escudo);
		pj1.choqueConObjeto(autoBot);
		assertFalse(pj1.getExplote());
		assertTrue(autoBot.getExplote());
	}

	@Test
	public void CamionObstaculoExplotaCuandoEsChocadoPorAutoJugadorConEscudo() {
		pj1.choqueConObjeto(escudo);
		pj1.choqueConObjeto(camionBot);
		assertFalse(pj1.getExplote());
		assertTrue(camionBot.getExplote());
	}

	@Test
	public void PozoExplotaCuandoEsChocadoPorAutoJugadorCon_Escudo() {
		pj1.choqueConObjeto(escudo);
		pj1.choqueConObjeto(pozo);
		assertFalse(pj1.getExplote());
		assertTrue(pozo.getExplote());
	}

	@Test
	public void ManchaDeAceiteExplotaCuandoEsChocadoPorAutoJugadorConEscudo() {
		pj1.choqueConObjeto(escudo);
		pj1.choqueConObjeto(aceite);
		assertFalse(pj1.getExplote());
		assertTrue(aceite.getExplote());
	}

	@Test
	public void LosEscudosNoSonAcumulables() {
		pj1.choqueConObjeto(escudo);
		pj1.choqueConObjeto(escudo2);

		pj1.choqueConObjeto(autoBot);
		assertFalse(pj1.getExplote());

		pj1.choqueConObjeto(camionBot);
		assertTrue(pj1.getExplote());
	}

	@Test
	public void AutoJugadorChocaAceleradorExtremoIncrementaVelocidadActualPorEncimaDeSuVelocidadMaxima() {
		pj1.acelerar();
		pj1.acelerar();
		assertEquals((int) pj1.getVelocidadActual(), 2);

		pj1.choqueConObjeto(acelerador);
		assertEquals((int) pj1.getVelocidadActual(), 200);
	}

	@Test
	public void AceleradorExtremoNoPermiteAlAutoJugadorFrenarNiAcelerar() {
		pj1.choqueConObjeto(acelerador);
		assertFalse(pj1.acelerar());
		assertFalse(pj1.frenar());
	}

	@Test
	public void AutoObstaculoChocaConAceleradorExtremoYNoAumentaSuVelocidad() {
		autoBot.acelerar();
		assertEquals((int) autoBot.getVelocidadActual(), 1);

		autoBot.choqueConObjeto(acelerador);
		assertEquals((int) autoBot.getVelocidadActual(), 1); // LA VELOCIDAD NO FUE AFECTADA
	}

	@Test
	public void CamionObstaculoChocaConAceleradorExtremoYNoAumentaSuVelocidad() {
		camionBot.acelerar();
		assertEquals((int) camionBot.getVelocidadActual(), 1);

		camionBot.choqueConObjeto(acelerador);
		assertEquals((int) camionBot.getVelocidadActual(), 1); // LA VELOCIDAD NO FUE AFECTADA
	}

	@Test
	public void AutoJugadorExplotaTeniendoAceleradorExtremoYLoPierde() {
		pj1.choqueConObjeto(acelerador);
		pj1.choqueConObjeto(camionBot); // ACA EXPLOTA

		assertEquals((int) pj1.getVelocidadActual(), 0);
		assertTrue(pj1.acelerar());
		assertTrue(pj1.frenar());
	}

	@Test
	public void AutoJugadorChocaConMiniaturizadorSuAnchoYLargoSeReducenALaMitad() {
		assertEquals((int) pj1.getTamanio(), 30 * 40);

		pj1.choqueConObjeto(miniaturizacion);
		assertEquals((int) pj1.getTamanio(), 15 * 20);
	}

	@Test
	public void AutoObstaculoChocaConMiniaturizadorNoPasaNada() {
		assertEquals((int) autoBot.getTamanio(), 30 * 40);

		autoBot.choqueConObjeto(miniaturizacion);
		assertEquals((int) autoBot.getTamanio(), 30 * 40); // EL TAMANIO NO FUE AFECTADO
	}

	@Test
	public void CamionObstaculoChocaConMiniaturizadorNoPasaNada() {
		assertEquals((int) camionBot.getTamanio(), 80 * 30);

		camionBot.choqueConObjeto(miniaturizacion);
		assertEquals((int) camionBot.getTamanio(), 80 * 30); // EL TAMANIO NO FUE AFECTADO
	}

	@Test
	public void AutoJugadorExplotaTeniendoMiniaturizadorVuelveALaNormalidad() {
		assertEquals((int) pj1.getTamanio(), 30 * 40); // TAMAÑO ORIGINAL

		pj1.choqueConObjeto(miniaturizacion);
		assertEquals((int) pj1.getTamanio(), 15 * 20); // TAMAÑO DISMINUIDO

		pj1.choqueConObjeto(camionBot); // ACA EXPLOTA
		assertEquals((int) pj1.getTamanio(), 30 * 40); // TAMAÑO NORMALIZADO
	}
}
