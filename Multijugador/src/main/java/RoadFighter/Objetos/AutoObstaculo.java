package RoadFighter.Objetos;

import RoadFighter.SpriteAnimation;
import RoadFighter.Interfaces.Colisionable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AutoObstaculo extends Vehiculo {
	
	public AutoObstaculo(int x, int y) {

		this.imageSpacing = 6;
		this.width = 48;
		this.height = 48;
		this.maxSpeed = 700;
		this.speed = 0;
		this.y = y;
		this.x = x;

		Image spriteImages = new Image("file:src/main/resources/img/sprites2.png", 136 * 3, 90 * 3, false, false);

		render = new ImageView(spriteImages);
		resetViewport();

		// Seteo ancla en la mitad del eje X y en la mitad del eje Y
		render.relocate(-width / 2, -height / 2);

		setX(x);
		setY(y);	
		
		movilidadBloqueada = true;
		
		explosionAnimation = new SpriteAnimation(render, Duration.millis(500), 3, 3, 21 * 3, imageSpacing, imageSpacing, width, height);
		explosionAnimation.setCycleCount(3);
		explosionAnimation.setOnFinished(event -> desaparecer());		
		
		derrapeAnimation = new SpriteAnimation(render, Duration.millis(500), 5, 500, imageSpacing, 2*imageSpacing + height, imageSpacing, width, height);
		derrapeAnimation.setCycleCount(2);
		derrapeAnimation.setOnFinished(event -> recuperarControl());
	}

	@Override
	public void colisionasteConObjeto(Colisionable colisionable) {
		colisionable.colisionasteConAutoObstaculo(this);		
	}

	@Override
	public void colisionasteConAutoJugador(AutoJugador auto) {
		if(!auto.getTengoEscudo()) {
			if(!auto.getFueraDeJuego() && !this.getFueraDeJuego()) {
				if(this.getX() > auto.getX()) {				
					this.derrapeIzquierdo();
					auto.derrapeDerecho();
				}else {
					this.derrapeDerecho();
					auto.derrapeIzquierdo();
				}	
			}	
		}else {
			this.explosion();
			auto.perderEscudo();
		}
			
	}

	@Override
	public void colisionasteConAutoObstaculo(AutoObstaculo auto) {
		if(!auto.getFueraDeJuego() && !this.getFueraDeJuego()) {
			if(this.getX() > auto.getX()) {				
				this.derrapeIzquierdo();
				auto.derrapeDerecho();
			}else {
				this.derrapeDerecho();
				auto.derrapeIzquierdo();
			}		
		}		
	}

	@Override
	public void colisionasteConCamionObstaculo(CamionObstaculo camion) {
		if(!camion.getFueraDeJuego() && !this.getFueraDeJuego()) {
			this.explosion();
		}			
	}		
}
