import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;

/**
 * Esta clase será la encargada de comunicar nuestra vista de la clase pantalla,
 * con el modelo de nuestra aplicación y las correspondientes clases Mapa y Actor.
 */
public class Controlador implements ActionListener {

	// Almacena el objeto pantalla que será la vista de la aplicación.
	private Pantalla pantalla;
	
	// Almacena el mapa por el cual se moverán los actores del juego.
	private Mapa mapa;
	
	// Define el marcador que representará la puntuación del jugador.
	private MarcadorPuntos marcador;
	
	// Almacena los distintos actores del juego.
	private ArrayList<Actor> actores;
	
	// Almacena el objeto de tipo Jugador que representa a Pacman.
	private Jugador pacman;
	
	// Define el estado actual del juego.
	private Estado estado;
	
	// Almacena el número de PacDots y PowerPellets restantes del juego.
	private int contadorPacDots;

	// Controlará los turnos o "ticks" del juego.
	private Timer timer;

	/**
	 * Contructor controlador.
	 *
	 * @param pantalla Objeto de tipo Pantalla a controlar.
	 */
	public Controlador(Pantalla pantalla) {

		this.pantalla = pantalla;
		pantalla.addKeyListener(new TAdapter());
		mapa = new Mapa();
		marcador = new MarcadorPuntos();
		actores = new ArrayList<Actor>();
		pacman = new Jugador(this);
		actores.add(pacman);
		actores.add(new Blinky(this));
		actores.add(new Inky(this));
		actores.add(new Clyde(this));
		actores.add(new Pinky(this));
		contadorPacDots = mapa.getPacdots();

		timer = new Timer(225, this);
		setEstado(Estado.BIENVENIDA);
	}

	/**
	 * Devuelve el objeto Jugador del juego.
	 *
	 * @return Objeto de tipo Jugador.
	 */
	public Jugador getPacman() {
		return pacman;
	}

	/**
	 * Devuelve el mapa del juego.
	 *
	 * @return Objeto de tipo Mapa.
	 */
	public Mapa getMapa() {
		return mapa;
	}

	/**
	 * Devuelve la pantalla (o vista) del juego.
	 *
	 * @return Objeto de tipo Pantalla.
	 */
	public Pantalla getPantalla() {
		return pantalla;
	}

	/**
	 * Devuelve la colección de actores del juego.
	 *
	 * @return ArrayList de actores.
	 */
	public ArrayList<Actor> getActores() {
		return actores;
	}

	/**
	 * Devuelve el timer del juego.
	 *
	 * @return Objeto de tipo Timer.
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * Devuelve el estado del juego.
	 *
	 * @return Tipo enumerado Estado.
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * Establece el estado del juego. Si el estado a establecer es INICIADO
	 * el timer del juego arranca, de lo contrario, el timer del juego se 
	 * detiene y la pantalla se repinta una última vez.
	 *
	 * @param e Nuevo estado.
	 */
	public void setEstado (Estado e) {
		estado = e;

		if (e == Estado.INICIADO) {
			timer.start();
		} else {
			timer.stop();
			pantalla.repaint();
		}
	}

	/**
	 * Hace que los actores del juego actúen.
	 */
	private void accionActores() {

		for (Actor actor: actores) {
			actor.actuar();
		}
	}

	/**
	 * Devuelve el contador de PacDots y PowerPellets.
	 *
	 * @return Valor entero.
	 */
	public int getContadorPacDots() {
		return contadorPacDots;
	}

	/**
	 * Establece el valor del contador de PacDots y PowerPellets.
	 *
	 * @param numero Nuevo valor entero del contador.
	 */
	public void setContadorPacDots (int numero) {
		contadorPacDots = numero;
	}

	/**
	 * Comprueba el contador de PacDots y PowerPellets. Si el contador 
	 * es menor que 0 o 0, el juego pasa a estado COMPLETADO.
	 */
	private void comprobarPacDots() {

		if (contadorPacDots <= 0) {
			setEstado(Estado.COMPLETADO);
		}
	}

	/**
	 * Comprueba las acciones de Pacman. 
	 * Si pacman come un PowerPellet los fantasmas pasan al estado 
	 * asustado.
	 * Si pacman come un PacDot o un PowerPellet, el contador de 
	 * PacDots decrece una unidad.
	 */
	private void comprobarPacman() {

		if (pacman.puntoGrandeComido()) {
			for (Actor actor: actores) {
				if (actor instanceof Fantasma) {
					((Fantasma) actor).asustar();
				}
			}
		}

		if (pacman.puntoComido()) {
			contadorPacDots--;
		}
	}

	/**
	 * Comprueba si hay un fantasma en una determinada posición.
	 *
	 * @param p Posicion
	 * @return Valor booleano. Devuelve True, si hay una fantasma y
	 * False si no hay ninguno.
	 */
	public Boolean hayOtroFantasma (Posicion p) {

		Boolean cierto = false;

		for (Actor actor: actores) {
			if (actor instanceof Fantasma) {
				if (actor.getPosicion().equals(p)) {
					cierto = true;
				}
			}
		}
		return cierto;
	}

	/**
	 * Comprueba las colisiones entre Pacman y los fantasmas.
	 * Si colisiona Pacman con un fantasma y no está asustado, el juego finaliza.
	 * Si colisiona Pacman con un fantasma y está asustado, pasa a su posición
	 * inicial, pacman gana 100 puntos y el fantasma se recupera.
	 */
	public void comprobarColisiones() {

		Rectangle recPacman = pacman.getBounds();

		for (Actor actor: actores) {
			if (actor instanceof Fantasma) {
				Rectangle recFantasma = actor.getBounds();
				if (recPacman.intersects(recFantasma) && !((Fantasma) actor).estaAsustado()) {
					setEstado(Estado.FIN);
				} else if (recPacman.intersects(recFantasma) && ((Fantasma) actor).estaAsustado()) {
					actor.setPosicion(14, 11);
					pacman.sumarPuntuacion(100);
					((Fantasma) actor).setAsustado(false);						
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 
	 * Acciones a realizar cada transcurso del timer del juego.
	 */
	@Override
	public void actionPerformed (ActionEvent e) {

		accionActores();
		marcador.setPuntuacion(pacman.getPuntuacion());
		comprobarPacman();
		comprobarPacDots();
		comprobarColisiones();
		pantalla.repaint();
	}

	/**
	 * Clase interna TAdapter.
	 * 
	 * Registra las pulsaciones de teclado del juego.
	 */
	private class TAdapter extends KeyAdapter {

		/* (non-Javadoc)
		 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
		 */
		public void keyPressed(KeyEvent e) {
			
			switch (e.getKeyCode()) {
			case KeyEvent.VK_P:
				if (getEstado() == Estado.INICIADO) {
					setEstado(Estado.PAUSA);
				} else if (getEstado() == Estado.PAUSA) {
					setEstado(Estado.INICIADO);
				}
				break;
			case KeyEvent.VK_ENTER:
				if (getEstado() == Estado.BIENVENIDA) {
					setEstado(Estado.INICIADO);
				} 
				break;
			}

			pacman.keyPressed(e);
		}
	}
}
