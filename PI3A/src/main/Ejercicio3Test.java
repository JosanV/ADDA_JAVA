package main;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.common.Files2;
import us.lsi.graphs.GraphsReader;

public class Ejercicio3Test {
	public static void main(String[] args) {
		test3A("ficheros/PI3Ej3DatosEntradaGrafo.txt");
		
		test3B("ficheros/PI3Ej3DatosEntradaGrafo.txt", "ficheros/PI3Ej3DatosEntradaB.txt");
		
		test3C("ficheros/PI3Ej3DatosEntradaGrafo.txt", "ficheros/PI3Ej3DatosEntradaC.txt");
	}
	
	public static void test3A(String fichero) {
		SimpleGraph<Usuario,Relacion> g = GraphsReader.newGraph(fichero,
				Usuario::ofFormat,
				Relacion::ofFormat,
				() -> new SimpleWeightedGraph<Usuario,Relacion>(Usuario::of, Relacion::of));
		
		Set<Graph<Usuario, Relacion>> res = Ejercicio3.ejercicio3A(g);
		System.out.println("Existen " + res.size() + " grupos. Su composicion es:" );
		int i = 1;
		for (Graph<Usuario, Relacion> graph : res) {
			System.out.println("Grupo " + i + " (" + graph.vertexSet().size() + " usuarios):");
			Set<Usuario> usuarios = graph.vertexSet();
			for (Usuario u : usuarios) {
				System.out.print(u.getNombre() + ", ");
			}
			System.out.println("");
			i++;
		}
		
	}
	
	
	public static void test3B(String ficheroGrafo, String ficheroParejas) {
		SimpleGraph<Usuario,Relacion> g = GraphsReader.newGraph(ficheroGrafo,
				Usuario::ofFormat,
				Relacion::ofFormat,
				() -> new SimpleWeightedGraph<Usuario,Relacion>(Usuario::of, Relacion::of));
		
		List<String> l = Files2.getLines(ficheroParejas);
		for (String s : l) {
			String[] pareja = s.split(",");
			Usuario a = new Usuario(pareja[0]);
			Usuario b = new Usuario(pareja[1]);
			Integer res = Ejercicio3.ejercicio3B(g,a,b);
			System.out.println("El grado de distanciamiento entre " + pareja[0] + " y " + pareja[1] + " es:       " + res);
		}
		
	}
	
	
	public static void test3C(String ficheroGrafo, String ficheroUsuarios) {
		SimpleGraph<Usuario,Relacion> g = GraphsReader.newGraph(ficheroGrafo,
				Usuario::ofFormat,
				Relacion::ofFormat,
				() -> new SimpleWeightedGraph<Usuario,Relacion>(Usuario::of, Relacion::of));
		
		List<String> l = Files2.getLines(ficheroUsuarios);
		for (String s : l) {
			Usuario u = new Usuario(s);
			for(Usuario usuario : g.vertexSet()) {
				if(usuario.equals(u)) {
					System.out.println(s);
					u = usuario;
				}
			}
			System.out.println("Los usuarios compatibles con " + u.getNombre() + " son: " + Ejercicio3.ejercicio3C(g, u).toString() + "\n");
		}
	}
	
}
