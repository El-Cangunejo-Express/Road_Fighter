package RoadFighter;

public class Miniaturizador extends PowerUp {

	public Miniaturizador(Punto coordenada) {
		this.coordenada = coordenada;
		this.largo = 20;
		this.ancho = 20;
		this.nombre = "Miniaturizador";
		this.tieneMovimiento = false;
	}

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		System.out.println(auto.getNombre() + " obtuvo " + this.getNombre());
		auto.setAncho(15);
		auto.setLargo(20);
		this.desaparecer = true;
	}
}
