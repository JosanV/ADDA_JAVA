package main;

import java.util.function.Function;
import java.util.function.Predicate;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.common.TriFunction;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.views.CompleteGraphView;
import us.lsi.graphs.views.SubGraphView;

public class Ejercicio1 {
	
	public static <V, E> Graph<V, E> ejercicio1a(Graph<V, E> g, Predicate<V> pred1, Predicate<E> pred2){
		Graph<V, E> res = SubGraphView.of(g, pred1, pred2);
		return res;
	}
	
	public static <V, E> Graph<V, E> ejercicio1b(Graph<V, E> g, TriFunction<V, V, Double, E> t, Function<E, Double> f1, 
			Function<E, V> f2, Function<E, V> f3) {
		Graph<V, E> res = CompleteGraphView.of(g, t, 1000.0, f1, f2, f3);	//1000.0 porque lo ponen en el PDF de sols
		return res;
	}
	
	public static <V, E> Graph<V, E> ejercicio1c(SimpleWeightedGraph<V, E> g, Function<E,E> f1) {
		Graph<V, E> res = Graphs2.toDirectedWeightedGraph(g, f1);
		return res;
	}
	
	
}
