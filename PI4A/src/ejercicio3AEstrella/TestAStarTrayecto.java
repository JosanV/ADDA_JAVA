package ejercicio3AEstrella;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import ejercicio3PD.DatosTrayecto;
import ejercicio3PD.SolucionTrayecto;
import us.lsi.astar.AStarAlgorithm;
import us.lsi.astar.AStarGraph;
import us.lsi.astar.PredicateHeuristic;
import us.lsi.graphs.Graphs2;

public class TestAStarTrayecto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatosTrayecto.iniDatos("ficheros/PI4AEj3DatosEntrada1SinGrafo.txt");
		System.out.println("DT inicial: " + DatosTrayecto.getTrayectos());
		
		//Vertice inicial y final 
		TrayectoVertex e0 = TrayectoVertex.of();
		TrayectoVertex e1 = TrayectoVertex.lastVertex();
		
		
		//Grafo virtual. Aristas -> Alternativas y su peso
		AStarGraph<TrayectoVertex, TrayectoEdge> graph = Graphs2.astarSimpleVirtualGraph(x -> x.getEdgeWeight());
		
		//Algoritmo A*
		BiFunction<TrayectoVertex, TrayectoVertex, Double> heuristic = (v1,v2)->-TrayectoVertex.heuristic(v1,v2);
		AStarAlgorithm<TrayectoVertex, TrayectoEdge> a = AStarAlgorithm.of(graph,e0,e1,heuristic);
		
		//Solucion
		SolucionTrayecto s = SolucionTrayecto.empty();
		a.getPathEdgeList().stream().forEach(e -> s.add(e.a.intValue(), DatosTrayecto.getTrayecto(e.getSource().getIndex())));
		System.out.println(s);
		
//		List<EstanteriaEdge> edges = a.getPathEdgeList();
//		System.out.println(edges);
		
		
	}

}
