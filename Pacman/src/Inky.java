/**
 * Esta clase únicamente tiene como fin representar en el juego al
 * fantasma del juego original, Inky. El comportamiento de Inky es
 * exactamente el mismo que el del fantasma Clyde. 
 * 
 * @autor Marcos Garrido García.
 * @Version 1.0 - 23/04/2014.
 */
public class Inky extends Clyde {

	/**
	 * Constructor Inky.
	 *
	 * @param control Objeto de tipo controlador encargado de controlar a Inky.
	 */
	public Inky (Controlador control) {

		super(control);
		setPosicion(16,17);
	}

	/* (non-Javadoc)
	 * @see Clyde#setImagenes()
	 */
	@Override
	protected void setImagenes() {

		setImagen("inky_right", Direccion.DERECHA);
		setImagen("inky_left", Direccion.IZQUIERDA);
		setImagen("inky_up", Direccion.ARRIBA);
		setImagen("inky_down", Direccion.ABAJO);		
	}
} 

