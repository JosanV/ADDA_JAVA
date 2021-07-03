package ejercicio6PLI;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.pli.AlgoritmoPLI;
import us.lsi.pli.SolutionPLI;

public class Ejercicio6PLI {
	
	public static String getConstraints() {
		int num = DatosTarea.getTareas().size();
		String r = "";
		r = r + "max: ";
		for (int i = 0; i < num; i++) {
			if (i != 0)
				r = r + "+";
			r = r + String.format("%d*x%d", DatosTarea.getGanancia(i), i);
		}

		r = r + ";\n\n";
		for (int i = 0; i < num; i++) {
			for (int j = i; j < num; j++) {
				if (DatosTarea.getSolapamiento(i, j) == 1) {
					r = r + String.format("x%d + x%d <= 1;\n", i, j);
				}
			}
		}

		r = r + "\nbin ";
		r = r + IntStream.range(0, num).boxed().map(i -> String.format("x%d", i))
				.collect(Collectors.joining(",", "", ";\n"));
		r = r + "\n\n";
		return r;

	}

	public static void main(String[] args) {
		DatosTarea.iniDatos("ficheros/PI3Ej6DatosEntrada2.txt");
////		System.out.println(DatosTarea.getTareas());
		System.out.println(getConstraints());
//		SolutionPLI s = AlgoritmoPLI.getSolution(getConstraints());
//		System.out.println("Solución del problema Tareas:");
//		System.out.println("-------------------");
//		System.out.println("Ganancia de la solución:");
//		System.out.println(s.getGoal());
//		System.out.println("Tareas seleccionadas:");
//		for (int i = 0; i < s.getNumVar(); i++) {
//			if (s.getSolution(i) > 0) {
//				System.out.print(DatosTarea.getTareas().get(i));
//			}
//		}
		String r = "max: ";
		r = r + 2 + "x" + 0;
		System.out.println(r);

	}
}


