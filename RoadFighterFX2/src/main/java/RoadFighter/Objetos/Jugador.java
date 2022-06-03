package RoadFighter.Objetos;

public class Jugador {
	
	private int numeroJugador;	
	private int posX;
	private int posY;
	private Score score;	
	private AutoJugador autoJugador;
	
	
	public Jugador(int numeroJugador) {
		this.numeroJugador = numeroJugador;		
		this.score = new Score();	
		
		if(this.numeroJugador == 1) {
			this.posX = 366;
			this.posY = 650;
		}
		
		this.autoJugador = new AutoJugador(this.posX, this.posY);
	}

	public Score getScore() {
		return score;
	}

	public AutoJugador getAutoJugador() {
		return autoJugador;
	}
}
