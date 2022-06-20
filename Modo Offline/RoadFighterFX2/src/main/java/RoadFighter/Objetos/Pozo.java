package RoadFighter.Objetos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pozo extends ObjetoDeMapa {

	public Pozo(int x, int y) {

		this.imageSpacing = 0;
		this.width = 30;
		this.height = 30;
		this.maxSpeed = 0;
		this.speed = 0;
		this.y = y;
		this.x = x;

		Image spriteImages = new Image("file:src/main/resources/img/pozo.png", width, height, false, false);

		render = new ImageView(spriteImages);
		resetViewport();

		// Seteo ancla en la mitad del eje X y en la mitad del eje Y
		render.relocate(-width / 2, -height / 2);

		setY(y);
		render.setX(x);			
	}

	@Override
	public void colisionasteConAutoJugador(AutoJugador auto) {
		this.desaparecer();		
		if (!auto.getFueraDeJuego()) {
			if(!auto.getTengoEscudo()) {
				auto.explosion();			
			} else {
				auto.perderEscudo();
			}
		}		
	}

	@Override
	public void colisionasteConAutoObstaculo(AutoObstaculo auto) {
		this.desaparecer();	
		if (!auto.getFueraDeJuego()) {
			auto.explosion();
		}
	}

	@Override
	public void colisionasteConCamionObstaculo(CamionObstaculo camion) {
		this.desaparecer();
	}

}