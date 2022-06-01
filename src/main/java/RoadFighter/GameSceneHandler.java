package RoadFighter;

import java.util.List;

import RoadFighter.interfaces.Collidator;
import RoadFighter.interfaces.Collideable;
import RoadFighter.utils.GameObjectBuilder;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class GameSceneHandler extends SceneHandler {

	private AutoJugador player;
//	private Background background;
	private Carretera background;
//	private Ground ground;
//	private PipeBuilder pipeBuilder;
//	private Score score;
//	private FpsInfo fpsInfo;
//	private Radio radio;

	// TODO pause
	// boolean paused = false;
	boolean started = false;
	boolean ended = false;

	// prueba
	boolean acelera = false;

	public GameSceneHandler(RoadFighterGame g) {
		super(g);
	}

	protected void prepareScene() {
		Group rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.BLACK);
	}

	protected void defineEventHandlers() {
		mouseEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					makeAction();
				}
			}
		};

		keyEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {

				case W:
				case UP:
					acelera = true;
					makeAction();
					break;
					
				case A:
				case LEFT:
					player.irIzquierda();
					break;

				case D:
				case RIGHT:
					player.irDerecha();
					break;
					
				case R:
					restart();
					break;

				case Q:
				case ESCAPE:
//					g.startMenu();
					player.explotar();
					break;

				default:
					acelera = false;
					break;
				}
			}
		};

		keyEventRelease = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {

				case W:
				case UP:
					acelera = false;
					break;

				case A:
				case LEFT:
					break;

				case D:
				case RIGHT:
					break;

				case R:
					restart();
					break;

				case Q:
				case ESCAPE:
//					g.startMenu();
					break;

				default:
					break;
				}
			}
		};
	}

	public void load(boolean fullStart) {
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);

//		score = new Score();
		player = new AutoJugador(new Punto(Config.playerCenter, Config.baseHeight / 2), new Jugador("Elber"));
		//background = new Background();
		background = new Carretera(300, 200);
//		ground = new Ground();
//		pipeBuilder = new PipeBuilder();
//		fpsInfo = new FpsInfo(fps);
//		radio = new Radio(Config.playerCenter, Config.baseHeight / 2, player);

		// Add to builder
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(rootGroup);
		gameOB.add(background, player);// , radio, player, ground, score, fpsInfo, pipeBuilder);

		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}

	public void restart() {
		cleanData();
		load(false);
	}

	private void cleanData() {
		GameObjectBuilder.getInstance().removeAll();
		ended = false;
		started = false;
		Config.baseSpeed = 250;
	}

	private void makeAction() {
		if (!started) {
			started = true;
//			pipeBuilder.startBuilding(2 * NANOS_IN_SECOND);
//			radio.start();
		}
//		player.push();
	}

	public void update(double delta) {
		super.update(delta);
		background.setAcelera(acelera);
		checkColliders();

		if (!ended) {
//			if (player.isDead()) {
//				ended = true;
//				pipeBuilder.stopBuilding();
//				Config.baseSpeed = 0;
//
//				// Improve
//				TranslateTransition tt = new TranslateTransition(Duration.millis(50), scene.getRoot());
//				tt.setFromX(-20f);
//				tt.setByX(20f);
//				tt.setCycleCount(6);
//				tt.setAutoReverse(true);
//				tt.playFromStart();
//				tt.setOnFinished(event -> {
//					scene.getRoot().setTranslateX(0);
//				});
//			}
		}
	}

	private void checkColliders() {
		List<Collidator> collidators = GameObjectBuilder.getInstance().getCollidators();
		List<Collideable> collideables = GameObjectBuilder.getInstance().getCollideables();

		for (int i = 0; i < collidators.size(); i++) {
			Collidator collidator = collidators.get(i);

			for (int j = i + 1; j < collidators.size(); j++) {
				Collidator otherCollidator = collidators.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), otherCollidator.getCollider());

				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.collide(otherCollidator);
					otherCollidator.collide(collidator);
				}
			}

			for (int j = 0; j < collideables.size(); j++) {
				Collideable collideable = collideables.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), collideable.getCollider());

				// TODO test times
				// XXX Si el substract es distinto???
				// Check intersects
				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.collide(collideable);
				} else {
					// Check contains
					Bounds collideableBounds = collideable.getCollider().getBoundsInLocal();
					Bounds collidatorBounds = collidator.getCollider().getBoundsInLocal();

					if (collideableBounds.contains(collidatorBounds.getCenterX(), collidatorBounds.getCenterY())) {
						collidator.collide(collideable);
					}
				}
			}
		}
	}

	public void unload() {
		cleanData();
		super.unload();
	}

}
