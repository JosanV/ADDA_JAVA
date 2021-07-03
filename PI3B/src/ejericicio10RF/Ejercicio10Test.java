package ejericicio10RF;

import java.util.List;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.common.Files2;
import us.lsi.flowgraph.FlowGraph;
import us.lsi.flowgraph.FlowGraphSolution;
import us.lsi.flowgraph.FlowGraph.TipoDeOptimizacion;
import us.lsi.graphs.GraphsReader;
import us.lsi.pli.AlgoritmoPLI;
import us.lsi.pli.SolutionPLI;


public class Ejercicio10Test {

	public static void main(String[] args) {
		test10("ficheros/PI3Ej10DatosEntrada_andalucia.txt", "ficheros/GrafoSolucion10And.txt");
		test10("ficheros/PI3Ej10DatosEntrada_castillalamancha.txt", "ficheros/GrafoSolucion10CLM.txt");
	}
	
	public static void test10(String fEntrada, String fSalida) {
		SimpleWeightedGraph<Ciudad, Carretera> g = GraphsReader.newGraph(fEntrada,
				Ciudad::ofFormat,
				Carretera::ofFormat,
				() -> new SimpleWeightedGraph<Ciudad, Carretera>(Ciudad::of, Carretera::of),
				Carretera::getKm);
		List<String> datos = Files2.getLines(fEntrada);
		Ciudad origen = Ciudad.ofFormat(datos.get(1).split(","));
		Ciudad destino = Ciudad.ofFormat(datos.get(2).split(","));
		
		Ejercicio10RF a = new Ejercicio10RF(origen, destino, g);
		a.creaFichero("./ficheros/fSalida10.txt");
		
		FlowGraph.allInteger = true;
		FlowGraph fg = FlowGraph.newGraph("./ficheros/fSalida10.txt", TipoDeOptimizacion.Max);
		SolutionPLI s = AlgoritmoPLI.getSolution(fg.getConstraints());
		FlowGraphSolution fgs = FlowGraphSolution.create(fg, s);
		fgs.exportToDot(fSalida);
	}
}
