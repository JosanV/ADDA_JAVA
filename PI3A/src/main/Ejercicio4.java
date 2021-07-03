package main;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.graph.AsSubgraph;

import us.lsi.common.Tuple;
import us.lsi.common.Tuple2;

public class Ejercicio4 {
	public static Set<Camara> ejercicio4A(Graph<Camara, Pasillo> g){
		return new GreedyVCImpl<Camara, Pasillo>(g).getVertexCover();
	}
	
	public static Tuple2<Integer, SpanningTree<Pasillo>> ejercicio4B(Graph<Camara, Pasillo> g){	//<Numero equipos, Cableado>
		Tuple2<Integer, SpanningTree<Pasillo>> res = Tuple.create(0, null);
		Set<Camara> vCamaras = ejercicio4A(g);	//Vertices con camara
		AsSubgraph<Camara, Pasillo> gCamaras = new AsSubgraph<Camara, Pasillo>(g, vCamaras);	//Grafo solo con vertices con camaras
		
		Set<Graph<Camara, Pasillo>> equipos = new BiconnectivityInspector<Camara, Pasillo>(gCamaras).getConnectedComponents();	//Habrá un equpo por componente conexa
		SpanningTree<Pasillo> caminoCable = new KruskalMinimumSpanningTree<Camara, Pasillo>(gCamaras).getSpanningTree();	//Calculara el mejor recorrido para los cables
		
		res = res.setKey(equipos.size());
		res = res.setValue(caminoCable);
		return res;
	}
	
}
