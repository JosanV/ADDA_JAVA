package ejercicio2BT;

import ejercicio2PD.DatosTarea;
import us.lsi.bt.AlgoritmoBT;

public class ejercicio2TestBT {
	public static void main(String[] args) {
		DatosTarea.iniDatos("ficheros/PI4AEj2DatosEntrada2.txt");
		
		AlgoritmoBT.metricasOK = true;
		AlgoritmoBT.conFiltro = false;
		
		EstadoTarea e = EstadoTarea.createInitial();
		var a = AlgoritmoBT.create(e);
		a.ejecuta();
//		System.out.println(a.getSolucion()); 
//		System.out.println(a.getMejorValor());
//		System.out.println(AlgoritmoBT.metricas);
		
		a.getSolucion().getM().forEach((key, value) -> System.out.println("Tarea nº " + key + ": " + value));
		System.out.println("Ganancia total: " + a.getMejorValor());
	}
	
}
