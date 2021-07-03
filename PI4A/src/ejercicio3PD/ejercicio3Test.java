package ejercicio3PD;

import us.lsi.common.Files2;
import us.lsi.pd.AlgoritmoPD;

public class ejercicio3Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatosTrayecto.iniDatos("ficheros/PI4AEj3DatosEntrada1SinGrafo.txt");
		
		AlgoritmoPD.isRandomize = false;
		AlgoritmoPD.conFiltro = false;
		AlgoritmoPD.metricasOK = true;
		
		for(String s : Files2.getLines("ficheros/PI4AEj3DatosEntrada2.txt")) {
			String[] valores = s.split(",");
			int origen = Integer.parseInt(valores[0]);
			int destino = Integer.parseInt(valores[1]);
			ProblemaTrayectosPD p = ProblemaTrayectosPD.createInitial(origen, destino);
			var a = AlgoritmoPD.createPDR(p);
			a.ejecuta();
			
			System.out.println("Origen = " + origen + "; Destino = " + destino + "; Solucion:");
			a.getSolucion().getM().forEach((key, value) -> System.out.println("      ID Trayecto = " + key + ": " + value));
			System.out.println("Coste total: " + a.getSolucionParcial().valorDeObjetivo);
			System.out.println("=================================================================================");
		}
		
		
		
		
	}

}
