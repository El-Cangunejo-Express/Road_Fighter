package RoadFighter.Objetos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ManchaDeAceite extends ObjetoDeMapa {

	public ManchaDeAceite(int x, int y) {

		this.imageSpacing = 0;
		this.width = 30;
		this.height = 40;
		this.maxSpeed = 0;
		this.speed = 0;
		this.y = y;
		this.x = x;

		Image spriteImages = new Image("file:src/main/resources/img/aceite.png", width, height, false, false);

		render = new ImageView(spriteImages);
		resetViewport();

		// Seteo ancla en la mitad del eje X y en la mitad del eje Y
		render.relocate(-width / 2, -height / 2);

		render.setX(x);
		setY(y);
	}

	@Override
	public void colisionasteConAutoJugador(AutoJugador auto) {
		this.desaparecer();
		if (!auto.getFueraDeJuego()) {
			if (!auto.getTengoEscudo()) {			
				if(auto.getX() >= this.getX()) {
					auto.derrapeDerecho();
				}else {
					auto.derrapeIzquierdo();
				}
			} else {
				auto.perderEscudo();
			}
		}		
	}

	@Override
	public void colisionasteConAutoObstaculo(AutoObstaculo auto) {
		this.desaparecer();	
		if (!auto.getFueraDeJuego()) {
			if (auto.getX() > this.getX()) {
				auto.derrapeDerecho();
			} else {
				auto.derrapeIzquierdo();
			}
		}
		
	}

	@Override
	public void colisionasteConCamionObstaculo(CamionObstaculo camion) {
		this.desaparecer();
	}
}
