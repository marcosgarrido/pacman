import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta clase abstracta nos permitir� definir las variables y m�todos comunes de
 * los fantasmas del juego, lo que nos permitir� ahorrarnos repetir l�neas de
 * c�digo que son comunes a todos los fantasmas.
 * 
 * @autor Marcos Garrido Garc�a.
 * @version 1.0 - 23/04/2014.
 */
public abstract class Fantasma extends Actor {
	
	
	// Almacena como par�metro el tiempo que un fantasma est� asustado, en milisegundos.
	private static final int TIEMPOASUSTADO = 5000;

	// Define si un fantasma est� asustado o no.
	protected boolean asustado;


	/**
	 * Contructor fantasma.
	 *
	 * @param control Objeto de tipo controlador encargado de controlar al fantasma.
	 */
	public Fantasma (Controlador control) {

		super(control);
		asustado = false;
	}

	/* (non-Javadoc)
	 * @see Actor#getImagen()
	 * 
	 * Sobreescritura del m�todo de la superclase para definir el caso concreto en 
	 * el que el fantasma est� asustado.
	 * Si el fantasma est� asustado tendr� la imagen definida en este m�todo, de 
	 * lo contrario recuperar� la imagen con el m�todo de la superclase.
	 */
	@Override
	public Image getImagen() {

		Image imagen;

		if (asustado) {
			ImageIcon i = null;
			try {
			i = new ImageIcon(this.getClass().getResource("ghost_scared" + ".gif"));
			} catch (NullPointerException e) {
				System.out.println("No se ha podido cargar la imagen ghost_scared.gif");
			}
			imagen = i.getImage();			
		} else {
			imagen = super.getImagen();
		}
		return imagen;
	}

	/* (non-Javadoc)
	 * @see Actor#puedeMover(Posicion)
	 * 
	 * Sobreescritura del m�todo abstracto definido en la superclase para adecuarlo
	 * al caso de que el actor sea un fantasma.
	 * Controlamos la colisi�n entre fantasmas impidiendo el movimiento si en la 
	 * posici�n a mover ya hay otro fantasma.
	 */
	public boolean puedeMover (Posicion p) {

		if (control.hayOtroFantasma(p)) {
			return false;
		} 
		switch (control.getMapa().getCelda(p)) {
		case "pp":
		case "pg":
		case "vv":
		case "mf":
			return true;
		default:
			return false;
		}
	}


	/**
	 * Devuelve el estado del fantasma.
	 *
	 * @return Valor booleano. Devuelve True, si el fantasma est� asustado,
	 * de lo contrario devuelve False.
	 */
	public Boolean estaAsustado() {		
		return asustado;
	}

	/**
	 * Establece el valor de la variable asustado.
	 *
	 * @param b Nuevo valor booleano.
	 */
	public void setAsustado (Boolean b) {
		asustado = b;		
	}

	/**
	 * Asusta al fantasma durante el tiempo definido en la constante
	 * TIEMPOASUSTADO.
	 */
	public void asustar() {

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				asustado = false;
			}
		};

		timer.schedule(task, TIEMPOASUSTADO);

		asustado = true;
	}
	
	/**
	 * Mueve en una direcci�n aleatoria al fantasma.
	 */
	public void moverAleatorio() {
		
		if (!puedeMover(proximaPosicion(posicion,direccion))) {
			direccion = direccion.random();
		}
		if (puedeMover(proximaPosicion(posicion,direccion))) {
			posicion = proximaPosicion(posicion, direccion);			
		}
	}
}