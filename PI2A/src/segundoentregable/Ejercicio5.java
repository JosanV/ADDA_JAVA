package segundoentregable;

import java.util.Arrays;
import java.util.List;

public class Ejercicio5 {
	
	private static int indexMinimo(int[] numeros, int i, int j) {
		int index = i;
		int min = numeros[index];

		for (int x = i; x <= j; x++) {
			if (numeros[x] < min) {
				min = numeros[x];
				index = x;
			}
		}
		return index;
	}
	
	public static int ejercicio5Rec(int[] numeros) {
		return ejercicio5RecAux(numeros, 0, numeros.length-1, 0);
	}
	
	public static int ejercicio5RecAux(int[] numeros, int i, int j, int acum) {
		
		int n = indexMinimo(numeros, i, j);
		if(i == j) {					//si queda solo 1
			if(numeros[n] > acum) {
				acum = numeros[n];
			}
		} else if(j == n) {				//si esta el ultimo (j)
			int tam = (j-i) + 1;
			if(tam*numeros[n] > acum) {
				acum = tam*numeros[n];
			}
			acum = ejercicio5RecAux(numeros, i, n-1, acum);	//izq
		} else if(i == n) {				//si esta el primero (i)
			int tam = (j-i) + 1;
			if(tam*numeros[n] > acum) {
				acum = tam*numeros[n];
			}
			acum = ejercicio5RecAux(numeros, n+1, j, acum);	//der
		} else {
			int tam = (j-i) + 1;
			if(tam*numeros[n] > acum) {
				acum = tam*numeros[n];
			}
			acum = ejercicio5RecAux(numeros, n+1, j, acum);	//der
			acum = ejercicio5RecAux(numeros, i, n-1, acum);	//izq
		}
		return acum;
	}
	
	public static void muestraEjercicio5(String fichero) {
		List<String> l = LeeFichero.leeFichero(fichero);
		for (String s : l) {
			String[] numeros = s.split(",");
			int[] numerosInt = new int[numeros.length];
			for (int i = 0; i < numeros.length; i++) {
				numerosInt[i] = Integer.valueOf(numeros[i]);
			}
			System.out.println("Histograma: " + Arrays.deepToString(numeros));
			System.out.println("Area maxima: " + ejercicio5Rec(numerosInt) + "\n");
		}
	}
}
