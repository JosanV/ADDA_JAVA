package practica2individual;

import java.util.List;

import us.lsi.common.Files2;

public class LeeFichero {
	public static List<String> leeFichero(String fichero){
		return Files2.getLines(fichero);
	}
}
