import java.io.File;

public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		double [] c1 = {6,5};
		int[] e1 = {2, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {0,-2,0,0,-9};
		int[] e2 = {4, 3, 2, 1, 5};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		p1.saveToFile("p1");
		p2.saveToFile("p2");
		p1.add(p2).saveToFile("p3");
		Polynomial p4 = new Polynomial(new File("p2")).add(new Polynomial(new File("p3")));;
		p4.saveToFile("p4");
		Polynomial p5 = new Polynomial(new File("p2")).multiply(new Polynomial(new File("p3")));;
		p5.saveToFile("p5");
		}
}	
