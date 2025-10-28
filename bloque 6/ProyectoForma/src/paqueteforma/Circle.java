package paqueteforma;

public class Circle extends Shape {
	private double radius;

	public Circle() {
		super();
		this.radius = 1;
	}

	public Circle(double radius) {
		super();
		this.radius = radius;
	}

	public Circle(double radius, String color, boolean filled) {
		super(color, filled);
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getArea() {
		double area = Math.PI * Math.pow(this.radius, 2);
		return area;
	}
	
	public double getPerimeter() {
		double perimetro = 2 * Math.PI * this.radius;
		return perimetro;
	}

	@Override
	public String toString() {
		return "Circle [radius=" + radius + ", getColor()=" + getColor() + ", isFilled()=" + isFilled() + "]";
	}
	
	
	
	

}
