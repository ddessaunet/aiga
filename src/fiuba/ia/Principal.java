package fiuba.ia;

public class Principal {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ResolucionAlgoritmo resolutor = new ResolucionAlgoritmo();
		resolutor.resolver();
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Tiempo de ejecucion: "+totalTime+"ms.");
	}

}
