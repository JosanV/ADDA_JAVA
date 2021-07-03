package segundoentregable2;

import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.Tree;

public class Ejercicio7 {
	public static <E> List<E> ejercicio7(Tree<E> t) {
		List<E> res = new ArrayList<E>();
		if (t.isEmpty()) {
			return res;
		} else if (t.isLeaf()) {
			res.add(t.getLabel());
		} else {
			int i = 0;
			while (i < t.getNumOfChildren()) {
				res.addAll(ejercicio7(t.getChild(i)));
				i++;
			}
		}

		return res;

	}

	public static void muestraEjercicio7(String fichero) {
		List<String> res = LeeFichero.leeFichero(fichero);
		for (String s : res) {
			Tree<String> t = Tree.parse(s);
			System.out.println("Arbol: " + t);
			System.out.println("Lista de hojas: " + ejercicio7(t));
			System.out.println("\n");
		}

	}

}
