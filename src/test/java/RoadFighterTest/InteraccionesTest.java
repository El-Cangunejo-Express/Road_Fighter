package RoadFighterTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import RoadFighter.AutoJugador;
import RoadFighter.AutoObstaculo;
import RoadFighter.CamionObstaculo;
import RoadFighter.Escudo;
import RoadFighter.ManchaDeAceite;
import RoadFighter.Pozo;
import RoadFighter.Punto;

public class InteraccionesTest {

	@Test
	public void JugadorChocaJugadorPierdeElControl() {
		AutoJugador pj1 = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoJugador pj2 = new AutoJugador(new Punto(0, 0), "Denis");

		pj1.choqueConObjeto(pj2);

		assertTrue(pj1.getPerderControl());
		assertTrue(pj2.getPerderControl());
	}

	@Test
	public void JugadorChocaBotPierdeElControl() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);

		assertTrue(pj.getPerderControl());
		assertTrue(bot.getPerderControl());
	}

	@Test
	public void JugadorChocaJugadorNoExplota() {
		AutoJugador pj1 = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoJugador pj2 = new AutoJugador(new Punto(0, 0), "Denis");

		pj1.choqueConObjeto(pj2);
		assertFalse(pj1.getExplotar());
		assertFalse(pj2.getExplotar());
	}

	@Test
	public void JugadorChocaBotNoExplota() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);

		assertFalse(pj.getExplotar());
		assertFalse(bot.getExplotar());
	}

	@Test
	public void JugadorChocaCamionYExplotaSoloElJugador() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		CamionObstaculo bot = new CamionObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);
		assertTrue(pj.getExplotar());
		assertFalse(bot.getExplotar());
	}

	@Test
	public void BotChocaCamionYExplotaSoloElBot() {
		AutoObstaculo bot1 = new AutoObstaculo(new Punto(0, 0));
		CamionObstaculo bot2 = new CamionObstaculo(new Punto(0, 0));

		bot1.choqueConObjeto(bot2);
		assertTrue(bot1.getExplotar());
		assertFalse(bot2.getExplotar());
	}

	@Test
	public void JugadorChocaConAceiteYPierderControl() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		pj.choqueConObjeto(aceite);
		assertTrue(pj.getPerderControl());
	}

	@Test
	public void AutoBotChocaConAceiteYPierderControl() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		bot.choqueConObjeto(aceite);
		assertTrue(bot.getPerderControl());
	}

	@Test
	public void CamionChocaConAceiteYNoPierderControl() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		assertFalse(camion.getPerderControl());
		camion.choqueConObjeto(aceite);
		assertFalse(camion.getPerderControl());
	}

	@Test
	public void JugadorChocaConPozoYExplota() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Pozo pozo = new Pozo(new Punto(0, 0));

		pj.choqueConObjeto(pozo);
		assertTrue(pj.getExplotar());
	}

	@Test
	public void BotChocaConPozoYExplota() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Pozo pozo = new Pozo(new Punto(0, 0));

		bot.choqueConObjeto(pozo);
		assertTrue(bot.getExplotar());
	}

	@Test
	public void CamionChocaConPozoYNoExplota() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Pozo pozo = new Pozo(new Punto(0, 0));

		assertFalse(camion.getExplotar());
		camion.choqueConObjeto(pozo);
		assertFalse(camion.getExplotar());
	}

	@Test
	public void JugadorChocaPozoConEscudoNoExplotaYPierdeEscudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Pozo pozo = new Pozo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(pozo);
		assertFalse(pj.getExplotar());
		assertFalse(pj.getEscudo());
	}

	@Test
	public void JugadorChocaPozoConEscudoDosVecesYExplota() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Pozo pozo = new Pozo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(pozo);
		pozo = new Pozo(new Punto(0, 0));
		pj.choqueConObjeto(pozo);
		assertTrue(pj.getExplotar());
	}

	@Test
	public void JugadorSeEquipaConEscudoAlChocarlo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		assertTrue(pj.getEscudo());
	}

	@Test
	public void LosAutoBotsNoSePuedeEquiparConEscudo() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		bot.choqueConObjeto(escudo);
		assertFalse(bot.getExplotar());
	}

	@Test
	public void LosCamionesNoSePuedeEquiparConEscudo() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		camion.choqueConObjeto(escudo);
		assertFalse(camion.getExplotar());
	}

	@Test
	public void AutoBotExplotaCuandoEsChocadoPorJugadorConEscudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(bot);
		assertFalse(pj.getExplotar());
		assertTrue(bot.getExplotar());
	}

	@Test
	public void CamionExplotaCuandoEsChocadoPorJugadorConEscudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(camion);
		assertFalse(pj.getExplotar());
		assertTrue(camion.getExplotar());
	}

	@Test
	public void LosEscudosNoSonAcumulables() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		CamionObstaculo bot2 = new CamionObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));
		Escudo escudo2 = new Escudo(new Punto(10, 10));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(escudo2);
		pj.choqueConObjeto(bot);
		assertFalse(pj.getExplotar());

		pj.choqueConObjeto(bot2);
		assertTrue(pj.getExplotar());
	}

//	@Test
//	public void PowerUpVelocidadExtremaIncrementaVelocidadMaxima() {
//		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
//		VelocidadExtrema powerUpVelocidad = new VelocidadExtrema(new Punto(0, 0));
//
//		pj.acelerar();
//		pj.acelerar();
//
//		assertEquals((int) pj.getVelocidadActual(), 2);
//		pj.choqueConObjeto(powerUpVelocidad);
//		assertEquals((int) pj.getVelocidadActual(), 200);
//	}
//
//	@Test
//	public void PowerUpVelocidadExtremaNoPermiteFrenarNiAcelerar() {
//		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
//		VelocidadExtrema velocidad = new VelocidadExtrema(new Punto(0, 0));
//
//		pj.choqueConObjeto(velocidad);
//
//		assertFalse(pj.acelerar());
//		assertFalse(pj.frenar());
//	}
//
//	@Test
//	public void UnJugadorChocaConVelocidadExtremaYPierdeElControlNoElPowerUp() {
//		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
//		VelocidadExtrema velocidad = new VelocidadExtrema(new Punto(0, 0));
//		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
//
//		pj.choqueConObjeto(velocidad);
//
//		pj.choqueConObjeto(bot); // PIERDE EL CONTROL
//		assertEquals((int) pj.getVelocidadActual(), 200);
//	}
//
//	@Test
//	public void UnJugadorExplotaConVelocidadExtremaYLaPierde() {
//		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
//		VelocidadExtrema velocidad = new VelocidadExtrema(new Punto(0, 0));
//		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
//
//		pj.choqueConObjeto(velocidad);
//		pj.choqueConObjeto(camion); // Aca Explota
//
//		assertEquals((int) pj.getVelocidadActual(), 0);
//	}
}
