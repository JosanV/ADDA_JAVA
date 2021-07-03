package segundoentregable2;

import java.util.List;
import java.util.function.Function;

import us.lsi.common.Tuple;
import us.lsi.common.Tuple2;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio9 {

	public static Tuple2<Integer, Integer> ejercicio9(Tree<Integer> t) {
		Tuple2<Integer, Integer> res = Tuple.create(Integer.MAX_VALUE, Integer.MIN_VALUE);	//(min,max)
		if (t.isEmpty()) {
			res = res.setKey(null);
			res = res.setValue(null);
		} else if (t.isLeaf()) {
			res = res.setKey(t.getLabel());
			res = res.setValue(t.getLabel());
		} else {
			for (int i = 0; i < t.getNumOfChildren(); i++) {
				Tuple2<Integer, Integer> aux = ejercicio9(t.getChild(i)); // Llamada recursiva

				if (res.getV1() != null && res.getV2() != null && aux.getV1() != null && aux.getV2() != null) {
					// Min
					if (aux.getV1() < res.getV1()) {
						res = res.setKey(aux.getV1());
					}
					if (t.getLabel() < res.getV1()) {
						res = res.setKey(t.getLabel());
					}

					// Max
					if (aux.getV2() > res.getV2()) {
						res = res.setValue(aux.getV2());
					}
					if (t.getLabel() > res.getV2()) {
						res = res.setValue(t.getLabel());
					}
				}

			}
		}
		return res;
	}

	public static void muestraejercicio9(String fichero) {
		List<String> res = LeeFichero.leeFichero(fichero);
		Function<String, Integer> f1 = x -> Integer.parseInt((String) x);
		for (String s : res) {
			Tree<String> t = Tree.parse(s);
			Tuple2<Integer, Integer> tuple = ejercicio9(Ejercicio6.ejercicio6Nary(t, f1));
	
			System.out.println("Arbol: " + t);
			System.out.println("Min: " + tuple.getV1() + ", Max: " + tuple.getV2() + "\n");
		}
	}

}
