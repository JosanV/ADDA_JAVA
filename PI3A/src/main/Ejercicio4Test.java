package main;
import java.io.PrintWriter;
import java.util.Set;

import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.IntegerComponentNameProvider;

import us.lsi.common.Files2;
import us.lsi.common.Tuple2;
import us.lsi.graphcolors.GraphColors;
import us.lsi.graphs.GraphsReader;
public class Ejercicio4Test {

	public static void main(String[] args) {
		test4("ficheros/PI3Ej4DatosEntrada1.txt");
		test4("ficheros/PI3Ej4DatosEntrada2.txt");
		test4("ficheros/PI3Ej4DatosEntrada3.txt");
		
		
	}
	
	public static void test4(String fichero) {
		SimpleGraph<Camara,Pasillo> g = GraphsReader.newGraph(fichero,
				Camara::ofFormat,
				Pasillo::ofFormat,
				() -> new SimpleWeightedGraph<Camara,Pasillo>(Camara::of, Pasillo::of),
				Pasillo::getDistancia);
		
		Set<Camara> resA = Ejercicio4.ejercicio4A(g);
		Tuple2<Integer, SpanningTree<Pasillo>> resB = Ejercicio4.ejercicio4B(g);
		
		System.out.println("Las camaras deben colocarse en los siguientes "  + resA.size() + " cruces: ");
		for (Camara c : resA) {
			System.out.println("       - Cruce " + c.getNombre());
		}
		System.out.println("El nº de equipos necesarios es " + resB.getV1());
		System.out.println("Los metros de cable necesarios son " + resB.getV2().getWeight() + "\n");
		
		//Para expotar en dot primer predicado
		DOTExporter<Camara, Pasillo> exp = new DOTExporter<Camara, Pasillo>(new IntegerComponentNameProvider<>(),
				v -> v.getNombre(),
				e -> String.format("%.2f", e.getDistancia()),
				v -> GraphColors.getColorIf(7, v, x -> resA.contains(x)),
				e -> GraphColors.getColorIf(7, e, x -> resB.getV2().getEdges().contains(x)));
		
		PrintWriter archivo = Files2.getWriter("./ficheros/ej4_fichero" + fichero.charAt(fichero.length()-5) +".gv");
		exp.exportGraph(g, archivo);
		
	}

}
