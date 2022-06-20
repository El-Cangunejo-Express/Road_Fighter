package RoadFighter.Objetos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AceleradorExtremo extends PowerUp {

	public AceleradorExtremo(int x, int y) {

		super(x, y);
		Image spriteImages = new Image("file:src/main/resources/img/acelerador.png", width, height, false, false);

		render = new ImageView(spriteImages);
		resetViewport();

		// Seteo ancla en la mitad del eje X y en la mitad del eje Y
		render.relocate(-width / 2, -height / 2);

		render.setX(x);
		setY(y);
	}

	@Override
	public void colisionasteConAutoJugador(AutoJugador auto) {
		this.desaparecer();
		auto.bloquearAceleracion();
		auto.setSpeed(2000);
	}

}
