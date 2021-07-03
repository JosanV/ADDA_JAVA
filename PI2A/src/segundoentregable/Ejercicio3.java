package segundoentregable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Ejercicio3 {
	public static <E> Set<E> ejercicio3Rec(List<E> lista, E a, E b, Comparator<E> cmp){
		Set<E> tSet = new TreeSet<E>();
		tSet.addAll(ejercicio3RecAux(lista, a, b, cmp, 0, lista.size()-1, new ArrayList<E>()));
		return tSet;
	}
	
	public static <E> List<E> ejercicio3RecAux(List<E> lista, E a, E b, Comparator<E> cmp, int i, int j, List<E> acum){
		int n = (i+j)/2;
		if(i == j || i > j) {		//tan solo queda uno o se ha pasado de uno
			if((cmp.compare(lista.get(n), a) >= 0) && (cmp.compare(lista.get(n), b) < 0)) {
				acum.add(lista.get(n));
			}
		} else {
			if((cmp.compare(lista.get(n), a) >= 0) && (cmp.compare(lista.get(n), b) < 0)) {	//dentro de los limites
				acum.add(lista.get(n));								//se agrega el elemento porque esta ya dentro
				ejercicio3RecAux(lista, a, b, cmp, n+1, j, acum);	//der
				ejercicio3RecAux(lista, a, b, cmp, i, n-1, acum);	//izq
			} else if((cmp.compare(lista.get(n), a) < 0)) {			//n esta a la izq de los limites, hay que ir a la derecha
				ejercicio3RecAux(lista, a, b, cmp, n+1, j, acum);	//der
			} else {												//n esta a la der de los limites, hay que ir a la izquierda
				ejercicio3RecAux(lista, a, b, cmp, i, n-1, acum);	//izq
				
			}
		}
		
		return acum;
	}
	
	public static void muestraEjercicio3(String fichero) {
		List<String> l = LeeFichero.leeFichero(fichero);
		Comparator<Integer> cmp = Comparator.naturalOrder();
		
		for (String s : l) {
			List<Integer> aux = new ArrayList<Integer>();
			String[] split = s.split(";");
			String[] rango = split[1].split(" ");
			String[] numeros = split[0].split(",");
			int a = Integer.valueOf(rango[0]);
			int b = Integer.valueOf(rango[1]);
			for (String string : numeros) {
				aux.add(Integer.valueOf(string));
			}
			System.out.println("Lista: " + aux);
			System.out.println("Rango: [" + a + ", " + b + ")");
			System.out.println("Conjunto: " + ejercicio3Rec(aux, a, b, cmp) + "\n");
		}
	}
	
	
}
