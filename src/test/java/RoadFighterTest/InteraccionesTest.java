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
	public void Jugador_Choca_Jugador_Pierde_El_Control() {
		AutoJugador pj1 = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoJugador pj2 = new AutoJugador(new Punto(0, 0), "Denis");

		pj1.choqueConObjeto(pj2);

		assertTrue(pj1.getPerdiControl());
		assertTrue(pj2.getPerdiControl());
	}

	@Test
	public void Jugador_Choca_Bot_Pierde_El_Control() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);

		assertTrue(pj.getPerdiControl());
		assertTrue(bot.getPerdiControl());
	}

	@Test
	public void Jugador_Choca_Jugador_No_Explota() {
		AutoJugador pj1 = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoJugador pj2 = new AutoJugador(new Punto(0, 0), "Denis");

		pj1.choqueConObjeto(pj2);
		assertFalse(pj1.getExplote());
		assertFalse(pj2.getExplote());
	}

	@Test
	public void Jugador_Choca_Bot_No_Explota() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);

		assertFalse(pj.getExplote());
		assertFalse(bot.getExplote());
	}

	@Test
	public void Jugador_Choca_Camion_Y_Explota_Solo_El_Jugador() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		CamionObstaculo bot = new CamionObstaculo(new Punto(0, 0));

		pj.choqueConObjeto(bot);
		assertTrue(pj.getExplote());
		assertFalse(bot.getExplote());
	}

	@Test
	public void Bot_Choca_Camion_Y_Explota_Solo_El_Bot() {
		AutoObstaculo bot1 = new AutoObstaculo(new Punto(0, 0));
		CamionObstaculo bot2 = new CamionObstaculo(new Punto(0, 0));

		bot1.choqueConObjeto(bot2);
		assertTrue(bot1.getExplote());
		assertFalse(bot2.getExplote());
	}

	@Test
	public void Jugador_Choca_Con_Aceite_Y_Pierde_Control() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		pj.choqueConObjeto(aceite);
		assertTrue(pj.getPerdiControl());
	}

	@Test
	public void Auto_Bot_Choca_Con_Aceite_Y_Pierde_Control() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		bot.choqueConObjeto(aceite);
		assertTrue(bot.getPerdiControl());
	}

	@Test
	public void Camion_Choca_Con_Aceite_Y_No_Pierde_Control() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		ManchaDeAceite aceite = new ManchaDeAceite(new Punto(0, 0));

		assertFalse(camion.getPerdiControl());
		camion.choqueConObjeto(aceite);
		assertFalse(camion.getPerdiControl());
	}

	@Test
	public void Jugador_Choca_Con_Pozo_Y_Explota() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Pozo pozo = new Pozo(new Punto(0, 0));

		pj.choqueConObjeto(pozo);
		assertTrue(pj.getExplote());
	}

	@Test
	public void Bot_Choca_Con_Pozo_Y_Explota() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Pozo pozo = new Pozo(new Punto(0, 0));

		bot.choqueConObjeto(pozo);
		assertTrue(bot.getExplote());
	}

	@Test
	public void Camion_Choca_Con_Pozo_Y_No_Explota() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Pozo pozo = new Pozo(new Punto(0, 0));

		assertFalse(camion.getExplote());
		camion.choqueConObjeto(pozo);
		assertFalse(camion.getExplote());
	}

	@Test
	public void Jugador_Choca_Pozo_Con_Escudo_No_Explota_Y_Pierde_Escudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Pozo pozo = new Pozo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(pozo);
		assertFalse(pj.getExplote());
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
		assertTrue(pj.getExplote());
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
		assertFalse(bot.getExplote());
	}

	@Test
	public void Los_Camiones_No_Se_Pueden_Equipar_Con_Escudo() {
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		camion.choqueConObjeto(escudo);
		assertFalse(camion.getExplote());
	}

	@Test
	public void Auto_Bot_Explota_Cuando_Es_Chocado_Por_Jugador_Con_Escudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(bot);
		assertFalse(pj.getExplote());
		assertTrue(bot.getExplote());
	}

	@Test
	public void Camion_Explota_Cuando_Es_Chocado_Por_Jugador_Con_Escudo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		CamionObstaculo camion = new CamionObstaculo(new Punto(0, 0));
		Escudo escudo = new Escudo(new Punto(0, 0));

		pj.choqueConObjeto(escudo);
		pj.choqueConObjeto(camion);
		assertFalse(pj.getExplote());
		assertTrue(camion.getExplote());
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
		assertFalse(pj.getExplote());

		pj.choqueConObjeto(bot2);
		assertTrue(pj.getExplote());
	}

	@Test
	public void Chocar_Acelerador_Extremo_Incrementa_Velocidad_Actual_Por_Encima_De_Velocidad_Maxima() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AceleradorExtremo acelerador = new AceleradorExtremo(new Punto(0, 0));

		pj.acelerar();
		pj.acelerar();

		pj.choqueConObjeto(acelerador);
		assertEquals((int) pj.getVelocidadActual(), 200);
	}

	@Test
	public void Acelerador_Extremo_No_Permite_Al_Auto_Jugador_Frenar_Ni_Acelerar() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		AceleradorExtremo acelerador = new AceleradorExtremo(new Punto(0, 0));

		pj.choqueConObjeto(acelerador);

		assertFalse(pj.acelerar());
		assertFalse(pj.frenar());
	}

	@Test
	public void Un_Bot_No_Choca_Con_Acelerador__Extremo_Y_No_Aumenta_Su_Velocidad() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		AceleradorExtremo acelerador = new AceleradorExtremo(new Punto(0, 0));

		bot.acelerar(); // VELOCIDAD = 1
		bot.choqueConObjeto(acelerador);

		assertEquals((int) bot.getVelocidadActual(), 1); // LA VELOCIDAD NO FUE AFECTADA
	}

	@Test
	public void Auto_Jugador_Explota_Teniendo_Acelerador_Extremo_Y_Lo_Pierde() {
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
	public void Auto_Jugador_Choca_Con_Miniaturizador_Su_Ancho_Y_Largo_Se_Reducen_A_La_Mitad() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		Miniaturizador miniaturizacion = new Miniaturizador(new Punto(0, 0));

		assertEquals((int) pj.getTamanio(), 30 * 40);
		pj.choqueConObjeto(miniaturizacion);
		assertEquals((int) pj.getTamanio(), 15 * 20);
	}

	@Test
	public void Auto_Obstaculo_Choca_Con_Miniaturizador_No_Pasa_Nada() {
		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));
		Miniaturizador miniaturizacion = new Miniaturizador(new Punto(0, 0));

		assertEquals((int) bot.getTamanio(), 30 * 40);
		bot.choqueConObjeto(miniaturizacion);
		assertEquals((int) bot.getTamanio(), 30 * 40); // EL TAMANIO NO FUE AFECTADO
	}

	@Test
	public void Camion_Obstaculo_Choca_Con_Miniaturizador_No_Pasa_Nada() {
		CamionObstaculo bot = new CamionObstaculo(new Punto(0, 0));
		Miniaturizador miniaturizacion = new Miniaturizador(new Punto(0, 0));

		assertEquals((int) bot.getTamanio(), 80 * 30);
		bot.choqueConObjeto(miniaturizacion);
		assertEquals((int) bot.getTamanio(), 80 * 30); // EL TAMANIO NO FUE AFECTADO
	}

	@Test
	public void Jugador_Explota_Teniendo_Miniaturizador_Vuelve_A_La_Normalidad() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");
		CamionObstaculo bot2 = new CamionObstaculo(new Punto(0, 0));
		Miniaturizador miniaturizacion = new Miniaturizador(new Punto(0, 0));

		pj.choqueConObjeto(miniaturizacion);// TAMAÑO DISMINUIDO
		pj.choqueConObjeto(bot2); // ACA EXPLOTA
		assertEquals((int) pj.getTamanio(), 30 * 40); // TAMAÑO NORMALIZADO
	}
}
