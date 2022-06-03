package RoadFighter;

import java.util.ArrayList;
import java.util.List;
import RoadFighter.Interfaces.Colisionable;
import RoadFighter.Interfaces.Colisionador;
import RoadFighter.Interfaces.Renderable;
import RoadFighter.Interfaces.Updatable;
import RoadFighter.Objetos.AutoJugador;
import RoadFighter.Objetos.Carretera;
import RoadFighter.Objetos.GameObject;
import RoadFighter.Objetos.Jugador;
import RoadFighter.Objetos.Meta;
import RoadFighter.Objetos.ObjetoDeMapa;
import RoadFighter.Objetos.PowerUp;
import RoadFighter.Objetos.Score;
import RoadFighter.Objetos.SeñalAcelerador;
import RoadFighter.Objetos.SeñalEscudo;
import RoadFighter.Objetos.Vehiculo;
import RoadFighter.Objetos.Victoria;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import RoadFighter.utils.AudioResources;
import RoadFighter.utils.Titulo;

public class Partida extends Application {

	private static final double NANOS_IN_SECOND_D = 1_000_000_000.0;

	AudioResources audios = new AudioResources();
	Carretera carretera = new Carretera();
	Victoria victoria = new Victoria();
	Titulo menu = new Titulo();
	SeñalEscudo señalEscudo  = new SeñalEscudo();
	SeñalAcelerador señalAcelerador  = new SeñalAcelerador();

	Jugador jugador1 = new Jugador(1);	
	Score score1 = jugador1.getScore();
	AutoJugador autoJugador1 = jugador1.getAutoJugador();
		
	Group root = new Group();
	Scene currentScene = new Scene(root);

	private List<GameObject> allGameObjects = carretera.getObjetos();
	private List<Updatable> updatables = new ArrayList<Updatable>();
	private List<Renderable> renderables = new ArrayList<Renderable>();
	private List<Colisionador> colisionadores = new ArrayList<Colisionador>();
	private List<Colisionable> colisionables = new ArrayList<Colisionable>();

	long previousNanoFrame;
	boolean primeraVez = false;

	@Override
	public void start(Stage stage) {

		add();

		addUpdateEachFrameTimer();

		stage.setScene(currentScene);
		stage.getIcons().add(new Image("file:src/main/resources/ico/logo.png"));
		stage.setTitle("RoadFighter");
		stage.show();

		root.getChildren().add(menu.getRender());		
		menu.activarMenu();	
		
		addInputEvents();		
	}

	private void add() {		

		allGameObjects.add(0,carretera);		
		allGameObjects.add(score1);
		allGameObjects.add(señalEscudo);
		allGameObjects.add(señalAcelerador);
		allGameObjects.add(audios);		
		allGameObjects.add(autoJugador1);
		
		for (GameObject gameObject : allGameObjects) {

			if (Updatable.class.isAssignableFrom(gameObject.getClass())) {
				Updatable updatableGameObject = (Updatable) gameObject;
				updatables.add(updatableGameObject);
			}

			if (Renderable.class.isAssignableFrom(gameObject.getClass())) {
				Renderable renderableGameObject = (Renderable) gameObject;
				renderables.add(renderableGameObject);
				root.getChildren().add(renderableGameObject.getRender());
			}

			if (Colisionador.class.isAssignableFrom(gameObject.getClass())) {
				Colisionador collidatorGameObject = (Colisionador) gameObject;
				colisionadores.add(collidatorGameObject);
			} else if (Colisionable.class.isAssignableFrom(gameObject.getClass())) {
				Colisionable collideableGameObject = (Colisionable) gameObject;
				colisionables.add(collideableGameObject);
			}
		}						
	}

	private void remove(GameObject gameObject) {

		if (Updatable.class.isAssignableFrom(gameObject.getClass())) {
			Updatable updatableGameObject = (Updatable) gameObject;
			updatables.remove(updatableGameObject);
		}

		if (Renderable.class.isAssignableFrom(gameObject.getClass())) {
			Renderable renderableGameObject = (Renderable) gameObject;
			renderables.remove(renderableGameObject);
			root.getChildren().remove(renderableGameObject.getRender());
		}

		if (Colisionador.class.isAssignableFrom(gameObject.getClass())) {
			Colisionador collidatorGameObject = (Colisionador) gameObject;
			colisionadores.remove(collidatorGameObject);
		} else if (Colisionable.class.isAssignableFrom(gameObject.getClass())) {
			Colisionable collideableGameObject = (Colisionable) gameObject;
			colisionables.remove(collideableGameObject);
		}

		allGameObjects.remove(gameObject);
	}

	private void addInputEvents() {
		currentScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case RIGHT:
				case D:
					autoJugador1.setDirectionRight(true);
					break;

				case LEFT:
				case A:
					autoJugador1.setDirectionLeft(true);
					break;

				case UP:
				case W:
					autoJugador1.setAcelerando(true);
					break;
					
				case ADD:				
					audios.subirVolumen();
					break;

				case SUBTRACT:				
					audios.bajarVolumen();
					break;
					
				case H:				
					audios.playYametekudasai();
					break;
					
				case SPACE:		
					if(	root.getChildren().contains(menu.getRender()) ) {								
						root.getChildren().remove(menu.getRender());
						
						audios.playAudioIntro();	
						primeraVez = true;
					}													
					break;

				default:
					break;
				}
			}
		});

		currentScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case RIGHT:
				case D:
					autoJugador1.setDirectionRight(false);
					break;

				case LEFT:
				case A:
					autoJugador1.setDirectionLeft(false);
					break;

				case UP:
				case W:
					autoJugador1.setAcelerando(false);
					break;

				default:
					break;
				}
			}
		});			
	}

	private void addUpdateEachFrameTimer() {
		previousNanoFrame = System.nanoTime();
		AnimationTimer gamerTimer = new AnimationTimer() {

			@Override
			public void handle(long currentNano) {
				update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D);
				previousNanoFrame = currentNano;
			}
		};
		gamerTimer.start();
	}

	private void update(double deltaTime) {	
		
		double speedJugador;
		
		if(!audios.getAudioIntro().isPlaying() && primeraVez) {
			for (int i = 0; i < colisionadores.size(); i++) {
				Vehiculo vehiculo = (Vehiculo) colisionadores.get(i);
				vehiculo.desbloquearMovilidad();							
			}
			audios.playAudioRun();	
			primeraVez = false;
		}		
				
		speedJugador = autoJugador1.update(deltaTime, 0);
		score1.update((autoJugador1.getYActual() - 650) * -1, carretera.getDistanciaTotal());
		audios.update();

		if(!autoJugador1.getTengoEscudo()) {
			if(	root.getChildren().contains(señalEscudo.getRender()) ) {								
				root.getChildren().remove(señalEscudo.getRender());
			}			
		}else {
			if( !root.getChildren().contains(señalEscudo.getRender()) ) {
				root.getChildren().add(señalEscudo.getRender());
			}
		}
		
		if(!autoJugador1.getTengoAcelerador()) {
			if(	root.getChildren().contains(señalAcelerador.getRender()) ) {								
				root.getChildren().remove(señalAcelerador.getRender());
			}			
		}else {
			if( !root.getChildren().contains(señalAcelerador.getRender()) ) {
				root.getChildren().add(señalAcelerador.getRender());
			}
		}
		
		for (Updatable updatable : updatables) {
			if (!AutoJugador.class.isAssignableFrom(updatable.getClass())) {
				updatable.update(deltaTime, speedJugador);
			}
		}
		checkColliders();
		
		if(autoJugador1.getFueraDeJuego()) {
			audios.stopAudioRun();
		}
		
		if(autoJugador1.getReapareci()) {
			audios.playAudioRun();
		}
		
		removesColisionadores();

	}

	private void checkColliders() {

		for (int i = 0; i < colisionadores.size(); i++) {
			Colisionador colisionador = colisionadores.get(i);

			for (int j = i + 1; j < colisionadores.size(); j++) {
				Colisionador otroColisionador = colisionadores.get(j);

				if (((ObjetoDeMapa) colisionador).hayContactoCon((ObjetoDeMapa) otroColisionador)) {
					colisionador.colisionasteConObjeto(otroColisionador);
					
					if( ((Vehiculo)colisionador).getDerrapando() || ((Vehiculo)otroColisionador).getDerrapando() ) {
						audios.playAudioDerrape();
					}else if(((Vehiculo)colisionador).getFueraDeJuego() || ((Vehiculo)otroColisionador).getFueraDeJuego()){
						audios.playAudioExplosion();
					}
				}
			}

			for (int j = 0; j < colisionables.size(); j++) {
				Colisionable colisionable = colisionables.get(j);

				if (((ObjetoDeMapa) colisionador).hayContactoCon((ObjetoDeMapa) colisionable)) {
					colisionador.colisionasteConObjeto(colisionable);

					ObjetoDeMapa objetoDeMapa = (ObjetoDeMapa) colisionable;

					if (objetoDeMapa.getEliminar()) {
						remove(objetoDeMapa);
					}
					
					if (PowerUp.class.isAssignableFrom(colisionable.getClass())
							&& AutoJugador.class.isAssignableFrom(colisionador.getClass())) {
						audios.playAudioBonus();
					}
					
					if( ((Vehiculo)colisionador).getFueraDeJuego() ) {
						audios.playAudioExplosion();
					}else if( ((Vehiculo)colisionador).getDerrapando() ) {
						audios.playAudioDerrape();
					}
					
					if (Meta.class.isAssignableFrom(colisionable.getClass())
							&& AutoJugador.class.isAssignableFrom(colisionador.getClass())) {
						
						root.getChildren().add(victoria.getRender());		
						victoria.finDeCarrera();											
						audios.playAudioClipEnd();
						autoJugador1.setReapareci(false);

						for (Colisionador objetoColisionador : colisionadores) {
							Vehiculo vehiculo = (Vehiculo) objetoColisionador;
														
							if( AutoJugador.class.isAssignableFrom(vehiculo.getClass()) ){
								vehiculo.bloquearMovilidad();
							}else {
								vehiculo.explosion();
							}							
						}
					}
				}
			}
		}
	}

	private void removesColisionadores() {

		for (int i = 0; i < colisionadores.size(); i++) {
			ObjetoDeMapa objetoDeMapa = (ObjetoDeMapa) colisionadores.get(i);

			if (objetoDeMapa.getEliminar()) {
				remove(objetoDeMapa);
				i--;
			}
		}		
	}

	public static void main(String[] args) {
		launch();
	}
}