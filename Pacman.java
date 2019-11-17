import javax.swing.JFrame;

/**
 * Esta clase se encarga de lanzar el juego, extendiendo la clase JFrame que 
 * nos proporcionará la ventana donde poder colocar los distintos elementos de 
 * éste.
 *
 * @autor Marcos Garrido García.
 * @version 1.0 - 23/04/2014.
 */

public class Pacman extends JFrame {
    
    // Parametriza la anchura de la ventana de nuestro juego.
    public static final int ANCHURA = 616;
    
    // Parametriza la altura de la ventana de nuestro juego.
    public static final int ALTURA = 704;
    
    // Parametriza el nombre de nuestro juego.
    public static final String NOMBRE = "Pacman V1.0";

    /**
     * Contructor Pacman.
     */
    public Pacman() {

        super(NOMBRE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ANCHURA, ALTURA);
        setLocationRelativeTo(null);
        add(new Pantalla());
        setVisible(true);
    }

    /**
     * Método Main.
     *
     * @param args
     */
    public static void main(String[] args) {        
        new Pacman();
    }
}