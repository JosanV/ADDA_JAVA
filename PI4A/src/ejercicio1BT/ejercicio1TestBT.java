package ejercicio1BT;

import ejercicio1PD.DatosEstanteria;
import us.lsi.bt.AlgoritmoBT;

public class ejercicio1TestBT {
	
	public static void main(String[] args) {
		DatosEstanteria.iniDatos("ficheros/PI4AEj1DatosEntrada4.txt");
		
		AlgoritmoBT.metricasOK = true;
		AlgoritmoBT.conFiltro = false;
		
		
		EstadoEstanteria e = EstadoEstanteria.createInitial();
		var a = AlgoritmoBT.create(e);
		a.ejecuta();
		System.out.println(a.getSolucion()); 
		System.out.println(a.getMejorValor());
		System.out.println(AlgoritmoBT.metricas);
	}
	
	
}
