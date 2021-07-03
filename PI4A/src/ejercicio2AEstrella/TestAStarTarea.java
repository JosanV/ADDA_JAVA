package ejercicio2AEstrella;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import ejercicio2PD.DatosTarea;
import ejercicio2PD.SolucionTarea;
import us.lsi.astar.AStarAlgorithm;
import us.lsi.astar.AStarGraph;
import us.lsi.astar.PredicateHeuristic;
import us.lsi.graphs.Graphs2;

public class TestAStarTarea {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatosTarea.iniDatosVoraz("ficheros/PI4AEj2DatosEntrada2.txt");
		System.out.println("DT inicial: " + DatosTarea.getTareas());
		
		//Vertice inicial y final 
		TareaVertex e0 = TareaVertex.of();
		TareaVertex e1 = TareaVertex.lastVertex();	//Esto
		
		//Predicate
//		Predicate<TareaVertex> predVerFinal = v -> v.getIndex() == DatosTarea.getTareas().size();
//		PredicateHeuristic<TareaVertex> heuristic = (x,y) -> TareaVertex.heuristic(x, (TareaVertex) predVerFinal);
		
		//Grafo virtual. Aristas -> Alternativas y su peso
		AStarGraph<TareaVertex, TareaEdge> graph = Graphs2.astarSimpleVirtualGraph(x -> x.getEdgeWeight());
		
		//Algoritmo A*
		BiFunction<TareaVertex, TareaVertex, Double> heuristic = (v1,v2)->-TareaVertex.heuristic(v1,v2);
		AStarAlgorithm<TareaVertex, TareaEdge> a = AStarAlgorithm.of(graph,e0,e1,heuristic);
		
		//Solucion
		SolucionTarea s = SolucionTarea.empty();
		a.getPathEdgeList().stream().forEach(e -> s.add(e.a.intValue(), DatosTarea.getTareas().get(e.getSource().getIndex())));
		System.out.println(s);
		
//		List<EstanteriaEdge> edges = a.getPathEdgeList();
//		System.out.println(edges);
		
		
	}

}
