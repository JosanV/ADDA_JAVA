package ejercicio9AG;

import java.util.List;
import java.util.stream.Collectors;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agchromosomes.ChromosomeFactory.CrossoverType;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.Files2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio9AG {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fichero = "ficheros/PI3Ej9DatosEntradaGrafo.txt";
		String ficheroOD = "ficheros/PI3Ej9DatosEntrada.txt";
		SimpleWeightedGraph<Ciudad, Carretera> g = GraphsReader.newGraph(fichero,
				Ciudad::ofFormat,
				Carretera::ofFormat,
				() -> new SimpleWeightedGraph<Ciudad, Carretera>(Ciudad::of, Carretera::of),
				Carretera::getKm);
		List<String> l = Files2.getLines(ficheroOD);
		List<Ciudad> vertices = g.vertexSet().stream().collect(Collectors.toList());
		for (int  i = 0; i < l.size(); i++) {
			String[] linea = l.get(i).split(",");
			Ciudad origen = Ciudad.ofName(linea[0].trim());
			Ciudad destino = Ciudad.ofName(linea[1].trim());
			
			AlgoritmoAG.ELITISM_RATE  = 0.20;
			AlgoritmoAG.CROSSOVER_RATE = 0.8;
			AlgoritmoAG.MUTATION_RATE = 0.7;
			AlgoritmoAG.POPULATION_SIZE = 50;
			
			StoppingConditionFactory.NUM_GENERATIONS = 4000;
			StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
			StoppingConditionFactory.FITNESS_MIN = 0;
			StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.SolutionsNumber;
			
			ChromosomeFactory.crossoverType = CrossoverType.OnePoint;
			
			ProblemaCiudadAGBin p = new ProblemaCiudadAGBin(g, origen, destino);
			AlgoritmoAG<ValuesInRangeChromosome<Integer>> ap = AlgoritmoAG.create(ChromosomeType.Binary, p);
			ap.ejecuta();
			System.out.println(ap.getBestChromosome().decode());
			
			
		}
	}
	
//	public static void test9(String )

}
