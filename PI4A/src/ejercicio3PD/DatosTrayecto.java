package ejercicio3PD;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Streams2;

public class DatosTrayecto {
	
	private static List<Trayecto> trayectos;
	public static Integer origen;
	public static Integer destino;
//	private static Map<Integer, List<Integer>> destinos;
	
	public static void iniDatos(String fichero) {
		trayectos = Streams2.fromFile(fichero)
					.map(t -> Trayecto.create(t))
					.collect(Collectors.toList());
		
//		destinos = getDestinos();
	}
	
	public static void iniDatosVoraz(String fichero) {
		DatosTrayecto.iniDatos(fichero);
		trayectos = trayectos.stream()
				.sorted(Comparator.comparing(Trayecto::getRatioAvancePrecio).reversed())
				.collect(Collectors.toList());		
	}

	
	
	public static Integer getOrigen() {
		return origen;
	}

	public static Integer getDestino() {
		return destino;
	}

	public static List<Trayecto> getTrayectos() {
		return trayectos;
	}

	public static Trayecto getTrayecto(int i) {
		return trayectos.get(i);
	}
	
//	private static Map<Integer, List<Integer>> getDestinos() {	//key: estacion origen - valor: indices de los trayectos para esa estacion
//		Map<Integer, List<Integer>> res = new HashMap<Integer, List<Integer>>();
//		for(int i = 0; i < DatosTrayecto.getTrayectos().size(); i++) {
//			int estacionOrigen = DatosTrayecto.getTrayecto(i).getOrigen();
//			if(res.containsKey(estacionOrigen)) {
//				List<Integer> destinos = res.get(estacionOrigen);
//				destinos.add(i);
//			} else {
//				List<Integer> destinos = new ArrayList<Integer>();
//				destinos.add(i);
//				res.put(estacionOrigen, destinos);
//			}
//		}
//		return res;
//	}
//	
//	public static List<Integer> getDestinosEstacion(int origen) {
//		List<Integer> res = new ArrayList<Integer>(destinos.get(origen));
//		return res;
//	}
	
	
	
}
