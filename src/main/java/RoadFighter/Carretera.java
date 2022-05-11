package RoadFighter;

import java.util.Collections;
import java.util.LinkedList;

public class Carretera {
	private double largo;
	private double ancho;
	private double limIzq;
	private double limDer;
	private LinkedList<ObjetoDelMapa> objetosDeLaCarretera = new LinkedList<ObjetoDelMapa>();

	public Carretera(double ancho, double longitud) {
		super();
		this.largo = longitud;
		this.ancho = ancho;
		this.limDer = this.ancho / 2;
		this.limIzq = -1 * limDer;
	}

	private boolean objetoFueraDeMiLimite(ObjetoDelMapa objeto) {
		boolean estaFueraDelLimite = false;
		double xObjeto = objeto.getCoordenada().getX();

		if ((xObjeto - objeto.getAncho() / 2) <= this.limIzq || (xObjeto + objeto.getAncho() / 2) >= this.limDer) {
			estaFueraDelLimite = true;
		}
		return estaFueraDelLimite;
	}

	private void detectarObjetoFueraDeCarretera() {
		for (int i = 0; i < objetosDeLaCarretera.size(); i++) {
			if (objetosDeLaCarretera.get(i).getTieneMovimiento()) {
				if (this.objetoFueraDeMiLimite(objetosDeLaCarretera.get(i))) {
					System.out.println(
							objetosDeLaCarretera.get(i).getNombre() + " se salio de los limites de la carretea");
					objetosDeLaCarretera.get(i).explotar();
					if (objetosDeLaCarretera.get(i).desaparecer) {
						objetosDeLaCarretera.remove(i);
						i--;
					}
				}
			}
		}
	}

	private void detectarChoque() {
		boolean boorrarObj1 = false;
		boolean boorrarObj2 = false;
		ObjetoDelMapa objeto1 = null;
		ObjetoDelMapa objeto2 = null;

		for (int i = 0; i < objetosDeLaCarretera.size() - 1; i++) {
			objeto1 = objetosDeLaCarretera.get(i);
			for (int j = i + 1; j < objetosDeLaCarretera.size(); j++) {
				objeto2 = objetosDeLaCarretera.get(j);

				if (objeto1.hayColisionCon(objeto2)) {
					if (objeto1.getTieneMovimiento()) {
						Vehiculo vehiculo = (Vehiculo) objeto1;
						vehiculo.choqueConObjeto(objeto2);
					} else {
						Vehiculo vehiculo = (Vehiculo) objeto2;
						vehiculo.choqueConObjeto(objeto1);
					}
					
					boorrarObj1 = objeto1.desaparecer;
					boorrarObj2 = objeto2.desaparecer;

					if (boorrarObj1 && boorrarObj2) {
						objetosDeLaCarretera.remove(i);
						objetosDeLaCarretera.remove(i);
						i--;
						break;
					} else if (boorrarObj1 && !boorrarObj2) {
						objetosDeLaCarretera.remove(i);
						i--;
						break;
					} else if (!boorrarObj1 && boorrarObj2) {
						objetosDeLaCarretera.remove(j);
						j--;
					} 
				}
			}

		}
	}

	public boolean agregarObjeto(ObjetoDelMapa objeto) {
		boolean pudeAgregar = false;
		double yObjeto = objeto.getCoordenada().getY();

		if (!this.objetoFueraDeMiLimite(objeto)
				&& ((yObjeto - objeto.getLargo() / 2) >= 0 && (yObjeto + objeto.getLargo() / 2) <= this.largo)) {
			objetosDeLaCarretera.add(objeto);
			pudeAgregar = true;
		}
		return pudeAgregar;
	}

	public double getLimIzq() {
		return limIzq;
	}

	public double getLimDer() {
		return limDer;
	}

	public double getLargo() {
		return largo;
	}

	public void actualizar() {
		Collections.sort(objetosDeLaCarretera, new ComparadorDeObjetosDelMapa());
		this.detectarObjetoFueraDeCarretera();
		this.detectarChoque();
	}

	/// METODOS USADOS EN LOS TESTS

	public int getCantidadDeObjetos() {
		return this.objetosDeLaCarretera.size();
	}

	///
}
