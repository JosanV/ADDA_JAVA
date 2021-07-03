package ejercicio2PD;

import java.util.stream.Collectors;

import us.lsi.pd.AlgoritmoPD;

public class ejercicio2Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatosTarea.iniDatos("ficheros/PI4AEj2DatosEntrada2.txt");
		
		
		AlgoritmoPD.isRandomize = false;
		AlgoritmoPD.conFiltro = false;
		AlgoritmoPD.metricasOK = true;
		
//		System.out.println(DatosTarea.getTareas());
		ProblemaTareasPD p = ProblemaTareasPD.createInitial();
//		System.out.println("Problema inicial: " + p);
		var a = AlgoritmoPD.createPDR(p);
		a.ejecuta();
//		System.out.println(AlgoritmoPD.metricas.toString());
//		System.out.println("Solucion= "+a.getSolucion());
		a.getSolucion().getM().forEach((key, value) -> System.out.println("Tarea nº " + key + ": " + value));
		System.out.println("Ganancia total: " + a.getSolucionParcial().valorDeObjetivo);
		
	}

}
