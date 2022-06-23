package RoadFighter.Objetos;

import java.util.ArrayList;
import java.util.List;
import RoadFighter.Interfaces.Renderable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Carretera extends GameObject implements Renderable{	
	private double distanciaTotal = 40420;
	private int width = 720;
	private int height = 720;
	private ImageView render;
	
	private List<GameObject> GameObjects = new ArrayList<GameObject>();	
	
	public Carretera() {		
		Image fondo = new Image("file:src/main/resources/img/carretera.png", width, height, false, false);
		render = new ImageView(fondo);	
		
		cargarObjetos();
	}
	
	private void cargarObjetos() {

		GuardarrailIzquierdo guardarrailIzq = new GuardarrailIzquierdo(118, 720);
		GuardarrailDerecho guardarrailDer = new GuardarrailDerecho(610, 720);
		AdornoCarretera adorno = new AdornoCarretera(360, 625);
		Tree tree = new Tree(670, 720);
		Meta meta = new Meta(364, -19900);
		
		Pozo pozo1 = new Pozo(242, -1000);						/// pozos y aceites 242-325-408-491 salvo excepciones
		Pozo pozo2 = new Pozo(408, -3000);
		Pozo pozo3 = new Pozo(325, -5000);
		Pozo pozo4 = new Pozo(491, -7000);
		Pozo pozo5 = new Pozo(491, -9000);
		Pozo pozo6 = new Pozo(408, -11000);
		Pozo pozo7 = new Pozo(325, -13000);
		Pozo pozo8 = new Pozo(242, -15000);
		Pozo pozo9 = new Pozo(408, -17000);
		Pozo pozo10 = new Pozo(325, -19000);

		ManchaDeAceite aceite1 = new ManchaDeAceite(325, -2000);
		ManchaDeAceite aceite2 = new ManchaDeAceite(408, -4000);
		ManchaDeAceite aceite3 = new ManchaDeAceite(242, -6000);
		ManchaDeAceite aceite4 = new ManchaDeAceite(491, -8000);
		ManchaDeAceite aceite5 = new ManchaDeAceite(242, -10000);
		ManchaDeAceite aceite6 = new ManchaDeAceite(491, -12000);
		ManchaDeAceite aceite7 = new ManchaDeAceite(325, -14000);
		ManchaDeAceite aceite8 = new ManchaDeAceite(408, -16000);
		ManchaDeAceite aceite9 = new ManchaDeAceite(242, -18000);
		ManchaDeAceite aceite10 = new ManchaDeAceite(325, -19500);

		AceleradorExtremo acelerador1 = new AceleradorExtremo(180, -5000); 	/// en cualquier lado
		AceleradorExtremo acelerador2 = new AceleradorExtremo(460, -10000);
		AceleradorExtremo acelerador3 = new AceleradorExtremo(366, -15000);

		Escudo escudo1 = new Escudo(360, -20); 		///en cualquier lado
		Escudo escudo2 = new Escudo(500, -8500); 	
		Escudo escudo3 = new Escudo(200, -15700); 		

		AutoObstaculo autoObstaculo1 = new AutoObstaculo(200, 650); /// autos 200-283-366-449-531
		AutoObstaculo autoObstaculo2 = new AutoObstaculo(531, 650);
		AutoObstaculo autoObstaculo3 = new AutoObstaculo(283, 550);
		AutoObstaculo autoObstaculo4 = new AutoObstaculo(449, 550);
		AutoObstaculo autoObstaculo5 = new AutoObstaculo(283, -50);		
		AutoObstaculo autoObstaculo6 = new AutoObstaculo(366, -200);
		AutoObstaculo autoObstaculo7 = new AutoObstaculo(449, -350);
		AutoObstaculo autoObstaculo8 = new AutoObstaculo(366, -550);
		AutoObstaculo autoObstaculo9 = new AutoObstaculo(531, -750);
		AutoObstaculo autoObstaculo10 = new AutoObstaculo(200, -1000);
		AutoObstaculo autoObstaculo11 = new AutoObstaculo(283, -900);
		AutoObstaculo autoObstaculo12 = new AutoObstaculo(449, -1200);
		AutoObstaculo autoObstaculo13 = new AutoObstaculo(200, -1500);
		AutoObstaculo autoObstaculo14 = new AutoObstaculo(449, -2000);
		AutoObstaculo autoObstaculo15 = new AutoObstaculo(531, -2100);
		AutoObstaculo autoObstaculo16 = new AutoObstaculo(531, -1900);
		AutoObstaculo autoObstaculo17 = new AutoObstaculo(200, -2350);
		AutoObstaculo autoObstaculo18 = new AutoObstaculo(366, -2600);
		AutoObstaculo autoObstaculo19 = new AutoObstaculo(200, -2900);
		AutoObstaculo autoObstaculo20 = new AutoObstaculo(283, -2900);
		AutoObstaculo autoObstaculo21 = new AutoObstaculo(449, -3200);
		AutoObstaculo autoObstaculo22 = new AutoObstaculo(200, -3600);
		AutoObstaculo autoObstaculo23 = new AutoObstaculo(283, -3700);
		AutoObstaculo autoObstaculo24 = new AutoObstaculo(283, -3500);
		AutoObstaculo autoObstaculo25 = new AutoObstaculo(449, -4000);
		AutoObstaculo autoObstaculo26 = new AutoObstaculo(200, -4100);
		AutoObstaculo autoObstaculo27 = new AutoObstaculo(531, -4400);
		AutoObstaculo autoObstaculo28 = new AutoObstaculo(200, -4600);
		AutoObstaculo autoObstaculo29 = new AutoObstaculo(283, -4700);
		AutoObstaculo autoObstaculo30 = new AutoObstaculo(283, -4500);
		AutoObstaculo autoObstaculo31 = new AutoObstaculo(531, -4900);
		AutoObstaculo autoObstaculo32 = new AutoObstaculo(531, -4800);
		AutoObstaculo autoObstaculo33 = new AutoObstaculo(283, -5300);
		AutoObstaculo autoObstaculo34 = new AutoObstaculo(449, -5600);
		AutoObstaculo autoObstaculo35 = new AutoObstaculo(366, -5700);
		AutoObstaculo autoObstaculo36 = new AutoObstaculo(200, -6000);
		AutoObstaculo autoObstaculo37 = new AutoObstaculo(283, -6100);
		AutoObstaculo autoObstaculo38 = new AutoObstaculo(200, -6200);
		AutoObstaculo autoObstaculo39 = new AutoObstaculo(449, -6500);
		AutoObstaculo autoObstaculo40 = new AutoObstaculo(531, -6800);
		AutoObstaculo autoObstaculo41 = new AutoObstaculo(283, -6900);
		AutoObstaculo autoObstaculo42 = new AutoObstaculo(283, -7000);
		AutoObstaculo autoObstaculo43 = new AutoObstaculo(449, -7200);
		AutoObstaculo autoObstaculo44 = new AutoObstaculo(366, -7300);
		AutoObstaculo autoObstaculo45 = new AutoObstaculo(449, -7400);
		AutoObstaculo autoObstaculo46 = new AutoObstaculo(200, -7600);
		AutoObstaculo autoObstaculo47 = new AutoObstaculo(283, -7700);
		AutoObstaculo autoObstaculo48 = new AutoObstaculo(366, -7800);
		AutoObstaculo autoObstaculo49 = new AutoObstaculo(531, -8000);
		AutoObstaculo autoObstaculo50 = new AutoObstaculo(531, -8100);
		AutoObstaculo autoObstaculo51 = new AutoObstaculo(366, -8350);
		AutoObstaculo autoObstaculo52 = new AutoObstaculo(200, -8600);
		AutoObstaculo autoObstaculo53 = new AutoObstaculo(531, -8600);
		AutoObstaculo autoObstaculo54 = new AutoObstaculo(449, -8700);
		AutoObstaculo autoObstaculo55 = new AutoObstaculo(283, -8700);

		CamionObstaculo camionObstaculo0 = new CamionObstaculo(150, -500);
		CamionObstaculo camionObstaculo00 = new CamionObstaculo(580, -500);
		CamionObstaculo camionObstaculo1 = new CamionObstaculo(150, -5000);
		CamionObstaculo camionObstaculo2 = new CamionObstaculo(580, -8000);
		CamionObstaculo camionObstaculo3 = new CamionObstaculo(150, -11000);

		GameObjects.add(guardarrailIzq);
		GameObjects.add(guardarrailDer);
		GameObjects.add(adorno);
		GameObjects.add(tree);
		GameObjects.add(meta);

		GameObjects.add(pozo1);
		GameObjects.add(pozo2);
		GameObjects.add(pozo3);
		GameObjects.add(pozo4);
		GameObjects.add(pozo5);
		GameObjects.add(pozo6);
		GameObjects.add(pozo7);
		GameObjects.add(pozo8);
		GameObjects.add(pozo9);
		GameObjects.add(pozo10);

		GameObjects.add(aceite1);
		GameObjects.add(aceite2);
		GameObjects.add(aceite3);
		GameObjects.add(aceite4);
		GameObjects.add(aceite5);
		GameObjects.add(aceite6);
		GameObjects.add(aceite7);
		GameObjects.add(aceite8);
		GameObjects.add(aceite9);
		GameObjects.add(aceite10);

		GameObjects.add(acelerador1);
		GameObjects.add(acelerador2);
		GameObjects.add(acelerador3);

		GameObjects.add(escudo1);
		GameObjects.add(escudo2);
		GameObjects.add(escudo3);		
		
		GameObjects.add(autoObstaculo1);
		GameObjects.add(autoObstaculo2);
		GameObjects.add(autoObstaculo3);
		GameObjects.add(autoObstaculo4);
		GameObjects.add(autoObstaculo5);
		GameObjects.add(autoObstaculo6);
		GameObjects.add(autoObstaculo7);
		GameObjects.add(autoObstaculo8);
		GameObjects.add(autoObstaculo9);
		GameObjects.add(autoObstaculo10);
		GameObjects.add(autoObstaculo11);
		GameObjects.add(autoObstaculo12);
		GameObjects.add(autoObstaculo13);
		GameObjects.add(autoObstaculo14);
		GameObjects.add(autoObstaculo15);
		GameObjects.add(autoObstaculo16);
		GameObjects.add(autoObstaculo17);
		GameObjects.add(autoObstaculo18);
		GameObjects.add(autoObstaculo19);
		GameObjects.add(autoObstaculo20);
		GameObjects.add(autoObstaculo21);
		GameObjects.add(autoObstaculo22);
		GameObjects.add(autoObstaculo23);
		GameObjects.add(autoObstaculo24);
		GameObjects.add(autoObstaculo25);
		GameObjects.add(autoObstaculo26);
		GameObjects.add(autoObstaculo27);
		GameObjects.add(autoObstaculo28);
		GameObjects.add(autoObstaculo29);
		GameObjects.add(autoObstaculo30);
		GameObjects.add(autoObstaculo31);
		GameObjects.add(autoObstaculo32);
		GameObjects.add(autoObstaculo33);
		GameObjects.add(autoObstaculo34);
		GameObjects.add(autoObstaculo35);
		GameObjects.add(autoObstaculo36);
		GameObjects.add(autoObstaculo37);
		GameObjects.add(autoObstaculo38);
		GameObjects.add(autoObstaculo39);
		GameObjects.add(autoObstaculo40);
		GameObjects.add(autoObstaculo41);
		GameObjects.add(autoObstaculo42);
		GameObjects.add(autoObstaculo43);
		GameObjects.add(autoObstaculo44);
		GameObjects.add(autoObstaculo45);
		GameObjects.add(autoObstaculo46);
		GameObjects.add(autoObstaculo47);
		GameObjects.add(autoObstaculo48);
		GameObjects.add(autoObstaculo49);
		GameObjects.add(autoObstaculo50);
		GameObjects.add(autoObstaculo51);
		GameObjects.add(autoObstaculo52);
		GameObjects.add(autoObstaculo53);
		GameObjects.add(autoObstaculo54);
		GameObjects.add(autoObstaculo55);

		GameObjects.add(camionObstaculo0);
		GameObjects.add(camionObstaculo00);
		GameObjects.add(camionObstaculo1);
		GameObjects.add(camionObstaculo2);
		GameObjects.add(camionObstaculo3);		
	}
	
	@Override
	public Node getRender() {
		return render;
	}	
	
	public double getDistanciaTotal() {
		return distanciaTotal;
	}	
	
	public  List<GameObject> getObjetos(){
		return this.GameObjects;
	}
}
