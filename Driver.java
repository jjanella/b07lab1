import java.io.File;

public class Driver {
	public static void main(String [] args) {
		Polynomial p0 = rw(new Polynomial());
		Polynomial p1 = rw(new Polynomial(new double[]{6, 5}, new int[]{2, 3}));
		double [] c2 = {0,-2,0,0,-9};
		int[] e2 = {4, 3, 2, 1, 5};
		Polynomial p2 = rw(new Polynomial(c2, e2));
		Polynomial p3 = rw(p1.add(p2));
		Polynomial p4 = rw(p2.add(p3));
		Polynomial p5 = rw(p2.multiply(p3));
		System.out.println(new File("p"));
		assert p5.evaluate(2) == 4598532;

		assert p1.multiply(p0).evaluate(3) == 0;
		assert p0.multiply(p1).evaluate(0.5) == 0;
		assert p1.evaluate(2) == 64;
		assert p1.add(p0).evaluate(2) == 64;
		assert p0.add(p1).evaluate(2) == 64;
		System.out.println("All seems good");
	}

	private static Polynomial rw(Polynomial poly) {
		poly.saveToFile("p");
		Polynomial load = new Polynomial(new File("./p"));
		assert poly.evaluate(69420) == load.evaluate(69420);
		return load;
	}

}	
