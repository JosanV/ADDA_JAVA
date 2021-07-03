package practica2individual;

import java.util.List;
import java.util.stream.IntStream;

public class PI1B_EJERCICIO3 {
	
	//Iterativo
	public static Boolean ejercicio3Iterativo(String cadena) {
		boolean res = true;
		int i = 0;
		while((i < cadena.length()/2) && res) {
			res = cadena.charAt(i) == cadena.charAt(cadena.length()-1-i);
			i++;
		}
		return res;
	}
	
	
	//Recursivo no final
	public static Boolean ejercicio3RecursivoNoFinal(String cadena) {
		return ejercicio3RecursivoNoFinalAux(cadena, 0);
	}
	
	public static Boolean ejercicio3RecursivoNoFinalAux(String cadena, int i) {
		Boolean res;
		boolean b = cadena.charAt(i) == cadena.charAt(cadena.length() - 1 - i);
		
		if((i < cadena.length()/2)) {
			res = ejercicio3RecursivoNoFinalAux(cadena, i+1) && b;
		} else {
			res = b;
		}
		
		return res;
	}
	
	
	//Recursivo final
	public static Boolean ejercicio3RecursivoFinal(String cadena) {
		return ejercicio3RecursivoFinalAux(cadena, 0, true);
	}
	
	public static Boolean ejercicio3RecursivoFinalAux(String cadena, int i, boolean acum) {
		Boolean res;
		if(acum && (i < cadena.length()/2)) {
			acum = cadena.charAt(i) == cadena.charAt(cadena.length() - 1 - i);
			res = ejercicio3RecursivoFinalAux(cadena, i+1, acum);
		} else {
			res = acum;
		}
		
		return res;
	}
	
		
	//Funcional
	public static Boolean ejercicio3Funcional(String cadena) {
		return IntStream.range(0, cadena.length() / 2)
						.noneMatch(i -> cadena.charAt(i) != cadena.charAt(cadena.length() - 1 - i));	//Si alguno cumple esta condicion devuelve false
	}
	
	
	
	public static void muestraDatos(String fichero) {
		List<String> l = LeeFichero.leeFichero(fichero);
		
		//Iterativo
		int i = 0;
		for (String s : l) {
			if(ejercicio3Iterativo(s)) {
				i++;
			}
			System.out.println("esPalindromoIterativa("+s+")="+ejercicio3Iterativo(s));
		}
		System.out.println(i + " palíndromos de " + l.size() + " palabras");
		System.out.println("\n");
		
		//No final
		i = 0;
		for (String s : l) {
			if(ejercicio3RecursivoNoFinal(s)) {
				i++;
			}
			System.out.println("esPalindromoNoFinal("+s+")="+ejercicio3RecursivoNoFinal(s));
		}
		System.out.println(i + " palíndromos de " + l.size() + " palabras");
		System.out.println("\n");
		
		//Final
		i = 0;
		for (String s : l) {
			if(ejercicio3RecursivoFinal(s)) {
				i++;
			}
			System.out.println("esPalindromoFinal("+s+")="+ejercicio3RecursivoFinal(s));
		}
		System.out.println(i + " palíndromos de " + l.size() + " palabras");
		System.out.println("\n");
		
		//Funcional
		i = 0;
		for (String s : l) {
			if(ejercicio3Funcional(s)) {
				i++;
			}
			System.out.println("esPalindromoFuncional("+s+")="+ejercicio3Funcional(s));
		}
		System.out.println(i + " palíndromos de " + l.size() + " palabras");
		System.out.println("\n");
		
	}
		
	
}
