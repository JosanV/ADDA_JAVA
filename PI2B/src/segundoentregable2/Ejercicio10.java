package segundoentregable2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio10 {
	public static Set<String> ejercicio10(BinaryTree<String> bt, String e) {
		Set<String> res = new HashSet<String>();
		if (bt.isEmpty()) {
			return res;
		} else if (bt.isLeaf()) {
			if (bt.getLabel().compareTo(e) >= 0) {
				res.add(bt.getLabel());
			}
		} else if (bt.isBinary() && bt.getLabel().compareTo(e) > 0) {
			res.addAll(ejercicio10(bt.getLeft(), e));
			res.addAll(ejercicio10(bt.getRight(), e));
			res.add(bt.getLabel());
		} else if (bt.isBinary() && bt.getLabel().compareTo(e) == 0) {
			res.addAll(ejercicio10(bt.getRight(), e));
			res.add(bt.getLabel());
		} else {
			res.addAll(ejercicio10(bt.getRight(), e));
		}
		return res;
	}
	
	
	public static void muestraEjercicio10(String fichero) {
		List<String> res = LeeFichero.leeFichero(fichero);
		
		System.out.println("                                                       TEST DEL EJERCICIO 10\r\n" + 
				"=======================================================================================================================================================");
		for (String s : res) {
			String[] nombres = s.split(";");
			BinaryTree<String> bt = BinaryTree.parse(nombres[0]);
			Set<String> aux = ejercicio10(bt, nombres[1]);
			System.out.println("Entrada: " + bt);
			System.out.println("Salida:  Los elementos mayores o iguales que "+ nombres[1] +" son " + aux.size() + ": " + aux);
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}
	
}
