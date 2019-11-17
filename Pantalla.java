import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Esta clase extiende JPanel, que será el contendor para los elementos 
 * de nuestro juego, actuando como la vista de nuestra aplicación.
 *
 * @autor Marcos Garrido García.
 * @version 1.0 - 23/04/2014.
 */

public class Pantalla extends JPanel {

    // Concreta el tamaño de las imágenes del juego.
    public static final int TAMANO_IMAGEN = 22;   

    // Almacena el objeto que controla la vista.
    private Controlador control;                 

    /**
     * Constructor Pantalla.
     *
     * Crea un nuevo objeto controlador para 
     * nuestro tablero de juego, el cual se encargará de comunicar la 
     * propia vista del juego con el modelo de nuestra aplicación.
     */
    public Pantalla() {

        control = new Controlador(this);
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }

    /**
     * Dibuja el mapa de nuestro juego, el 
     * cual consta del laberinto, y los puntos que va comiendo el jugador.
     * 
     * @param g Objeto de contexto gráfico.
     */
    private void dibujarMapa(Graphics g) {

        Posicion p = new Posicion();

        control.getMapa();
        for (int x = 0 ; x < control.getMapa().getColumnas(); x++) {
            p.setX(x);
            control.getMapa();
            for (int y = 0 ; y < control.getMapa().getFilas(); y++) {
                p.setY(y);
                g.drawImage(control.getMapa().getImage(p), (p.getX() * TAMANO_IMAGEN), (p.getY() * TAMANO_IMAGEN), null);
            }            
        }
    }

    /**
     * Dibuja en nuestra vista una pantalla de inicio para nuestro 
     * juego.
     * 
     * @param g Objeto de contexto gráfico.
     */
    private void dibujarBienvenida (Graphics g) {

        ImageIcon i = new ImageIcon(this.getClass().getResource("pacman_logo.png"));
        Image logo = i.getImage();

        g.drawImage(logo, 50, 150, null);



        g.setFont(new Font("Bauhaus 93", Font.BOLD, 25));
        g.setColor(Color.ORANGE);
        String pausa = "Pulsa Enter para comenzar la partida...";
        g.drawString(pausa, centroCadena(pausa,g,true), centroCadena(pausa,g,false));
    }

    /**
     * Dibuja los distintos personajes de nuestro juego, incluído Pacman 
     * y los fantasmas.
     *
     * @param g Objeto de contexto gráfico.
     */
    private void dibujarActores(Graphics g) {

        for (Actor actor: control.getActores()) {
            g.drawImage(actor.getImagen(), (actor.getX() * TAMANO_IMAGEN), (actor.getY() * TAMANO_IMAGEN), null);
        }
    }

    /**
     * Dibuja en la pantalla el marcador con los puntos del jugador.
     * 
     * @param g Objeto de contexto gráfico.
     */
    private void dibujarMarcador (Graphics g) {     

        g.setFont(new Font("Cooper std", Font.BOLD, 20));
        g.setColor(Color.WHITE);    
        String marcador = "Puntuacion " + Integer.toString(control.getPacman().getPuntuacion());
        g.drawString(marcador, centroCadena(marcador,g,true), 16);
    }

    /**
     * Dibuja la pantalla cuando nuestro juego se encuentra pausado.
     *
     * @param g Objeto de contexto gráfico.
     */
    private void dibujarPausa (Graphics g) {

        g.setFont(new Font("Bauhaus 93", Font.BOLD, 80));
        g.setColor(Color.ORANGE);
        String pausa = "PAUSA";
        g.drawString(pausa, centroCadena(pausa,g,true), centroCadena(pausa,g,false));
    }

    /**
     * Dibuja la pantalla cuando matan al jugador.
     * 
     * @param g Objeto de contexto gráfico.
     */
    private void dibujarGameOver (Graphics g) {

        g.setFont(new Font("Impact", Font.BOLD, 80));
        g.setColor(Color.RED);
        String pausa = "GAME OVER";
        g.drawString(pausa, centroCadena(pausa,g,true), centroCadena(pausa,g,false));
    }

    /**
     * Dibuja por pantalla una felicitación cuando el jugador completa 
     * el juego con éxito.
     * 
     * @param g Objeto de contexto gráfico.
     */
    private void dibujarFelicitacion (Graphics g) {

        int y = Pacman.ALTURA/3;

        g.setFont(new Font("Hobo std", Font.BOLD, 40));
        g.setColor(Color.ORANGE);
        String felicitacion = "¡FELICIDADES\r\nHAS COMPLETADO\r\nEL JUEGO CON ÉXITO!";
        for (String linea: felicitacion.split("\r\n")) {
            g.drawString(linea, centroCadena(linea,g,true), y += g.getFontMetrics().getHeight());
        }
    }

    /**
     * Combina los distintos métodos anteriores y los llama en 
     * funcion del estado del juego.
     *
     * @param g Objeto de contexto gráfico.
     */
    private void dibujarEstado (Graphics g) {

        switch (control.getEstado()) {
        case PAUSA:
            dibujarPausa(g);
            break;
        case FIN:
            dibujarGameOver(g);
            break;
        case COMPLETADO:
            dibujarFelicitacion(g);
            break;
        default:
            break;
        }

    }

    /**
     * Devuelve el centro horizontal o vertical de la pantalla, para una cadena, 
     * en función del ancho y alto de nuestra ventana y el tipo de fuente.
     *
     * @param cadena Cadena de texto a centrar.
     * @param g Objeto de contexto gráfico.
     * @param horizontal Valor booleano para indicar si queremos el centro horizonal 
     * o el centro vertical.
     * @return Valor entero.
     */
    private int centroCadena (String cadena, Graphics g, boolean horizontal) {

        int centro = 0;

        if (horizontal == true) {
            int longAnchoCad = (int) g.getFontMetrics().getStringBounds(cadena, g).getWidth();
            centro = (Pacman.ANCHURA - longAnchoCad)/2;
        } else {
            int longAltoCad = (int) g.getFontMetrics().getStringBounds(cadena, g).getHeight();
            centro = (Pacman.ALTURA - longAltoCad)/2;
        }
        return centro;
    }

    /**
     * Sobreescribe el método correspondiente a la clase JPanel. 
     * Se encarga de dibujar todo el contenido de nuestra pantalla.
     *
     * @param g Objeto de contexto gráfico.
     */
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);        
        g2d.setRenderingHints(rh);

        if (control.getEstado() == Estado.BIENVENIDA) {
            dibujarBienvenida(g2d);
        } else {
            dibujarMapa(g2d);
            dibujarActores(g2d);
            dibujarMarcador(g2d);
            dibujarEstado(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

}
