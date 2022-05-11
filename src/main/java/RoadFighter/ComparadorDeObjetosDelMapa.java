package RoadFighter;

import java.util.Comparator;

public class ComparadorDeObjetosDelMapa implements Comparator<ObjetoDelMapa> {

	public int compare(ObjetoDelMapa o1, ObjetoDelMapa o2) {
		int retorno = 0;

		if (o1.coordenada.getY() < o2.coordenada.getY()) {
			retorno = -1;
		} else if (o1.coordenada.getY() > o2.coordenada.getY()) {
			retorno = 1;
		} else {
			if (o1.coordenada.getX() < o2.coordenada.getX()) {
				retorno = -1;
			} else if (o1.coordenada.getX() > o2.coordenada.getX()) {
				retorno = 1;
			}else {
				retorno = 0;
			}
		}
		return retorno;
	}
}