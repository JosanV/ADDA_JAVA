package ejercicio2Voraz;

import java.util.List;

import ejercicio2BT.EstadoTarea;
import ejercicio2PD.DatosTarea;
import ejercicio2PD.SolucionTarea;

public class VorazTarea {
	
	public static SolucionTarea getSolucionVoraz(EstadoTarea e) {
		SolucionTarea r = SolucionTarea.empty();
			while(!e.esCasoBase()) {
				List<Integer> alternativas = e.getAlternativas();
				Integer a = alternativas.get(alternativas.size()-1);
				if(a == 1) {
					r.add(e.getIndex()+1, DatosTarea.getTarea(e.getIndex()));
				}
				
				e = e.avanza(a);
			}
		
		
		return r;
	}
	
	
	public static void main(String[] args) {

		DatosTarea.iniDatosVoraz("ficheros/PI4AEj2DatosEntrada2.txt");
		System.out.println(DatosTarea.getTareas());
		
		EstadoTarea e = EstadoTarea.createInitial();
		SolucionTarea s = getSolucionVoraz(e);
		System.out.println(s);
		
		s.getM().forEach((key, value) -> System.out.println("Tarea nº " + key + ": " + value));
		System.out.println("Ganancia total: " + e.getGananciaAcumulada());
		
		
	}

}
