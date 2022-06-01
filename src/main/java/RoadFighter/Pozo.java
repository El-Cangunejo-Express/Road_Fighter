package RoadFighter;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Pozo extends ObjetoDelMapa {

	public Pozo(Punto coordenada) {
		this.coordenada = coordenada;
		this.largo = 20;
		this.ancho = 20;
		this.nombre = "pozo";
		this.tieneMovimiento = false;
		
		this.iniImagen();
	}

	private void iniImagen() {
		render = new ImageView(imagenSprites);
		render.setViewport(new Rectangle2D(175, 200, 30, 30)); // Pozo
	}

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		// System.out.println(auto.getNombre() + " ha chocado con " + this.getNombre());
		if (!auto.getEscudo()) {
			auto.explotar();
		} else {
			auto.perderEscudo();
		}

		this.explotar();
		this.tengoQueDesaparecer = true;
	}

	@Override
	public void choqueConAutoObstaculo(AutoObstaculo auto) {
		// System.out.println(auto.getNombre() + " ha chocado con " + this.getNombre());
		this.explotar();
		this.tengoQueDesaparecer = true;
		auto.explotar();
		auto.tengoQueDesaparecer = true;
	}

	@Override
	public void choqueConCamion(CamionObstaculo camion) {
		// System.out.println(camion.getNombre() + " ha chocado con " +
		// this.getNombre());
		this.explotar();
		this.tengoQueDesaparecer = true;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
