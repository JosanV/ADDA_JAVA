package ejercicio5AG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;

public class ProblemaUniversoAGBin implements ValuesInRangeProblemAG<Integer, List<Integer>>{

	public ProblemaUniversoAGBin(String fichero) {
		DatosConjunto.iniDatos(fichero);
	}	
	
	@Override
	public Integer getVariableNumber() {
		return DatosConjunto.getConjuntos().size();
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
		Set<Integer> universo = DatosConjunto.getUniverso();
		Double peso = 0.0;
		Set<Integer> universoAux = new HashSet<Integer>();
		
		int i = 0;
		for (Integer j: cr.decode()) {
			if(j == 1) {
				peso = peso + DatosConjunto.getConjunto(i).getPeso();
				universoAux.addAll(DatosConjunto.getConjunto(i).getSetElementos());
			}
			i++;
		}
		
		Double fitness = -(peso * peso) - (10000*(universo.size() - universoAux.size()));
		return fitness;
	}

	@Override
	public List<Integer> getSolucion(ValuesInRangeChromosome<Integer> cr) {
		return cr.decode();	//Lista de ceros y unos si los coge
	}

}
