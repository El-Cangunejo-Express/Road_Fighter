package RoadFighter.utils;

import RoadFighter.SpriteAnimation;
import RoadFighter.Interfaces.Renderable;
import javafx.animation.Animation;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Titulo implements Renderable{
	
	private int width = 720;
	private int height = 720;
	private ImageView render;
	private SpriteAnimation menu;

	
	public Titulo() {			
		Image spriteImages = new Image("file:src/main/resources/img/titulo.png", width*2, height, false, false);
		render = new ImageView(spriteImages);	
		
		menu = new SpriteAnimation(render, Duration.millis(1000), 2, 1000, 0,0,0, width, height);
		menu.setCycleCount(Animation.INDEFINITE);
	}	
	
	@Override
	public Node getRender() {
		return render;
	}
	
	public void activarMenu() {
		menu.play();
	}
}