package fiuba.ia;

import org.encog.ml.genetic.BasicGeneticAlgorithm;
import org.encog.ml.genetic.GeneticAlgorithm;
import org.encog.ml.genetic.genes.IntegerGene;
import org.encog.ml.genetic.genome.CalculateGenomeScore;
import org.encog.ml.genetic.mutate.MutateShuffle;
import org.encog.ml.genetic.population.BasicPopulation;
import org.encog.ml.genetic.population.Population;

public class ResolucionAlgoritmo {

	public static final int TAMANIO_POBLACION = 20;
	public static final double PORCENTAJE_MUTACION = 0.25;
	//public static final double PORCENTAJE_SELECCION = 0.5;
	public static final double PORCENTAJE_REPRODUCCION = 0.25;
	public static final double PORCENTAJE_POBLACION_USADA_PARA_REPRODUCCION = 0.25;
	public static final int MAXIMA_CANT_SOLUCIONES_IGUALES = 5; // Condicion de corte
	
	private static int CANTIDAD_DE_GENES = 3;
	
	private GeneticAlgorithm algoritmo;

	private void inicializarPoblacion(GeneticAlgorithm ag){		
		CalculateGenomeScore ganancia = new Ganancia();
		ag.setCalculateScore(ganancia);
		Population poblacion = new BasicPopulation(TAMANIO_POBLACION);
		ag.setPopulation(poblacion);

		for (int i = 0; i < TAMANIO_POBLACION; i++) {
			final Genoma genoma = new Genoma(ag);
			ag.getPopulation().add(genoma);
			ag.calculateScore(genoma);
		}
		
		poblacion.claim(ag);
		poblacion.sort();
	}
	
	public void imprimirSolucion() {
		for (int i=0; i < CANTIDAD_DE_GENES; i++){ // Busco CANTIDAD_DE_GENES soluciones ??? Ver si esto es asi o si entendi mal!
			System.out.print("|" + ""+ ((IntegerGene)algoritmo.getPopulation().getBest().getChromosomes().get(0).getGenes().get(i)).getValue() + "|");
		}
		System.out.println("");
	}
	
	public void resolver() {
		StringBuilder builder = new StringBuilder();

		algoritmo = new BasicGeneticAlgorithm();
		
		inicializarPoblacion(algoritmo);
		algoritmo.setMutationPercent(PORCENTAJE_MUTACION);
		algoritmo.setPercentToMate(PORCENTAJE_REPRODUCCION);
		algoritmo.setMatingPopulation(PORCENTAJE_POBLACION_USADA_PARA_REPRODUCCION);
		algoritmo.setCrossover(new Cruce());
		algoritmo.setMutate(new MutateShuffle()); // A simple mutation where genes are shuffled

		int cantidadSolucionesIguales = 0;
		int iteracion = 1;
		double gananciaSolucionAnterior = Double.MAX_VALUE;
		
		// Uso condicion de corte
		while (cantidadSolucionesIguales < MAXIMA_CANT_SOLUCIONES_IGUALES) {
			algoritmo.iteration();
			iteracion++;
			double gananciaSolucion = algoritmo.getPopulation().getBest().getScore();
			builder.setLength(0);
			builder.append("Iteracion: ");
			builder.append(iteracion);
			builder.append(" Ganancia: ");
			builder.append(gananciaSolucion);

			System.out.println(builder.toString());
			
			if (gananciaSolucionAnterior == gananciaSolucion && gananciaSolucion > 0){
				cantidadSolucionesIguales++;
			} 
			else{
				cantidadSolucionesIguales = 0;
				imprimirSolucion();
			}
			gananciaSolucionAnterior = gananciaSolucion;
		}

		imprimirSolucion();
	}
	
}
