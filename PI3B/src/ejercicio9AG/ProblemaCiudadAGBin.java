package ejercicio9AG;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;

public class ProblemaCiudadAGBin implements ValuesInRangeProblemAG<Integer, List<Integer>>{
	private SimpleWeightedGraph<Ciudad, Carretera> grafo;
	private List<Ciudad> vertices;
	private Set<Carretera> aristas;
	private Ciudad origen;
	private Ciudad destino;
	
	public SimpleWeightedGraph<Ciudad, Carretera> getGrafo() {
		return grafo;
	}

	public List<Ciudad> getVertices() {
		return vertices;
	}

	public Set<Carretera> getAristas() {
		return aristas;
	}

	public Ciudad getOrigen() {
		return origen;
	}

	public Ciudad getDestino() {
		return destino;
	}

	public ProblemaCiudadAGBin(SimpleWeightedGraph<Ciudad, Carretera> grafo, Ciudad origen, Ciudad destino) {
		super();
		this.grafo = grafo;
		this.vertices = grafo.vertexSet().stream().collect(Collectors.toList());
		this.aristas = grafo.edgeSet();
		this.origen = origen;
		this.destino = destino;
	}

	@Override
	public Integer getVariableNumber() {
		return vertices.size();
	}

	@Override
	public Integer getMax(Integer i) {
		return 2;
	}

	@Override
	public Integer getMin(Integer i) {
		return 0;
	}
	
	@Override
	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {
		Double km = 0.;
		Integer fallo = 0;
		List<Integer> ls = cr.decode();
		SimpleWeightedGraph<Ciudad, Carretera> aux = new SimpleWeightedGraph<Ciudad, Carretera>(Carretera.class);
		
		int cont = 0;
		for(Integer i: ls) {
			if(i > 0) {
				aux.addVertex(vertices.get(cont));
			}
			cont++;
		}
		
		for (Carretera c : aristas) {
			if(aux.vertexSet().contains(c.getSource()) && aux.vertexSet().contains(c.getTarget())) {
				aux.addEdge(c.getSource(), c.getTarget(), c);
				km = km + c.getKm();
			}
		}
		
		if(!aux.vertexSet().contains(origen)) {
			fallo = fallo + 1000;
		}
		if(!aux.vertexSet().contains(destino)) {
			fallo = fallo + 1000;
		}
		
		int menor = 0;
		int normal = 0;
		int mayor = 0;
		for (Ciudad c: aux.vertexSet()) {
			if(c.getHabitantes()<=50000)	menor++;
			if(c.getHabitantes()<50000 && c.getHabitantes()>100000)	normal++;
			if(c.getHabitantes()>=100000)	mayor++;
		}
		
		if(menor == 0) fallo = fallo +100;
		if(normal == 0) fallo = fallo +100;
		if(mayor == 0) fallo = fallo +100;
		
		Double fitness = -km -1000*fallo;
		return fitness;
	}

	@Override
	public List<Integer> getSolucion(ValuesInRangeChromosome<Integer> cr) {
		return cr.decode();
	}
}
