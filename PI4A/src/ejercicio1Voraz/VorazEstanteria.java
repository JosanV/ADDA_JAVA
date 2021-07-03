package ejercicio1Voraz;

import java.util.List;

import ejercicio1BT.EstadoEstanteria;
import ejercicio1PD.DatosEstanteria;
import ejercicio1PD.SolucionEstanteria;

public class VorazEstanteria {
	
	public static SolucionEstanteria getSolucionVoraz(EstadoEstanteria e) {
		SolucionEstanteria r = SolucionEstanteria.empty();
			while(!e.esCasoBase()) {
				List<Integer> alternativas = e.getAlternativas();
				Integer a = alternativas.get(alternativas.size()-1);	//coge el primero, y si se llena coge el siguiente y así.
				r.add(DatosEstanteria.getLibros().get(e.getIndex()), a);
				e = e.avanza(a);
			}
		
		
		return r;
	}
	
	public static void main(String[] args) {
		DatosEstanteria.iniDatosVoraz("ficheros/PI4AEj1DatosEntrada4.txt");
		System.out.println(DatosEstanteria.getLibros());
		
		EstadoEstanteria e = EstadoEstanteria.createInitial();
		SolucionEstanteria s = getSolucionVoraz(e);
		System.out.println(s);
	}
}
