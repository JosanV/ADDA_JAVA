package ejercicio6AG;

import java.util.ArrayList;
import java.util.List;

import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;

public class ProblemaTareasAGBin implements ValuesInRangeProblemAG<Integer, List<Integer>>{
	
	public ProblemaTareasAGBin(String fichero) {
		DatosTarea.iniDatos(fichero);
	}
	
	@Override
	public Integer getVariableNumber() {
		return DatosTarea.getTareas().size();
	}

	@Override
	public Integer getMax(Integer i) {
		return 2;
	}

	@Override
	public Integer getMin(Integer i) {
		return 0;
	}

	@Override
	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {
//		Double ganancia = 0.;
//		Integer solapamiento = 0;
//		int c = 0;
//		for (Integer i : cr.decode()) {
//			if(i == 1) {
//				ganancia = ganancia + DatosTarea.getGanancia(c);
//				for(int j=0; j<DatosTarea.getTareas().size(); j++) {
//					solapamiento = solapamiento + DatosTarea.getSolapamiento(c, j);
//				}
//			}
//			c++;
//		}
////		System.out.println(ganancia + " : " + solapamiento);
//		Double fitness = ganancia - 40*solapamiento;
//		System.out.println(ganancia);
//		System.out.println("Double: " + fitness);
//		return fitness;
		
		List<Tarea> tareas = DatosTarea.getTareas();
		Double fitness = 0.;
		int n = 0;
		List<Tarea> listaAux = new ArrayList<>();
		for (Integer i : cr.decode()) {
			if (i == 1) {
				fitness += tareas.get(n).getGanancia();
				listaAux.add(tareas.get(n));
			}
			n++;
		}

		for (int i = 0; i < listaAux.size(); i++) {	//Recorrido de la lista con las tareas escogidas
			for (int j = 0; j < listaAux.size(); j++) {
				if (listaAux.get(j).getInicio().isAfter(listaAux.get(i).getInicio())
						&& listaAux.get(j).getInicio().isBefore(listaAux.get(i).getFin())
						&& !listaAux.get(j).equals(listaAux.get(i))) {
					fitness = fitness - 1000;	//Penalizar tareas en horarios solapados
				}
			}
		}
		return fitness;
	
	}

	@Override
	public List<Integer> getSolucion(ValuesInRangeChromosome<Integer> cr) {
		return cr.decode();
	}
	
}
