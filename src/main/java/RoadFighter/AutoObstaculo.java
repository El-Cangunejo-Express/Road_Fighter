package RoadFighter;

public class AutoObstaculo extends Vehiculo {

	public AutoObstaculo(Punto coordenada) {
		this.coordenada = coordenada;
		this.largo = 40;
		this.ancho = 30;
		this.nombre = "auto-bot";
		this.tieneMovimiento = true;
		this.velocidadActual = 0;
		this.velocidadMaxima = 80;
	}
	
	@Override
	public void choqueConObjeto(ObjetoDelMapa objeto) {
		objeto.choqueConAutoObstaculo(this);
	}
	
	@Override
	public void choqueConAutoJugador(AutoJugador auto) {
		System.out.println(auto.getNombre() + " y " + this.getNombre() + " han chocado");		
		if(!auto.getEscudo()) {
			auto.perderElControl();
			this.perderElControl();
		}else {
			this.explotar();
			this.desaparecer = true;
			auto.perderEscudo();
		}	
	}
	
	@Override
	public void choqueConAutoObstaculo(AutoObstaculo auto) {
		System.out.println(auto.getNombre() + " y " + this.getNombre() + " han chocado");
		auto.perderElControl();	
		this.perderElControl();
		
	}
	
	@Override
	public void choqueConCamion(CamionObstaculo camion) {
		System.out.println(this.getNombre() + " y " + camion.getNombre() + " han chocado");
		this.explotar();
		this.desaparecer = true;
	}

	

}
