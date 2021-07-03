package ejercicio5PLI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.Streams2;

public class DatosConjunto {
	
	private static List<Conjunto> conjuntos;
	
	
	
	public static void iniDatos(String fichero) {
		conjuntos = Streams2.fromFile(fichero)
				.map(s -> Conjunto.create(s))
				.collect(Collectors.toList());
	}

	public static List<Conjunto> getConjuntos() {
		return conjuntos;
	}
	
	public static Set<Integer> getUniverso() {
		Set<Integer> universo = new HashSet<Integer>();
		getConjuntos().stream().forEach(s -> universo.addAll(s.getSetElementos()));
		return universo;
	}
	
	public static List<Integer> incluyeIndice(int i){
		
		return IntStream.range(0, getConjuntos().size())
				.boxed()
				.filter(p -> getConjunto(p).getSetElementos().contains(i))
				.collect(Collectors.toList());
			
	}
	
	public static Conjunto getConjunto(int i) {
		return getConjuntos().get(i);
	}
	
	public static Integer getPeso(int i){
		return getConjuntos().get(i).getPeso();
	}
	
	public static Set<Integer> getSetElementos(int i){
		return getConjuntos().get(i).getSetElementos();
	}
	
}
