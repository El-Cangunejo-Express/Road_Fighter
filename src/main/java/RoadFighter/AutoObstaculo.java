package RoadFighter;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AutoObstaculo extends Vehiculo {

	public AutoObstaculo(Punto coordenada) {
		this.coordenada = coordenada;
		this.largo = 40;
		this.ancho = 30;
		this.nombre = "auto-bot";
		this.tieneMovimiento = true;
		this.velocidadActual = 0;
		this.velocidadMaxima = 80;

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

		switch ((int) Math.floor(Math.random() * 12 + 1)) {
		case 1:
			render.setViewport(new Rectangle2D(6, 115, 20, 40)); // rojo 1
			break;
		case 2:
			render.setViewport(new Rectangle2D(30, 115, 20, 40)); // rojo 2
			break;
		case 3:
			render.setViewport(new Rectangle2D(50, 115, 20, 40)); // rojo 3
			break;
		case 4:
			render.setViewport(new Rectangle2D(80, 115, 20, 40)); // rojo 4
			break;
		case 5:
			render.setViewport(new Rectangle2D(6, 150, 20, 40)); // azul 1
			break;
		case 6:
			render.setViewport(new Rectangle2D(30, 150, 20, 40)); // azul 2
			break;
		case 7:
			render.setViewport(new Rectangle2D(50, 150, 20, 40)); // azul 3
			break;
		case 8:
			render.setViewport(new Rectangle2D(80, 150, 20, 40)); // azul 4
			break;
		case 9:
			render.setViewport(new Rectangle2D(6, 190, 20, 40)); // amarillo 1
			break;
		case 10:
			render.setViewport(new Rectangle2D(30, 190, 20, 40)); // amarillo 2
			break;
		case 11:
			render.setViewport(new Rectangle2D(50, 190, 20, 40)); // amarillo 3
			break;
		case 12:
			render.setViewport(new Rectangle2D(80, 190, 20, 40)); // amarillo 4
			break;
		default:
			render.setViewport(new Rectangle2D(6, 115, 20, 40)); // rojo 1
		}
	}

	@Override
	public void choqueConObjeto(ObjetoDelMapa objeto) {
		objeto.choqueConAutoObstaculo(this);
	}

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		if (!auto.getEscudo()) {
			auto.perderElControl();
			this.perderElControl();
		} else {
			this.explotar();
			this.tengoQueDesaparecer = true;
			auto.perderEscudo();
		}
	}

	@Override
	public void choqueConAutoObstaculo(AutoObstaculo auto) {
		auto.perderElControl();
		this.perderElControl();

	}

	@Override
	public void choqueConCamion(CamionObstaculo camion) {
		this.explotar();
		this.tengoQueDesaparecer = true;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
