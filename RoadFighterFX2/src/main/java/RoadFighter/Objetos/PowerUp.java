package RoadFighter.Objetos;

public abstract class PowerUp extends ObjetoDeMapa {

	public PowerUp(int x, int y) {
		this.imageSpacing = 0;
		this.width = 30;
		this.height = 30;
		this.maxSpeed = 0;
		this.speed = 0;	
		this.y = y;
		this.x = x;
	}	
	
	@Override
	public void colisionasteConAutoObstaculo(AutoObstaculo auto) {}

	@Override
	public void colisionasteConCamionObstaculo(CamionObstaculo camion) {}
	
}
