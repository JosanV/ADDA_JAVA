package main;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import us.lsi.common.Files2;

public class Ejercicio2Test {
	public static void main(String[] args) {
		test2("ficheros/PI3Ej2DatosEntrada.txt");
		
	}
	
	public static void test2(String fichero) {
		Graph<Integer, DefaultEdge> g = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
		List<String> l = Files2.getLines(fichero);
		
		int n = Integer.parseInt(l.get(0));
		for (int i = 1; i <= n; i++) {	//Vertices - No hay comensal 0
			g.addVertex(i);
		}
		
		for (int i = 1; i < l.size(); i++) {	//Aristas - Primera linea no es arista por lo que i = 1
			String[] s = l.get(i).split(",");
			Integer i1 = Integer.parseInt(s[0]);
			Integer i2 = Integer.parseInt(s[1]);
			g.addEdge(i1, i2);
		}
//		g.get
		
		Integer resNumMesas = Ejercicio2.ejercicio2Tamanio(g);	//Apartado A
		List<Set<Integer>> resComposicionMesas = Ejercicio2.ejercicio2Composicion(g);
		
		System.out.println("Salida apartado A: " + resNumMesas);
		System.out.println("Salida apartado B: ");
		int i = 1;
		for (Set<Integer> set : resComposicionMesas) {
			System.out.println("Mesa " + i + ". Tiene " + set.size() + " comensales: " + set.toString());
			i++;
		}
		
	}
}
