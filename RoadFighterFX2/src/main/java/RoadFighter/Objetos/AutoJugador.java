package RoadFighter.Objetos;

import RoadFighter.SpriteAnimation;
import RoadFighter.Interfaces.Colisionable;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AutoJugador extends Vehiculo {
	private boolean acelerando = false;
	private boolean aceleracionBloqueada = false;	
	private boolean tengoEscudo = false;
	private boolean tengoAcelerador = false;
	private boolean reapareci = false;
	private SpriteAnimation reaparicionAnimation;
	private int posYOriginal;
	private int posXOriginal;

	public AutoJugador(int x, int y) {

		this.imageSpacing = 6;
		this.width = 48;
		this.height = 48;
		this.maxSpeed = 1000;
		this.speed = 0;
		this.y = y;
		this.x = x;
		this.posYOriginal = y;
		this.posXOriginal = x;

		Image spriteImages = new Image("file:src/main/resources/img/sprites.png", 136 * 3, 90 * 3, false, false);

		render = new ImageView(spriteImages);
		resetViewport();

		// Seteo ancla en la mitad del eje X y en la mitad del eje Y
		render.relocate(-width / 2, -height / 2);

		setX(x);
		render.setY(y);
		
		movilidadBloqueada = true;

		explosionAnimation = new SpriteAnimation(render, Duration.millis(500), 3, 3, 21 * 3, imageSpacing, imageSpacing,
				width, height);
		explosionAnimation.setCycleCount(3);
		explosionAnimation.setOnFinished(event -> reaparecer());

		reaparicionAnimation = new SpriteAnimation(render, Duration.millis(500), 2, 500, 79 * 3, imageSpacing,
				imageSpacing, width, height);
		reaparicionAnimation.setCycleCount(3);
		reaparicionAnimation.setOnFinished(event -> setValoresPorDefecto());

		derrapeAnimation = new SpriteAnimation(render, Duration.millis(500), 5, 500, imageSpacing,
				2 * imageSpacing + height, imageSpacing, width, height);
		derrapeAnimation.setCycleCount(2);
		derrapeAnimation.setOnFinished(event -> recuperarControl());

	}

	private void reaparecer() {
		this.x = posXOriginal;
		reapareci = true;
		directionRight = false;
		directionLeft = false;		
		render.setX(x);
		resetViewport();
		reaparicionAnimation.play();
	}

	private void setValoresPorDefecto() {
		width = 48;
		height = 48;
		maxSpeed = 1000;
		derrapeDer = false;
		derrapeIzq = false;
		directionRight = false;
		directionLeft = false;
		movilidadBloqueada = false;
		aceleracionBloqueada = false;
		estoyFueraDeJuego = false;
		tengoAcelerador = false;
		tengoEscudo = false;
		resetViewport();
	}

	public void irse() {	

		TranslateTransition noTranslate = new TranslateTransition(Duration.millis(700), render);
		TranslateTransition translateUp = new TranslateTransition(Duration.millis(2500), render);
		translateUp.setToY(-1000);

		SequentialTransition sq = new SequentialTransition(render, noTranslate, translateUp);

		sq.play();
	}

	public void setDirectionRight(boolean b) {

		if (derrapeDer) {
			directionRight = true;
		} else if (derrapeIzq) {
			directionRight = false;
		} else {
			directionRight = b;
		}
	}

	public void setDirectionLeft(boolean b) {

		if (derrapeDer) {
			directionLeft = false;
		} else if (derrapeIzq) {
			directionLeft = true;
		} else {
			directionLeft = b;
		}
	}

	public void setAcelerando(boolean b) {
		if (!movilidadBloqueada) {
			this.acelerando = b;
		} else {
			this.acelerando = false;
		}
	}

	public void setNewSpeed(double speed) {
		this.maxSpeed = speed;
		this.speed = this.maxSpeed;
		this.tengoAcelerador = true;
	}

	public double getYActual() {
		return y;
	}	
	
	public boolean getReapareci() {
		return reapareci;
	}	
	
	public boolean setReapareci(boolean b) {
		return reapareci = b;
	}	
	
	public void bloquearAceleracion() {
		aceleracionBloqueada = true;
	}

	public void obtenerEscudo() {
		tengoEscudo = true;
	}

	public void perderEscudo() {
		tengoEscudo = false;
	}

	public boolean getTengoEscudo() {
		return tengoEscudo;
	}
	
	public boolean getTengoAcelerador() {
		return tengoAcelerador;
	}

	@Override
	protected void setY(double y) {
		if (!movilidadBloqueada) {
			this.y = y;
		}
	}

	@Override
	public double getY() {
		return posYOriginal;
	}

	@Override
	public double update(double deltaTime, double speedJugador) {
		int directionH = directionLeft ? -1 : (directionRight ? 1 : 0);

		if (!movilidadBloqueada) {

			if (!aceleracionBloqueada) {

				if (acelerando) {
					speed += 2000 * deltaTime;
					if (speed > maxSpeed) {
						speed = maxSpeed;
					}
				} else {
					speed -= 1000 * deltaTime;
					if (speed < 0) {
						speed = 0;
					}
				}
			}

			setX(x + directionH * speed / 3 * deltaTime);
			setY(y - speed * deltaTime);

		} else {
			speed = 0;
		}

		setY(y - speed * deltaTime);

		return speed;
	}

	@Override
	public void explosion() {
		movilidadBloqueada = true;
		tengoAcelerador = false;
		reapareci = false;
		super.explosion();
	}

	@Override
	public void derrapeDerecho() {
		bloquearAceleracion();
		speed = 500;		
		tengoAcelerador = false;
		super.derrapeDerecho();
	}

	@Override
	public void derrapeIzquierdo() {
		bloquearAceleracion();
		speed = 500;	
		tengoAcelerador = false;
		super.derrapeIzquierdo();
	}

	@Override
	public void recuperarControl() {
		aceleracionBloqueada = false;
		derrapeDer = false;
		derrapeIzq = false;
		maxSpeed = 1000;
		super.recuperarControl();
	}

	@Override
	public void colisionasteConObjeto(Colisionable colisionable) {
		colisionable.colisionasteConAutoJugador(this);
	}

	@Override
	public void colisionasteConAutoJugador(AutoJugador auto) {
		if (!this.getTengoEscudo()) {
			if(!auto.getTengoEscudo()) {
				if (!auto.getFueraDeJuego() && !this.getFueraDeJuego()) {
					if(this.getX() >= auto.getX()) {
						this.derrapeDerecho();
						auto.derrapeIzquierdo();
					}else {
						this.derrapeIzquierdo();
						auto.derrapeDerecho();
					}		
				}
			}else {
				this.explosion();				
				auto.perderEscudo();
			}
			
		} else {
			if(!auto.getTengoEscudo()) {
				auto.explosion();
				this.perderEscudo();
			}else {
				this.explosion();
				this.perderEscudo();
				auto.explosion();
				auto.perderEscudo();
			}
		}
	}

	@Override
	public void colisionasteConAutoObstaculo(AutoObstaculo auto) {
		if (!this.getTengoEscudo()) {
			if (!auto.getFueraDeJuego() && !this.getFueraDeJuego()) {						
				if(this.getX() >= auto.getX()) {
					this.derrapeDerecho();
					auto.derrapeIzquierdo();
				}else {
					this.derrapeIzquierdo();
					auto.derrapeDerecho();
				}		
			}
		} else {
			auto.explosion();
			this.perderEscudo();
		}
	}

	@Override
	public void colisionasteConCamionObstaculo(CamionObstaculo camion) {
		if (!this.getTengoEscudo()) {
			if (!camion.getFueraDeJuego() && !this.getFueraDeJuego()) {
				this.explosion();
			}
		} else {
			camion.explosion();
			this.perderEscudo();
		}
	}
}
