package ejercicio7AG;

import java.util.List;

import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;

public class ProblemaSumaAGRange implements ValuesInRangeProblemAG<Integer, List<Integer>>{
	
	public ProblemaSumaAGRange(String linea) {
		DatosConjunto.iniDatos(linea);
	}
	
	@Override
	public Integer getVariableNumber() {
		return DatosConjunto.getListaNumeros().size();
	}

	@Override
	public Integer getMax(Integer i) {
		return (DatosConjunto.getSumaObjetivo() /
				DatosConjunto.getListaNumeros().stream().min((x,y)-> x.compareTo(y)).get()) + 1;
	}

	@Override
	public Integer getMin(Integer i) {
		return 0;
	}

	@Override
	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {
		Integer nElementos = 0;
		Double suma = 0.;
		int c = 0;
		for (Integer i : cr.decode()) {
			if(i > 0) {	//Si coge ese numero 1 o mas veces
				nElementos = nElementos + i;
				suma = suma + i*DatosConjunto.getListaNumeros().get(c);
			}
			c++;
		}
		Double dif = DatosConjunto.getSumaObjetivo() - suma;
		Double fitness = -nElementos - (1000 * Math.abs((dif)));	//
		return fitness;
	}

	@Override
	public List<Integer> getSolucion(ValuesInRangeChromosome<Integer> cr) {
		// TODO Auto-generated method stub
		return cr.decode();
	}
	
}
