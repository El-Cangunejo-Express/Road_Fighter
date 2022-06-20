package RoadFighter.Objetos;

import RoadFighter.SpriteAnimation;
import RoadFighter.Interfaces.Colisionable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CamionObstaculo extends Vehiculo{
	
	public CamionObstaculo(int x, int y) {

		this.imageSpacing = 6;
		this.width = 48;
		this.height = 48*2;
		this.maxSpeed = 500;
		this.speed = 0;
		this.y = y;
		this.x = x;

		Image spriteImages = new Image("file:src/main/resources/img/sprites3.png", 222, 108, false, false);

		render = new ImageView(spriteImages);
		resetViewport();

		// Seteo ancla en la mitad del eje X y en la mitad del eje Y
		render.relocate(-width / 2, -height / 2);

		setX(x);
		setY(y);
		
		movilidadBloqueada = true;
		
		explosionAnimation = new SpriteAnimation(render, Duration.millis(500), 3, 3, 21 * 3, imageSpacing, imageSpacing, width, height/2);
		explosionAnimation.setCycleCount(3);
		explosionAnimation.setOnFinished(event -> desaparecer());
	}

	@Override
	public void colisionasteConObjeto(Colisionable colisionable) {
		colisionable.colisionasteConCamionObstaculo(this);		
	}

	@Override
	public void colisionasteConAutoJugador(AutoJugador auto) {
		if(!auto.getTengoEscudo()) {
			if(!auto.getFueraDeJuego() && !this.getFueraDeJuego()) {
				auto.explosion();	
			}	
		}else {
			this.explosion();
			auto.perderEscudo();
		}
				
	}

	@Override
	public void colisionasteConAutoObstaculo(AutoObstaculo auto) {
		if(!auto.getFueraDeJuego() && !this.getFueraDeJuego()) {
			auto.explosion();	
		}			
	}

	@Override
	public void colisionasteConCamionObstaculo(CamionObstaculo camion) {
		if(!camion.getFueraDeJuego() && !this.getFueraDeJuego()) {
			this.explosion();		
			camion.desaparecer();	
		}			
	}	
}
