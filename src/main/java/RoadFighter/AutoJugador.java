package RoadFighter;

public class AutoJugador extends Vehiculo {
	/// ATRIBUTOD USADOS EN LOS TESTS
	private boolean reapareci = false;
	///
	private boolean tengoEscudo = false;
	private double coordenadaXoriginal;

	public AutoJugador(Punto coordenada, String nombre) {
		this.coordenada = coordenada;
		this.coordenadaXoriginal = coordenada.getX();
		this.largo = 40;
		this.ancho = 30;
		this.nombre = nombre;
		this.tieneMovimiento = true;
		this.velocidadActual = 0;
		this.velocidadMaxima = 100;
		this.jugabilidadBloqueada = true;
	}

	private void setearValoresIniciales() {
		this.largo = 40;
		this.ancho = 30;
		this.velocidadActual = 0;
		this.coordenada = new Punto(coordenadaXoriginal, coordenada.getY());
	}

	public void reaparecer() {
		setearValoresIniciales();
		System.out.println(this.nombre + " ha reaparecido");
		this.reapareci = true;
	}

	public void obtenerEscudo() {
		this.tengoEscudo = true;
	}

	public void perderEscudo() {
		this.tengoEscudo = false;
	}

	@Override
	public void explotar() {
		super.explotar();
		this.reaparecer();
	}

	@Override
	public void choqueConObjeto(ObjetoDelMapa objeto) {
		objeto.choqueConAutoJugador(this);
	}

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		System.out.println(this.getNombre() + " y " + auto.getNombre() + " han chocado");

		if (!this.getEscudo()) {
			if (!auto.getEscudo()) {
				auto.perderElControl();
				this.perderElControl();
			} else {
				this.explotar();
				auto.perderEscudo();
			}
		} else {
			if (!auto.getEscudo()) {
				auto.explotar();
			} else {
				this.explotar();
				auto.explotar();
				auto.perderEscudo();
			}
			
			this.perderEscudo();
		}
	}

	@Override
	public void choqueConAutoObstaculo(AutoObstaculo auto) {
		System.out.println(this.getNombre() + " y " + auto.getNombre() + " han chocado");

		if (!this.getEscudo()) {
			auto.perderElControl();
			this.perderElControl();
		} else {
			auto.explotar();
			this.perderEscudo();
		}
	}

	@Override
	public void choqueConCamion(CamionObstaculo camion) {
		System.out.println(this.getNombre() + " y " + camion.getNombre() + " han chocado");

		if (!this.getEscudo()) {
			this.explotar();
		} else {
			camion.explotar();
			this.perderEscudo();
		}
	}

	public void setVelocidad(double velocidad) {
		this.velocidadActual = velocidad;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	/// METODOS USADOS EN LOS TESTS

	public boolean getReaparecer() {
		return this.reapareci;
	}

	public boolean getJuganilidadBloqueada() {
		return this.jugabilidadBloqueada;
	}

	public boolean getEscudo() {
		return this.tengoEscudo;
	}
	///

}
