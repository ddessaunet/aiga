package fiuba.ia;

import java.util.ArrayList;
import java.util.Random;

import org.encog.ml.genetic.GeneticAlgorithm;
import org.encog.ml.genetic.genes.IntegerGene;
import org.encog.ml.genetic.genome.BasicGenome;
import org.encog.ml.genetic.genome.Chromosome;

/**
 * A genome is the basic blueprint for creating an organism in Encog.
 */
public class Genoma extends BasicGenome {

	private Chromosome cromosoma;
	
	public Genoma(final GeneticAlgorithm algoritmo) {
		Integer[] organismo = new Integer[Constante.CANTIDAD_DE_GENES]; // 3 valores tiene cada individuo: cant prod1, cant prod2, cant prod3
		
		// Lleno una lista con valores random
		ArrayList<Integer> listaPosiblesValores = new ArrayList<Integer>();
		for(int i=0; i < Constante.CANTIDAD_DE_GENES; i++){
			switch (i){
				case 0:
					listaPosiblesValores.add((new Random()).nextInt(50)); break;
				case 1:
					listaPosiblesValores.add((new Random()).nextInt(50)); break;
				case 2:
					listaPosiblesValores.add((new Random()).nextInt(50)); break;
			}
		}
		
		listaPosiblesValores.toArray(organismo);
		
		this.cromosoma = new Chromosome();
		this.getChromosomes().add(this.cromosoma);
		
		for(int i=0; i < Constante.CANTIDAD_DE_GENES; i++){
			IntegerGene gen = new IntegerGene();
			gen.setValue(organismo[i]);
			this.cromosoma.getGenes().add(gen);
		}
		setOrganism(organismo);
				
		encode();
		
	}

	@Override
	public void decode() {
		Chromosome chromosome = this.getChromosomes().get(0);
		Integer[] organism = new Integer[chromosome.size()];
		
		for(int i=0;i<chromosome.size();i++)
		{
			IntegerGene gene = (IntegerGene)chromosome.get(i);
			organism[i] = gene.getValue();
		}
		
		setOrganism(organism);
	}

	@Override
	public void encode() {
        Chromosome chromosome = this.getChromosomes().get(0);
		
		Integer[] organism = (Integer[])getOrganism();

		for(int i=0; i<chromosome.size(); i++)
		{
			IntegerGene gene = (IntegerGene)chromosome.get(i);
			gene.setValue(organism[i]);
		}
	}

}

