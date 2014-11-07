package fiuba.ia;

import org.encog.ml.genetic.BasicGeneticAlgorithm;
import org.encog.ml.genetic.GeneticAlgorithm;
import org.encog.ml.genetic.genes.IntegerGene;
import org.encog.ml.genetic.genome.CalculateGenomeScore;
import org.encog.ml.genetic.genome.Genome;
import org.encog.ml.genetic.mutate.MutateShuffle;
import org.encog.ml.genetic.population.BasicPopulation;
import org.encog.ml.genetic.population.Population;
import org.encog.util.obj.ObjectCloner;

import java.util.ArrayList;
import java.util.List;

public class ResolucionAlgoritmo {

	private GeneticAlgorithm algoritmo;
    private GeneticAlgorithm mejores;
    private int cantidadSolucionesIguales = 0;

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

        // Genero una poblacion vacia para guardar a los mejores.
        Population poblacionMejores = new BasicPopulation();
        mejores = new BasicGeneticAlgorithm();
        mejores.setCalculateScore(ganancia);
        mejores.setPopulation(poblacionMejores);
		
		poblacion.claim(algoritmo);
		poblacion.sort();
	}
	
	public void imprimirSolucion() {
        System.out.println();
        System.out.print("La mejor solucion hallada fue: ");
		for (int i=0; i < Config.CANTIDAD_DE_GENES; i++){

			System.out.print("|" + ""
                    + ((IntegerGene)mejores.getPopulation()
                    .getBest().getChromosomes().get(0)
                    .getGenes().get(i)).getValue() + "|");
		}
		System.out.println("");
	}

    private void imprimirGeneracion() {
        List<Genome> organismos = algoritmo.getPopulation().getGenomes();
        System.out.print("|Poblacion Actual: ");
        for (int i=0; i < Config.TAMANIO_POBLACION; i++) {
            System.out.print("|" + organismos.get(i).getScore() + "");
        }
        System.out.println("|");
    }

    private void imprimirPoblacionInicial() {
        List<Genome> organismos = algoritmo.getPopulation().getGenomes();
        System.out.print("|Poblacion Inicial:");
        for (int i=0; i < Config.TAMANIO_POBLACION; i++) {
            System.out.print("|" + organismos.get(i).getScore() + "");
        }
        System.out.println("|");
    }

    private void guardarMejores(Genome individuo) {

        for (int i = 0; i < mejores.getPopulation().size(); i++) {
            if (mejores.getPopulation().get(i).getScore() == individuo.getScore()) {
                cantidadSolucionesIguales++;
                return;
            }
        }
        mejores.getPopulation().add((Genome)ObjectCloner.deepCopy(individuo));
    }

    public void resolver() {
		StringBuilder builder = new StringBuilder();


		int iteracion = 1;
        double gananciaSolucion = 0.0;
		double gananciaSolucionAnterior = Double.MAX_VALUE;

        this.imprimirPoblacionInicial();

		// Uso condicion de corte
		while (cantidadSolucionesIguales < Config.MAXIMA_CANT_SOLUCIONES_IGUALES) {

            algoritmo.iteration();

            this.imprimirGeneracion();

            this.guardarMejores(algoritmo.getPopulation().getBest());
		}

		imprimirSolucion();
	}
	
}
