package segundoentregable;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio1 {
	
	//Ejercicio 1
	public static boolean ejercicio1Rec(int[][] entrada) {
		return ejercicio1RecAux(entrada, 0, 0, entrada.length-1, entrada.length-1, entrada.length);
	}
	
	public static boolean ejercicio1RecAux(int[][] entrada, int x1, int y1, int x2, int y2, int n) {
		boolean res;
		if(n == 2) {
			res = entrada[x1][y1] < entrada[x2][y2];
		} else {
			int fX=(x1+x2)/2;	//Formula recurrente de x
			int fY=(y1+y2)/2;	//Formula recurrente de y
			res = (entrada[x1][y1] < entrada[x2][y2])
				&& ejercicio1RecAux(entrada, x1, y1, fX, fY, n/2)					//Arriba izquierda
				&& ejercicio1RecAux(entrada, x1, y1 + (n/2), fX, y2, n/2)			//Arriba derecha
				&& ejercicio1RecAux(entrada, x1 + (n/2), y1, x2, fY, n/2)			//Abajo izquierda
				&& ejercicio1RecAux(entrada, x1 + (n/2), y1 + (n/2), x2, y2, n/2);	//Abajo derecha
		}
		  
		return res;
	}
	
	public static int[][] leeEjercicio1(String linea) {
		String[] filas = linea.split(",");
		int[][] m = new int[filas.length][filas.length];
		
		List<String> lfilas = new ArrayList<>();
		for (int i = 0; i < filas.length; i++) {
			lfilas.add(filas[i]);
		}
		
		for (int i = 0; i < filas.length; i++) {
			String[] colms = lfilas.get(i).replace('{', ' ').replace('}', ' ').trim().split(" ");
			for (int j = 0; j < colms.length; j++) {
				m[i][j] = Integer.valueOf(colms[j]);
			}
		}
		return m;
		
		
	}
	
	public static void muestraEjercicio1(String fichero) {
		List<String> l = LeeFichero.leeFichero(fichero);
		for (String string : l) {
			System.out.println("La matriz: " + string);
			if(ejercicio1Rec(leeEjercicio1(string))) {
				System.out.println("Cumple la propiedad\n");
			} else {
				System.out.println("NO cumple la propiedad\n");
			}
		}
	}
	
}
