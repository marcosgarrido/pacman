/**
 * Esta clase nos permitir� definir las variables y m�todos del fantasma Blinky.
 * El movimiento de Blinky tratar� de acercarlo a Pacman, primeramente de forma 
 * vertical, y despu�s de forma horizontal. Si no puede acercarse m�s
 * horizontalmente, usar� un movimiento aleatorio.
 * 
 * @autor Marcos Garrido Garc�a.
 * @version 1.0 - 23/04/2014.
 */
public class Blinky extends Fantasma {

	/**
	 * Contructor Blinky.
	 *
	 * @param control Objeto de tipo controlador encargado de controlar a Blinky.
	 */
	public Blinky (Controlador control) {

		super(control);
		setPosicion(11,11);
		direccion = Direccion.ARRIBA;
	}

	/* (non-Javadoc)
	 * @see Actor#setImagenes()
	 */
	@Override
	protected void setImagenes() {

		setImagen("blinky_right", Direccion.DERECHA);
		setImagen("blinky_left", Direccion.IZQUIERDA);
		setImagen("blinky_up", Direccion.ARRIBA);
		setImagen("blinky_down", Direccion.ABAJO);		
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
	 * Definici�n del m�todo abstracto de la superclase.
	 * Si Blinky no esta asustado, mover� preferentemente en vertical.
	 * Si est� asustado su movimiento ser� aleatorio.
	 */
	@Override
	public void mover() {

		if (!asustado) {
			moverVertical();
		} else {
			moverAleatorio();			
		} 
	}


	/**
	 * Mueve verticalmente a Blinky en funcion de la p�sicion de Pacman.
	 * Si no puede mover verticalmente comienza a mover horizontalmente.
	 */
	public void moverVertical() {

		int distanciaX = posicion.getX() - control.getPacman().getPosicion().getX();
		int distanciaY = posicion.getY() - control.getPacman().getPosicion().getY();

		if (distanciaY < 0) {
			if (puedeMover(proximaPosicion(posicion, Direccion.ABAJO))) {
				direccion = Direccion.ABAJO;
				posicion = proximaPosicion(posicion, Direccion.ABAJO);
			} else {
				moverHorizontal(distanciaX);
			}
		} else if (distanciaY > 0) {
			if (puedeMover(proximaPosicion(posicion, Direccion.ARRIBA))) {
				direccion = Direccion.ARRIBA;	
				posicion = proximaPosicion(posicion, Direccion.ARRIBA);
			} else {
				moverHorizontal(distanciaX);
			}
		} else if (distanciaY == 0) {
			moverHorizontal(distanciaX);
		}	
		
	}

	/**
	 * Mueve horizontalmente a Blinky.
	 * Si no puede mover horizontalmente, mueve aleatoriamente.
	 *
	 * @param distanciaX Valor entero con la distancia X hasta Pacman.
	 */
	private void moverHorizontal(int distanciaX) {

		if (distanciaX < 0) {
			if (puedeMover(proximaPosicion(posicion, Direccion.DERECHA))) {
				direccion = Direccion.DERECHA;
				posicion = proximaPosicion(posicion, Direccion.DERECHA);
			} else {
				moverAleatorio();
			}
		} else if (distanciaX > 0) {
			if (puedeMover(proximaPosicion(posicion, Direccion.IZQUIERDA))) {
				direccion = Direccion.IZQUIERDA;
				posicion = proximaPosicion(posicion, Direccion.IZQUIERDA);
			} else {
				moverAleatorio();
			}
		}		
	} 
}
