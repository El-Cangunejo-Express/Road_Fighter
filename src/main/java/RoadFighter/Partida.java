package RoadFighter;

import java.util.ArrayList;

public class Partida {
	private Carretera carretera;
	private int jugadoresMinimos = 2;
	private int jugadoresMaximos = 5;
	private int jugadoresActuales = 0;
	private Jugador ganador;
	private ArrayList<Jugador> jugadores;
	private ArrayList<AutoJugador> autosJugadores;

	public Partida() {
		super();
		this.carretera = carreteraPredefinida();
		this.ganador = null;
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
					auto = new AutoJugador(new Punto(i, coordendaY), jugadores.get(i).getNombre());
				} else if (insertarEnLadoDerecho) {
					auto = new AutoJugador(new Punto(desplazamiento, coordendaY), jugadores.get(i).getNombre());
					insertarEnLadoDerecho = false;
				} else {
					auto = new AutoJugador(new Punto(-1 * desplazamiento, coordendaY), jugadores.get(i).getNombre());
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
					auto = new AutoJugador(new Punto(desplazamiento, coordendaY), jugadores.get(i).getNombre());
					insertarEnLadoDerecho = false;
				} else {
					auto = new AutoJugador(new Punto(-1 * desplazamiento, coordendaY), jugadores.get(i).getNombre());
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
			System.out.println("Se necesitan mas jugadores para iniciar");
		}

		return inicie;
	}

	public boolean verificarGanador() {
		boolean hayGanador = false;

		for (int i = 0; i < jugadoresActuales; i++) {
			if (autosJugadores.get(i).getCoordenada().getY() >= carretera.getLargo()) {
				hayGanador = true;
			}
		}

		return hayGanador;
	}

	public void actualizar() {
		carretera.actualizar();

		if (verificarGanador()) {
			terminar();
		}
	}

	public Jugador asignarPuntuaciones() {
		Jugador ganador = null;
		Jugador jugador = null;
		double avanceEnCarrera = 0;
		double largoCarretera = this.carretera.getLargo();

		for (int i = 0; i < jugadoresActuales; i++) {

			jugador = jugadores.get(i);
			avanceEnCarrera = autosJugadores.get(i).getCoordenada().getY();
			jugador.actualizarPuntuacion(10000 * (avanceEnCarrera / largoCarretera));

			if (i == 0 || ganador.getPuntuacion() < jugador.getPuntuacion()) {
				ganador = jugador;
			}
		}

		return ganador;
	}

	public void terminar() {
		habilitarDesabilitarJugabilidad();
		this.ganador = asignarPuntuaciones();
		System.out.println("El ganador fue el jugador: " + this.ganador.getNombre());
	}

	/// METODOS USADOS EN LOS TESTS

	public ArrayList<AutoJugador> getAutosJugadores() {
		return this.autosJugadores;
	}

	public Carretera getCarretera() {
		return this.carretera;
	}

	///
}
