/**

 * Esta clase nos servirá para manejar en bloque las coordenadas
 * x e y de los distintos elementos de nuestro juego, como el mapa y
 * los actores.
 * 
 * @autor Marcos Garrido García.
 * @version 1.0 - 23/04/2014.
 */
public class Posicion {
	
	// Almacena la coordenada x.
	private int x;
	
	// Almacena la coordenada y.
	private int y;
	
	/**
	 * Contructor Posicion.
	 * 
	 * Inicializa nueva la posición a la coordenada 0,0.
	 */
	public Posicion() {
		x = 0;
		y = 0;
	}
	
	/**
	 * Sobrecarga Constructor Posicion.
	 * 
	 * Inicializa la nueva posicion a unas coordenadas dadas.
	 *
	 * @param x Valor entero para la coordenada x.
	 * @param y Valor entero para la coordenada y.
	 */
	public Posicion (int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Devuelve la coordenada x.
	 *
	 * @return Valor entero.
	 */
	public int getX () {
		return x;
	}

	/**
	 * Devuelve la coordenada y.
	 *
	 * @return Valor entero.
	 */
	public int getY () {
		return y;
	}

	/**
	 * Establece la coordenada x.
	 *
	 * @param Valor entero.
	 */
	public void setX (int x) {
		this.x = x;
	}
	
	/**
	 * Establece la coordenada y.
	 *
	 * @param y Valor entero.
	 */
	public void setY (int y) {
		this.y = y;
	}
	
	/**
	 * Establece la posicion.
	 *
	 * @param x Valor entero de la coordenada x.
	 * @param y Valor entero de la coordenada y.
	 */
	public void setPosicion (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sobreescribe el método equals de la superclase object.
	 *
	 * @param p Posición.
	 * @return Valor booleano.
	 */
	@Override
	public boolean equals (Object o) {
		
		Posicion p = (Posicion) o;
		
		return (p.getX() == x) && (p.getY() == y);
	}
}
