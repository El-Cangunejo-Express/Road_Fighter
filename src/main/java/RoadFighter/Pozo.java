package RoadFighter;

public class Pozo extends ObjetoDelMapa {

	public Pozo(Punto coordenada) {
		this.coordenada = coordenada;
		this.largo = 20;
		this.ancho = 20;
		this.nombre = "pozo";
		this.tieneMovimiento = false;
	}

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		System.out.println(auto.getNombre() + " ha chocado con " + this.getNombre());

		if (!auto.getEscudo()) {
			auto.explotar();
		} else {
			auto.perderEscudo();
		}

		this.explotar();
		this.desaparecer = true;
	}

	@Override
	public void choqueConAutoObstaculo(AutoObstaculo auto) {
		System.out.println(auto.getNombre() + " ha chocado con " + this.getNombre());
		this.explotar();
		this.desaparecer = true;
		auto.explotar();
		auto.desaparecer = true;
	}

	@Override
	public void choqueConCamion(CamionObstaculo camion) {
		System.out.println(camion.getNombre() + " ha chocado con " + this.getNombre());
		this.explotar();
		this.desaparecer = true;
	}
}
