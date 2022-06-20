package RoadFighter.Objetos;

import RoadFighter.Interfaces.Colisionable;
import RoadFighter.Interfaces.Renderable;
import RoadFighter.Interfaces.Updatable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class ObjetoDeMapa extends GameObject implements Renderable, Updatable, Colisionable {

	protected int imageSpacing;
	protected int width;
	protected int height;
	protected double maxSpeed;
	protected double speed;
	protected ImageView render;
	protected double x = 0;
	protected double y = 0;
	protected boolean tengoQueDesaparecer = false;

	protected void resetViewport() {
		render.setViewport(new Rectangle2D(imageSpacing, imageSpacing, width, height));
	}

	protected void setY(double y) {
		this.y = y;
		render.setY(y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean getEliminar() {
		return this.tengoQueDesaparecer;
	}

	public void desaparecer() {
		tengoQueDesaparecer = true;
	}

	public boolean hayContactoCon(ObjetoDeMapa otro) {
		boolean hayColision = false;

		double distX = Math.abs(this.getX() - otro.getX());
		double distY = Math.abs(this.getY() - otro.getY());

		if (GuardarrailDerecho.class.isAssignableFrom(this.getClass())
				|| GuardarrailIzquierdo.class.isAssignableFrom(this.getClass())
				|| GuardarrailDerecho.class.isAssignableFrom(otro.getClass())
				|| GuardarrailIzquierdo.class.isAssignableFrom(otro.getClass())) {

			if (distX <= this.width / 2 + otro.width / 2) {
				hayColision = true;
			}

		} else if ((distX <= this.width / 2 + otro.width / 2) && (distY <= this.height / 2 + otro.height / 2)) {
			hayColision = true;
		}
		return hayColision;
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public double update(double deltaTime, double speedJugador) {
		speed = maxSpeed - speedJugador;
		setY(y - speed * deltaTime);
		return 0;
	}

}
