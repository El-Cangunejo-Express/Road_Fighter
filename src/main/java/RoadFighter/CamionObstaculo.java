package RoadFighter;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CamionObstaculo extends Vehiculo {

	public CamionObstaculo(Punto coordenada) {
		this.coordenada = coordenada;
		this.largo = 80;
		this.ancho = 30;
		this.nombre = "camion-bot";
		this.tieneMovimiento = true;
		this.velocidadActual = 0;
		this.velocidadMaxima = 60;

		// javaFx
		iniImagen();

		render.relocate(coordenada.getX() - ancho / 2, 0);
		render.setX(coordenada.getX());
		render.setY(coordenada.getY());

		collider = new Rectangle(coordenada.getX() - colliderWidth / 2, coordenada.getY() - colliderHeight / 2,
				colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
	}

	private void iniImagen() {
		render = new ImageView(imagenSprites);
		render.setViewport(new Rectangle2D(100, 150, 30, 70)); // camion
	}

	@Override
	public void choqueConObjeto(ObjetoDelMapa objeto) {
		objeto.choqueConCamion(this);
	}

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		// System.out.println(auto.getNombre() + " ha chocado con " + this.getNombre());
		if (!auto.getEscudo()) {
			auto.explotar();
		} else {
			this.explotar();
			this.tengoQueDesaparecer = true;
			auto.perderEscudo();
		}
	}

	@Override
	public void choqueConAutoObstaculo(AutoObstaculo auto) {
		auto.explotar();
		auto.tengoQueDesaparecer = true;
	}

	@Override
	public void choqueConCamion(CamionObstaculo camion) {
		camion.explotar();
		camion.tengoQueDesaparecer = true;
		this.explotar();
		this.tengoQueDesaparecer = true;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
