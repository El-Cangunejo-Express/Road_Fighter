package RoadFighter.Objetos;

import RoadFighter.Interfaces.Renderable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SeñalEscudo extends GameObject implements Renderable{
	
	private int imageSpacing = 0;
	private int width = 30;
	private int height = 30;		
	private double x = 56;
	private double y = 100;	
	private ImageView render;

	
	public SeñalEscudo() {		
		
		Image spriteImages = new Image("file:src/main/resources/img/escudo.png", width, height, false, false);

		render = new ImageView(spriteImages);
		resetViewport();

		// Seteo ancla en la mitad del eje X y en la mitad del eje Y
		render.relocate(-width / 2, -height / 2);

		render.setY(y);
		render.setX(x);			
	}
	
	private void resetViewport() {
		render.setViewport(new Rectangle2D(imageSpacing, imageSpacing, width, height));
	}
	
	
	@Override
	public Node getRender() {
		return render;
	}

}
