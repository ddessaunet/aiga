package fiuba.ia;

import org.encog.ml.genetic.BasicGeneticAlgorithm;
import org.encog.ml.genetic.GeneticAlgorithm;
import org.encog.ml.genetic.genes.IntegerGene;
import org.encog.ml.genetic.genome.CalculateGenomeScore;
import org.encog.ml.genetic.mutate.MutateShuffle;
import org.encog.ml.genetic.population.BasicPopulation;
import org.encog.ml.genetic.population.Population;

public class ResolucionAlgoritmo {

	private GeneticAlgorithm algoritmo;

    public ResolucionAlgoritmo() {
        this.configuracion();
    }

	private void configuracion(){

        algoritmo = new BasicGeneticAlgorithm();

        algoritmo.setMutationPercent(Config.PORCENTAJE_MUTACION);
        algoritmo.setPercentToMate(Config.PORCENTAJE_REPRODUCCION);
        algoritmo.setMatingPopulation(Config.PORCENTAJE_POBLACION_USADA_PARA_REPRODUCCION);
        algoritmo.setCrossover(new Cruce());
        algoritmo.setMutate(new MutateShuffle()); // A simple mutation where genes are shuffled

		CalculateGenomeScore ganancia = new Ganancia();
        algoritmo.setCalculateScore(ganancia);
		Population poblacion = new BasicPopulation(Config.TAMANIO_POBLACION);
        algoritmo.setPopulation(poblacion);

        // Populo mi algoritmo genetico.
		for (int i = 0; i < Config.TAMANIO_POBLACION; i++) {
			Genoma genoma = new Genoma();
            algoritmo.getPopulation().add(genoma);
            algoritmo.calculateScore(genoma);
		}
		
		poblacion.claim(algoritmo);
		//poblacion.sort();
	}
	
	public void imprimirSolucion() {
		for (int i=0; i < Config.CANTIDAD_DE_GENES; i++){ // Busco CANTIDAD_DE_GENES soluciones ??? Ver si esto es asi o si entendi mal!
			System.out.print("|" + ""+ ((IntegerGene)algoritmo.getPopulation().getBest().getChromosomes().get(0).getGenes().get(i)).getValue() + "|");
		}
		System.out.println("");
	}

	public void resolver() {
		StringBuilder builder = new StringBuilder();

		int cantidadSolucionesIguales = 0;
		int iteracion = 1;
		double gananciaSolucionAnterior = Double.MAX_VALUE;
		
		// Uso condicion de corte
		while (cantidadSolucionesIguales < Config.MAXIMA_CANT_SOLUCIONES_IGUALES) {

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
