import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.RenderableImage;

import javax.swing.JPanel;

import com.sun.javafx.animation.TickCalculation;

/**
 * Klasa opisująca panel Swing, w którym odbywa się rysowanie grafiki gry.
 * Obsługuje jednocześnie część logiki gry związaną z połączeniem innych części
 * w jedno.
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class Game extends JPanel implements Runnable,KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Flaga ozaczająca, czy wątek działa
	 */
	private boolean isRunning = false;
	/**
	 * Główny wątek gry
	 */
	private Thread thread;
	/**
	 * Nick gracza
	 */
	public String nick;
	/**
	 * Obiekt gracza
	 */
	public static Player player;
	/**
	 * Obiekt mapy
	 */
	public static Mapa mapa;
	/**
	 * Obiekt reprezentujący GUI
	 */
	public static GUI gui;

	/**
	 * Konstruktor przyjmuje String oznaczający nick gracza, ustawia wstępne
	 * wymiary planszy gry i tworzy obiekt gracza i mapy
	 * 
	 * @param nicktext
	 *            Nick gracza
	 */
	public Game(String nicktext) {
		Dimension dimension = new Dimension(Config.GameWidth, Config.GameHeight);
		setPreferredSize(dimension);
		player = new Player(Config.GameWidth / 2, Config.GameHeight / 2);
		mapa = new Mapa(Config.FileMap);
		nick = nicktext;
		gui = new GUI();
		addKeyListener(this);
		setFocusable(true);
	}

	/**
	 * Metoda odpowiedzialna za rozpoczęcie pracy wątku odpowiedzialnego za
	 * rysowanie
	 */
	public synchronized void start() {
		if (isRunning)
			return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		setFocusable(true);
	}

	/**
	 * Metoda kończąca pracę programu, gdy flaga isRunning
	 */
	public synchronized void stop() {
		if (!isRunning)
			return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda paint, rysująca grafikę. Tworzy BufferedImage o wymiarach
	 * początkowych, na podstawie którego tworzony jest kontekst graficzny,
	 * przekazywany do drugiego bufora, o rozmiarach takich, jak JPanel. W ten
	 * sposób generowana grafika rozciągana jest do aktualnych rozmiarów okna.
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void paint(Graphics g) {

		BufferedImage dbImage = new BufferedImage(Config.GameWidth, Config.GameHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics dbg = dbImage.getGraphics();
		paintComponent(dbg);

		BufferedImage scaled = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D gg = scaled.createGraphics();
		gg.drawImage(dbImage, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(scaled, 0, 0, this);
	}

	/**
	 * Metoda paintComponenet, wywoływana jest z metody paint - przy każdym jej
	 * wywołaniu rysuje tło planszy, a także wywołuje metody render() jej
	 * składowych
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, Config.GameWidth, Config.GameHeight);
		player.render(g);
		mapa.render(g);
		gui.render(g, nick);
		g.dispose();
		setFocusable(true);
	}

	@Override
	


	/**
	 * metoda typu KeyListener reagująca na nakliknięcie guzika
	 */
	public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {

            case KeyEvent.VK_LEFT:
            	 player.x -= Config.PacmanSpeed;
            	
                break;

            case KeyEvent.VK_RIGHT:
            	 player.x+= Config.PacmanSpeed;
            	 
                break;

            case KeyEvent.VK_UP:

            	 player.y -= Config.PacmanSpeed;
            	 
                break;

            case KeyEvent.VK_DOWN:
            	 player.y += Config.PacmanSpeed;
            	 
                break;

            
            default:
                break;
        }

    }

	
	/**
	 * metoda, która reaguje na zwolnienie klawisza true na false i zatrzymująca pacmana
	 */
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
		if(e.getKeyCode() == KeyEvent.VK_UP) player.up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
		
		
	

	

	
    


	

}
