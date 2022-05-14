package RoadFighterTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import RoadFighter.AceleradorExtremo;
import RoadFighter.AutoJugador;
import RoadFighter.AutoObstaculo;
import RoadFighter.CamionObstaculo;
import RoadFighter.Escudo;
import RoadFighter.ManchaDeAceite;
import RoadFighter.Miniaturizador;
import RoadFighter.Pozo;
import RoadFighter.Punto;

public class InteraccionesTest {

	@Test
	public void JugadorChocaJugadorPierdeElControl() {
		AutoJugador pj1 = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoJugador pj2 = new AutoJugador(new Punto(0, 0), "Denis");

		pj1.choqueConObjeto(pj2);

		assertTrue(pj1.getPerdiControl());
		assertTrue(pj2.getPerdiControl());
	}

	@Test
	public void JugadorChocaBotPierdeElControl() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);

		assertTrue(pj.getPerdiControl());
		assertTrue(bot.getPerdiControl());
	}

	@Test
	public void JugadorChocaJugadorNoExplota() {
		AutoJugador pj1 = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoJugador pj2 = new AutoJugador(new Punto(0, 0), "Denis");

		pj1.choqueConObjeto(pj2);
		assertFalse(pj1.getExplote());
		assertFalse(pj2.getExplote());
	}

	@Test
	public void JugadorChocaBotNoExplota() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);

		assertFalse(pj.getExplote());
		assertFalse(bot.getExplote());
	}

	@Test
	public void JugadorChocaCamionYExplotaSoloElJugador() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		CamionObstaculo bot = new CamionObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);
		assertTrue(pj.getExplote());
		assertFalse(bot.getExplote());
	}

	@Test
	public void BotChocaCamionYExplotaSoloElBot() {
		AutoObstaculo bot1 = new AutoObstaculo(new Punto(0, 0));
		CamionObstaculo bot2 = new CamionObstaculo(new Punto(0, 0));

		bot1.choqueConObjeto(bot2);
		assertTrue(bot1.getExplote());
		assertFalse(bot2.getExplote());
	}

	@Test
	public void JugadorChocaConAceiteYPierdeControl() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		pj.choqueConObjeto(aceite);
		assertTrue(pj.getPerdiControl());
	}

	@Test
	public void AutoBotChocaConAceiteYPierdeControl() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		bot.choqueConObjeto(aceite);
		assertTrue(bot.getPerdiControl());
	}

	@Test
	public void CamionChocaConAceiteYNoPierdeControl() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		assertFalse(camion.getPerdiControl());
		camion.choqueConObjeto(aceite);
		assertFalse(camion.getPerdiControl());
	}

	@Test
	public void JugadorChocaConPozoYExplota() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Pozo pozo = new Pozo(new Punto(0, 0));

		pj.choqueConObjeto(pozo);
		assertTrue(pj.getExplote());
	}

	@Test
	public void BotChocaConPozoYExplota() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Pozo pozo = new Pozo(new Punto(0, 0));

		bot.choqueConObjeto(pozo);
		assertTrue(bot.getExplote());
	}

	@Test
	public void CamionChocaConPozoYNoExplota() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Pozo pozo = new Pozo(new Punto(0, 0));

		assertFalse(camion.getExplote());
		camion.choqueConObjeto(pozo);
		assertFalse(camion.getExplote());
	}

	@Test
	public void JugadorChocaPozoConEscudoNoExplotaYPierdeEscudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Pozo pozo = new Pozo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(pozo);
		assertFalse(pj.getExplote());
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
		assertTrue(pj.getExplote());
	}

	@Test
	public void JugadorSeEquipaConEscudoAlChocarlo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		assertTrue(pj.getEscudo());
	}

	@Test
	public void LosAutoBotsNoSePuedenEquiparConEscudo() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		bot.choqueConObjeto(escudo);
		assertFalse(bot.getExplote());
	}

	@Test
	public void LosCamionesNoSePuedenEquiparConEscudo() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		camion.choqueConObjeto(escudo);
		assertFalse(camion.getExplote());
	}

	@Test
	public void AutoBotExplotaCuandoEsChocadoPorJugadorConEscudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(bot);
		assertFalse(pj.getExplote());
		assertTrue(bot.getExplote());
	}

	@Test
	public void CamionExplotaCuandoEsChocadoPorJugadorConEscudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(camion);
		assertFalse(pj.getExplote());
		assertTrue(camion.getExplote());
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
		assertFalse(pj.getExplote());

		pj.choqueConObjeto(bot2);
		assertTrue(pj.getExplote());
	}

	@Test
	public void ChocarAceleradorExtremoIncrementaVelocidadActualPorEncimaDeVelocidadMaxima() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AceleradorExtremo acelerador = new AceleradorExtremo(new Punto(0, 0));

		pj.acelerar();
		pj.acelerar();

		pj.choqueConObjeto(acelerador);
		assertEquals((int) pj.getVelocidadActual(), 200);
	}

	@Test
	public void AceleradorExtremoNoPermiteAlAutoJugadorFrenarNiAcelerar() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AceleradorExtremo acelerador = new AceleradorExtremo(new Punto(0, 0));

		pj.choqueConObjeto(acelerador);

		assertFalse(pj.acelerar());
		assertFalse(pj.frenar());
	}

	@Test
	public void UnBotNoChocaConAceleradorExtremoYNoAumentaSuVelocidad() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		AceleradorExtremo acelerador = new AceleradorExtremo(new Punto(0, 0));

		bot.acelerar(); // VELOCIDAD = 1
		bot.choqueConObjeto(acelerador);

		assertEquals((int) bot.getVelocidadActual(), 1); // LA VELOCIDAD NO FUE AFECTADA
	}

	@Test
	public void AutoJugadorExplotaTeniendoAceleradorExtremoYLoPierde() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AceleradorExtremo velocidad = new AceleradorExtremo(new Punto(0, 0));
		CamionObstaculo bot2 = new CamionObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(velocidad);
		pj.choqueConObjeto(bot2); // ACA EXPLOTA

		assertEquals((int) pj.getVelocidadActual(), 0);
		assertTrue(pj.acelerar());
		assertTrue(pj.frenar());
	}

	@Test
	public void AutoJugadorChocaConMiniaturizadorSuAnchoYLargoSeReducenALaMitad() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Miniaturizador miniaturizacion = new Miniaturizador(new Punto(0, 0));

		assertEquals((int) pj.getTamanio(), 30 * 40);
		pj.choqueConObjeto(miniaturizacion);
		assertEquals((int) pj.getTamanio(), 15 * 20);
	}

	@Test
	public void AutoObstaculoChocaConMiniaturizadorNoPasaNada() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Miniaturizador miniaturizacion = new Miniaturizador(new Punto(0, 0));

		assertEquals((int) bot.getTamanio(), 30 * 40);
		bot.choqueConObjeto(miniaturizacion);
		assertEquals((int) bot.getTamanio(), 30 * 40); // EL TAMANIO NO FUE AFECTADO
	}

	@Test
	public void CamionObstaculoChocaConMiniaturizadorNoPasaNada() {
		CamionObstaculo bot = new CamionObstaculo(new Punto(0, 0));
		Miniaturizador miniaturizacion = new Miniaturizador(new Punto(0, 0));

		assertEquals((int) bot.getTamanio(), 80 * 30);
		bot.choqueConObjeto(miniaturizacion);
		assertEquals((int) bot.getTamanio(), 80 * 30); // EL TAMANIO NO FUE AFECTADO
	}

	@Test
	public void JugadorExplotaTeniendoMiniaturizadorVuelveALaNormalidad() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		CamionObstaculo bot2 = new CamionObstaculo(new Punto(0, 0));
		Miniaturizador miniaturizacion = new Miniaturizador(new Punto(0, 0));

		pj.choqueConObjeto(miniaturizacion);// TAMAÑO DISMINUIDO
		pj.choqueConObjeto(bot2); // ACA EXPLOTA
		assertEquals((int) pj.getTamanio(), 30 * 40); // TAMAÑO NORMALIZADO
	}
}
