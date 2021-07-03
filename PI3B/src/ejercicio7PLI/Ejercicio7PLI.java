package ejercicio7PLI;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.pli.AlgoritmoPLI;
import us.lsi.pli.SolutionPLI;



public class Ejercicio7PLI {

	public static String getConstraints(int i) {
		String r = "min: ";
		int num = DatosConjunto.getListaNumeros(i).getElementos().size();
		r = r + IntStream.range(0, num)
			.boxed()
			.map(p -> String.format("x%d", p))
			.collect(Collectors.joining(" + ", "", ";\n\n"));
		
		r = r + IntStream.range(0, num)
			.boxed()
			.map(p -> String.format("%d*x%d", DatosConjunto.getListaNumeros(i).getElementos().get(p), p))
			.collect(Collectors.joining(" + ", "", " = " + DatosConjunto.getSumaObjetivo(i) + ";"));
		
		
		r= r + "\n\n";
		r = r +"int ";
		r = r + IntStream.range(0, num)
				.boxed()
				.map(p->String.format("x%d",p))
				.collect(Collectors.joining(",","",";\n"));		
		r = r +"\n\n";
		
		
		return r;		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatosConjunto.iniDatos("ficheros/PI3Ej7DatosEntrada.txt");
		for (int i = 0; i < DatosConjunto.getListasNumeros().size(); i++) {
			System.out.println(getConstraints(i));
			System.out.println("=============================================================================");
			System.out.println("Conjunto de Entrada: " + DatosConjunto.getListaNumeros(i).getElementos());
			SolutionPLI s = AlgoritmoPLI.getSolution(getConstraints(i));
			System.out.println("Suma objetivo: " + DatosConjunto.getSumaObjetivo(i));
			String suma = "";
			for(int j=0;j<s.getNumVar();j++){
				double solucion = s.getSolution(j);
				if(solucion > 0) {
					suma = suma + " + ";
					if(solucion > 1) {
						suma = suma + (int) solucion + "*" + DatosConjunto.getListaNumeros(i).getElementos().get(j);
					} else {
						suma = suma +  DatosConjunto.getListaNumeros(i).getElementos().get(j);
					}
				}
//				System.out.println(s.getName(j)+" = "+s.getSolution(j));
			}
			if(suma.equals("")) {
				System.out.println("No hay solución exacta");
			} else {
				System.out.print("Hay solución exacta con " + (int) s.getGoal() + " elementos: ");
				System.out.println(suma);
			}
			

		}
		
	}

}
