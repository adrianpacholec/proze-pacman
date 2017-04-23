import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.sun.org.glassfish.external.statistics.Statistic;

/**
 * Klasa opisująca panel Swing, w którym odbywa się rysowanie grafiki gry.
 * Obsługuje jednocześnie część logiki gry związaną z połączeniem innych części
 * w jedno.
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class Game extends JPanel implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Flaga ozaczająca, czy wątek działa
	 */
	public static int start;
	public static boolean gwiazdka;
	public boolean isRunning = false;
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
	 * zmienna potrzebna do przekazania predkosci wrogow
	 */
	public int speedlevel;
	/**
	 * obiekt gry
	 */

	/**
	 * ramka która zawiera grę
	 */
	public JFrame frame;

	public int points;

	public String pointsinString;
	/**
	 * sciezka do pliku gdzie znajduje sie mapa, sluzaca do zmieniania mapy po
	 * przejsciu obecnej
	 */
	public static String mapaPath;

	/**
	 * Konstruktor przyjmuje String oznaczający nick gracza, ustawia wstępne
	 * wymiary planszy gry i tworzy obiekt gracza i mapy
	 * 
	 * @param nicktext
	 *            Nick gracza
	 */
	public Game(String nicktext, int speedlevel, JFrame frame, String mapaPath, int points) {
		this.points = points;
		Game.mapaPath = mapaPath;
		this.frame = frame;
		this.speedlevel = speedlevel;

		Dimension dimension = new Dimension(Config.GameWidth, Config.GameHeight);
		setPreferredSize(dimension);
		nick = nicktext;
		player = new Player(Config.GameWidth / 2, Config.GameHeight / 2, speedlevel, this, frame, nick, mapaPath);
		mapa = new Mapa(mapaPath, speedlevel);

		gui = new GUI();
		addKeyListener(this);
		setFocusable(true);

		class TimeListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
				mapa.update();
				player.update(nick);

				// gui.render(nicktext, points);
				// mapa2.update();
				// mapa.tick();
			}
		}

		ActionListener listener = new TimeListener();
		Timer timer = new Timer(1000 / 60, listener);
		timer.start();

	}

	/**
	 * Metoda odpowiedzialna za rozpoczęcie pracy wątku odpowiedzialnego za
	 * rysowanie
	 */

	@Override
	public void run() {
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
			if (Player.win) {

				frame.dispose();
				this.setVisible(false);
				Victory victory = new Victory(nick, player.getScore());
				player.points = 0;
				player.life = 3;
			} else {
				frame.dispose();
				this.setVisible(false);
				Defeat defeat = new Defeat(nick, player.getScore());
				player.points = 0;
				player.life = 3;
			}

			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void nextLevel(){ this.setVisible(false); Game game2 = new
	 * Game(nick, speedlevel, frame, Config.FileMap2, points); frame.add(game2);
	 * frame.pack(); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * frame.setLocationRelativeTo(null); frame.setVisible(true); game2.run(); }
	 */

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
		mapa.render(g);
		player.render(g);
		gui.render(g, nick, player.getScore());
		g.dispose();
		setFocusable(true);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			player.right = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			player.left = true;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			player.up = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			player.down = true;

	}

	/**
	 * metoda, która reaguje na zwolnienie klawisza true na false i zatrzymująca
	 * pacmana
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			player.right = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			player.left = false;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			player.up = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			player.down = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
