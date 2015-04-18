/** 
 * Esta clase nos permitirá definir las variables y métodos del fantasma Clyde.
 * El movimiento de clyde es aleatorio, cada vez que no pueda mover a la
 * siguiente posicion en su dirección, escogerá aleatoriamente otra dirección.
 *  
 * @autor Marcos Garrido García.
 * @version 1.0 - 23/04/2014.
 */
public class Clyde extends Fantasma {

	/**
	 * Contructor Clyde.
	 *
	 * @param control Objeto de tipo controlador encargado de controlar a Clyde.
	 */
	public Clyde(Controlador control) {
		super(control);
		setPosicion(12,17);
	}
	
	/* (non-Javadoc)
	 * @see Actor#setImagenes()
	 */
	@Override
	protected void setImagenes() {

		setImagen("clyde_right", Direccion.DERECHA);
		setImagen("clyde_left", Direccion.IZQUIERDA);
		setImagen("clyde_up", Direccion.ARRIBA);
		setImagen("clyde_down", Direccion.ABAJO);
	}
	
	/* (non-Javadoc)
	 * @see Actor#mover()
	 * 
	 * Definición del método abstrato de la superclase.
	 * Clyde tendrá siempre un movimiento aleatorio.
	 */
	@Override
	public void mover() {
		moverAleatorio();
	}

	/* (non-Javadoc)
	 * @see Actor#actuar()
	 */
	@Override
	public void actuar() {
		mover();
	}
}