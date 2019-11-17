/** 
 * Esta clase nos servirá para definir las direcciones posibles
 * de nuestro juego.
 */
public enum Direccion {

	IZQUIERDA, DERECHA, ARRIBA, ABAJO;

	/**
	 * Devuelve una dirección aleatoria.
	 *
	 * @return Tipo enumerado de dirección.
	 */
	public Direccion random() {
		return values()[(int)(Math.random() * values().length)];
	}
}
