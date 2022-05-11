package RoadFighter;

public class Punto {
	private double x;
	private double y;

	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getDistanciaXCon(Punto punto) {
		return Math.abs(this.x - punto.x);
	}

	public double getDistanciaYCon(Punto punto) {
		return Math.abs(this.y - punto.y);
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void restarX(double n) {
		this.x -= n;
	}

	public void sumarX(double n) {
		this.x += n;
	}

	public void sumarY(double n) {
		this.y += n;
	}

	public void ceroX() {
		this.x = 0;
	}

	@Override
	public String toString() {
		return "Punto (" + x + ", " + y + ")";
	}
}
