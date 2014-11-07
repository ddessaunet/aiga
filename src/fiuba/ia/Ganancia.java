package fiuba.ia;

import org.encog.ml.genetic.genome.CalculateGenomeScore;
import org.encog.ml.genetic.genome.Genome;

public class Ganancia implements CalculateGenomeScore {
	
	/*
	 * Devuelve la aptitud de ese individuo
	 */
	@Override
	public double calculateScore(Genome genoma) {
		Integer ganancia = 0;
		
		Individuo individuo = new Individuo((Integer[])genoma.getOrganism());
		
		// Verifico que el individuo este bien conformado
		
		// Calculo total de horas insumidas en la fabricacion de los 3 tipos de productos
		int totalHoras = individuo.getOrganismo()[0] * Config.HORAS_PROD_1
                + individuo.getOrganismo()[1] * Config.HORAS_PROD_2
                + individuo.getOrganismo()[2] * Config.HORAS_PROD_3;
	
		// Calculo total de materia prima insumida en la fabricacion de los 3 tipos de productos
		int totalMaterial = individuo.getOrganismo()[0] * Config.MATERIALES_PROD_1
                + individuo.getOrganismo()[1] * Config.MATERIALES_PROD_2
                + individuo.getOrganismo()[2] * Config.MATERIALES_PROD_3;
		
		// Verifico que las cantidades de horas y materia prima insumidas no se hayan excedido de lo disponible
		if ((totalHoras > Config.HORAS_DISPONIBLES) || (totalMaterial > Config.MATERIA_PRIMA_DISPONIBLE)){
			return ganancia; // devuelvo 0.0
		}

		
		// Valido que el individuo cumpla con las restricciones de cantidad de horas y materia prima
		// 500 horas de trabajo y 400 u de materia prima
		if ((totalHoras <= Config.HORAS_DISPONIBLES) && (totalMaterial <= Config.MATERIA_PRIMA_DISPONIBLE)){
			ganancia += individuo.getOrganismo()[0] * Config.GANANCIA_PROD_1; // organismo[0] = cantidad de unidades PROD 1
			ganancia += individuo.getOrganismo()[1] * Config.GANANCIA_PROD_2; // organismo[1] = cantidad de unidades PROD 2
			ganancia += individuo.getOrganismo()[2] * Config.GANANCIA_PROD_3; // organismo[2] = cantidad de unidades PROD 3
		} else {
            return 0;
        }
		// Devuelvo la ganancia correspondiente de fabricaci�n de todos los productos o 0 si no se cumplieron las restricciones
		return ganancia;
	}
	
	public boolean shouldMinimize() {
		return false;
	}
}
