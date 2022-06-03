package RoadFighter.Objetos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GuardarrailDerecho extends ObjetoDeMapa{
		
	public GuardarrailDerecho(int x, int y) {	
		
		this.imageSpacing = 0;
		this.width = 10;
		this.height = 720*30;
		this.maxSpeed = 0;
		this.speed = 0;
		this.y = y;
		this.x = x;
		
		Image spriteImages = new Image("file:src/main/resources/img/guardarail-der.png", width, height, false, false);

		render = new ImageView(spriteImages);
		resetViewport();

		// Seteo ancla en la mitad del eje X y en el final del eje Y
		render.relocate(-width / 2, -height);
		
		render.setX(x);
		setY(y);			
	}	

	@Override
	public void colisionasteConAutoJugador(AutoJugador auto) {			
		if (!auto.getFueraDeJuego()) {
			if (!auto.getTengoEscudo()) {			
				auto.explosion();
			} else {
				auto.perderEscudo();
			}
		}		
	}

	@Override
	public void colisionasteConAutoObstaculo(AutoObstaculo auto) {
		if(!auto.getFueraDeJuego()) {
			auto.explosion();	
		}	
	}

	@Override
	public void colisionasteConCamionObstaculo(CamionObstaculo camion) {
		if(!camion.getFueraDeJuego()) {
			camion.explosion();	
		}
	}

	
}
