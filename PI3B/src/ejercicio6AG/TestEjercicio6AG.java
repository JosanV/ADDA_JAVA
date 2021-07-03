package ejercicio6AG;

import java.util.ArrayList;
import java.util.List;

import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agchromosomes.ChromosomeFactory.CrossoverType;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestEjercicio6AG {

	public static void main(String[] args) {
		test6("ficheros/PI3Ej6DatosEntrada.txt");
		test6("ficheros/PI3Ej6DatosEntrada2.txt");
	}

	
	private static void test6(String fichero){

		AlgoritmoAG.ELITISM_RATE  = 0.20;
		AlgoritmoAG.CROSSOVER_RATE = 0.9;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 40;
		
		StoppingConditionFactory.NUM_GENERATIONS = 300;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 1000000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.SolutionsNumber;
		
		ChromosomeFactory.crossoverType = CrossoverType.OnePoint;
		
		ValuesInRangeProblemAG<Integer, List<Integer>> p = new ProblemaTareasAGBin(fichero);
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> ap = AlgoritmoAG.create(ChromosomeType.Binary, p);
		ap.ejecuta();
		
		//SOLUCION LEGIBLE
		System.out.println("Solucion del problema Tareas");
		System.out.println("----------------------");
		System.out.println("Ganancia de la solución:");
		
		Double peso = 0.;
		List<Tarea> res = new ArrayList<Tarea>();
		int c = 0;
		for (int i : ap.getBestChromosome().decode()) {
			if(i == 1) {
				peso = peso + DatosTarea.getGanancia(c);
				res.add(DatosTarea.getTareas().get(c));
			}
			c++;
		}
		
		System.out.println(peso);
		System.out.println("Subconjuntos elegidos y peso asociado:");
		System.out.println(res);
		
//		System.out.println(DatosTarea.getTareas());
//		System.out.println(RangeChromosome.bitsNumber);
//		System.out.println(ap.getBestChromosome().decode());
//		System.out.println(p.getSolucion(ap.getBestChromosome()));
		
	}
	
}
