package ejercicio1AEstrella;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import ejercicio1PD.DatosEstanteria;
import ejercicio1PD.SolucionEstanteria;
import us.lsi.astar.AStarAlgorithm;
import us.lsi.astar.AStarGraph;
import us.lsi.astar.PredicateHeuristic;
import us.lsi.graphs.Graphs2;

public class TestAStarEstanteria {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatosEstanteria.iniDatosVoraz("ficheros/PI4AEj1DatosEntrada5.txt");
		System.out.println("DT inicial: " + DatosEstanteria.getLibros());
		
		//Vertice inicial y final 
		EstanteriaVertex e0 = EstanteriaVertex.of();
		EstanteriaVertex e1 = EstanteriaVertex.lastVertex();
		
		
		//Grafo virtual. Aristas -> Alternativas y su peso
		AStarGraph<EstanteriaVertex, EstanteriaEdge> graph = Graphs2.astarSimpleVirtualGraph(x -> x.getEdgeWeight());
		
		//Algoritmo A*
		BiFunction<EstanteriaVertex, EstanteriaVertex, Double> heuristic = (v1,v2)->-EstanteriaVertex.heuristic(v1,v2);
		AStarAlgorithm<EstanteriaVertex, EstanteriaEdge> a = AStarAlgorithm.of(graph,e0,e1,heuristic);
		
		//Solucion
		SolucionEstanteria s = SolucionEstanteria.empty();
		a.getPathEdgeList().stream().forEach(e -> s.add(DatosEstanteria.getLibros().get(e.getSource().getIndex()) ,e.a.intValue()));
		System.out.println(s);
		
//		List<EstanteriaEdge> edges = a.getPathEdgeList();
//		System.out.println(edges);
		
		
	}

}
