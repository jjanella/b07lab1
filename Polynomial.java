import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Polynomial {
		public double[] cof;
		public int[] exp;

		public Polynomial() {
			cof = new double[] {0.0};
			exp = new int[] {1};
		}

		public Polynomial(double[] cof, int[] exp) {
			this.cof = cof;
			this.exp = exp;
		}

		public Polynomial(File file) {
			String data;
			try {
				Scanner scanner = new Scanner(file);
				data = scanner.nextLine();
				data = data.replace("-", "+-");
				String[] terms = data.split("\\+");
				cof = new double[terms.length];
				exp = new int[terms.length];
				for (int i = 0; i < terms.length; i++) {
					if (!terms[i].equals("")) {
						double c = Double.parseDouble(terms[i].split("x")[0]);
						int e = Integer.parseInt(terms[i].split("x")[1]);
						add_term(this, c, e);
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			} catch (NumberFormatException e) {
				System.out.println("Invalid Number");
			}
		}

		static private Polynomial simplify(Polynomial poly) {
			int z_count = 0;
			for (double item : poly.cof) {
				if (item == 0.0) {
					z_count++;
				}
			}

			double[] new_cof = new double[poly.cof.length - z_count];
			int[] new_exp = new int[poly.cof.length - z_count];

			int idx = 0;
			for (int i = 0; i < poly.cof.length; i++) {
				if (poly.cof[i] != 0.0) {
					new_cof[idx] = poly.cof[i];
					new_exp[idx] = poly.exp[i];
					idx++;
				}
			}
			poly.cof = new_cof;
			poly.exp = new_exp;
			if (poly.cof.length == 0) {
				poly.cof = new double[] {0.0};
				poly.exp = new int[] {1};
			}
			return poly;
		}

		static private Polynomial add_term(Polynomial poly, double new_cof, int new_exp) {
			for (int i = 0; i < poly.exp.length; i++) {
				if (poly.exp[i] == new_exp) {
					poly.cof[i] += new_cof;
					return poly;
				}
			}
			
			double[] new_cofs = new double[poly.cof.length + 1];
			int[] new_exps = new int[poly.exp.length + 1];

			for (int i = 0; i < poly.cof.length; i++) {
				new_cofs[i] = poly.cof[i];
				new_exps[i] = poly.exp[i];
			}

			new_cofs[poly.cof.length] = new_cof;
			new_exps[poly.cof.length] = new_exp;
			poly.cof = new_cofs;
			poly.exp = new_exps;
			return poly;
		}

		public Polynomial add(Polynomial poly) {
			Polynomial new_poly = new Polynomial(cof, exp);
			for (int i = 0; i < poly.cof.length; i++) {
				new_poly = add_term(new_poly, poly.cof[i], poly.exp[i]);
			}
			simplify(new_poly);
			return new_poly;
		};

		public Polynomial multiply(Polynomial poly) {
			Polynomial new_poly = new Polynomial();
			for(int i = 0; i < poly.cof.length; i++) {
				for (int j = 0; j < cof.length; j++) {
					add_term(new_poly, cof[j] * poly.cof[i], exp[j] + poly.exp[i]);
				}
			}
			simplify(new_poly);
			return new_poly;
		}

		public double evaluate(double val) {
			double sum = 0;
			for (int i = 0; i < cof.length; i++) {
				sum += cof[i] * Math.pow(val, exp[i]);
			}
			return sum;
		}

		public boolean hasRoot(double val) {
			return evaluate(val) == 0.0;
		}
		
		public void saveToFile(String filename) {
			simplify(this);
			String data = Double.toString(cof[0]).concat("x").concat(Integer.toString(exp[0]));
			for (int i = 1; i < cof.length; i++) {
				if (cof[i] >= 0.0) {
					data = data.concat("+");
				}
				data = data.concat(Double.toString(cof[i]));
				data = data.concat("x");
				data = data.concat(Integer.toString(exp[i]));
			}
			
			try {
				PrintWriter writer = new PrintWriter(filename, "UTF-8");
				writer.println(data);
				writer.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			} catch (UnsupportedEncodingException e) {
				System.out.println("UTF-8 not supported");
			}
		}
}
