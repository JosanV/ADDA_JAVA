package ejercicio5AG;

import java.util.ArrayList;
import java.util.List;

import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agchromosomes.ChromosomeFactory.CrossoverType;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestEjercicio5AG {

	public static void main(String[] args) {
		test5("ficheros/PI3Ej5DatosEntrada.txt");
		test5("ficheros/PI3Ej5DatosEntrada2.txt");
	}
	
	private static void test5(String fichero){

		AlgoritmoAG.ELITISM_RATE  = 0.20;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		
		StoppingConditionFactory.NUM_GENERATIONS = 2000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
//		StoppingConditionFactory.FITNESS_MIN = 0;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.SolutionsNumber;
		
		ChromosomeFactory.crossoverType = CrossoverType.OnePoint;
		
		ValuesInRangeProblemAG<Integer, List<Integer>> p = new ProblemaUniversoAGBin(fichero);
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> ap = AlgoritmoAG.create(ChromosomeType.Binary, p);
		ap.ejecuta();
		
		//SOLUCION LEGIBLE
		System.out.println("Solucion del problema");
		System.out.println("----------------------");
		System.out.println("Coste (suma de pesos de la solución):");
		
		Double peso = 0.;
		List<Conjunto> res = new ArrayList<Conjunto>();
		int c = 0;
		for (int i : ap.getBestChromosome().decode()) {
			if(i == 1) {
				peso = peso + DatosConjunto.getPeso(c);
				res.add(DatosConjunto.getConjunto(c));
			}
			c++;
		}
		
		System.out.println(peso);
		System.out.println("Subconjuntos elegidos y peso asociado:");
		System.out.println(res);
		
	}

}
