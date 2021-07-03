package ejercicio5PLI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.pli.AlgoritmoPLI;
import us.lsi.pli.SolutionPLI;

public class Ejercicio5PLI {

	public static String getConstraints() {
		String r = "min: ";
		int num = DatosConjunto.getConjuntos().size(); 
		r = r + IntStream.range(0, num)
		.boxed()
		.map(i -> String.format("%d*x%d", DatosConjunto.getConjunto(i).getPeso() , i))
		.collect(Collectors.joining("+", "", ";\n\n"));
		

		for (Integer numero : DatosConjunto.getUniverso()) {
			List<Integer> temp = DatosConjunto.incluyeIndice(numero); // Indices de los conjuntos que incluyen al numero especifico del universo
			if (temp.size() == 1) { // Siempre lo va a contener al menos 1 ya que el universo se ha creado a partir de los Sets
				r = r + "x" + temp.get(0);
			} else {
				for (int j = 0; j < temp.size(); j++) { // Numeros de la lista de indices que tienen el numero del universo
					if (j < temp.size() - 1) {
						r = r + "x" + temp.get(j) + " + ";
					} else {
						r = r + "x" + temp.get(j);
					}
				}
			}
			r = r + " >= 1;\n";

		}
		
		r = r +"\nbin ";
		r = r + IntStream.range(0, num)
				.boxed()
				.map(i->String.format("x%d",i))
				.collect(Collectors.joining(",","",";\n"));		
		r = r +"\n\n";
		
		
		return r;		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatosConjunto.iniDatos("ficheros/PI3Ej5DatosEntrada.txt");
		
		SolutionPLI s = AlgoritmoPLI.getSolution(getConstraints());
		System.out.println(getConstraints());
		
		System.out.println("Solucion del problema");
		System.out.println("----------------------");
		System.out.println("Coste (suma de pesos de la solución):");
		System.out.println(s.getGoal());
		
		List<Conjunto> res = new ArrayList<Conjunto>();
		for(int i=0;i<s.getNumVar();i++){
			if(s.getSolution(i) > 0) {
				res.add(DatosConjunto.getConjunto(i));
			}
		}
		System.out.println("Subconjuntos elegidos y peso asociado:");
		System.out.println(res);
	}

}
