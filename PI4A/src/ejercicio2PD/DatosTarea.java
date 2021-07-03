package ejercicio2PD;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Streams2;

public class DatosTarea {

	private static List<Tarea> tareas;

	public static void iniDatos(String fichero) {
		tareas = Streams2.fromFile(fichero)
				.map(s -> Tarea.create(s))
				.collect(Collectors.toList());
	}
	
	public static void iniDatosVoraz(String fichero) {
		DatosTarea.iniDatos(fichero);
//		tareas = tareas.stream().sorted(Comparator.comparing(Tarea::getRatioGananciaDuracion).reversed()).collect(Collectors.toList());
		Collections.reverse(tareas);
	}


	public static List<Tarea> getTareas() {
		return tareas;
	}

	public static Tarea getTarea(int i) {
		return tareas.get(i);
	}

	public static Integer getGanancia(int i) {
		return DatosTarea.getTarea(i).getGanancia();
	}

	public static LocalTime getInicio(int i) {
		return DatosTarea.getTarea(i).getInicio();
	}

	public static Duration getDuracion(int i) {
		return DatosTarea.getTarea(i).getDuracion();
	}

	public static LocalTime getFinal(int i) {
		return DatosTarea.getTarea(i).getFinal();
	}


}
