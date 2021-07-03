package ejercicio3BT;

import ejercicio3PD.DatosTrayecto;
import us.lsi.bt.AlgoritmoBT;
import us.lsi.common.Files2;

public class ejercicio3TestBT {
	public static void main(String[] args) {
		DatosTrayecto.iniDatos("ficheros/PI4AEj3DatosEntrada1SinGrafo.txt");

		AlgoritmoBT.metricasOK = true;
		AlgoritmoBT.conFiltro = false;
		

		for(String s : Files2.getLines("ficheros/PI4AEj3DatosEntrada2.txt")) {
			
			String[] valores = s.split(",");
			int origen = Integer.parseInt(valores[0]);
			int destino = Integer.parseInt(valores[1]);
			DatosTrayecto.origen = origen;
			DatosTrayecto.destino = destino;
			
			EstadoTrayecto e = EstadoTrayecto.createInitial();
			var a = AlgoritmoBT.create(e);
			a.ejecuta();
			
			System.out.println("Origen = " + origen + "; Destino = " + destino + "; Solucion:");
			a.getSolucion().getM().forEach((key, value) -> System.out.println("      ID Trayecto = " + key + ": " + value));
			System.out.println("Coste total: " + a.getMejorValor());
			System.out.println("=================================================================================");
		}
	
	
	}
	
}
