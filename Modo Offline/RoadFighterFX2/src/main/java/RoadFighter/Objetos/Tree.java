package RoadFighter.Objetos;

import RoadFighter.Interfaces.Renderable;
import RoadFighter.Interfaces.Updatable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tree extends GameObject implements Renderable, Updatable{
	private int imageSpacing = 0;
	private int width = 60;
	private int height = 720*30;
	private double maxSpeed = 0;
	private double speed = 0;	
	private double x;
	private double y;	
	private ImageView render;	
	
	public Tree(int x, int y) {	
		
		this.y = y;
		this.x = x;
			
		Image spriteImages = new Image("file:src/main/resources/img/tree.png", width, height, false, false);

		render = new ImageView(spriteImages);
		resetViewport();

		// Seteo ancla en la mitad del eje X y en el final del eje Y
		render.relocate(-width / 2, -height);

		render.setX(this.x);
		setY(this.y);			
	}
	
	private void resetViewport() {
		render.setViewport(new Rectangle2D(imageSpacing, imageSpacing, width, height));
	}	
	
	private void setY(double y) {
		this.y = y;
		render.setY(y);
	}	

	@Override
	public double update(double deltaTime, double speedJugador) {
		speed = maxSpeed - speedJugador;
		setY(y - speed * deltaTime);
		return 0;
	}

	@Override
	public Node getRender() {
		return render;
	}	
}