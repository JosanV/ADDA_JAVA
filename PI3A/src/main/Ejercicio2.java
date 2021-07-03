package main;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;


public class Ejercicio2 {
	public static <V,E> Integer ejercicio2Tamanio(Graph<V,E> g) {
		return new GreedyColoring<V, E>(g).getColoring().getNumberColors();
	}
	
	public static <V,E> List<Set<V>> ejercicio2Composicion(Graph<V,E> g) {
		return new GreedyColoring<V, E>(g).getColoring().getColorClasses();
	}
}
