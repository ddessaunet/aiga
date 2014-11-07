package fiuba.ia;

import java.util.ArrayList;
import java.util.Random;

import org.encog.ml.genetic.GeneticAlgorithm;
import org.encog.ml.genetic.genes.IntegerGene;
import org.encog.ml.genetic.genome.BasicGenome;
import org.encog.ml.genetic.genome.Chromosome;

/*
 * A genome is the basic blueprint for creating an organism in Encog.
 */
public class Genoma extends BasicGenome {

	private Chromosome cromosoma;
	
	public Genoma() {

		if (Config.SOLO_INDIVIDUOS_VALIDOS) {
            this.buildOnlyValidValuesOrganism();
        } else {
            this.buildRandomValuesOrganism();
        }
		
		this.cromosoma = new Chromosome();
		this.getChromosomes().add(this.cromosoma);
		
		for(int i=0; i < Config.CANTIDAD_DE_GENES; i++){
			IntegerGene gen = new IntegerGene();
			gen.setValue(((Integer[])this.getOrganism())[i]);
			this.cromosoma.getGenes().add(gen);
		}
				
		encode();
	}

    private void buildRandomValuesOrganism() {
        // 3 valores tiene cada individuo: cant prod1, cant prod2, cant prod3
        Integer[] organismo = new Integer[Config.CANTIDAD_DE_GENES];

        // Lleno una lista con valores random
        ArrayList<Integer> productos = new ArrayList<Integer>();
        for(int i=0; i < Config.CANTIDAD_DE_GENES; i++){
            switch (i){
                case 0:
                    productos.add((new Random()).nextInt(84)); break;
                case 1:
                    productos.add((new Random()).nextInt(63)); break;
                case 2:
                    productos.add((new Random()).nextInt(67)); break;
            }
        }
        productos.toArray(organismo);
        this.setOrganism(organismo);
    }

    private void buildOnlyValidValuesOrganism() {

        this.buildRandomValuesOrganism();
        Ganancia ganancia = new Ganancia();

        while (ganancia.calculateScore(this) == 0.0) {
            this.buildRandomValuesOrganism();
        }
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