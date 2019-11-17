/** 
 * Esta clase nos servir� para definir las direcciones posibles
 * de nuestro juego.
 */
public enum Direccion {

	IZQUIERDA, DERECHA, ARRIBA, ABAJO;

	/**
	 * Devuelve una direcci�n aleatoria.
	 *
	 * @return Tipo enumerado de direcci�n.
	 */
	public Direccion random() {
		return values()[(int)(Math.random() * values().length)];
	}
}
