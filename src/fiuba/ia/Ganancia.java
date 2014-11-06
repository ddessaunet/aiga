package fiuba.ia;

import org.encog.ml.genetic.genome.CalculateGenomeScore;
import org.encog.ml.genetic.genome.Genome;

public class Ganancia implements CalculateGenomeScore {
	
	private static int HORAS_DISPONIBLES = 500;
	private static int MATERIA_PRIMA_DISPONIBLE = 400;
	
	private static final int HORAS_PROD_1 = 6;
	private static final int HORAS_PROD_2 = 8;
	private static final int HORAS_PROD_3 = 3;
	private static final int MATERIALES_PROD_1 = 4;
	private static final int MATERIALES_PROD_2 = 2;
	private static final int MATERIALES_PROD_3 = 6;	
	
	private static int GANANCIA_PROD_1 = 7;
	private static int GANANCIA_PROD_2 = 5;
	private static int GANANCIA_PROD_3 = 6;

	/**
	 * Devuelve la aptitud de ese individuo
	 */
	@Override
	public double calculateScore(Genome genoma) {
		double ganancia = 0.0;
		
		Individuo individuo = new Individuo((Integer[])genoma.getOrganism());
		
		// Verifico que el individuo este bien conformado
		
		// Calculo total de horas insumidas en la fabricacion de los 3 tipos de productos
		int totalHoras = individuo.getOrganismo()[0] * HORAS_PROD_1 + individuo.getOrganismo()[1] * HORAS_PROD_2 +
				individuo.getOrganismo()[2] * HORAS_PROD_3;
	
		// Calculo total de materia prima insumida en la fabricacion de los 3 tipos de productos
		int totalMaterial = individuo.getOrganismo()[0] * MATERIALES_PROD_1 + individuo.getOrganismo()[1] * MATERIALES_PROD_2 +
				individuo.getOrganismo()[2] * MATERIALES_PROD_3;
		
		// Verifico que las cantidades de horas y materia prima insumidas no se hayan excedido de lo disponible
		if ((totalHoras > HORAS_DISPONIBLES) || (totalMaterial > MATERIA_PRIMA_DISPONIBLE)){
			return ganancia; // devuelvo 0.0
		}

		
		// Valido que el individuo cumpla con las restricciones de cantidad de horas y materia prima
		// 500 horas de trabajo y 400 u de materia prima
		if ((totalHoras <= HORAS_DISPONIBLES) && (totalMaterial <= MATERIA_PRIMA_DISPONIBLE)){
			ganancia += individuo.getOrganismo()[0] * GANANCIA_PROD_1; // organismo[0] = cantidad de unidades PROD 1
			ganancia += individuo.getOrganismo()[1] * GANANCIA_PROD_2; // organismo[1] = cantidad de unidades PROD 2
			ganancia += individuo.getOrganismo()[2] * GANANCIA_PROD_3; // organismo[2] = cantidad de unidades PROD 3			
		} else {
            return 0;
        }
		
//		if ((individuo.getOrganismo()[3] <= HORAS_DISPONIBLES) && (individuo.getOrganismo()[4] <= MATERIA_PRIMA_DISPONIBLE)){
//			ganancia += individuo.getOrganismo()[0] * GANANCIA_PROD_1; // organismo[0] = cantidad de unidades PROD 1
//			ganancia += individuo.getOrganismo()[1] * GANANCIA_PROD_2; // organismo[1] = cantidad de unidades PROD 2
//			ganancia += individuo.getOrganismo()[2] * GANANCIA_PROD_3; // organismo[2] = cantidad de unidades PROD 3
//		}
		
		// Devuelvo la ganancia correspondiente de fabricaci�n de todos los productos o 0 si no se cumplieron las restricciones
		return ganancia;
	}
	
	public boolean shouldMinimize() {
		return false;
	}
}
