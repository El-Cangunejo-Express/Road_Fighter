package RoadFighterTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import RoadFighter.AutoJugador;
import RoadFighter.AutoObstaculo;
import RoadFighter.CamionObstaculo;
import RoadFighter.Jugador;
import RoadFighter.Punto;

public class VehiculoTest {

	private Jugador jugador;
	private AutoJugador pj;
	private AutoObstaculo bot;
	private CamionObstaculo bot2;

	@Before
	public void inicializaciones() {
		jugador = new Jugador("Nahuel");
		pj = new AutoJugador(new Punto(0, 0), jugador);
		bot = new AutoObstaculo(new Punto(0, 0));
		bot2 = new CamionObstaculo(new Punto(0, 0));
	}

	@Test
	public void vehiculoIniciaEnReposo() {
		assertEquals((int) pj.getVelocidadActual(), 0);
	}

	@Test
	public void acelerarIncrementaEnUnoLaVelocidad() {
		pj.acelerar();
		assertEquals((int) pj.getVelocidadActual(), 1);
	}

	@Test
	public void JugadorNoPuedeAcelerarPorEncimaDeVelocidadMaxima() {
		// Incremento hasta su velocidad maxima
		for (int i = 0; i < pj.getVelocidadMaxima(); i++) {
			pj.acelerar();
		}

		assertFalse(pj.acelerar());
	}

	@Test
	public void Auto_Obstaculo_No_Puede_Acelerar_Por_Encima_De_Su_Velocidad_Maxima() {
		// Incremento hasta su velocidad maxima
		for (int i = 0; i < bot.getVelocidadMaxima(); i++) {
			bot.acelerar();
		}

		assertFalse(bot.acelerar());
	}

	@Test
	public void CamionObstaculoNoPuedeAcelerarPorEncimaDeSuVelocidadMaxima() {
		// Incremento hasta su velocidad maxima
		for (int i = 0; i < bot2.getVelocidadMaxima(); i++) {
			bot2.acelerar();
		}

		assertFalse(bot2.acelerar());
	}

	@Test
	public void NoAvanzarConJugabilidadBloqueada() {
		assertTrue(pj.getJugabilidadBloqueada()); // Al inicio la jugabilidad esta bloqueada
		pj.acelerar();
		assertFalse(pj.avanzar());
	}

	@Test
	public void AvanzarConJugabilidadDesbloqueada() {
		pj.habilitarDesabilitarJugabilidad();
		pj.acelerar();
		assertTrue(pj.avanzar());
	}

	@Test
	public void AvanzaPosicionesSegunVelocidadActual() {
		pj.habilitarDesabilitarJugabilidad();
		pj.acelerar(); // VELOCIDAD=1
		pj.acelerar(); // VELOCIDAD=2
		pj.acelerar(); // VELOCIDAD=3
		pj.avanzar();
		assertEquals((int) pj.getCoordenada().getY(), 3);
		pj.acelerar(); // VELOCIDAD=4
		pj.acelerar(); // VELOCIDAD=5
		pj.avanzar();
		assertEquals((int) pj.getCoordenada().getY(), 8);
		pj.avanzar();
		assertEquals((int) pj.getCoordenada().getY(), 13);
	}

	@Test
	public void FrenarDecrementaLaVelocidadEnUno() {
		pj.acelerar(); // VELOCIDAD=1
		pj.acelerar(); // VELOCIDAD=2
		pj.acelerar(); // VELOCIDAD=3
		assertEquals((int) pj.getVelocidadActual(), 3);
		pj.frenar();
		assertEquals((int) pj.getVelocidadActual(), 2);
		pj.frenar();
		assertEquals((int) pj.getVelocidadActual(), 1);
	}

	@Test
	public void alFrenarLaVelocidadNuncaSeVuelveNegativa() {
		pj.acelerar();// VELOCIDAD=1
		pj.acelerar();// VELOCIDAD=2
		pj.acelerar();// VELOCIDAD=3
		pj.frenar();// VELOCIDAD=2
		assertEquals((int) pj.getVelocidadActual(), 2);
		pj.frenar();// VELOCIDAD=1
		assertEquals((int) pj.getVelocidadActual(), 1);
		pj.frenar();// VELOCIDAD=0
		assertEquals((int) pj.getVelocidadActual(), 0);
		pj.frenar();// SIGUE=0
		pj.frenar();// SIGUE=0
		pj.frenar();// SIGUE=0

		assertEquals((int) pj.getVelocidadActual(), 0);
	}

	@Test
	public void NoPuedoFrenarSiLaVelocidadYaEsDeCero() {
		pj.acelerar(); // VELOCIDAD=1
		pj.acelerar(); // VELOCIDAD=2
		pj.frenar(); // VELOCIDAD=1
		pj.frenar(); // VELOCIDAD=0

		assertFalse(pj.frenar());
	}

	@Test
	public void MoverALaDerecha() {
		pj.habilitarDesabilitarJugabilidad();
		assertEquals((int) pj.getCoordenada().getX(), 0);

		pj.irADerecha();// COORDENADA X=1
		assertEquals((int) pj.getCoordenada().getX(), 1);

		pj.irADerecha();// COORDENADA X=2
		assertEquals((int) pj.getCoordenada().getX(), 2);

		pj.irADerecha();// COORDENADA X=3
		assertEquals((int) pj.getCoordenada().getX(), 3);
	}

	@Test
	public void MoverALaIzquiuera() {
		pj.habilitarDesabilitarJugabilidad();
		assertEquals((int) pj.getCoordenada().getX(), 0);

		pj.irAIzquierda();// COORDENADA Y=-1
		assertEquals((int) pj.getCoordenada().getX(), -1);

		pj.irAIzquierda();// COORDENADA Y=-2
		assertEquals((int) pj.getCoordenada().getX(), -2);

		pj.irAIzquierda();// COORDENADA Y=-3
		assertEquals((int) pj.getCoordenada().getX(), -3);
	}

	@Test
	public void ChocarYReaparecerSinVelocidadEnLaMismaCoordenadaYPeroEnSuCoordenadaXOriginal() {
		pj.habilitarDesabilitarJugabilidad();

		pj.acelerar();// VELOCIDAD=1
		pj.acelerar();// VELOCIDAD=2
		pj.acelerar();// VELOCIDAD=3
		pj.avanzar();// COORDENADA Y=3
		assertEquals(pj.getCoordenada(), new Punto(0, 3));
		pj.avanzar();// COORDENADA Y=6
		assertEquals(pj.getCoordenada(), new Punto(0, 6));
		pj.irADerecha();// COORDENADA X=1
		assertEquals(pj.getCoordenada(), new Punto(1, 6));
		pj.irADerecha();// COORDENADA X=2
		assertEquals(pj.getCoordenada(), new Punto(2, 6));

		pj.explotar();

		assertEquals((int) pj.getVelocidadActual(), 0);
		assertEquals(pj.getCoordenada(), new Punto(0, 6));
	}
}