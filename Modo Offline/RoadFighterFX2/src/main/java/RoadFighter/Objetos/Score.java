package RoadFighter.Objetos;

import RoadFighter.Interfaces.Renderable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Score extends GameObject implements Renderable{
	
	private int y = 15;
	private int score = 0;
	private Text scoreText;	
	private Text puntaje;	
	private VBox render;
	private double maxScore = 10000;
	
	public Score() {
		
		scoreText = new Text("SCORE");
		puntaje = new Text("" + score);
				

		render = new VBox(scoreText, puntaje);
		render.setSpacing(5);
		render.setAlignment(Pos.TOP_CENTER);
		render.setTranslateY(y);		
		render.setPrefWidth(110);		

		scoreText.setTextAlignment(TextAlignment.CENTER);
		puntaje.setTextAlignment(TextAlignment.CENTER);
		scoreText.setFill(Color.WHITE);
		puntaje.setFill(Color.WHITE);
		
		Font font1 = Font.loadFont(ClassLoader.getSystemResource("font/ka1.ttf").toString(), 20);
		Font font2 = Font.loadFont(ClassLoader.getSystemResource("font/ka1.ttf").toString(), 15);
		
		scoreText.setFont(font1);
		puntaje.setFont(font2);		
		
		DropShadow ds = new DropShadow();
		ds.setColor(Color.BLACK);
		scoreText.setEffect(ds);
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int valor) {
		score = valor;
	}	
	
	@Override
	public Node getRender() {
		return render;
	}	
		
	public void update(double distanciaRecorrida, double distanciaTotal) {
					
		score = (int)(maxScore * (distanciaRecorrida/distanciaTotal));
				
		if (score > maxScore) {
			score = (int)maxScore;
		}		
				
		puntaje.setText("" + score);		
	}
}
