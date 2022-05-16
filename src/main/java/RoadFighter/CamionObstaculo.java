package RoadFighter;

public class CamionObstaculo extends Vehiculo {

	public CamionObstaculo(Punto coordenada) {
		this.coordenada = coordenada;
		this.largo = 80;
		this.ancho = 30;
		this.nombre = "camion-bot";
		this.tieneMovimiento = true;
		this.velocidadActual = 0;
		this.velocidadMaxima = 60;
	}

	@Override
	public void choqueConObjeto(ObjetoDelMapa objeto) {
		objeto.choqueConCamion(this);
	}

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		// System.out.println(auto.getNombre() + " ha chocado con " + this.getNombre());
		if (!auto.getEscudo()) {
			auto.explotar();
		} else {
			this.explotar();
			this.tengoQueDesaparecer = true;
			auto.perderEscudo();
		}
	}

	@Override
	public void choqueConAutoObstaculo(AutoObstaculo auto) {
		// System.out.println(auto.getNombre() + " ha chocado con " + this.getNombre());
		auto.explotar();
		auto.tengoQueDesaparecer = true;
	}

	@Override
	public void choqueConCamion(CamionObstaculo camion) {
		// System.out.println(camion.getNombre() + " ha chocado con " +
		// this.getNombre());
		camion.explotar();
		camion.tengoQueDesaparecer = true;
		this.explotar();
		this.tengoQueDesaparecer = true;
	}
}
