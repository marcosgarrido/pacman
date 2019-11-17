/**
 * Esta clase nos permitirá definir las variables y métodos del fantasma Pinky.
 * El movimiento de Blinky tratará de acercarlo a Pacman, primeramente de forma 
 * horizontal, y después de forma vertical. Si no puede acercarse más
 * horizontalmente, usará un movimiento aleatorio.
 * 
 * @autor Marcos Garrido García.
 * @version 1.0 - 23/04/2014.
 */
public class Pinky extends Fantasma {

	/**
	 * Constructor Pinky.
	 *
	 * @param control Objeto de tipo controlador encargado de controlar a Pinky.
	 */
	public Pinky (Controlador control) {

		super(control);
		posicion = new Posicion(16,11);
		direccion = Direccion.ARRIBA;
	}

	/* (non-Javadoc)
	 * @see Actor#setImagenes()
	 */
	@Override
	protected void setImagenes() {

		setImagen("pinky_right", Direccion.DERECHA);
		setImagen("pinky_left", Direccion.IZQUIERDA);
		setImagen("pinky_up", Direccion.ARRIBA);
		setImagen("pinky_down", Direccion.ABAJO);		
	}

	/**
	 * Mueve horizontalmente a Pinky en funcion de la pósicion de Pacman.
	 * Si no puede mover horizontalmente comienza a mover verticalmente.
	 */
	public void moverHorizontal() {

		int distanciaX = posicion.getX() - control.getPacman().getPosicion().getX();
		int distanciaY = posicion.getY() - control.getPacman().getPosicion().getY();


		if (distanciaX < 0) {
			if (puedeMover(proximaPosicion(posicion, Direccion.DERECHA))) {
				posicion = proximaPosicion(posicion, Direccion.DERECHA);
			} else {
				moverVertical(distanciaY);
			}
		} else if (distanciaX > 0) {
			if (puedeMover(proximaPosicion(posicion, Direccion.IZQUIERDA))) {
				posicion = proximaPosicion(posicion, Direccion.IZQUIERDA);		
			} else {
				moverVertical(distanciaY);
			}
		} else if (distanciaX == 0) {
			moverVertical(distanciaY);
		}		
	}

	/**
	 * Mueve verticalmente a Pinky.
	 * Si no puede mover verticalmente, mueve aleatoriamente.
	 *
	 * @param distanciaY Valor entero con la distancia Y hasta Pacman.
	 */
	private void moverVertical(int distanciaY) {

		if (distanciaY < 0) {
			if (puedeMover(proximaPosicion(posicion, Direccion.ABAJO))) {
				direccion = Direccion.ABAJO;
				posicion = proximaPosicion(posicion, Direccion.ABAJO);
			} else {
				moverAleatorio();
			}
		} else if (distanciaY > 0) {
			if (puedeMover(proximaPosicion(posicion, Direccion.ARRIBA))) {
				posicion = proximaPosicion(posicion, Direccion.ARRIBA);
			} else {
				moverAleatorio();
			}
		}		
	}

	/* (non-Javadoc)
	 * @see Actor#actuar()
	 */
	@Override
	public void actuar() {
		mover();
	}

	/* (non-Javadoc)
	 * @see Actor#mover()
	 * 
	 * Definición del método abstracto de la superclase.
	 * Si Pinky no esta asustado, moverá preferentemente en vertical.
	 * Si está asustado su movimiento será aleatorio.
	 */
	@Override
	public void mover() {

		if (!asustado) {
			moverHorizontal();
		} else {
			moverAleatorio();
		}		
	}
}
