public class Polynomial {
		public double[] cof;

		public Polynomial() {
			cof = new double[] {0.0};
		}

		public Polynomial(double[] cof) {
			this.cof = cof;
		}

		public Polynomial add(Polynomial poly) {
			int len = Math.max(poly.cof.length, this.cof.length);
			double[] sum = new double[len];
			for (int i = 0; i < len; i++) {
				if (i < this.cof.length) {
					sum[i] += cof[i];
				}
				if (i < poly.cof.length) {
					sum[i] += poly.cof[i];
				}
			}
			return new Polynomial(sum);
		};

		public double evaluate(double val) {
			double sum = 0;
			for (int i = 0; i < cof.length; i++) {
				sum += cof[i] * Math.pow(val, i);
			}
			return sum;
		}

		public boolean hasRoot(double val) {
			return evaluate(val) == 0.0;
		}
}
