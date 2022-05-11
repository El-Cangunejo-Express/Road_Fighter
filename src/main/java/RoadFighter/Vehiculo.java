package RoadFighter;

public abstract class Vehiculo extends ObjetoDelMapa {

	protected double velocidadActual;
	protected double velocidadMaxima;
	protected boolean jugabilidadBloqueada = false;

	public boolean acelerar() {
		boolean acelero = false;
		if (velocidadActual < velocidadMaxima) {
			velocidadActual = velocidadActual + 1;
			acelero = true;
			System.out.println(this.nombre + " acelero, velocidad actual " + this.velocidadActual + " m/s");
		} else {
			System.out.println(this.nombre + " no puede acelerar mas");
		}
		return acelero;
	}

	public boolean frenar() {
		boolean freno = false;

		if (velocidadActual > velocidadMaxima) {
			System.out.println(this.nombre + " no puede frenar");
			return freno;
		}

		if (velocidadActual > 0) {
			velocidadActual = velocidadActual - 1;
			freno = true;
			System.out.println(this.nombre + " bajo la velocidad a " + this.velocidadActual + " m/s");
		} else {
			System.out.println(this.nombre + " ya esta quieto");
		}
		return freno;
	}

	public void perderElControl() {
		System.out.println(this.nombre + " perdio el control");
		perderControl = true;
	}

	public void irADerecha() {
		if (!jugabilidadBloqueada) {
			System.out.println(this.nombre + " fue hacia la derecha");
			this.coordenada.sumarX(1.0);
		}

	}

	public void irAIzquierda() {
		if (!jugabilidadBloqueada) {
			System.out.println(this.nombre + " fue hacia la izquierda");
			this.coordenada.restarX(1.0);
		}
	}

	public boolean avanzar() {
		boolean avance = false;
		if (!jugabilidadBloqueada) {
			if (velocidadActual != 0) {
				this.coordenada.sumarY(velocidadActual);
				avance = true;
			}
		}
		return avance;
	}

	public void habilitarDesabilitarJugabilidad() {
		if (jugabilidadBloqueada) {
			jugabilidadBloqueada = false;
		} else {
			jugabilidadBloqueada = true;
		}
	}

	public abstract void choqueConObjeto(ObjetoDelMapa objeto);

	/// METODOS USADOS EN LOS TESTS

	public double getVelocidadActual() {
		return this.velocidadActual;
	}

	public Punto getCoordenada() {
		return this.coordenada;
	}

	///
}
