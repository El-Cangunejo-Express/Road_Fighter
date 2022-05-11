package RoadFighterTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import RoadFighter.AutoJugador;
import RoadFighter.Carretera;
import RoadFighter.Jugador;
import RoadFighter.Partida;

public class PartidaTest {

	@Test
	public void puedoAgregarJugadorSimepreQueNoMePaseDeLaCantidadMaxima() {
		Partida partida = new Partida();
		
		Jugador pj1 = new Jugador("Nahuel");
		Jugador pj2 = new Jugador("Denis");
		Jugador pj3 = new Jugador("Abigail");
		Jugador pj4 = new Jugador("David");
		Jugador pj5 = new Jugador("Nicolas");
		Jugador pj6 = new Jugador("Daniel");
		
		assertTrue(partida.agregarJugador(pj1));  		
		assertTrue(partida.agregarJugador(pj2));  
		assertTrue(partida.agregarJugador(pj3));  
		assertTrue(partida.agregarJugador(pj4));  
		assertTrue(partida.agregarJugador(pj5));  
		
		assertFalse(partida.agregarJugador(pj6));  
	}
	
	@Test
	public void noPuedoIniciarLaPartidaConMenosDeDosJugadores() {
		Partida partida = new Partida();
		
		Jugador pj1 = new Jugador("Nahuel");
		Jugador pj2 = new Jugador("Denis");		
		
		assertTrue(partida.agregarJugador(pj1)); 
		
		assertFalse(partida.iniciar());  
		
		assertTrue(partida.agregarJugador(pj2));  
		
		assertTrue(partida.iniciar());	 
	}
	
	@Test
	public void todosLosVehiculosDeLosJugadoresInicianEnUnIgualdadDeCondiciones() {
		Partida partida = new Partida();
		
		Jugador pj1 = new Jugador("Nahuel");
		Jugador pj2 = new Jugador("Denis");
		Jugador pj3 = new Jugador("Abigail");
		Jugador pj4 = new Jugador("David");
		Jugador pj5 = new Jugador("Nicolas");	
		
		assertTrue(partida.agregarJugador(pj1));  		
		assertTrue(partida.agregarJugador(pj2));  
		assertTrue(partida.agregarJugador(pj3));  
		assertTrue(partida.agregarJugador(pj4));  
		assertTrue(partida.agregarJugador(pj5)); 
		
		assertTrue(partida.iniciar());	 
		
		ArrayList<AutoJugador> autos = partida.getAutosJugadores();
		
		assertEquals(autos.size(), 5);	
		
		assertEquals((int)autos.get(0).getCoordenada().getX(), 0); 		
		assertEquals((int)autos.get(0).getCoordenada().getY(), 50); 	
		assertEquals((int)autos.get(1).getCoordenada().getX(), 60); 	
		assertEquals((int)autos.get(1).getCoordenada().getY(), 50); 		
		assertEquals((int)autos.get(2).getCoordenada().getX(), -60); 
		assertEquals((int)autos.get(2).getCoordenada().getY(), 50);	
		assertEquals((int)autos.get(3).getCoordenada().getX(), 120); 
		assertEquals((int)autos.get(3).getCoordenada().getY(), 50); 
		assertEquals((int)autos.get(4).getCoordenada().getX(), -120);
		assertEquals((int)autos.get(4).getCoordenada().getY(), 50);
		
		assertEquals((int)autos.get(0).getVelocidadActual(), 0);  		
		assertEquals((int)autos.get(1).getVelocidadActual(), 0);
		assertEquals((int)autos.get(2).getVelocidadActual(), 0);
		assertEquals((int)autos.get(3).getVelocidadActual(), 0);
		assertEquals((int)autos.get(4).getVelocidadActual(), 0);
	}
	
	@Test
	public void alAvanzarDetectaChoquesYFueraDeLimite() {
		Partida partida = new Partida();
		
		Jugador pj1 = new Jugador("Nahuel");
		Jugador pj2 = new Jugador("Denis");
		Jugador pj3 = new Jugador("Abigail");
		Jugador pj4 = new Jugador("David");
		Jugador pj5 = new Jugador("Nicolas");
				
		assertTrue(partida.agregarJugador(pj1));  		
		assertTrue(partida.agregarJugador(pj2));  
		assertTrue(partida.agregarJugador(pj3));  
		assertTrue(partida.agregarJugador(pj4));  
		assertTrue(partida.agregarJugador(pj5)); 
		
		assertTrue(partida.iniciar());	 
		
		ArrayList<AutoJugador> autos = partida.getAutosJugadores();
		
		assertEquals(autos.size(), 5);	
		
		for(int i = 0; i < 5; i++) {
			autos.get(0).acelerar();
			autos.get(1).acelerar();
			autos.get(2).acelerar();
			autos.get(3).acelerar();
			autos.get(4).acelerar();			
		}			
		
		assertEquals((int)autos.get(0).getVelocidadActual(), 5);  		
		assertEquals((int)autos.get(1).getVelocidadActual(), 5);
		assertEquals((int)autos.get(2).getVelocidadActual(), 5);
		assertEquals((int)autos.get(3).getVelocidadActual(), 5);
		assertEquals((int)autos.get(4).getVelocidadActual(), 5);
		
		autos.get(0).avanzar(); autos.get(0).avanzar();
		autos.get(1).avanzar(); autos.get(1).avanzar();
		autos.get(2).avanzar(); autos.get(2).avanzar();
		autos.get(3).avanzar(); autos.get(3).avanzar();
		autos.get(4).avanzar(); autos.get(4).avanzar();


		for(int i = 0; i < 15; i++) {			
			autos.get(4).irAIzquierda();			
		}		
		
		Carretera carretera = partida.getCarretera();
		assertEquals((int)carretera.getCantidadDeObjetos(), 6);  			//ACTUALMENTE HAY 5 AUTOS Y 3 OBSTACULOS EN (0,100) (60, 80) (-60,150)
					
		assertEquals((int)autos.get(0).getCoordenada().getX(), 0); 		
		assertEquals((int)autos.get(0).getCoordenada().getY(), 60); 
		
		assertEquals((int)autos.get(1).getCoordenada().getX(), 60); 		//AUTO DEL SEGUNDO JUGADOR DEBERIA CHOCAR Y ELIMINANDO UN ELEMENTO DE LA PISTA
		assertEquals((int)autos.get(1).getCoordenada().getY(), 60); 		//Y VOLVIENDO CERO LA VELOCIDAD DEL VEHICULO Y SU COORDENADA X VUELVE A LA ORIGINAL
		
		assertEquals((int)autos.get(2).getCoordenada().getX(), -60); 
		assertEquals((int)autos.get(2).getCoordenada().getY(), 60);	
		
		assertEquals((int)autos.get(3).getCoordenada().getX(), 120); 
		assertEquals((int)autos.get(3).getCoordenada().getY(), 60); 
		
		assertEquals((int)autos.get(4).getCoordenada().getX(), -135); 		//AUTO DEL QUINTO JUGADOR TOCA LIMITE IZQUIERDO, DEBE EXPLOTAR
		assertEquals((int)autos.get(4).getCoordenada().getY(), 60);
		
		partida.actualizar();
		
		assertEquals((int)carretera.getCantidadDeObjetos(), 5);				//AHORA HAY UN ELEMENTO MENOS
		
		assertEquals((int)autos.get(1).getCoordenada().getY(), 60); 		//EL SEGUNDO COCHE EXPLOTO Y REAPARECIO
		assertEquals((int)autos.get(1).getCoordenada().getX(), 60);
		assertEquals((int)autos.get(1).getVelocidadActual(), 0);
		
		assertEquals((int)autos.get(4).getCoordenada().getY(), 60); 		//EL QUINTO COCHE EXPLOTO Y REAPARECIO
		assertEquals((int)autos.get(4).getCoordenada().getX(), -120);
		assertEquals((int)autos.get(4).getVelocidadActual(), 0);
				
		for(int i = 0; i < 28; i++) {			
			autos.get(0).avanzar();											//AUTO DEL PRIMER JUGADOR AVANZA HASTA EL FINAL DE LA CARRETERA			
		}	
		
		assertEquals((int)autos.get(0).getCoordenada().getY(), 200); 	
		assertEquals((int)autos.get(0).getCoordenada().getX(), 0); 
		
		partida.actualizar();												//AUTO DEL PRIMER JUGADOR GANA		
	}
	
	@Test
	public void alTerminarLesDaPuntajeALosJugadoresAcordeCuantoAvanzaron() {
		Partida partida = new Partida();
		
		Jugador pj1 = new Jugador("Nahuel");
		Jugador pj2 = new Jugador("Denis");
		Jugador pj3 = new Jugador("Abigail");
		Jugador pj4 = new Jugador("David");
		Jugador pj5 = new Jugador("Nicolas");
		
		assertEquals((int)pj1.getPuntuacion(), 0); 
		assertEquals((int)pj2.getPuntuacion(), 0); 
		assertEquals((int)pj3.getPuntuacion(), 0); 
		assertEquals((int)pj4.getPuntuacion(), 0); 
		assertEquals((int)pj5.getPuntuacion(), 0); 
				
		assertTrue(partida.agregarJugador(pj1));  		
		assertTrue(partida.agregarJugador(pj2));  
		assertTrue(partida.agregarJugador(pj3));  
		assertTrue(partida.agregarJugador(pj4));  
		assertTrue(partida.agregarJugador(pj5)); 
		
		assertTrue(partida.iniciar());	 
		
		ArrayList<AutoJugador> autos = partida.getAutosJugadores();
		
		assertEquals(autos.size(), 5);	
		
		for(int i = 0; i < 5; i++) {
			autos.get(0).acelerar();
			autos.get(1).acelerar();
			autos.get(2).acelerar();
			autos.get(3).acelerar();
			autos.get(4).acelerar();			
		}		
		
		autos.get(0).avanzar(); autos.get(0).avanzar();
		autos.get(1).avanzar(); autos.get(1).avanzar();
		autos.get(2).avanzar(); autos.get(2).avanzar();
		autos.get(3).avanzar(); autos.get(3).avanzar();
		autos.get(4).avanzar(); autos.get(4).avanzar();


		for(int i = 0; i < 15; i++) {			
			autos.get(4).irAIzquierda();			
		}		
						
		partida.actualizar();		
		
		for(int i = 0; i < 28; i++) {			
			autos.get(0).avanzar();											//AUTO DEL PRIMER JUGADOR AVANZA HASTA EL FINAL DE LA CARRETERA			
		}	
			
		partida.actualizar();												//AUTO DEL PRIMER JUGADOR GANA		
		
		assertEquals((int)pj1.getPuntuacion(), 10000); 						//LAS UNTUACIONES LAS DA SEGUN = 10000 * (avanceEnCarrera / largoCarretera)
		assertEquals((int)pj2.getPuntuacion(), 3000); 
		assertEquals((int)pj3.getPuntuacion(), 3000); 
		assertEquals((int)pj4.getPuntuacion(), 3000); 
		assertEquals((int)pj5.getPuntuacion(), 3000); 
	}
}
