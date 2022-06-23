package RoadFighter.utils;

import RoadFighter.Interfaces.Renderable;
import RoadFighter.Objetos.GameObject;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class AudioResources extends GameObject implements Renderable{
	
	private	double volumen = 100;	
	private Text volumenText;		
	private VBox render;
	
	private AudioClip audioIntro = new AudioClip("file:src/main/resources/snd/intro.wav");	
	private AudioClip audioRun = new AudioClip("file:src/main/resources/snd/run.wav");
	private AudioClip audioExplosion = new AudioClip("file:src/main/resources/snd/crash.wav");
	private AudioClip audioDerrape = new AudioClip("file:src/main/resources/snd/drift.wav");
	private AudioClip audioBonus = new AudioClip("file:src/main/resources/snd/bonus.wav");
	private AudioClip end = new AudioClip("file:src/main/resources/snd/end.wav");	
	private AudioClip audioYametekudasai = new AudioClip("file:src/main/resources/snd/yametekudasai.wav");
	
	
	public AudioResources() {
		volumenText = new Text("Volumen:" + (int)volumen);
		
		render = new VBox(volumenText);
		render.setSpacing(5);
		render.setAlignment(Pos.TOP_CENTER);
		render.setTranslateY(10);	
		render.setTranslateX(610);
		render.setPrefWidth(110);	
		
		
		volumenText.setTextAlignment(TextAlignment.CENTER);		
		volumenText.setFill(Color.BLACK);
		
		Font font = Font.loadFont(ClassLoader.getSystemResource("font/ka1.ttf").toString(), 10);		
		
		volumenText.setFont(font);		
		
		DropShadow ds = new DropShadow();
		ds.setColor(Color.WHITE);
		volumenText.setEffect(ds);
	}
	
	private void setVolumen() {
		audioIntro.setVolume(volumen/100);
		audioExplosion.setVolume(volumen/100);
		audioDerrape.setVolume(volumen/100);
		audioRun.setVolume(volumen/100);
		audioBonus.setVolume(volumen/100);		
		end.setVolume(volumen/100);
		audioYametekudasai.setVolume(volumen/100);
	}				
	
	public void playAudioIntro() {		
			audioIntro.play();	
	}
	
	public AudioClip getAudioIntro() {
		return audioIntro;
	}
	
	public void playAudioRun() {
		if(!audioRun.isPlaying() && !end.isPlaying()) {
			audioRun.play();
		}			
	}
	
	public void stopAudioRun() {
		if(audioRun.isPlaying()) {
			audioRun.stop();
		}			
	}
	
	public void playAudioBonus() {
		audioBonus.play();					
	}	
		
	public void playAudioClipEnd() {
		
		if(audioRun.isPlaying()) {
			audioRun.stop();
		}	
		if(!end.isPlaying()) {
			end.play();
		}			
	}
	
	public void playAudioExplosion() {
		
		if(audioDerrape.isPlaying()) {
			audioDerrape.stop();
		}	
				
		if(!end.isPlaying() && !audioExplosion.isPlaying()) {
			audioExplosion.play();
		}		
	}
	
	public void playAudioDerrape() {
		if(!end.isPlaying() && !audioDerrape.isPlaying()) {
			audioDerrape.play();
		}		
	}
	
	public void playYametekudasai() {
		if(!audioYametekudasai.isPlaying()) {
			audioYametekudasai.play();
		}		
	}
	
	public void subirVolumen () {
		volumen += 0.50;
		if(volumen > 100) {
			volumen = 100;
		}
		setVolumen();		
	}
	
	public void bajarVolumen () {
		volumen -= 0.50;
		if(volumen < 0) {
			volumen = 0;
		}
		setVolumen();		
	}
	
	public void update() {		
		volumenText.setText("Volumen: " + (int)volumen);			
	}
	
	@Override
	public Node getRender() {
		return render;
	}
}
