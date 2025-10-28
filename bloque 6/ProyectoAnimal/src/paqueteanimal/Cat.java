package paqueteanimal;

public class Cat extends Mammal {

	public Cat(String name) {
		super(name);
	}
	
	public void greets() {
		System.out.println("MEOW");
	}

	@Override
	public String toString() {
		return "Cat [toString()=" + super.toString() + "]";
	}

}
