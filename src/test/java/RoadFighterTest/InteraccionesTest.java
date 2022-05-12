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
	public void Jugador_Choca_Jugador_Pierde_El_Control() {
		AutoJugador pj1 = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoJugador pj2 = new AutoJugador(new Punto(0, 0), "Denis");

		pj1.choqueConObjeto(pj2);

		assertTrue(pj1.getPerderControl());
		assertTrue(pj2.getPerderControl());
	}

	@Test
	public void Jugador_Choca_Bot_Pierde_El_Control() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);

		assertTrue(pj.getPerderControl());
		assertTrue(bot.getPerderControl());
	}

	@Test
	public void Jugador_Choca_Jugador_No_Explota() {
		AutoJugador pj1 = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoJugador pj2 = new AutoJugador(new Punto(0, 0), "Denis");

		pj1.choqueConObjeto(pj2);
		assertFalse(pj1.getExplotar());
		assertFalse(pj2.getExplotar());
	}

	@Test
	public void Jugador_Choca_Bot_No_Explota() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);

		assertFalse(pj.getExplotar());
		assertFalse(bot.getExplotar());
	}

	@Test
	public void Jugador_Choca_Camion_Y_Explota_Solo_El_Jugador() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		CamionObstaculo bot = new CamionObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);
		assertTrue(pj.getExplotar());
		assertFalse(bot.getExplotar());
	}

	@Test
	public void Bot_Choca_Camion_Y_Explota_Solo_El_Bot() {
		AutoObstaculo bot1 = new AutoObstaculo(new Punto(0, 0));
		CamionObstaculo bot2 = new CamionObstaculo(new Punto(0, 0));

		bot1.choqueConObjeto(bot2);
		assertTrue(bot1.getExplotar());
		assertFalse(bot2.getExplotar());
	}

	@Test
	public void Jugador_Choca_Con_Aceite_Y_Pierder_Control() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		pj.choqueConObjeto(aceite);
		assertTrue(pj.getPerderControl());
	}

	@Test
	public void Auto_Bot_Choca_Con_Aceite_Y_Pierder_Control() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		bot.choqueConObjeto(aceite);
		assertTrue(bot.getPerderControl());
	}

	@Test
	public void Camion_Choca_Con_Aceite_Y_No_Pierder_Control() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		assertFalse(camion.getPerderControl());
		camion.choqueConObjeto(aceite);
		assertFalse(camion.getPerderControl());
	}

	@Test
	public void Jugador_Choca_Con_Pozo_Y_Explota() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Pozo pozo = new Pozo(new Punto(0, 0));

		pj.choqueConObjeto(pozo);
		assertTrue(pj.getExplotar());
	}

	@Test
	public void Bot_Choca_Con_Pozo_Y_Explota() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Pozo pozo = new Pozo(new Punto(0, 0));

		bot.choqueConObjeto(pozo);
		assertTrue(bot.getExplotar());
	}

	@Test
	public void Camion_Choca_Con_Pozo_Y_No_Explota() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Pozo pozo = new Pozo(new Punto(0, 0));

		assertFalse(camion.getExplotar());
		camion.choqueConObjeto(pozo);
		assertFalse(camion.getExplotar());
	}

	@Test
	public void Jugador_Choca_Pozo_Con_Escudo_No_Explota_Y_Pierde_Escudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Pozo pozo = new Pozo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(pozo);
		assertFalse(pj.getExplotar());
		assertFalse(pj.getEscudo());
	}

	@Test
	public void Jugador_Choca_Pozo_Con_Escudo_Dos_Veces_Y_Explota() {
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
	public void Jugador_Se_Equipa_Con_Escudo_Al_Chocarlo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		assertTrue(pj.getEscudo());
	}

	@Test
	public void Los_Auto_Bots_No_Se_Pueden_Equipar_Con_Escudo() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		bot.choqueConObjeto(escudo);
		assertFalse(bot.getExplotar());
	}

	@Test
	public void Los_Camiones_No_Se_Pueden_Equipar_Con_Escudo() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		camion.choqueConObjeto(escudo);
		assertFalse(camion.getExplotar());
	}

	@Test
	public void Auto_Bot_Explota_Cuando_Es_Chocado_Por_Jugador_Con_Escudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(bot);
		assertFalse(pj.getExplotar());
		assertTrue(bot.getExplotar());
	}

	@Test
	public void Camion_Explota_Cuando_Es_Chocado_Por_Jugador_Con_Escudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(camion);
		assertFalse(pj.getExplotar());
		assertTrue(camion.getExplotar());
	}

	@Test
	public void Los_Escudos_No_Son_Acumulables() {
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
}
