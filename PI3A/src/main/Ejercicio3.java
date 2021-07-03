package main;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class Ejercicio3 {
		public static <V,E> Set<Graph<V,E>> ejercicio3A(Graph<V,E> g){
			return new BiconnectivityInspector(g).getConnectedComponents();
		}
		
		public static <V,E> Integer ejercicio3B(Graph<V,E> g, V a, V b) {
			Integer res = -1;
			if(g.containsVertex(a) && g.containsVertex(b)) {	//Si los vertices existen en ese grafo
				GraphPath<V,E> camino = new DijkstraShortestPath(g).getPath(a, b);
				if(camino != null) {
					res = camino.getLength() - 1;	//aristas-1 = vertices intermedios
				}
			}
			return res;
		}
		
		public static <E> Set<String> ejercicio3C(Graph<Usuario,E> g, Usuario a){	//Necesita el metodo getCategoria
			Set<String> res = new HashSet<String>();
			for(Usuario u : g.vertexSet()) {
				Integer dist = ejercicio3B(g, a, u);
				if((u != a) && ((u.getCategoria().equals(a.getCategoria())) || ((dist != -1) && (dist <= 2)))) {
					res.add(u.getNombre());
				}
			}
			return res;
			
		}
}
