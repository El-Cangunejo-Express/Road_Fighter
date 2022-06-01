package RoadFighter;

import java.util.ArrayList;

import RoadFighter.utils.GameObjectBuilder;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Partida extends SceneHandler {
	private Carretera carretera;
	private int jugadoresMinimos = 2;
	private int jugadoresMaximos = 5;
	private int jugadoresActuales = 0;
	private Jugador ganador = null;
	private ArrayList<Jugador> jugadores;
	private ArrayList<AutoJugador> autosJugadores;

	// test
	boolean acelera = false;

	public Partida(RoadFighterGame g) {
		super(g);
		this.carretera = carreteraPredefinida();
		this.jugadores = new ArrayList<Jugador>();
		this.autosJugadores = new ArrayList<AutoJugador>();
	}

	private Carretera carreteraPredefinida() {
		Carretera carretera = new Carretera(300, 200);

		// AGREGAR TODOS LOS OBJETOS QUE SE DESEE A LA PISTA
		carretera.agregarObjeto(new Pozo(new Punto(Config.playerCenter - 60, Config.baseHeight - 78)));
		return carretera;
	}

	private void habilitarDesabilitarJugabilidad() {
		for (int i = 0; i < jugadoresActuales; i++) {
			autosJugadores.get(i).habilitarDesabilitarJugabilidad();
		}
	}

	public boolean agregarJugador(Jugador jugador) {
		boolean pudeInsertar = false;

		if (this.jugadoresActuales < this.jugadoresMaximos) {
			jugadores.add(jugador);
			pudeInsertar = true;
			jugadoresActuales++;
		}

		return pudeInsertar;
	}

	public void insertarJugadoresEnCarretera() {
		double coordendaY = Config.baseHeight - 50;
		double coordenadaX = Config.playerCenter - 50;
		boolean insertarEnLadoDerecho = true;
		double desplazamiento = 0;
		AutoJugador auto;

		for (int i = 0; i < jugadoresActuales; i++) {
			auto = new AutoJugador(new Punto(coordenadaX + desplazamiento, coordendaY), jugadores.get(i));
			desplazamiento += 10;
			// insertarEnLadoDerecho = true;
			autosJugadores.add(auto);
			carretera.agregarObjeto(auto);
		}

//		if (jugadoresActuales % 2 != 0) {
//			desplazamiento = 60;
//
//			for (int i = 0; i < jugadoresActuales; i++) {
//				if (i == 0) {
//					auto = new AutoJugador(new Punto(coordenadaX, coordendaY), jugadores.get(i));
//				} else if (insertarEnLadoDerecho) {
//					auto = new AutoJugador(new Punto(coordenadaX, coordendaY + desplazamiento), jugadores.get(i));
//					insertarEnLadoDerecho = false;
//				} else {
//					auto = new AutoJugador(new Punto(180, coordendaY + desplazamiento), jugadores.get(i));
//					desplazamiento += 60;
//					insertarEnLadoDerecho = true;
//				}
//
//				autosJugadores.add(auto);
//				carretera.agregarObjeto(auto);
//			}
//		} else {
//			desplazamiento = 30;
//
//			for (int i = 0; i < jugadoresActuales; i++) {
//				if (insertarEnLadoDerecho) {
//					auto = new AutoJugador(new Punto(coordenadaX, coordendaY), jugadores.get(i));
//					insertarEnLadoDerecho = false;
//				} else {
//					auto = new AutoJugador(new Punto(coordenadaX, coordendaY + desplazamiento), jugadores.get(i));
//					desplazamiento += 60;
//					insertarEnLadoDerecho = true;
//				}
//
//				autosJugadores.add(auto);
//				carretera.agregarObjeto(auto);
//			}
//		}
	}

	public boolean iniciar() {
		boolean inicie = false;

		if (this.jugadoresActuales >= this.jugadoresMinimos) {
			insertarJugadoresEnCarretera();
			habilitarDesabilitarJugabilidad();
			inicie = true;
		} else {
		}

		return inicie;
	}

	public void actualizar() {
		carretera.actualizar();
		asignarPuntuaciones();

		if (ganador != null) {
			terminar();
		}
	}

	public void asignarPuntuaciones() {
		int puntuacionMaxima = 10000;
		AutoJugador auto = null;
		double avanceEnCarrera = 0;
		double largoCarretera = this.carretera.getLargo();

		for (int i = 0; i < jugadoresActuales; i++) {
			auto = autosJugadores.get(i);

			avanceEnCarrera = auto.getCoordenada().getY();
			auto.getJugador().actualizarPuntuacion(puntuacionMaxima * (avanceEnCarrera / largoCarretera));

			if (auto.getJugador().getPuntuacion() >= puntuacionMaxima) {
				ganador = auto.getJugador();
			}
		}
	}

	public void terminar() {
		habilitarDesabilitarJugabilidad();
	}

	/// METODOS USADOS EN LOS TESTS

	public ArrayList<AutoJugador> getAutosJugadores() {
		return this.autosJugadores;
	}

	public Carretera getCarretera() {
		return this.carretera;
	}

	public Jugador getGanador() {
		return this.ganador;
	}

	public int getJugadoresMaximos() {
		return this.jugadoresMaximos;
	}

	@Override
	protected void prepareScene() {
		Group rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.BLACK);
	}

	@Override
	protected void defineEventHandlers() {
		keyEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {

				case W:
				case UP:
					acelera = true;
					// makeAction();
					break;

				case A:
				case LEFT:
					autosJugadores.get(0).irIzquierda();
					break;

				case D:
				case RIGHT:
					autosJugadores.get(0).irDerecha();
					break;

				case R:
					restart();
					break;

				case Q:
				case ESCAPE:
//					g.startMenu();
					autosJugadores.get(0).explotar();
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

	public void restart() {
		cleanData();
		load(false);
	}

	private void cleanData() {
		GameObjectBuilder.getInstance().removeAll();
//		ended = false;
//		started = false;
		Config.baseSpeed = 250;
	}

	public void load(boolean fullStart) {
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);

		// Add to builder
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(rootGroup);
		gameOB.add(autosJugadores); // solo agrega jugadores
		gameOB.add(carretera); // agrega lo demas

		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}

	public void unload() {
		cleanData();
		super.unload();
	}

	public void update(double delta) {
		super.update(delta);
		carretera.setAcelera(acelera);
	}
}
