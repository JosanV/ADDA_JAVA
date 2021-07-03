package ejercicio6PLI;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Streams2;

public class DatosTarea {

	private static List<Tarea> tareas;
	private static Comparator<Tarea> ordenObjetos;

	public static void iniDatos(String fichero) {
		ordenObjetos = Comparator.naturalOrder();
		tareas = Streams2.fromFile(fichero)
				.map(s -> Tarea.create(s))
				.collect(Collectors.toList());
	}

	public static List<Tarea> getTareas() {
		return tareas;
	}

	public static Comparator<Tarea> getOrdenObjetos() {
		return ordenObjetos;
	}

	public static Tarea getObjeto(int i) {
		return DatosTarea.getTareas().get(i);
	}

	public static Integer getGanancia(int i) {
		return DatosTarea.getTareas().get(i).getGanancia();
	}

	public static LocalTime getInicio(int i) {
		return DatosTarea.getTareas().get(i).getInicio();
	}

	public static LocalTime getDuracion(int i) {
		return DatosTarea.getTareas().get(i).getDuracion();
	}

	public static LocalTime getHorafinal(int i) {
		LocalTime a = DatosTarea.getTareas().get(i).getInicio()
				.plusHours(DatosTarea.getTareas().get(i).getDuracion().getHour());
		return a.plusMinutes(DatosTarea.getTareas().get(i).getDuracion().getMinute());
	}

	public static Integer getSolapamiento(int i1, int i2) {
		int a = 1;
		if (DatosTarea.getTareas().get(i2).getInicio().isAfter(DatosTarea.getTareas().get(i1).getInicio())
				&& DatosTarea.getTareas().get(i2).getInicio().isBefore(DatosTarea.getHorafinal(i1))) {
			a = 1;

		} else if (DatosTarea.getHorafinal(i2).isAfter(DatosTarea.getTareas().get(i1).getInicio())
				&& DatosTarea.getHorafinal(i2).isBefore(DatosTarea.getHorafinal(i1))) {
			a = 1;

		} else if (DatosTarea.getHorafinal(i1).equals(DatosTarea.getTareas().get(i2).getInicio())) {

			a = 0;

		} else if (DatosTarea.getTareas().get(i1).getInicio().equals(DatosTarea.getTareas().get(i2).getInicio())
				&& DatosTarea.getHorafinal(i1).equals(DatosTarea.getHorafinal(i2))) {
			a = 0;

		} else {
			a = 0;
		}
		return a;

	}

}
