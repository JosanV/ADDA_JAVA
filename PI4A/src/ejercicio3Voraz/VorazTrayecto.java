package ejercicio3Voraz;

import java.util.Comparator;
import java.util.List;

import ejercicio3BT.EstadoTrayecto;
import ejercicio3PD.DatosTrayecto;
import ejercicio3PD.SolucionTrayecto;
import us.lsi.common.Files2;

public class VorazTrayecto {

	public static SolucionTrayecto getSolucionVoraz(EstadoTrayecto e) {
		SolucionTrayecto r = SolucionTrayecto.empty();
			while(!e.esCasoBase()) {
				List<Integer> alternativas = e.getAlternativas();
				Integer a = alternativas.stream().max(Comparator.comparing(x -> DatosTrayecto.getTrayecto(x).getRatioAvancePrecio())).get();
//				Integer a = alternativas.get(0);
				r.add(a, DatosTrayecto.getTrayecto(a));
				e = e.avanza(a);
			}
		
		
		return r;
	}
	
	
	public static void main(String[] args) {
		
		DatosTrayecto.iniDatos("ficheros/PI4AEj3DatosEntrada1SinGrafo.txt");
		
		for (String s : Files2.getLines("ficheros/PI4AEj3DatosEntrada2.txt")) {
			String[] valores = s.split(",");
			int origen = Integer.parseInt(valores[0]);
			int destino = Integer.parseInt(valores[1]);
			DatosTrayecto.origen = origen;
			DatosTrayecto.destino = destino;
			
			EstadoTrayecto e = EstadoTrayecto.createInitial();
			SolucionTrayecto a = getSolucionVoraz(e);

			System.out.println("Origen = " + origen + "; Destino = " + destino + "; Solucion:");
			a.getM().forEach((key, value) -> System.out.println("      ID Trayecto = " + key + ": " + value));
			System.out.println("Ganancia total: " + e.getCosteAcumulado());
			System.out.println("=================================================================================");
		}

	}

}
