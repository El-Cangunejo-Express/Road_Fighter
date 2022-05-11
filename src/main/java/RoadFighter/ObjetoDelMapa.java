package RoadFighter;

public abstract class ObjetoDelMapa {

	/// ATRIBUTOD USADOS EN LOS TESTS
	protected boolean explotar = false;
	protected boolean perderControl = false;
	///
	protected Punto coordenada;
	protected double ancho;
	protected double largo;
	protected String nombre;
	protected boolean tieneMovimiento;
	protected boolean desaparecer = false;

	public Punto getCoordenada() {
		return this.coordenada;
	}

	public double getAncho() {
		return this.ancho;
	}

	public double getLargo() {
		return this.largo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean getTieneMovimiento() {
		return this.tieneMovimiento;
	}

	public void explotar() {
		System.out.println(this.nombre + " ha explotado");
		explotar = true;
	}

	public boolean hayColisionCon(ObjetoDelMapa objeto) {
		boolean hayColision = false;

		double distX = this.coordenada.getDistanciaXCon(objeto.coordenada);
		double distY = this.coordenada.getDistanciaYCon(objeto.coordenada);

		if ((distX <= this.ancho / 2 + objeto.ancho / 2) && (distY <= this.largo / 2 + objeto.largo / 2)) {
			hayColision = true;
		}

		return hayColision;
	}

	public abstract void choqueConAutoJugador(AutoJugador auto);

	public abstract void choqueConAutoObstaculo(AutoObstaculo auto);

	public abstract void choqueConCamion(CamionObstaculo camion);

	/// METODOS USADOS EN LOS TESTS

	public boolean getExplotar() {
		return this.explotar;
	}

	public boolean getPerderControl() {
		return this.perderControl;
	}

	public double getTamanio() {
		return largo * ancho;
	}

	///

}
