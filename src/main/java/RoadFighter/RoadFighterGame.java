package RoadFighter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RoadFighterGame extends Application {
	// private GameSceneHandler gameSceneHandler;
	private Partida gameSceneHandler;
	private Stage stage;

	Scene currentScene;
	long previousNanoFrame;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;

//		menuSceneHandler = new MenuSceneHandler(this);
//		Scene scene = menuSceneHandler.getScene();
		// gameSceneHandler = new GameSceneHandler(this);
		gameSceneHandler = new Partida(this);
		gameSceneHandler.agregarJugador(new Jugador("Elber"));
		gameSceneHandler.agregarJugador(new Jugador("Aldo"));
		//gameSceneHandler.insertarJugadoresEnCarretera();
		
		Scene scene = gameSceneHandler.getScene();
		
		stage.setScene(scene);
//		menuSceneHandler.load();

		// XXX patron state para controlar paso de escenas?

		// Scale
		// TODO scale and fill to maintain proportion (also center)
		// scale = new Scale();
		// dinamico, cada vez que cambio el tamaÃ±o de ventana
		// scale.setX(scene.getWidth() / WIDTH);
		// scale.setY(scene.getHeight() / HEIGHT);
		// images.getTransforms().add(scale);

		stage.getIcons().add(new Image("file:src/main/resources/Ico/Cangunejo.jpg"));
		stage.setTitle("Road Fighter Game | Programacion Avanzada");
		
		gameSceneHandler.iniciar();
		gameSceneHandler.load(true);
		stage.show();
	}

	public void startGame() {
		// gameSceneHandler = new GameSceneHandler(this);
		gameSceneHandler = new Partida(this);
		Scene scene = gameSceneHandler.getScene();
		stage.setScene(scene);
		gameSceneHandler.load(true);
	}

}
