import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


/**
 * En esta clase definiremos las variables y métodos correspondientes a
 * Pacman, el cual es manejado por el jugador mediante la pulsación de 
 * las teclas.
 * 
 * @autor Marcos Garrido García.
 * @version 1.0 - 23/04/2014.
 */
public class Jugador extends Actor {

	// Almacena la puntuación correspondiente al jugador.
	private int puntuacion;
	
	// Define la próxima dirección del jugador.
	private Direccion proximaDireccion;

	/**
	 * Constructor Jugador.
	 *
	 * @param control Objeto de tipo controlador encargado de controlar al jugador.
	 */
	public Jugador (Controlador control) {

		super(control);		
		setPosicion(13,23);
		direccion = Direccion.IZQUIERDA; 
		proximaDireccion = direccion;
		puntuacion = 0;
	}

	/**
	 * Devuelve la puntuacion del jugador.
	 *
	 * @return Valor entero.
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	
	/**
	 * Devuelve la próxima dirección del actor.
	 *
	 * @return Tipo enumerado de dirección.
	 */
	public Direccion getProximaDireccion () {
		return proximaDireccion;
	}
	
	/**
	 * Añade puntos a la puntuación del jugador.
	 *
	 * @param p Valor entero a añadir a la puntuación.
	 */
	public void sumarPuntuacion (int p) {
		puntuacion += p;
	}

	/* (non-Javadoc)
	 * @see Actor#setImagen(java.lang.String, Direccion)
	 * 
	 * Sobreescritura del método para utilizar una extensión de archivo distinto
	 * que en el método de la superclase.
	 */
	protected void setImagen (String s, Direccion d) {  
		
		ImageIcon i = null;
		try {
		i = new ImageIcon(this.getClass().getResource(s + ".gif"));
		} catch (NullPointerException e) {
			System.out.println("No se ha podido cargar la imagen " + s + ".gif");
		}
		imagenes.put(d,i.getImage());
	}   

	/* (non-Javadoc)
	 * @see Actor#setImagenes()
	 */
	protected void setImagenes() {

		setImagen("pacman_right", Direccion.DERECHA);
		setImagen("pacman_left", Direccion.IZQUIERDA);
		setImagen("pacman_up", Direccion.ARRIBA);
		setImagen("pacman_down", Direccion.ABAJO);
	}

	/* (non-Javadoc)
	 * @see Actor#puedeMover(Posicion)
	 * 
	 * Sobreescritura del método abstracto definido en la superclase para adecuarlo
	 * al caso de que el actor es pacman.
	 */
	public boolean puedeMover (Posicion p) {

		switch (control.getMapa().getCelda(p)) {
		case "pp":
		case "pg":
		case "vv":
		case "pa":
			return true;
		default:
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see Actor#mover()
	 * 
	 * Definición del método abstracto de la superclase.
	 * Cuando Pacman pueda mover hacia la próxima dirección, la próxima
	 * dirección será la dirección a tomar y moverá a la siguiente posición.
	 * Mientras Pacman pueda mover hacia su dirección actual, moverá a la
	 * siguiente posicion en su dirección actual.
	 */
	public void mover() {

		if (puedeMover(proximaPosicion(posicion, proximaDireccion))) {
			direccion = proximaDireccion;
			posicion = proximaPosicion(posicion, direccion);
		} else if (puedeMover(proximaPosicion(posicion, direccion))){
			posicion = proximaPosicion(posicion, direccion);
		}
	}

	/**
	 * Permite al jugador entrar por un lado del pasillo y salir por el
	 * otro lado.
	 */
	public void atravesarPasillo() {	

		if (getX() == 0 && getY() == 14) {
			setPosicion(26,14);
		} else if (getX() == 27 && getY() == 14) {
			setPosicion(1,14);
		}
	}

	/**
	 * Comprueba si Pacman se ha comido un PacDot o un PowerPellet.
	 * Si se ha comido cualquiera de los dos, suma 10 puntos a su
	 * puntuación y devuelve True. De lo contrario devuelve False.
	 *
	 * @return Valor booleano.
	 */
	public boolean puntoComido () {

		boolean comido;

		switch (control.getMapa().getCelda(posicion)) {
		case "pp":
		case "pg":
			control.getMapa().setCelda(posicion,"vv");
			puntuacion += 10;
			comido = true;
			break;
		default:
			comido = false;
		}
		return comido;
	}

	/**
	 * Comprueba si Pacman se ha comido un PowerPellet.
	 * Si se lo ha comido devuelve True, de lo contrario devuelve False.
	 *
	 * @return Valor booleano.
	 */
	public boolean puntoGrandeComido () {

		return control.getMapa().getCelda(posicion).equals("pg");
	}

	/* (non-Javadoc)
	 * @see Actor#actuar()
	 */
	public void actuar () {
		atravesarPasillo();
		mover();
	}

	/**
	 * Comprueba si el jugador ha pulsado una tecla de dirección.
	 * En el caso de que el jugador pulse una tecla de direccion, se almacenará
	 * la dirección en la variable proximaDirección, para que cuando el jugador
	 * pueda mover en dicha dirección lo haga. De esta forma tendremos la
	 * posibilidad de "memorizar" la dirección de la ultima tecla pulsada.
	 *
	 * @param e Objeto de tipo KeyEvent.
	 */
	public void keyPressed (KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			proximaDireccion = Direccion.IZQUIERDA;	
			break;
		case KeyEvent.VK_RIGHT:
			proximaDireccion = Direccion.DERECHA;
			break;
		case KeyEvent.VK_UP:
			proximaDireccion = Direccion.ARRIBA;
			break;
		case KeyEvent.VK_DOWN:
			proximaDireccion = Direccion.ABAJO;
			break;
		}
	}
}