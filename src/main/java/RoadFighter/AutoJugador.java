package RoadFighter;

import RoadFighter.interfaces.Collidator;
import RoadFighter.interfaces.Collideable;
import RoadFighter.interfaces.Renderable;
import RoadFighter.interfaces.Updatable;
import RoadFighter.utils.IndividualSpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class AutoJugador extends Vehiculo implements Updatable, Renderable, Collidator {
	/// ATRIBUTOD USADOS EN LOS TESTS
	private boolean reapareci = false;
	///
	private boolean tengoEscudo = false;
	private double coordenadaXoriginal;
	private Jugador jugador;

	public AutoJugador(Punto coordenada, Jugador jugador) {
		this.coordenada = coordenada;
		this.coordenadaXoriginal = coordenada.getX();
		this.largo = 40;
		this.ancho = 30;
		this.jugador = jugador;
		this.nombre = jugador.getNombre();
		this.tieneMovimiento = true;
		this.velocidadActual = 0;
		this.velocidadMaxima = 100;
		this.jugabilidadBloqueada = true;

		// javaFx
		render = new ImageView(imagenSprites);
		render.setViewport(new Rectangle2D(6, 6, 20, 30)); // establece la ubicacion en la imagen general
		render.relocate(coordenada.getX() - ancho / 2, 0);
		render.setX(coordenada.getX());
		render.setY(coordenada.getY());

		collider = new Rectangle(coordenada.getX() - colliderWidth / 2, coordenada.getY() - colliderHeight / 2,
				colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
	}

	private void resetViewport() {
		render.setViewport(new Rectangle2D(6, 6, 20, 30));
		render.setX(coordenada.getX());
		render.setY(coordenada.getY());
	}

	private void setearValoresIniciales() {
		this.largo = 40;
		this.ancho = 30;
		this.velocidadActual = 0;
		this.coordenada = new Punto(coordenadaXoriginal, coordenada.getY());
	}

	public void reaparecer() {
		setearValoresIniciales();
		jugabilidadBloqueada = false;
		this.reapareci = true;
		this.explote = false;

		this.resetViewport();
	}

	public void obtenerEscudo() {
		this.tengoEscudo = true;
	}

	public void perderEscudo() {
		this.tengoEscudo = false;
	}

	@Override
	public void explotar() {
		jugabilidadBloqueada = true;
		this.explote = true;

		// La imagen de la explosion esta en el (70,70), aca esta en la explosion
		// Esta es la posicion de las explosiones y el auto destruido
		animacionExplosion = new IndividualSpriteAnimation(render, Duration.millis(3000), 5, 5, 45, 70, 3, 20, 30);
		animacionExplosion.setCycleCount(1);
		animacionExplosion.setOnFinished(e -> this.reaparecer());
		animacionExplosion.play();
	}

	@Override
	public void choqueConObjeto(ObjetoDelMapa objeto) {
		objeto.choqueConAutoJugador(this);
	}

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		// System.out.println(this.getNombre() + " y " + auto.getNombre() + " han
		// chocado");
		if (!this.getEscudo()) {
			if (!auto.getEscudo()) {
				auto.perderElControl();
				this.perderElControl();
			} else {
				this.explotar();
				auto.perderEscudo();
			}
		} else {
			if (!auto.getEscudo()) {
				auto.explotar();
			} else {
				this.explotar();
				auto.explotar();
				auto.perderEscudo();
			}

			this.perderEscudo();
		}
	}

	@Override
	public void choqueConAutoObstaculo(AutoObstaculo auto) {
		// System.out.println(this.getNombre() + " y " + auto.getNombre() + " han
		// chocado");
		if (!this.getEscudo()) {
			auto.perderElControl();
			this.perderElControl();
		} else {
			auto.explotar();
			this.perderEscudo();
		}
	}

	@Override
	public void choqueConCamion(CamionObstaculo camion) {
		// System.out.println(this.getNombre() + " y " + camion.getNombre() + " han
		// chocado");
		if (!this.getEscudo()) {
			this.explotar();
		} else {
			camion.explotar();
			this.perderEscudo();
		}
	}

	public void setVelocidad(double velocidad) {
		this.velocidadActual = velocidad;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	public Jugador getJugador() {
		return this.jugador;
	}

	/// METODOS USADOS EN LOS TESTS

	public boolean getReapareci() {
		return this.reapareci;
	}

	public boolean getJugabilidadBloqueada() {
		return this.jugabilidadBloqueada;
	}

	public boolean getEscudo() {
		return this.tengoEscudo;
	}
	///
	
	@Override
	public Shape getCollider() {
		return collider;
	}

	@Override
	public void collide(Collideable collideable) {
		// TODO Auto-generated method stub

	}

	@Override
	public Node getRender() {
		return render;
	}

	public void irDerecha() {
		if (!this.jugabilidadBloqueada)
			render.setX(render.getX() + 1);
	}

	public void irIzquierda() {
		if (!this.jugabilidadBloqueada)
			render.setX(render.getX() - 1);
	}

	@Override
	public void update(double deltaTime) {
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
