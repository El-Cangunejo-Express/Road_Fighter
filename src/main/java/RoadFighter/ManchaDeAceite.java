package RoadFighter;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class ManchaDeAceite extends ObjetoDelMapa {

	public ManchaDeAceite(Punto coordenada) {
		this.coordenada = coordenada;
		this.largo = 20;
		this.ancho = 20;
		this.nombre = "Mancha de Aceite";
		this.tieneMovimiento = false;

		this.iniImagen();
	}

	private void iniImagen() {
		render = new ImageView(imagenSprites);
		render.setViewport(new Rectangle2D(175, 150, 30, 43)); // aceite
	}

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		// System.out.println(auto.getNombre() + " paso sobre " + this.getNombre());
		if (!auto.getEscudo()) {
			auto.perderElControl();
		} else {
			this.explotar();
			this.tengoQueDesaparecer = true;
			auto.perderEscudo();
		}
	}

	@Override
	public void choqueConAutoObstaculo(AutoObstaculo auto) {
		// System.out.println(auto.getNombre() + " paso sobre " + this.getNombre());
		auto.perderElControl();
	}

	@Override
	public void choqueConCamion(CamionObstaculo camion) {
		// System.out.println(camion.getNombre() + " paso sobre " + this.getNombre());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
