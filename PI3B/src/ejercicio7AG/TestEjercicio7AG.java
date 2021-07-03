package ejercicio7AG;

import java.util.List;

import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agchromosomes.ChromosomeFactory.CrossoverType;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.Files2;

public class TestEjercicio7AG {

	public static void main(String[] args) {
		
		List<String> fichero = Files2.getLines("ficheros/PI3Ej7DatosEntrada.txt");
		for (String string : fichero) {
			test7(string);
		}
	
	}
	
	private static void test7(String linea){
		AlgoritmoAG.ELITISM_RATE  = 0.20;
		AlgoritmoAG.CROSSOVER_RATE = 0.5;
		AlgoritmoAG.MUTATION_RATE = 0.95;
		AlgoritmoAG.POPULATION_SIZE = 150;
		
		StoppingConditionFactory.NUM_GENERATIONS = 4000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 0;	
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.SolutionsNumber;
		
		ChromosomeFactory.crossoverType = CrossoverType.OnePoint;
		
		ValuesInRangeProblemAG<Integer, List<Integer>> p = new ProblemaSumaAGRange(linea);
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> ap = AlgoritmoAG.create(ChromosomeType.Range, p);
		ap.ejecuta();
		
		
		//MOSTRAR CON FORMATO LEGIBLE
		System.out.println("=============================================================================");
		System.out.println("Conjunto de Entrada: " + DatosConjunto.getListaNumeros());
		System.out.println("Suma Objetivo: " + DatosConjunto.getSumaObjetivo());
		
		List<Integer> s = ap.getBestChromosome().decode();
		String suma = "";
		int sumaAux = 0;
		int c = 0;
		for (int i = 0; i < s.size(); i++) {
			if(s.get(i) > 0) {
				sumaAux = sumaAux + (int) s.get(i) * DatosConjunto.getListaNumeros().get(i);
				c = c + s.get(i);
				suma = suma + " + ";
				if(s.get(i) > 1) {
					suma = suma + (int) s.get(i) + "*" + DatosConjunto.getListaNumeros().get(i);
				} else {
					suma = suma + DatosConjunto.getListaNumeros().get(i);
				}
			}
		}
		if(sumaAux != DatosConjunto.getSumaObjetivo()) {
			System.out.println("No hay solucion exacta");
		} else {
			System.out.println("Hay solución exacta con " + c + " elementos: " + suma);
		}
		
	}

}
