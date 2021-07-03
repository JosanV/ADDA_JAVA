package segundoentregable2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class Ejercicio6 {
	
	//Binario
	public static <R, E> BinaryTree<R> ejercicio6Binary(BinaryTree<E> bt, Function<E, R> f){
		BinaryTree<R> res =  BinaryTree.empty();
		if(bt.isEmpty()) {
			return res;
		} else if(bt.isLeaf()) {
			E e = bt.getLabel();
			res = BinaryTree.leaf(f.apply(e));
		} else {
			BinaryTree<E> bleft = bt.getLeft();
			BinaryTree<E> bright = bt.getRight();
			res = BinaryTree.binary(f.apply(bt.getLabel()), ejercicio6Binary(bleft, f), ejercicio6Binary(bright, f));
		}
		return res;
	}
	
	
	//N-ario
	public static <E, R> Tree<R> ejercicio6Nary(Tree<E> arbol, Function<E, R> f) {
		Tree<R> res = null;
		if(arbol.isEmpty()) {
			res = Tree.empty();					
		} else if(arbol.isLeaf()) {
			res = Tree.leaf(f.apply(arbol.getLabel()));
		} else {
			res = Tree.nary(f.apply(arbol.getLabel()), ejercicio6NaryAux(arbol.getChildren().iterator(), new ArrayList<>(), f));
		}
		return res;
	}
	
	private static <E, R> List<Tree<R>> ejercicio6NaryAux(Iterator<Tree<E>> itArboles, List<Tree<R>> res, Function<E, R> f) {
		if(itArboles.hasNext()) {
			res.add(ejercicio6Nary(itArboles.next(), f));
			ejercicio6NaryAux(itArboles, res, f);
		}
		return res;
	}
	
	
	//Muestra ejercicio
	public static void muestraEjercicio6(String ficheroBinario, String ficheroNario) {
		
		Function<String, Integer> f1 = x -> Integer.parseInt((String) x);
		Function<String, Integer> f2 = x -> Integer.toString(Math.abs(Integer.parseInt((String) x))).length();
		Function<String, Integer> f3 = x -> (int) Math.pow(2, maximo(Integer.parseInt((String) x)));
		
		//Binario
		System.out.println("          TEST DEL EJERCICIO 6 PARA ARBOLES BINARIOS\r\n" + 
				   "==============================================================");
		List<String> res = LeeFichero.leeFichero(ficheroBinario);

		for (String s : res) {
			BinaryTree<String> aux = BinaryTree.parse(s);
			System.out.println("Entrada: " + s);
			System.out.println("		Salida1: " + ejercicio6Binary(aux, f1));
			System.out.println("		Salida2: " + ejercicio6Binary(aux, f2));
			System.out.println("		Salida3: " + ejercicio6Binary(aux, f3));
			System.out.println("--------------------------------------------------------------");
			
		}
		
		//N-ario
		System.out.println("\n          TEST DEL EJERCICIO 6 PARA ARBOLES N-ARIOS\r\n" + 
				   "==============================================================");
		List<String> res2 = LeeFichero.leeFichero(ficheroNario);

		for (String s : res2) {
			Tree<String> aux = Tree.parse(s);
			System.out.println("Entrada: " + aux);
			System.out.println("		Salida1: " + ejercicio6Nary(aux, f1));
			System.out.println("		Salida2: " + ejercicio6Nary(aux, f2));
			System.out.println("		Salida3: " + ejercicio6Nary(aux, f3));
			System.out.println("--------------------------------------------------------------");
			
		}
	}
	
	private static int maximo(int n) {
		int res = 0;
		n = Math.abs(n);
		while (n > 0) {
			if(n % 10 > res) {
				res = n % 10;
			}
		    n = n / 10;
		}
		return res;
	}
	
}
