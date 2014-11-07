package fiuba.ia;

public class Config {

    /*
     * Cantidad de genes de cada individuo.
     */
    public static final int CANTIDAD_DE_GENES = 3;

    /*
     * Parametros del algoritmo genetico.
     */
    public static final boolean SOLO_INDIVIDUOS_VALIDOS = true;
    public static final int TAMANIO_POBLACION = 20; // En caso de SOLO_INDIVIDUOS_VALIDOS = false, usar valor alto.
    public static final double PORCENTAJE_MUTACION = 0.25;
    public static final double PORCENTAJE_SELECCION = 0.5;
    public static final double PORCENTAJE_REPRODUCCION = 0.5;
    public static final double PORCENTAJE_POBLACION_USADA_PARA_REPRODUCCION = 0.25;
    public static final int MAXIMA_CANT_SOLUCIONES_IGUALES = 100; // Condicion de corte

    /*
     * Recursos disponibles para la produccion de materiales
     */
    public static final int HORAS_DISPONIBLES = 500;
    public static final int MATERIA_PRIMA_DISPONIBLE = 400;

    /*
     * Horas necesarias para la produccion de cada producto.
     */
    public static final int HORAS_PROD_1 = 6;
    public static final int HORAS_PROD_2 = 8;
    public static final int HORAS_PROD_3 = 3;

    /*
     * Cantidad de materia prima necesaria para la producción de cada elemento.
     */
    public static final int MATERIALES_PROD_1 = 4;
    public static final int MATERIALES_PROD_2 = 2;
    public static final int MATERIALES_PROD_3 = 6;

    /*
     * Ganancia de cada producto.
     */
    public static final int GANANCIA_PROD_1 = 7;
    public static final int GANANCIA_PROD_2 = 5;
    public static final int GANANCIA_PROD_3 = 6;

}