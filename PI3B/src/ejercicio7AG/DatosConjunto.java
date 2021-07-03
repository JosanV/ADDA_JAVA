package ejercicio7AG;

import java.util.List;

public class DatosConjunto {
	
	private static Conjunto conjunto;

	public static void iniDatos(String linea) {
		conjunto = Conjunto.create(linea);
	}

	public static Conjunto getConjunto() {
		return conjunto;
	}
	
	
	public static List<Integer> getListaNumeros() {
		return getConjunto().getElementos();
	}
	
	public static Integer getSumaObjetivo() {
		return getConjunto().getResultado();
	}
	
}
