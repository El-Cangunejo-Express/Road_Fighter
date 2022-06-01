package RoadFighter;

public class Escudo extends PowerUp {

	public Escudo(Punto coordenada) {
		this.coordenada = coordenada;
		this.largo = 20;
		this.ancho = 20;
		this.nombre = "Escudo";
		this.tieneMovimiento = false;
	}

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		// System.out.println(auto.getNombre() + " obtuvo " + this.getNombre());
		auto.obtenerEscudo();
		this.tengoQueDesaparecer = true;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}