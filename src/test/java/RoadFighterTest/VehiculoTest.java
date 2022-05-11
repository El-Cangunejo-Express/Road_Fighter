package RoadFighterTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import RoadFighter.AutoJugador;
import RoadFighter.AutoObstaculo;
import RoadFighter.CamionObstaculo;
import RoadFighter.Punto;

public class VehiculoTest {

	@Test
	public void vehiculoIniciaEnReposo() {
		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");

		assertEquals((int) pj.getVelocidadActual(), 0);
	}

	@Test
	public void acelerarIncrementaEnUnoLaVelocidad() {
		System.out.println("++++TEST CASE: acelerarIncrementaEnUnoLaVelocidad++++");

		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");

		pj.acelerar();
		assertEquals((int) pj.getVelocidadActual(), 1);
	}

	@Test
	public void JugadorNoPuedeAcelerarPorEncimaDeLaVelocidadMaxima() {
		System.out.println("++++TEST CASE: JugadorNoPuedeAcelerarPorEncimaDeLaVelocidadMaxima++++");

		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");

		// La velocidad maxima es 100
		for (int i = 0; i < 100; i++) {
			pj.acelerar();
		}

		assertFalse(pj.acelerar());
	}

	@Test
	public void AutoBotNoPuedeAcelerarPorEncimaDeLaVelocidadMaxima() {
		System.out.println("++++TEST CASE: AutoBotNoPuedeAcelerarPorEncimaDeLaVelocidadMaxima++++");

		AutoObstaculo bot = new AutoObstaculo(new Punto(0, 0));

		// La velocidad maxima es 80
		for (int i = 0; i < 80; i++) {
			bot.acelerar();
		}

		assertFalse(bot.acelerar());
	}

	@Test
	public void CamionNoPuedeAcelerarPorEncimaDeLaVelocidadMaxima() {
		System.out.println("++++TEST CASE: CamionNoPuedeAcelerarPorEncimaDeLaVelocidadMaxima++++");

		CamionObstaculo bot = new CamionObstaculo(new Punto(0, 0));

		// La velocidad maxima es 60
		for (int i = 0; i < 60; i++) {
			bot.acelerar();
		}

		assertFalse(bot.acelerar());
	}

	@Test
	public void NoAvanzarConJugabilidadBloqueada() {
		System.out.println("++++TEST CASE: NoAvanzarConJugabilidadBloqueada++++");

		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel"); // Al inicio la jugabilidad esta bloqueada

		pj.acelerar();
		assertFalse(pj.avanzar());
	}

	@Test
	public void AvanzarConJugabilidadDesbloqueada() {
		System.out.println("++++TEST CASE: AvanzarConJugabilidadDesbloqueada++++");

		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");// Al inicio la jugabilidad esta bloqueada

		pj.acelerar();
		pj.habilitarDesabilitarJugabilidad();
		assertTrue(pj.avanzar());
	}

	@Test
	public void AvanzaPosicionesSegunVelocidadActual() {
		System.out.println("++++TEST CASE: AvanzaPosicionesSegunVelocidadActual++++");

		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");

		pj.habilitarDesabilitarJugabilidad();
		pj.acelerar();// VELOCIDAD=1
		pj.acelerar();// VELOCIDAD=2
		pj.acelerar();// VELOCIDAD=3
		pj.avanzar();// El vechiculo esta en la posicion 3 del eje Y
		pj.acelerar();// VELOCIDAD=4
		pj.acelerar();// VELOCIDAD=5
		pj.avanzar();// Incremento en 5 la posicion
		pj.avanzar();// Incremento en 5 la posicion
		assertEquals((int) pj.getCoordenada().getY(), 13);
	}

	@Test
	public void FrenarDecrementaLaVelocidadEnUno() {
		System.out.println("++++TEST CASE: FrenarDecrementaLaVelocidadEnUno++++");

		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");

		pj.acelerar();// VELOCIDAD=1
		pj.acelerar();// VELOCIDAD=2
		pj.acelerar();// VELOCIDAD=3
		System.out.println("La velocidad actual es: " + pj.getVelocidadActual());
		pj.frenar();// VELOCIDAD=2
		assertEquals((int) pj.getVelocidadActual(), 2);
	}

	@Test
	public void alFrenarLaVelocidadNoEsNegativa() {
		System.out.println("++++TEST CASE: alFrenarLaVelocidadNoEsNegativa++++");

		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");

		pj.acelerar();// VELOCIDAD=1
		pj.acelerar();// VELOCIDAD=2
		pj.acelerar();// VELOCIDAD=3
		System.out.println("La velocidad actual es: " + pj.getVelocidadActual());// Velocidad actual es 3
		pj.frenar();// VELOCIDAD=2
		pj.frenar();// VELOCIDAD=1
		pj.frenar();// VELOCIDAD=0
		pj.frenar();// SIGUE=0
		pj.frenar();// SIGUE=0
		pj.frenar();// SIGUE=0

		assertEquals((int) pj.getVelocidadActual(), 0);
	}

	@Test
	public void MoverALaDerecha() {
		System.out.println("++++TEST CASE: MoverseALaDerecha++++");

		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");

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
		System.out.println("++++TEST CASE: meMuevoIzquiuera++++");

		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");

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
	public void ChocarYReaparecerSinVelocidadEnElCentroDelPlano() {
		System.out.println("++++TEST CASE: ChocarYReaparecerSinVelocidadEnElCentroDelPlano++++");

		AutoJugador pj = new AutoJugador(new Punto(0, 0), "Nahuel");

		pj.habilitarDesabilitarJugabilidad();
		pj.acelerar();// VELOCIDAD=1
		pj.acelerar();// VELOCIDAD=2
		pj.acelerar();// VELOCIDAD=3
		pj.avanzar();// COORDENADA Y=3
		pj.avanzar();// COORDENADA Y=6
		pj.irADerecha();// COORDENADA X=1
		pj.irADerecha();// COORDENADA X=2

		System.out.println("Velocidad Actual antes del choque: " + pj.getVelocidadActual());
		System.out.println("La posicion actual antes del choque: " + pj.getCoordenada().toString());

		pj.explotar();

		assertEquals((int) pj.getVelocidadActual(), 0);
		assertEquals((int) pj.getCoordenada().getX(), 0);
		assertEquals((int) pj.getCoordenada().getY(), 6);
	}
}