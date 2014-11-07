package fiuba.ia;

public class Individuo {
	public static int CANT_PRODUCTO_1 = 0;
	public static int CANT_PRODUCTO_2 = 1;
	public static int CANT_PRODUCTO_3 = 2;

    private Integer[] organismo; // PROD1, PROD2, PROD3, CANT_HORAS_REMANENTES, CANT_MAT_PRIMA_SOBRANTE, GANANCIA
	
	public Individuo(){
		this.organismo = new Integer[6];
	}
	
	public Individuo(Integer[] organismo){
		this.organismo = organismo;
	}
	
	public void setCantidadProducto1(Integer cantidad){
		this.organismo[CANT_PRODUCTO_1] = cantidad;
	}
	
	public void setCantidadProducto2(Integer cantidad){
		this.organismo[CANT_PRODUCTO_2] = cantidad;
	}
	
	public void setCantidadProducto3(Integer cantidad){
		this.organismo[CANT_PRODUCTO_3] = cantidad;
	}
	
	public int getCantidadProducto1(){
		return this.organismo[CANT_PRODUCTO_1];
	}
	
	public int getCantidadProducto2(){
		return this.organismo[CANT_PRODUCTO_2];
	}
	
	public int getCantidadProducto3(){
		return this.organismo[CANT_PRODUCTO_3];
	}
	
	public Integer[] getOrganismo(){
		return this.organismo;
	}
	
}
