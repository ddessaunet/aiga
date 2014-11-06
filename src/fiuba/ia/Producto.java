package fiuba.ia;

public class Producto {
	
	private TipoProducto tipo;
	
	public static int getTotalHoras(TipoProducto tipo){
		int horas = 0;;
		switch(tipo){
			case Producto1:
				horas = 6;
				break;
			case Producto2:
				horas = 8;
				break;
			case Producto3:
				horas = 3;
				break;				
			default:
				break;
		};
		return horas;	
	}
	
	public static int getTotalMaterial(TipoProducto tipo){
		int material = 0;;
		switch(tipo){
			case Producto1:
				material = 4;
				break;
			case Producto2:
				material = 2;
				break;
			case Producto3:
				material = 6;
				break;				
			default:
				break;
		};
		return material;
	}	

	public Producto(TipoProducto tipo){
		this.tipo = tipo;
	}
	
	public TipoProducto getTipo(){
		return this.tipo;
	}
	
}
