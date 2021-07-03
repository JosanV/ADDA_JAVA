package ejercicio8AG;

import java.util.List;

import us.lsi.ag.SeqNomalChromosome;
import us.lsi.ag.SeqNormalProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agchromosomes.ChromosomeFactory.CrossoverType;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.common.Files2;
import us.lsi.reinas.datos.Reina;



public class TestReinasAG {
	public static void main(String[] args){
		
		AlgoritmoAG.ELITISM_RATE  = 0.20;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 40;
		
		StoppingConditionFactory.NUM_GENERATIONS = 6000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 0.;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.SolutionsNumber;
		
		ChromosomeFactory.crossoverType = CrossoverType.OnePoint;
		
		
		for (String string : Files2.getLines("ficheros/PI3Ej8DatosEntrada.txt")) {
			String[] partes = string.split(":");
			int n = Integer.parseInt(partes[1]);
			
			
			SeqNormalProblemAG<List<Reina>> p = ProblemaReinasAG.create();
			ProblemaReinasAG.numeroDeReinas = n;		
			AlgoritmoAG<SeqNomalChromosome> ap = AlgoritmoAG.<SeqNomalChromosome>create(ChromosomeType.SqnPermutation,p);
			ap.ejecuta();
			
			System.out.println("================================");
			System.out.println("Numero de reinas: " + ProblemaReinasAG.numeroDeReinas);		
			SeqNomalChromosome cr = ap.getBestChromosome();
			System.out.println(p.getSolucion(cr));
			System.out.println("================================");
			
		}
	}	
}
