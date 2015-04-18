import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 * Esta clase nos permitirá ubicar los distintos elementos del mapa de
 * nuestro juego, por el que se moverán los actores del juego.
 * 
 * @autor Marcos Garrido García.
 * @version 1.0 - 23/04/2014.
 */

public class Mapa {
    
    // Parametriza la extension de los archivos de imágenes de los distintos elementos del mapa.
    private static final String EXTENSION_IMG = ".png";
    
    // Número de filas de la rejilla del mapa.
    private static final int FILAS = 31;
    
    // Número de columnas de la rejilla del mapa.
    private static final int COLUMNAS = 28;    
    
    // Número de PacDots y PowerPellets del mapa.
    private static final int PACDOTS = 244;
    
    /** Coleccion de cadenas, que representan los distintos
     * elementos del mapa. 
     * m1, m2, m3 y m4, representan las esquinas de los muros.
     * mh representa un muro horizontal.
     * mv representa un muro vertical.
     * mm representa una posicion vacia no atravesable por actores.
     * vv representa una posicion vacia atravesable por actores.
     * pp representa un PacDot.
     * pg representa un PowerPellet.
     * pa representa una posicion de pasillo atravesable por pacman.
     */
     private String[][] cuadricula;
    
    // Colección de imágenes en función de la cadena que representa el tipo de elemento del mapa.
    private HashMap<String, Image> imagenes;
    
    
    /**
     * Constructor Mapa.
     */
    public Mapa() {
        
        cuadricula = new String[][]{
            {"m1","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","m2","m1","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","m2"},
            {"mv","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","mv","mv","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","mv"}, 
            {"mv","pp","m1","mh","mh","m2","pp","m1","mh","mh","mh","m2","pp","mv","mv","pp","m1","mh","mh","mh","m2","pp","m1","mh","mh","m2","pp","mv"},
            {"mv","pg","mv","mm","mm","mv","pp","mv","mm","mm","mm","mv","pp","mv","mv","pp","mv","mm","mm","mm","mv","pp","mv","mm","mm","mv","pg","mv"},
            {"mv","pp","m4","mh","mh","m3","pp","m4","mh","mh","mh","m3","pp","m4","m3","pp","m4","mh","mh","mh","m3","pp","m4","mh","mh","m3","pp","mv"},
            {"mv","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","mv"},
            {"mv","pp","m1","mh","mh","m2","pp","m1","m2","pp","m1","mh","mh","mh","mh","mh","mh","m2","pp","m1","m2","pp","m1","mh","mh","m2","pp","mv"},
            {"mv","pp","m4","mh","mh","m3","pp","mv","mv","pp","m4","mh","mh","m2","m1","mh","mh","m3","pp","mv","mv","pp","m4","mh","mh","m3","pp","mv"},
            {"mv","pp","pp","pp","pp","pp","pp","mv","mv","pp","pp","pp","pp","mv","mv","pp","pp","pp","pp","mv","mv","pp","pp","pp","pp","pp","pp","mv"},
            {"m4","mh","mh","mh","mh","m2","pp","mv","m4","mh","mh","m2","vv","mv","mv","vv","m1","mh","mh","m3","mv","pp","m1","mh","mh","mh","mh","m3"},
            {"mm","mm","mm","mm","mm","mv","pp","mv","m1","mh","mh","m3","vv","m4","m3","vv","m4","mh","mh","m2","mv","pp","mv","mm","mm","mm","mm","mm"},
            {"mm","mm","mm","mm","mm","mv","pp","mv","mv","vv","vv","vv","vv","vv","vv","vv","vv","vv","vv","mv","mv","pp","mv","mm","mm","mm","mm","mm"},
            {"mm","mm","mm","mm","mm","mv","pp","mv","mv","vv","m1","mh","mh","mh","mh","mh","mh","m2","vv","mv","mv","pp","mv","mm","mm","mm","mm","mm"},
            {"mh","mh","mh","mh","mh","m3","pp","m4","m3","vv","mv","mm","mm","mm","mm","mm","mm","mv","vv","m4","m3","pp","m4","mh","mh","mh","mh","mh"},
            {"pa","vv","vv","vv","vv","vv","pp","vv","vv","vv","mv","mm","mm","mm","mm","mm","mm","mv","vv","vv","vv","pp","vv","vv","vv","vv","vv","pa"},
            {"mh","mh","mh","mh","mh","m2","pp","m1","m2","vv","mv","mm","mm","mm","mm","mm","mm","mv","vv","m1","m2","pp","m1","mh","mh","mh","mh","mh"},
            {"mm","mm","mm","mm","mm","mv","pp","mv","mv","vv","m4","mh","mh","mh","mh","mh","mh","m3","vv","mv","mv","pp","mv","mm","mm","mm","mm","mm"},
            {"mm","mm","mm","mm","mm","mv","pp","mv","mv","vv","vv","vv","vv","vv","vv","vv","vv","vv","vv","mv","mv","pp","mv","mm","mm","mm","mm","mm"},
            {"mm","mm","mm","mm","mm","mv","pp","mv","mv","vv","m1","mh","mh","mh","mh","mh","mh","m2","vv","mv","mv","pp","mv","mm","mm","mm","mm","mm"},
            {"m1","mh","mh","mh","mh","m3","pp","m4","m3","vv","m4","mh","mh","m2","m1","mh","mh","m3","vv","m4","m3","pp","m4","mh","mh","mh","mh","m2"},
            {"mv","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","mv","mv","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","mv"},
            {"mv","pp","m1","mh","mh","m2","pp","m1","mh","mh","mh","m2","pp","mv","mv","pp","m1","mh","mh","mh","m2","pp","m1","mh","mh","m2","pp","mv"},
            {"mv","pp","m4","mh","m2","mv","pp","m4","mh","mh","mh","m3","pp","m4","m3","pp","m4","mh","mh","mh","m3","pp","mv","m1","mh","m3","pp","mv"},
            {"mv","pg","pp","pp","mv","mv","pp","pp","pp","pp","pp","pp","pp","vv","vv","pp","pp","pp","pp","pp","pp","pp","mv","mv","pp","pp","pg","mv"},
            {"m4","mh","m2","pp","mv","mv","pp","m1","m2","pp","m1","mh","mh","mh","mh","mh","mh","m2","pp","m1","m2","pp","mv","mv","pp","m1","mh","m3"},
            {"m1","mh","m3","pp","m4","m3","pp","mv","mv","pp","m4","mh","mh","m2","m1","mh","mh","m3","pp","mv","mv","pp","m4","m3","pp","m4","mh","m2"},
            {"mv","pp","pp","pp","pp","pp","pp","mv","mv","pp","pp","pp","pp","mv","mv","pp","pp","pp","pp","mv","mv","pp","pp","pp","pp","pp","pp","mv"},
            {"mv","pp","m1","mh","mh","mh","mh","m3","m4","mh","mh","m2","pp","mv","mv","pp","m1","mh","mh","m3","m4","mh","mh","mh","mh","m2","pp","mv"},
            {"mv","pp","m4","mh","mh","mh","mh","mh","mh","mh","mh","m3","pp","m4","m3","pp","m4","mh","mh","mh","mh","mh","mh","mh","mh","m3","pp","mv"},
            {"mv","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","pp","mv"},
            {"m4","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","mh","m3"}            
            };
        
        imagenes = new HashMap<String, Image>();
        
        setImagenes();
    }
    
    /**
     * Establece la imagenes de los elementos del mapa.
     */
    private void setImagenes () {
  
    	setImagen("m1");
        setImagen("m2");
        setImagen("m3");
        setImagen("m4");
        setImagen("mh");
        setImagen("mv");
        setImagen("mf");
        setImagen("pp");
        setImagen("pg");
    }
    
    /**
     * Establece la imagen de un elemento del mapa.
     *
     * @param s Cadena con el nombre del recurso.
     */
    private void setImagen (String s) {    
    	
    	ImageIcon i = null;
    	try {
        i = new ImageIcon(this.getClass().getResource(s + EXTENSION_IMG));
    	} catch (NullPointerException e) {
    		System.out.println("No se ha podido cargar la imagen " + s + EXTENSION_IMG);
    	}
        imagenes.put(s,i.getImage());
    }
    
    /**
     * Devuelve la cadena que representa al elemento de una posición 
     * del mapa.
     *
     * @param p Posición.
     * @return Cadena que representa el elemento del mapa.
     */
    public String getCelda (Posicion p) {
        return cuadricula[p.getY()][p.getX()];
    }
    
    /**
     * Establece la cadena que representa a un elemento en una posición
     * del mapa.
     *
     * @param p Posición.
     * @param s Cadena que representa el elemento del mapa.
     */
    public void setCelda (Posicion p, String s) {
    	cuadricula[p.getY()][p.getX()] = s;
    }
    
    /**
     * Devuelve la imagen del elemento de una posición del mapa.
     *
     * @param p Posición
     * @return Objeto de tipo Image
     */
    public Image getImage (Posicion p) {        
        return imagenes.get(cuadricula[p.getY()][p.getX()]);     
    }
    
    /**
     * Devuelve la cuadrícula del mapa.
     *
     * @return Colección de cadenas que representan los elementos del mapa.
     */
    public String[][] getCuadricula () {    	
    	return cuadricula;
    }

	/**
	 * 
	 * Devuelve el número de filas de la cuadrícula del mapa.
	 * 
	 * @return Número de filas de la cuadrícula del mapa.
	 */
	public int getFilas() {
		return FILAS;
	}

	/**
	 * Devuelve el número de columnas de la cuadrícula del mapa.
	 * 
	 * @return Número de columnas de la cuadrícula del mapa.
	 */
	public int getColumnas() {
		return COLUMNAS;
	}

	/**
	 * Devuelve el número de pacdots del mapa.
	 * 
	 * @return Número de pacdots del mapa.
	 */
	public int getPacdots() {
		return PACDOTS;
	}
  }
