package RoadFighter;

import java.util.ArrayList;

public class Partida {
	private Carretera carretera;
	private int jugadoresMinimos = 2;
	private int jugadoresMaximos = 5;
	private int jugadoresActuales = 0;
	private Jugador ganador = null;
	private ArrayList<Jugador> jugadores;
	private ArrayList<AutoJugador> autosJugadores;

	public Partida() {
		super();
		this.carretera = carreteraPredefinida();
		this.jugadores = new ArrayList<Jugador>();
		this.autosJugadores = new ArrayList<AutoJugador>();
	}

	private Carretera carreteraPredefinida() {
		Carretera carretera = new Carretera(300, 200);

		// AGREGAR TODOS LOS OBJETOS QUE SE DESEE A LA PISTA
		carretera.agregarObjeto(new Pozo(new Punto(60, 80)));
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
		double coordendaY = 50;
		boolean insertarEnLadoDerecho = true;
		double desplazamiento;
		AutoJugador auto;

		if (jugadoresActuales % 2 != 0) {
			desplazamiento = 60;

			for (int i = 0; i < jugadoresActuales; i++) {
				if (i == 0) {
					auto = new AutoJugador(new Punto(i, coordendaY), jugadores.get(i));
				} else if (insertarEnLadoDerecho) {
					auto = new AutoJugador(new Punto(desplazamiento, coordendaY), jugadores.get(i));
					insertarEnLadoDerecho = false;
				} else {
					auto = new AutoJugador(new Punto(-1 * desplazamiento, coordendaY), jugadores.get(i));
					desplazamiento += 60;
					insertarEnLadoDerecho = true;
				}

				autosJugadores.add(auto);
				carretera.agregarObjeto(auto);
			}
		} else {
			desplazamiento = 30;

			for (int i = 0; i < jugadoresActuales; i++) {
				if (insertarEnLadoDerecho) {
					auto = new AutoJugador(new Punto(desplazamiento, coordendaY), jugadores.get(i));
					insertarEnLadoDerecho = false;
				} else {
					auto = new AutoJugador(new Punto(-1 * desplazamiento, coordendaY), jugadores.get(i));
					desplazamiento += 60;
					insertarEnLadoDerecho = true;
				}

				autosJugadores.add(auto);
				carretera.agregarObjeto(auto);
			}
		}
	}

	public boolean iniciar() {
		boolean inicie = false;

		if (this.jugadoresActuales >= this.jugadoresMinimos) {
			insertarJugadoresEnCarretera();
			habilitarDesabilitarJugabilidad();
			inicie = true;
		} else {
			// System.out.println("Se necesitanm mas jugadores para inicar");
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
		// System.out.println("El ganador fue el jugador: " + this.ganador.getNombre());
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

	///
}
