package ejercicio1PD;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.Files2;

public class DatosEstanteria {
	private static List<Libro> libros;
	private static List<Estante> estantes;
	
	public static void iniDatos(String fichero) {
		List<String> l = Files2.getLines(fichero);
		
		List<Libro> librosRes = new ArrayList<Libro>();
		List<Estante> estantesRes = new ArrayList<Estante>();
		
		int anchura = Integer.parseInt((l.get(l.size() - 1)));
		int aux = l.size(); // La posicion de # sera siempre menor que el maximo de lineas del fichero

		for (int i = 0; i < l.size() - 2; i++) { // size-2 -> anchura estanteria + #
			if (l.get(i).equals("#")) {
				aux = i;
			}
			if (i < aux) {
				librosRes.add(Libro.create(l.get(i)));
			}
			if (i > aux) {
				estantesRes.add(Estante.create(Integer.parseInt(l.get(i)), anchura));
			}
		}
		
		libros = librosRes;
		estantes = estantesRes;

		
	}
	
	public static void iniDatosVoraz(String fichero) {	//Ordenar los libros de menor anchura a mayor anchura
		DatosEstanteria.iniDatos(fichero);
		libros = libros.stream().sorted(Comparator.comparing(Libro::getAnchura)).collect(Collectors.toList());
	}

	public static List<Libro> getLibros() {
		return libros;
	}

	public static List<Estante> getEstantes() {
		return estantes;
	}
	
	public static Integer getAnchuraLibro(int i) {
		return libros.get(i).getAnchura();
	}
	
	public static Integer getAlturaLibro(int i) {
		return libros.get(i).getAltura();
	}
	
	public static Integer getAnchuraEstante() {
		return estantes.get(0).getAnchura();
	}
	
	public static Integer getAlturaEstante(int i) {
		return estantes.get(i).getAltura();
	}
	
	public static int getNumeroEstantes() {
		return estantes.size();
	}
	
	public static List<Integer> getListaAnchuras(){
		return IntStream.range(0, getNumeroEstantes()).map(i -> i = getAnchuraEstante()).boxed().collect(Collectors.toList());
	}
	
}
