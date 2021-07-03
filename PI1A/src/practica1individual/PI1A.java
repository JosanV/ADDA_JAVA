package practica1individual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import us.lsi.common.Files2;

public class PI1A {	

	//Otra forma de hacer el leeFichero
//	public static List<Integer> leeFicheroInt(String fichero){
//		return Files2.getLines(fichero)
//				.stream()
//				.map(l -> Integer.parseInt(l))
//				.collect(Collectors.toList());
//	}
	
	public static List<String> leeFichero(String fichero){
		return Files2.getLines(fichero);
	}
	
	
	//Ejercicio 1 - 1 - METODO QUE TENEMOS QUE HACER EN ITERATIVO
//	private static List<Integer> f (List<Integer> l) {
//		return l.stream()				//Pasa a stream
//		.filter(e -> (e%2 == 0))		//Se queda solo con los divisibles por 2
//		.map(e -> e*e)					//Lo eleva al cuadrado
//		.collect(Collectors.toList());	//Lo mete en una lista
//	}	
	
	
	private static List<Integer> fIter(List<Integer> l){
		List<Integer> acum = new ArrayList<Integer>();
		int i = 0;
		while(i < l.size()) {
			if((l.get(i) % 2) == 0) {
				acum.add(l.get(i)*l.get(i));
			}			
			i++;
		}
		return acum;
	}
	
	
	public static void ejercicio1a(String fichero){
		List<String> lineas = leeFichero(fichero);
		
		String[] numeros;
		int i = 0;
		while(i < lineas.size()) {
			if(lineas.get(i).isEmpty()) {
				System.out.println("Entrada: []");
				System.out.println("Salida: []");
				System.out.println("========================================");
				i++;
			} else {
				List<Integer> auxEntrada = new ArrayList<Integer>();
				List<Integer> auxSalida = new ArrayList<Integer>();
				
				numeros = (lineas.get(i)).split(",");
				
				int j = 0;
				while(j < numeros.length) {
					auxEntrada.add(Integer.parseInt(numeros[j]));
					j++;
				}
				
				auxSalida = fIter(auxEntrada);	//Llamada a la funcion iterativa
				
				System.out.println("Entrada: " + auxEntrada);
				System.out.println("Salida: " + auxSalida);
				System.out.println("========================================");
				
				i++;
			}
		}
	}
	
	
	
	
	
	
	
	//Ejercicio 1 - 2 - METODO QUE TENEMOS QUE HACER EN ITERATIVO
//	public static Map<Integer,List<String>> g(List<String> l) {
//		return l.stream()									
//		.collect(Collectors.groupingBy(String::length));	
//	}
	
	
	public static Map<Integer,List<String>> gIter(List<String> l) {
		Map<Integer, List<String>> acum = new HashMap<Integer, List<String>>();
		int i = 0;
		while(i < l.size()) {
			if(acum.containsKey(l.get(i).length())) {			//Si existe ya el numero en el mapa
				List<String> aux = acum.get(l.get(i).length());	//Lista auxiliar con los valores de ese numero
				aux.add(l.get(i));								//Se le anyade el nuevo String a esa lista auxiliar
//				acum.put(l.get(i).length(), aux);				//Se actualiza el Map
			} else {
				List<String> aux = new ArrayList<String>();		//Igual pero se crea una lista vacia
				aux.add(l.get(i));
				acum.put(l.get(i).length(), aux);
			}
			i++;
		}
		return acum;
	}
	
	
	public static void ejercicio1b(String fichero) {
		List<String> lista = leeFichero(fichero);
		Map<Integer, List<String>> mapa = gIter(lista);
		
		int max = 0;
		for (Integer key : mapa.keySet()) {		//Sacar el numero maximo de letras por si se salta algun numero
			if(key > max) {
				max = key;
			}
		}
		
		int j = 1;
		while(j <= max) {
			if(mapa.get(j) == null) {
				j++;
			}
			System.out.println(j + ":" + mapa.get(j));
			j++;
		}
	}
	
	
}
