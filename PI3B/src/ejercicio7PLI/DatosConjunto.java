package ejercicio7PLI;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Streams2;

public class DatosConjunto {
	
	private static List<Conjunto> listasNumeros;

	public static void iniDatos(String fichero) {
		listasNumeros = Streams2.fromFile(fichero)
				.map(s -> Conjunto.create(s))
				.collect(Collectors.toList());
	}

	public static List<Conjunto> getListasNumeros() {
		return listasNumeros;
	}
	
	
	public static Conjunto getListaNumeros(int i) {
		return getListasNumeros().get(i);
	}
	
	public static Integer getSumaObjetivo(int i) {
		return getListaNumeros(i).getResultado();
	}
	
}
