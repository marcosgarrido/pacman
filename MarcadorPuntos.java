/**
 * Esta clase nos servirá para crear un marcador de puntos en nuestro
 * juego.
 * 
 * @autor Marcos Garrido García.
 * @version 1.0 - 23/04/2014.
 */
public class MarcadorPuntos {
	
	// Esta será la puntuación que reflejará el marcador.
	private int puntuacion;
	
	/**
	 * Contructor marcadorPuntos.
	 */
	public MarcadorPuntos() {
		puntuacion = 0;
	}
	
	/**
	 * Devuelve la puntuación del marcador.
	 *
	 * @return Valor entero.
	 */
	public int getPuntuacion () {
		return puntuacion;
	}
	
	/**
	 * Establece la puntuación del marcador.
	 *
	 * @param p Valor entero.
	 */
	public void setPuntuacion (int p) {
		puntuacion = p;
	}
	
	/**
	 * Suma puntos al marcador.
	 *
	 * @param p Valor entero.
	 */
	public void sumarPuntos (int p) {
		puntuacion += p;
	}

}
