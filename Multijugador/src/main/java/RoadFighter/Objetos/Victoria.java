package RoadFighter.Objetos;

import RoadFighter.SpriteAnimation;
import RoadFighter.Interfaces.Renderable;
import javafx.animation.Animation;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Victoria implements Renderable{
	
	private int width = 720;
	private int height = 720;
	private ImageView render;
	private SpriteAnimation finCarreraAnimation;

	
	public Victoria() {			
		Image spriteImages = new Image("file:src/main/resources/img/victoria.png", width*2, height, false, false);
		render = new ImageView(spriteImages);	
		
		finCarreraAnimation = new SpriteAnimation(render, Duration.millis(500), 2, 100, 0,0,0, width, height);
		finCarreraAnimation.setCycleCount(Animation.INDEFINITE);
	}	
	
	@Override
	public Node getRender() {
		return render;
	}
	
	public void finDeCarrera() {
		finCarreraAnimation.play();
	}
}