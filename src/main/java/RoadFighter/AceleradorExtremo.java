package RoadFighter;

public class AceleradorExtremo extends PowerUp {

	public AceleradorExtremo(Punto coordenada) {
		this.coordenada = coordenada;
		this.largo = 20;
		this.ancho = 20;
		this.nombre = "Acelerador Extremo";
		this.tieneMovimiento = false;
	}

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		// System.out.println(auto.getNombre() + " obtuvo " + this.getNombre());
		auto.setVelocidad(200);
		this.tengoQueDesaparecer = true;
	}
}
