import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.ImageIcon;

/**
 * Esta clase abstracta nos permitirá definir las variables y métodos comunes de
 * los actores fantasmas y jugador, lo que nos permitirá ahorrarnos repetir líneas de
 * código que son comunes a todos los actores.
 * 
 * @autor Marcos Garrido García.
 * @version 1.0 - 23/04/2014.
 */
public abstract class Actor {

	// Permite parametrizar la extension de los archivos de imágenes de los distintos actores.
	private static final String EXTENSION_IMG = ".png";

	// Permite definir la posicion del actor.
	protected Posicion posicion;
	
	// Permite definir la direccion actual del actor.
	protected Direccion direccion;
	
	// Almacena el objeto que se encarga de controlar al actor.
	protected Controlador control;
	
	// Almacena la imagen de un actor en función de su dirección.
	protected HashMap<Direccion, Image> imagenes;

	/**
	 * Constructor Actor.
	 * 
	 * @param control Objeto de tipo controlador encargado de controlar al actor.
	 */
	public Actor(Controlador control) {

		posicion = new Posicion();
		this.control = control;
		imagenes = new HashMap<Direccion, Image>();
		direccion = Direccion.IZQUIERDA;
		setImagenes();
	}
	
	/**
	 * Devuelve el rectángulo del actor para la detección de 
	 * colisiones.
	 *
	 * @return Objeto de tipo Rectangle.
	 */
	public Rectangle getBounds() {
		return new Rectangle(getX() + 1, getY() + 1, 2, 2);
	}

	/**
	 * Devuelve la coordenada x de la posicion del actor.
	 *
	 * @return Valor entero de la coordenada x.
	 */
	public int getX() {
		return posicion.getX();
	}

	/**
	 * Devuelve la coordenada y de la posicion del actor.
	 *
	 * @return Valor entero de la coordenada y.
	 */
	public int getY() {
		return posicion.getY();
	}

	/**
	 * Devuelve la posicion del actor.
	 *
	 * @return Objeto de posicion.
	 */
	public Posicion getPosicion() {
		return posicion;
	}

	/**
	 * Devuelve la direccion actual del actor.
	 *
	 * @return Tipo enumerado de dirección.
	 */
	public Direccion getDireccion() {
		return direccion;
	}
	
	/**
	 * Devuelve la imagen del actor en funcion de su dirección actual.
	 *
	 * @return Objeto de imagen de tipo Image.
	 */
	public Image getImagen() {
		return imagenes.get(direccion);
	}

	/**
	 * Establece la posicion del actor.
	 *
	 * @param x Valor entero para coordenada x.
	 * @param y Valor entero para coordenada y.
	 */
	public void setPosicion(int x, int y) {
		posicion.setX(x);
		posicion.setY(y);
	}

	/**
	 * Establece la dirección actual del actor.
	 *
	 * @param d Tipo enumerado de dirección.
	 */
	public void setDireccion (Direccion d) {
		direccion = d;
	}

	/**
	 * Establece una imagen de actor en funcion de una dirección.
	 *
	 * @param s Cadena con el nombre del recurso.
	 * @param d Tipo enumerado de dirección.
	 */
	protected void setImagen (String s, Direccion d) {
		
		ImageIcon i = null;		
		try {
		i = new ImageIcon(this.getClass().getResource(s + EXTENSION_IMG));
		} catch (NullPointerException e) {
			System.out.println("No se ha podido cargar la imagen " + s + EXTENSION_IMG);
		}
		imagenes.put(d,i.getImage());
	}	

	/**
	 * Devuelve la próxima posición del actor a partir de otra posicion
	 * de referencia y una dirección.
	 *
	 * @param p Posición de referencia.
	 * @param d Tipo enumerado de dirección.
	 * @return Objeto de tipo Posicion.
	 */
	public Posicion proximaPosicion (Posicion p, Direccion d) {	

		Posicion proximaPosicion = new Posicion();

		switch (d) {
		case IZQUIERDA:
			proximaPosicion.setPosicion(p.getX() - 1, p.getY());
			break;
		case DERECHA:
			proximaPosicion.setPosicion(p.getX() + 1, p.getY());
			break;
		case ARRIBA:
			proximaPosicion.setPosicion(p.getX(), p.getY() - 1);
			break;
		case ABAJO:
			proximaPosicion.setPosicion(p.getX(), p.getY() + 1);
			break;
		}
		return proximaPosicion;
	}	
	

	/**
	 * Establece las distintas imágenes de los actores.
	 */
	protected abstract void setImagenes();

	/**
	 * Devuelve si un actor puede mover o no.
	 *
	 * @param p Posición de referencia
	 * @return Valor booleano. True si puede mover, False si no puede.
	 */
	public abstract boolean puedeMover(Posicion p);

	/**
	 * Mueve un actor.
	 */
	public abstract void mover();

	/**
	 * Hace que un actor actúe.
	 */
	public abstract void actuar();
}
