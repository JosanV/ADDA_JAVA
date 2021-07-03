package practica2individual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.lsi.common.Tuple;
import us.lsi.common.Tuple2;

public class PI1B_EJERCICIO5 {
	

	
	
	//Hechos para los nuevos ficheros sin numeros negativos
	//Iterativo
	public static Tuple2<Integer, Integer> ejercicio5Iterativo(int a, int b) {
		Tuple2<Integer, Integer> res = Tuple.create(0, 0);
		int acum = 0;
		while(a >= b) {
			acum = acum + 1;
			a -= b;
		}
		
		res = res.setKey(acum);
		res = res.setValue(a);
		
		return res;
		
	}
	
	
	//Recursivo no final
	public static Tuple2<Integer, Integer> ejercicio5RecursivoNoFinal(int a, int b) {
		Tuple2<Integer, Integer> res = Tuple.create(0, 0);
		
		if(a < b) {
			res = Tuple.create(0, a);
		} else {
			res = ejercicio5RecursivoNoFinal(a-b, b);
			res = Tuple.create(res.getV1()+1, res.getV2());
		}
		return res;
		
	}
	
	
	
	//Recursivo final
	public static Tuple2<Integer, Integer> ejercicio5RecursivoFinal(int a, int b) {
		return ejercicio5RecursivoFinalAux(a, b, 0);
	}
	
	public static Tuple2<Integer, Integer> ejercicio5RecursivoFinalAux(int a, int b, int acum) {
		
		Tuple2<Integer, Integer> res = Tuple.create(0, 0);
		
		if(a < b) {	
			res = res.setKey(acum);
			res = res.setValue(a);
		} else {
			acum++;
			res = ejercicio5RecursivoFinalAux(a-b, b, acum);
		}
		return res;
		
	}
	
	
	//Funcional
	public static Tuple2<Integer, Integer> ejercicio5Funcional(int a, int b) {
		Tuple2<Integer, Integer> res = Tuple.create(0, 0);
		if(a >= b) {
			int resto = Stream.iterate(a, n -> n >= 0 , n -> n - b).filter(n -> n < b).mapToInt(n -> n).sum();
			int cociente = (int) Stream.iterate(a, n -> n >= 0 , n -> n - b).count();
			res = res.setKey(cociente-1);
			res = res.setValue(resto);
		} else {
			res = res.setKey(0);
			res = res.setValue(a);
		}
		return res;
	}
	
	
	
	public static void muestraDatos(String fichero) {
		List<String> l = LeeFichero.leeFichero(fichero);
		int a = 0;
		int b = 0;
		String[] parse;
		Tuple2<Integer, Integer> res;
		
		System.out.println("-------------------- TEST DEL MÉTODO ITERATIVO --------------------");
		for (String s : l) {
			parse = s.split(",");
			a = Integer.valueOf(parse[0]);
			b = Integer.valueOf(parse[1]);
			if(b == 0) {
				System.out.println("Entrada: (" + a + "," + b + ")");
				System.out.println("Salida: (INFINITO,---)");
				System.out.println("========================================");
			} else {
				res = ejercicio5Iterativo(a, b);
				System.out.println("Entrada: (" + a + "," + b + ")");
				System.out.println("Salida: " + res.toString());
				System.out.println("========================================");
			}
		}
		System.out.println();
		
		
		System.out.println("-------------------- TEST DEL MÉTODO RECURSIVO FINAL --------------------");
		for (String s : l) {
			parse = s.split(",");
			a = Integer.valueOf(parse[0]);
			b = Integer.valueOf(parse[1]);
			if(b == 0) {
				System.out.println("Entrada: (" + a + "," + b + ")");
				System.out.println("Salida: (INFINITO,---)");
				System.out.println("========================================");
			} else {
				res = ejercicio5RecursivoFinal(a, b);
				System.out.println("Entrada: (" + a + "," + b + ")");
				System.out.println("Salida: " + res.toString());
				System.out.println("========================================");
			}
		}
		System.out.println();
		
		
		
		
		System.out.println("-------------------- TEST DEL MÉTODO RECURSIVO NO FINAL --------------------");
		for (String s : l) {
			parse = s.split(",");
			a = Integer.valueOf(parse[0]);
			b = Integer.valueOf(parse[1]);
			if(b == 0) {
				System.out.println("Entrada: (" + a + "," + b + ")");
				System.out.println("Salida: (INFINITO,---)");
				System.out.println("========================================");
			} else {
				res = ejercicio5RecursivoNoFinal(a, b);
				System.out.println("Entrada: (" + a + "," + b + ")");
				System.out.println("Salida: " + res.toString());
				System.out.println("========================================");
			}
		}
		System.out.println();
		
		
		
		System.out.println("-------------------- TEST DEL MÉTODO FUNCIONAL J11 (sólo Java) --------------------");
		for (String s : l) {
			parse = s.split(",");
			a = Integer.valueOf(parse[0]);
			b = Integer.valueOf(parse[1]);
			if(b == 0) {
				System.out.println("Entrada: (" + a + "," + b + ")");
				System.out.println("Salida: (INFINITO,---)");
				System.out.println("========================================");
			} else {
				res = ejercicio5Funcional(a, b);
				System.out.println("Entrada: (" + a + "," + b + ")");
				System.out.println("Salida: " + res.toString());
				System.out.println("========================================");
			}
		}
		System.out.println();
		
		
	}
	
	
	
}
