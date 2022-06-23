package RoadFighter.Objetos;

import RoadFighter.SpriteAnimation;
import RoadFighter.Interfaces.Colisionador;

public abstract class Vehiculo extends ObjetoDeMapa implements Colisionador{
	
	protected boolean directionRight = false;
	protected boolean directionLeft = false;	
	protected boolean movilidadBloqueada = false;	
	protected boolean estoyFueraDeJuego = false;
	protected boolean derrapeDer = false;
	protected boolean derrapeIzq = false;
	protected SpriteAnimation explosionAnimation;
	protected SpriteAnimation derrapeAnimation;	
				
	protected void setX(double x) {
		if(!movilidadBloqueada) {			
			this.x = x;
			render.setX(x);				
		}
		
		if (x < 118 || x > 610) {				
			explosion();					
		} 	
	}	
		
	protected void setY(double y) {
		if(!movilidadBloqueada) {
			this.y = y;
			render.setY(y);			
		}	
	}
	
	public void bloquearMovilidad() {
		this.movilidadBloqueada = true;
	}
	
	public void desbloquearMovilidad() {
		this.movilidadBloqueada = false;
	}
	
	@Override
	public double update(double deltaTime, double speedJugador) {
		int directionH = directionLeft ? -1 : (directionRight ? 1 : 0);	
		
		if(estoyFueraDeJuego) {
			maxSpeed = 0;
		}
		
		speed = maxSpeed - speedJugador;			
											
		setX(x + directionH * speed / 5 * deltaTime);
		setY(y - speed * deltaTime);
				
		return 0;
	}
	
	public void explosion() {		
		estoyFueraDeJuego = true;		
		if(derrapeIzq || derrapeDer) {
			derrapeAnimation.stop();
		}
		explosionAnimation.play();
	}	
	
	public void derrapeDerecho() {
		derrapeDer = true;
		directionRight = true;
		derrapeAnimation.play();					
	}
	
	public void derrapeIzquierdo() {		
		derrapeIzq = true;
		directionLeft = true;
		derrapeAnimation.play();					
	}
	
	public void recuperarControl() {
		directionRight = false;
		directionLeft = false;
		derrapeDer = false;
		derrapeIzq = false;
		resetViewport();
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getMaxSpeed() {
		return maxSpeed;
	}	
	
	public boolean getFueraDeJuego() {
		return estoyFueraDeJuego;
	}	
	
	public boolean getDerrapando() {
		if(derrapeDer || derrapeIzq) {
			return true;
		}else {
			return false;			
		}
	}
}