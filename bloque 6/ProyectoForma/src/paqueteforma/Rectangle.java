package paqueteforma;

public class Rectangle extends Shape {
	private double width;
	private double length;
	
	public Rectangle() {
		super();
		this.length = 1;
		this.width = 1;
	}

	public Rectangle(double width, double length) {
		super();
		this.width = width;
		this.length = length;
	}

	public Rectangle(double width, double length, String color, boolean filled) {
		super(color, filled);
		this.width = width;
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	
	public double getArea() {
		double area = this.length * this.width;
		return area;
	}
	
	public double getPerimeter() {
		double perimetro = ((2)*(this.length))+((2)*(this.width));
		return perimetro;
	}

	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", length=" + length + ", getColor()=" + getColor() + ", isFilled()="
				+ isFilled() + "]";
	}
	
	
}
