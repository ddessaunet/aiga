package fiuba.ia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.encog.ml.genetic.crossover.Crossover;
import org.encog.ml.genetic.genome.Chromosome;

public class Cruce implements Crossover {

	@Override
	public void mate(Chromosome madre, Chromosome padre,
			Chromosome hijo1, Chromosome hijo2) {

		ArrayList<Integer> ordenRandom = new ArrayList<Integer>(Config.CANTIDAD_DE_GENES);
		for(int i=0; i < Config.CANTIDAD_DE_GENES; i++){
			ordenRandom.add(i);
		}
		Collections.shuffle(ordenRandom, new Random(System.nanoTime()));
		for(int i=0; i < Config.CANTIDAD_DE_GENES -1; i++){
			int countN1 = 0;
			int countN2 = 0;
			int n1 = ordenRandom.get(i);
			int n2 = ordenRandom.get(i+1);
			for(int j=0; j < Config.CANTIDAD_DE_GENES; j++){
				if(ordenRandom.get(i).equals(n1)){
					if(countN1 > 2) continue;
					countN1++;
					ordenRandom.set(i, n2);
				}else if(ordenRandom.get(i).equals(n2)){
					if(countN2 > 2) continue;
					countN2++;
					ordenRandom.set(i, n1);
				}
			}
		}
	}

	
}
