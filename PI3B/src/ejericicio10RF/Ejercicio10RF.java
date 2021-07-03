package ejericicio10RF;

import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import org.jgrapht.Graphs;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.common.Files2;

public class Ejercicio10RF {
	
	private Ciudad origen;
	private Ciudad destino;
	private SimpleWeightedGraph<Ciudad, Carretera> grafo;
	
	public Ejercicio10RF(Ciudad origen, Ciudad destino, SimpleWeightedGraph<Ciudad, Carretera> grafo) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.grafo = grafo;
	}

	public Ciudad getOrigen() {
		return origen;
	}

	public Ciudad getDestino() {
		return destino;
	}

	public SimpleWeightedGraph<Ciudad, Carretera> getGrafo() {
		return grafo;
	}
	
	public void creaFichero(String file) {
		PrintWriter f = Files2.getWriter(file);

		f.println("#VERTEX#");
		for (Ciudad c : grafo.vertexSet()) {
			if(c.equals(origen)) {
				f.println(c.getNombre() + ",Source,0.0," + String.valueOf(Double.valueOf(Graphs.neighborListOf(grafo, origen).size())) + ",0.0," + c.getNombre());
			} else if (c.equals(destino)) {
				f.println(c.getNombre() + ",Sink,0.0," + String.valueOf(Double.valueOf(Graphs.neighborListOf(grafo, destino).size())) + ",0.0," + c.getNombre());
			} else {
				f.println(c.getNombre() + ",Intermediate,0.0,1.0,0.0," + c.getNombre());
			}
		}
		f.println("#EDGE#");
		List<Carretera> aristas = grafo.edgeSet().stream().collect(Collectors.toList());
		for(int i = 0; i < aristas.size(); i++) {
			String n1 = aristas.get(i).getSource().getNombre();
			String n2 = aristas.get(i).getTarget().getNombre();
			String km = String.valueOf(aristas.get(i).getKm());
			
			if(n1.equals(origen.getNombre())) {
				f.println(n1 + "," + n2 + ",0.0,1.0," + km);
			} else if(n1.equals(destino.getNombre())) {
				f.println(n2 + "," + n1 + ",0.0,1.0," + km);
			} else if(n2.equals(origen.getNombre())) {
				f.println(n2 + "," + n1 + ",0.0,1.0," + km);
			} else if(n2.equals(destino.getNombre())) {
				f.println(n1 + "," + n2 + ",0.0,1.0," + km);
			} else {
				f.println(n1 + "," + n2 + ",0.0,1.0," + km);
				f.println(n2 + "," + n1 + ",0.0,1.0," + km);
			}
		}
		f.close();
	}
}
