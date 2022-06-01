package RoadFighter;

import RoadFighter.utils.IndividualSpriteAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

//agregado GameObject
public abstract class ObjetoDelMapa {

	/// ATRIBUTOD USADOS EN LOS TESTS
	protected boolean explote = false;
	protected boolean perdiControl = false;
	///
	protected Punto coordenada;
	protected double ancho;
	protected double largo;
	protected String nombre;
	protected boolean tieneMovimiento;
	protected boolean tengoQueDesaparecer = false;

	protected IndividualSpriteAnimation animacionExplosion;
	protected Image imagenSprites = new Image("file:src/main/resources/Imagenes/Road Fighter-General-Sprites.png", 500,
			350, false, false);
	protected ImageView render;
	protected Rectangle collider;
	protected final double colliderTolerance = 0.75;
	protected final int colliderWidth = (int) (30 * colliderTolerance);
	protected final int colliderHeight = (int) (40 * colliderTolerance);

	public Punto getCoordenada() {
		return this.coordenada;
	}

	public double getAncho() {
		return this.ancho;
	}

	public double getLargo() {
		return this.largo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean getTieneMovimiento() {
		return this.tieneMovimiento;
	}

	public void explotar() {
		explote = true;
	}

	public void explotar(ImageView render) {
		// La imagen de la explosion esta en el (70,70), aca esta en la explosion
		// Esta es la posicion de las explosiones y el auto destruido
		animacionExplosion = new IndividualSpriteAnimation(render, Duration.millis(3000), 4, 5, 45, 70, 3, 20, 30);
		animacionExplosion.setCycleCount(1);
		animacionExplosion.play();
		explote = true;
	}

	public boolean hayColisionCon(ObjetoDelMapa objeto) {
		boolean hayColision = false;

		double distX = this.coordenada.getDistanciaXCon(objeto.coordenada);
		double distY = this.coordenada.getDistanciaYCon(objeto.coordenada);

		//if ((distX <= this.ancho / 2 + objeto.ancho / 2) && (distY <= this.largo / 2 + objeto.largo / 2)) {
		if(distX == 0 && distY == 0) {
			hayColision = true;
		}

		return hayColision;
	}

	public abstract void choqueConAutoJugador(AutoJugador auto);

	public abstract void choqueConAutoObstaculo(AutoObstaculo auto);

	public abstract void choqueConCamion(CamionObstaculo camion);

	/// METODOS USADOS EN LOS TESTS

	public boolean getExplote() {
		return this.explote;
	}

	public boolean getPerdiControl() {
		return this.perdiControl;
	}

	public double getTamanio() {
		return largo * ancho;
	}

	///
	public abstract void destroy();
}
