package RoadFighter.Interfaces;

import RoadFighter.Objetos.AutoJugador;
import RoadFighter.Objetos.AutoObstaculo;
import RoadFighter.Objetos.CamionObstaculo;

//Collideable
public interface Colisionable {	
	public void colisionasteConAutoJugador (AutoJugador auto);
	public void colisionasteConAutoObstaculo (AutoObstaculo auto);
	public void colisionasteConCamionObstaculo (CamionObstaculo camion);
}
