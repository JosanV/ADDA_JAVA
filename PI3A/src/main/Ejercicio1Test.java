package main;

import java.io.PrintWriter;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.IntegerComponentNameProvider;

import us.lsi.graphs.GraphsReader;
import us.lsi.common.Files2;
import us.lsi.graphcolors.GraphColors;

public class Ejercicio1Test {

	public static void main(String[] args) {
		//"ficheros/andalucia.txt"
		test1A_Andalucia("ficheros/PI3Ej1DatosEntrada_andalucia.txt");
		test1A_CLM("ficheros/PI3Ej1DatosEntrada_castillalamancha.txt");
		
		test1B_Andalucia("ficheros/PI3Ej1DatosEntrada_andalucia.txt");
		test1B_CLM("ficheros/PI3Ej1DatosEntrada_castillalamancha.txt");
		
		test1C_Andalucia("ficheros/PI3Ej1DatosEntrada_andalucia.txt");
		test1C_CLM("ficheros/PI3Ej1DatosEntrada_castillalamancha.txt");
		
	}
	
	
	public static void test1A_Andalucia(String fichero) {
		Graph<Ciudad,Carretera> g = GraphsReader.newGraph(fichero,
				Ciudad::ofFormat,
				Carretera::ofFormat,
				() -> new SimpleWeightedGraph<Ciudad,Carretera>(Ciudad::of, Carretera::of),
				Carretera::getKm);
		
		//Primer predicado
		Graph<Ciudad, Carretera> g1 = Ejercicio1.ejercicio1a(g, x->x.getNombre().contains("e"), x->x.getKm()<100);
		
		//Para expotar en dot primer predicado
		DOTExporter<Ciudad, Carretera> exp1 = new DOTExporter<Ciudad, Carretera>(new IntegerComponentNameProvider<>(),
				x -> x.getNombre(),
				x -> String.format("%.2f", x.getKm()),
				e -> GraphColors.getColorIf(1, e, x->x.getNombre().contains("u")),
				null);
		
		PrintWriter archivo1 = Files2.getWriter("./ficheros/ej1A_pred1_Andalucia.gv");
		exp1.exportGraph(g1, archivo1);
		
		
		//Segundo predicado
		Graph<Ciudad, Carretera> g2 = Ejercicio1.ejercicio1a(g, x->x.getHabitantes() < 500000, 
				x->((x.getSource().getNombre().length() >= 8) || (x.getTarget().getNombre().length() >= 8)) && 
				(x.getKm() > 150));
	
		//Para expotar en dot segundo predicado
		DOTExporter<Ciudad, Carretera> exp2 = new DOTExporter<Ciudad, Carretera>(new IntegerComponentNameProvider<>(),
				x -> x.getNombre(),
				x -> String.format("%.2f", x.getKm()),
				e -> GraphColors.getFilledColor(9),
				e -> GraphColors.getStyleIf("bold", e, x-> x.getKm() < 200));
		
		//Meterlo en un archivo para visualizarlo
		PrintWriter archivo2 = Files2.getWriter("./ficheros/ej1A_pred2_Andalucia.gv");
		exp2.exportGraph(g2, archivo2);

	}
	
	
	public static void test1A_CLM(String fichero) {
		Graph<Ciudad,Carretera> g = GraphsReader.newGraph(fichero,
				Ciudad::ofFormat,
				Carretera::ofFormat,
				() -> new SimpleWeightedGraph<Ciudad,Carretera>(Ciudad::of, Carretera::of),
				Carretera::getKm);
		
		//Primer predicado
		Graph<Ciudad, Carretera> g1 = Ejercicio1.ejercicio1a(g, x->x.getNombre().contains("e"), x->x.getKm()<100);
				
		//Para expotar en dot primer predicado
		DOTExporter<Ciudad, Carretera> exp1 = new DOTExporter<Ciudad, Carretera>(new IntegerComponentNameProvider<>(),
				x -> x.getNombre(),
				x -> String.format("%.2f", x.getKm()),
				e -> GraphColors.getColorIf(1, e, x->x.getNombre().contains("u")),
				null);
		
		//Meterlo en un archivo para visualizarlo
		PrintWriter archivo1 = Files2.getWriter("./ficheros/ej1A_pred1_CLM.gv");
		exp1.exportGraph(g1, archivo1);
	
		
		//Segundo predicado
		Graph<Ciudad, Carretera> g2 = Ejercicio1.ejercicio1a(g, x->x.getHabitantes() < 500000, 
				x->((x.getSource().getNombre().length() >= 8) || (x.getTarget().getNombre().length() >= 8)) && 
				(x.getKm() > 150));
		
		//Para expotar en dot segundo predicado
		DOTExporter<Ciudad, Carretera> exp2 = new DOTExporter<Ciudad, Carretera>(new IntegerComponentNameProvider<>(),
				v -> v.getNombre(),
				e -> String.format("%.2f", e.getKm()),
				v -> GraphColors.getFilledColor(9),
				e -> GraphColors.getStyleIf("bold", e, x-> x.getKm() < 200));
				
		//Meterlo en un archivo para visualizarlo
		PrintWriter archivo2 = Files2.getWriter("./ficheros/ej1A_pred2_CLM.gv");
		exp2.exportGraph(g2, archivo2);
				
	}
	
	
	
	public static void test1B_Andalucia(String fichero) {
		Graph<Ciudad,Carretera> g = GraphsReader.newGraph(fichero,
				Ciudad::ofFormat,
				Carretera::ofFormat,
				() -> new SimpleWeightedGraph<Ciudad,Carretera>(Ciudad::of, Carretera::of),
				Carretera::getKm);
		
		Graph<Ciudad, Carretera> g1 = Ejercicio1.ejercicio1b(g, Carretera::ofWeight, Carretera::getKm, Carretera::getSource,
				Carretera::getTarget);
		
		DOTExporter<Ciudad, Carretera> exp = new DOTExporter<Ciudad, Carretera>(new IntegerComponentNameProvider<>(),
				v -> v.getNombre(),
				e -> String.format("%.3f", e.getKm()),
				v -> GraphColors.getColor(0),
				e -> GraphColors.getColorIf(6, e, z -> g.containsEdge(z)));	//Si la arista existe en el original
		
		PrintWriter archivo = Files2.getWriter("./ficheros/ej1B_Andalucia.gv");
		exp.exportGraph(g1, archivo);
	}
	
	public static void test1B_CLM(String fichero) {
		Graph<Ciudad,Carretera> g = GraphsReader.newGraph(fichero,
				Ciudad::ofFormat,
				Carretera::ofFormat,
				() -> new SimpleWeightedGraph<Ciudad,Carretera>(Ciudad::of, Carretera::of),
				Carretera::getKm);
		
		Graph<Ciudad, Carretera> g1 = Ejercicio1.ejercicio1b(g, Carretera::ofWeight, Carretera::getKm, Carretera::getSource,
				Carretera::getTarget);
		
		DOTExporter<Ciudad, Carretera> exp = new DOTExporter<Ciudad, Carretera>(new IntegerComponentNameProvider<>(),
				v -> v.getNombre(),
				e -> String.format("%.3f", e.getKm()),
				v -> GraphColors.getColor(0),
				e -> GraphColors.getColorIf(6, e, z -> g.containsEdge(z)));	//Si la arista existe en el original
		
		PrintWriter archivo = Files2.getWriter("./ficheros/ej1B_CLM.gv");
		exp.exportGraph(g1, archivo);
	}
	
	
	public static void test1C_Andalucia(String fichero) {
		SimpleWeightedGraph<Ciudad,Carretera> g = GraphsReader.newGraph(fichero,
				Ciudad::ofFormat,
				Carretera::ofFormat,
				() -> new SimpleWeightedGraph<Ciudad,Carretera>(Ciudad::of, Carretera::of),
				Carretera::getKm);
		
		Graph<Ciudad, Carretera> g1 = Ejercicio1.ejercicio1c(g, Carretera::reverse);
		
		DOTExporter<Ciudad, Carretera> exp = new DOTExporter<Ciudad, Carretera>(new IntegerComponentNameProvider<>(),
				v -> v.getNombre(),						//Nombre vertices
				e -> (e.getNombre() + "--" + String.format("%.3f", e.getKm()) + " kms"),	//Nombre aristas
				v -> GraphColors.getFilledColor(4),			//Propiedades vertices
				e -> GraphColors.getStyle("bold"));	//Propiedades aristas
		
		PrintWriter archivo = Files2.getWriter("./ficheros/ej1C_Andaluca.gv");
		exp.exportGraph(g1, archivo);
		
	}
	
	
	public static void test1C_CLM(String fichero) {
		SimpleWeightedGraph<Ciudad,Carretera> g = GraphsReader.newGraph(fichero,
				Ciudad::ofFormat,
				Carretera::ofFormat,
				() -> new SimpleWeightedGraph<Ciudad,Carretera>(Ciudad::of, Carretera::of),
				Carretera::getKm);
		
		Graph<Ciudad, Carretera> g1 = Ejercicio1.ejercicio1c(g, Carretera::reverse);

		DOTExporter<Ciudad, Carretera> exp = new DOTExporter<Ciudad, Carretera>(new IntegerComponentNameProvider<>(),
				v -> v.getNombre(),						//Nombre vertices
				e -> (e.getNombre() + "--" + String.format("%.3f", e.getKm()) + " kms"),	//Nombre aristas
				v -> GraphColors.getFilledColor(4),			//Propiedades vertices
				e -> GraphColors.getStyle("bold"));	//Propiedades aristas
		
		PrintWriter archivo = Files2.getWriter("./ficheros/ej1C_CLM.gv");
		exp.exportGraph(g1, archivo);
		
	}
	

}
