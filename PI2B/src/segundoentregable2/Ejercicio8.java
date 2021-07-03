package segundoentregable2;

import java.util.List;
import java.util.function.Function;

import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio8 {
	public static int ejercicio8(BinaryTree<Integer> bt, int i) {
		int res = 0;
		if (bt.isEmpty()) {
			res = -1;
		} else if (bt.isLeaf()) {
			if (bt.getLabel() == i) {
				res = 0;
			} else {
				res = -1;
			}
		} else if (bt.isBinary() && bt.getLabel() == i) {
			res = 0;
		} else if (bt.isBinary() && bt.getLabel() < i) {
			res = ejercicio8(bt.getRight(), i);
			if (res >= 0) {
				res++;
			}
		} else if (bt.isBinary() && bt.getLabel() > i) {
			res = ejercicio8(bt.getLeft(), i);
			if (res >= 0) {
				res++;
			}
		}

		return res;
	}

	public static void muestraEjercicio8(String fichero) {

		List<String> res = LeeFichero.leeFichero(fichero);
		Function<String, Integer> f1 = x -> Integer.parseInt((String) x);

		System.out.println("                    TEST DEL EJERCICIO 8\r\n"
				+ "============================================================");
		for (String s : res) {
			int n = Character.getNumericValue(s.charAt(s.length() - 1));
			String aux = s.substring(0, s.length() - 2);
			BinaryTree<String> btString = BinaryTree.parse(aux);
			BinaryTree<Integer> btInt = Ejercicio6.ejercicio6Binary(btString, f1);
			System.out.println("Entrada: " + btInt);
			System.out.println("Salida: El nivel del elemento " + n + " es: " + ejercicio8(btInt, n));
			System.out.println("------------------------------------------------------------");
		}

	}
}
