package practica2individual;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import us.lsi.geometria.Punto2D;

public class PI1B_EJERCICIO4 {
	
	//Iterativo
	public static List<Punto2D> ejercicio4Iterativo(List<Punto2D> l, Predicate<Punto2D> p){
		int i = 0;
		List<Punto2D> acum = new ArrayList<Punto2D>();
		while(i < l.size()) {
			if(p.test((l.get(i)))) {
				acum.add(l.get(i));
			}
			i++;
		}
		return acum;
	}
	
	
	//Recursivo no final
	public static List<Punto2D> ejercicio4RecursivoNoFinal(List<Punto2D> l, Predicate<Punto2D> p){
		return ejercicio4RecursivoNoFinalAux(l, p, 0);
	}
	
	public static List<Punto2D> ejercicio4RecursivoNoFinalAux(List<Punto2D> l, Predicate<Punto2D> p, int i){
		List<Punto2D> res = new ArrayList<Punto2D>();
		if((i < l.size())) {
			res = ejercicio4RecursivoNoFinalAux(l, p, i+1);
			if(p.test((l.get(i)))) {
				res.add(l.get(i));
			}
		} 
		
		return res;
	}
	
	
	//Recursivo final
	public static List<Punto2D> ejercicio4RecursivoFinal(List<Punto2D> l, Predicate<Punto2D> p){
		return ejercicio4RecursivoFinalAux(l, p, 0, new ArrayList<Punto2D>());
	}
	
	public static List<Punto2D> ejercicio4RecursivoFinalAux(List<Punto2D> l, Predicate<Punto2D> p, int i, List<Punto2D> acum){
		List<Punto2D> res = new ArrayList<Punto2D>();
		if(i < l.size()) {
			if(p.test((l.get(i)))) {
				acum.add(l.get(i));
			}			
			res = ejercicio4RecursivoFinalAux(l, p, i+1, acum);
		} else {
			res = acum;
		}
		
		return res;
	}
	

	//Funcional
	public static <T> List<Punto2D> ejercicio4Funcional(List<Punto2D> l, Predicate<? super Punto2D> p){
		return l.stream().filter(p).collect(Collectors.toList());
	}
	
	
	
	//Muestradatos
	public static void muestraDatos(String fichero) {
		List<String> l = LeeFichero.leeFichero(fichero);
		List<Punto2D> listaPuntos = new ArrayList<Punto2D>();
		String[] parse;
		String aux;
		Predicate<Punto2D> prd1 = v -> v.getCuadrante().equals(Punto2D.Cuadrante.PRIMER_CUADRANTE);
		Predicate<Punto2D> prd2 = v -> v.getCuadrante().equals(Punto2D.Cuadrante.SEGUNDO_CUADRANTE);
		Predicate<Punto2D> prd3 = v -> v.getCuadrante().equals(Punto2D.Cuadrante.TERCER_CUADRANTE);
		Predicate<Punto2D> prd4 = v -> v.getCuadrante().equals(Punto2D.Cuadrante.CUARTO_CUADRANTE);
		
		for (String s : l) {
			Punto2D p = Punto2D.create();
			aux = s.substring(1, s.length()-1);
			parse = aux.split(",");
			p.setX(Double.parseDouble(parse[0]));
			p.setY(Double.parseDouble(parse[1]));
			listaPuntos.add(p);
		}
		
		System.out.println("-------------------- TEST DEL MÉTODO ITERATIVO --------------------");
		System.out.println("Entrada: " + l + ";  Selecc. puntos del primer cuadrante");
		System.out.println("Salida: " + ejercicio4Iterativo(listaPuntos, prd1));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del segundo cuadrante");
		System.out.println("Salida: " + ejercicio4Iterativo(listaPuntos, prd2));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del tercer cuadrante");
		System.out.println("Salida: " + ejercicio4Iterativo(listaPuntos, prd3));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del cuarto cuadrante");
		System.out.println("Salida: " + ejercicio4Iterativo(listaPuntos, prd4));
		System.out.println("========================================\n");
		
		
		System.out.println("-------------------- TEST DEL MÉTODO RECURSIVO FINAL --------------------");
		System.out.println("Entrada: " + l + ";  Selecc. puntos del primer cuadrante");
		System.out.println("Salida: " + ejercicio4RecursivoFinal(listaPuntos, prd1));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del segundo cuadrante");
		System.out.println("Salida: " + ejercicio4RecursivoFinal(listaPuntos, prd2));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del tercer cuadrante");
		System.out.println("Salida: " + ejercicio4RecursivoFinal(listaPuntos, prd3));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del cuarto cuadrante");
		System.out.println("Salida: " + ejercicio4RecursivoFinal(listaPuntos, prd4));
		System.out.println("========================================\n");
		
		
		System.out.println("-------------------- TEST DEL MÉTODO RECURSIVO NO FINAL --------------------");
		System.out.println("Entrada: " + l + ";  Selecc. puntos del primer cuadrante");
		System.out.println("Salida: " + ejercicio4RecursivoNoFinal(listaPuntos, prd1));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del segundo cuadrante");
		System.out.println("Salida: " + ejercicio4RecursivoNoFinal(listaPuntos, prd2));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del tercer cuadrante");
		System.out.println("Salida: " + ejercicio4RecursivoNoFinal(listaPuntos, prd3));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del cuarto cuadrante");
		System.out.println("Salida: " + ejercicio4RecursivoNoFinal(listaPuntos, prd4));
		System.out.println("========================================\n");
		
		
		System.out.println("-------------------- TEST DEL MÉTODO FUNCIONAL J11 (sólo Java) --------------------");
		System.out.println("Entrada: " + l + ";  Selecc. puntos del primer cuadrante");
		System.out.println("Salida: " + ejercicio4Funcional(listaPuntos, prd1));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del segundo cuadrante");
		System.out.println("Salida: " + ejercicio4Funcional(listaPuntos, prd2));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del tercer cuadrante");
		System.out.println("Salida: " + ejercicio4Funcional(listaPuntos, prd3));
		System.out.println("Entrada: " + l + ";  Selecc. puntos del cuarto cuadrante");
		System.out.println("Salida: " + ejercicio4Funcional(listaPuntos, prd4));
		System.out.println("========================================\n");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
