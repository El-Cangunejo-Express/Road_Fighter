package RoadFighter;

import java.util.Collections;
import java.util.LinkedList;

import RoadFighter.interfaces.Renderable;
import RoadFighter.interfaces.Updatable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Carretera extends ObjetoDelMapa implements Updatable, Renderable {
	private double largo;
	private double limIzq;
	private double limDer;
	private LinkedList<ObjetoDelMapa> objetosDeLaCarretera = new LinkedList<ObjetoDelMapa>();

	private HBox render;
	private double posY = 0;
	private double anchoCalle;
	private final int borderWidth = 150; // por defecto para los bordes de las esquinas
	
	private boolean acelera = false; //temporal

	public Carretera(double ancho, double longitud) {
		super();
		this.largo = longitud;
		this.limDer = ancho / 2;
		this.limIzq = -1 * limDer;
		this.anchoCalle = ancho;
		this.initFondo();
	}

	private boolean objetoFueraDeMiLimite(ObjetoDelMapa objeto) {
		boolean estaFueraDelLimite = false;
		double xObjeto = objeto.getCoordenada().getX();

		if ((xObjeto - objeto.getAncho() / 2) <= this.limIzq || (xObjeto + objeto.getAncho() / 2) >= this.limDer) {
			estaFueraDelLimite = true;
		}

		return estaFueraDelLimite;
	}

	private void initFondo() {
		Image backgroundImage = new Image("file:src/main/resources/Imagenes/calle.jpg", this.anchoCalle, this.largo,
				false, false);

		ImagePattern image_pattern = new ImagePattern(backgroundImage, this.anchoCalle, this.largo, this.anchoCalle,
				this.largo, false);

		Rectangle izq = new Rectangle(borderWidth, Config.baseHeight + this.largo);
		Rectangle calle = new Rectangle(this.anchoCalle, Config.baseHeight + this.largo);
		Rectangle der = new Rectangle(borderWidth, Config.baseHeight + this.largo);

		izq.setFill(Color.rgb(84, 192, 201));
		calle.setFill(image_pattern);
		der.setFill(Color.rgb(100, 224, 117));

		render = new HBox(izq, calle, der);
		// TODO zIndex list
		render.setViewOrder(10);
		render.setLayoutY(-this.largo); // esto me permite subir la imagen para dar la sensacion de que el auto avanza
	}

	private void detectarObjetoFueraDeCarretera() {
		for (int i = 0; i < objetosDeLaCarretera.size(); i++) {
			if (objetosDeLaCarretera.get(i).getTieneMovimiento()) {
				if (this.objetoFueraDeMiLimite(objetosDeLaCarretera.get(i))) {
					// System.out.println(objetosDeLaCarretera.get(i).getNombre() + " se salio de
					// los limites de la carretea");
					objetosDeLaCarretera.get(i).explotar();

					if (objetosDeLaCarretera.get(i).tengoQueDesaparecer) {
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

					boorrarObj1 = objeto1.tengoQueDesaparecer;
					boorrarObj2 = objeto2.tengoQueDesaparecer;

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

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
		posY += Config.baseSpeed * deltaTime * 01.01;

		if (acelera) {
			render.setTranslateY(posY % this.largo); // Este mueve el fondo
		}
		
		this.actualizar();
	}

	///

	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void choqueConAutoObstaculo(AutoObstaculo auto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void choqueConCamion(CamionObstaculo camion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	////temporal, para pruebas
	public void setAcelera(boolean cond) {
		acelera = cond;
	}
	
}
