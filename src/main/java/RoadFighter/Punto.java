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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;

		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Punto other = (Punto) obj;

		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;

		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Punto [x=" + x + ", y=" + y + "]";
	}

}
