package fiuba.ia;

public class Individuo {
	public static int CANT_PRODUCTO_1 = 0;
	public static int CANT_PRODUCTO_2 = 1;
	public static int CANT_PRODUCTO_3 = 2;
	public static int CANT_HORAS_REMANENTES = 3;
	public static int CANT_UNIDADES_MATERIA_PRIMA_SOBRANTES = 4;
//	public static int GANANCIA = 5;
	
	private Integer[] organismo; // PROD1, PROD2, PROD3, CANT_HORAS_REMANENTES, CANT_MAT_PRIMA_SOBRANTE, GANANCIA
	
	public Individuo(){
		this.organismo = new Integer[6];
	}
	
	public Individuo(Integer[] organismo){
		this.organismo = organismo;
	}
	
	public void setCantidadProducto1(int cantidad){
		this.organismo[CANT_PRODUCTO_1] = cantidad;
	}
	
	public void setCantidadProducto2(int cantidad){
		this.organismo[CANT_PRODUCTO_2] = cantidad;
	}
	
	public void setCantidadProducto3(int cantidad){
		this.organismo[CANT_PRODUCTO_3] = cantidad;
	}
	
	public void setCantidadHorasRemanentes(int cantidad){
		this.organismo[CANT_HORAS_REMANENTES] = cantidad;
	}
	
	public void setCantidadUnidadesMateriaPrimaSobrantes(int cantidad){
		this.organismo[CANT_UNIDADES_MATERIA_PRIMA_SOBRANTES] = cantidad;
	}
	
//	public void setGanancia(int cantidad){
//		this.organismo[GANANCIA] = cantidad;
//	}
	
	public int getCantidadProducto1(){
		return this.organismo[CANT_PRODUCTO_1];
	}
	
	public int getCantidadProducto2(){
		return this.organismo[CANT_PRODUCTO_2];
	}
	
	public int getCantidadProducto3(){
		return this.organismo[CANT_PRODUCTO_3];
	}
	
	public int getCantidadHorasRemanentes(){
		return this.organismo[CANT_HORAS_REMANENTES];
	}
	
	public int getCantidadUnidadesMateriaPrimaSobrantes(){
		return this.organismo[CANT_UNIDADES_MATERIA_PRIMA_SOBRANTES];
	}
	
//	public int getGanancia(){
//		return this.organismo[GANANCIA];
//	}
	
	public Integer[] getOrganismo(){
		return this.organismo;
	}
	
}
