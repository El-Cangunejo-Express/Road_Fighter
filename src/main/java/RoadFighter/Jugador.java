package RoadFighter;

public class Jugador {
	private String nombre;
	private double puntuacion;
	
	public Jugador(String nombre) {
		super();
		this.nombre = nombre;
		this.puntuacion = 0;
	}
	
	public void actualizarPuntuacion(double puntaje) {
		this.puntuacion = puntaje;
	}

	public String getNombre() {
		return nombre;
	}	
	
	public double getPuntuacion() {
		return puntuacion;
	}		
}
