/**
 * Esta clase nos servir� para crear un marcador de puntos en nuestro
 * juego.
 * 
 * @autor Marcos Garrido Garc�a.
 * @version 1.0 - 23/04/2014.
 */
public class MarcadorPuntos {
	
	// Esta ser� la puntuaci�n que reflejar� el marcador.
	private int puntuacion;
	
	/**
	 * Contructor marcadorPuntos.
	 */
	public MarcadorPuntos() {
		puntuacion = 0;
	}
	
	/**
	 * Devuelve la puntuaci�n del marcador.
	 *
	 * @return Valor entero.
	 */
	public int getPuntuacion () {
		return puntuacion;
	}
	
	/**
	 * Establece la puntuaci�n del marcador.
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
