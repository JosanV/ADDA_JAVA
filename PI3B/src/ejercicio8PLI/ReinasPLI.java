package ejercicio8PLI;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.Streams2;
import us.lsi.pli.AlgoritmoPLI;
import us.lsi.pli.SolutionPLI;

public class ReinasPLI {
	public static String getConstraints(Integer n) {
		String r = "min: ;\n\n";
		boolean first = true;

		for (int i = 0; i < n; i++) {
			first = true;
			for (int j = 0; j < n; j++) {
				if (first)
					first = false;
				else
					r = r + "+";
				r = r + String.format("x_%d_%d", i, j);
			}
			r = r + "=";
			r = r + 1;
			r = r + ";\n";
		}

		r = r + "\n\n";

		for (int i = 0; i < n; i++) {
			first = true;
			for (int j = 0; j < n; j++) {
				if (first)
					first = false;
				else
					r = r + "+";
				r = r + String.format("x_%d_%d", j, i);
			}
			r = r + "=";
			r = r + 1;
			r = r + ";\n";
		}

		r = r + "\n\n";
		int m;
		for (int d = -n + 1; d < n; d++) {
			first = true;
			m = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (d == j - i) {
						if (first)
							first = false;
						else
							r = r + "+";
						r = r + String.format("x_%d_%d", i, j);
						m++;
					}
				}
			}
			if (m > 0) {
				r = r + "<=";
				r = r + 1;
				r = r + ";\n";
			}
		}

		r = r + "\n\n";

		for (int d = 0; d < 2 * n - 2; d++) {
			first = true;
			m = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (d == j + i) {
						if (first)
							first = false;
						else
							r = r + "+";
						r = r + String.format("x_%d_%d", i, j);
						m++;
					}
				}
			}
			if (m > 0) {
				r = r + "<=";
				r = r + 1;
				r = r + ";\n";
			}
		}

		r = r + "\n\n";

		r = r + "bin ";

		first = true;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (first)
					first = false;
				else
					r = r + ",";
				r = r + String.format("x_%d_%d", i, j);
			}

		}

		r = r + ";\n\n";

		return r;
	}


	public static void main(String[] args) {
		test8("ficheros/PI3Ej8DatosEntrada.txt");

	}

	public static void test8(String fichero) {
		List<String> l = Files2.getLines(fichero);
		System.out.println(getConstraints(8));
		for (String linea : l) {
			String[] partes = linea.split(":");
			int n = Integer.parseInt(partes[1]);
			System.out.println("\n=============================================================================");
			System.out.println("Numero de Reinas: " + n);
			System.out.print("Solución: ");
			SolutionPLI s = AlgoritmoPLI.getSolution(getConstraints(n));
			for (int i = 0; i < s.getNumVar(); i++) {
				if (s.getSolution(i) == 1.0)
					
					System.out.print(s.getName(i) + " ");
			}
			System.out.println("\n=============================================================================");
		}
	}
	
	static String sum_f1(int d, int n) {
		return Streams2.allPairs(n, n).filter(p -> p.b - p.a == d).map(p -> String.format("x_%d_%d", p.a, p.b))
				.collect(Collectors.joining("+", "", " <= 1; \n"));
	}

	static String sum_f2(int d, int n) {
		return Streams2.allPairs(n, n).filter(p -> p.b + p.a == d).map(p -> String.format("x_%d_%d", p.a, p.b))
				.collect(Collectors.joining("+", "", " <= 1; \n"));
	}
}
