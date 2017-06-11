import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Klasa opisuj�ca panel Swing, w kt�rym odbywa si� rysowanie grafiki gry.
 * Zmiana? Obs�uguje jednocze�nie cz�� logiki gry zwi�zan� z po��czeniem innych
 * cz�ci w jedno.
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class Game extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean gwiazdka;
	/**
	 * Nick gracza
	 */
	public static String nick;

	static int points = 0;
	static int life = 3;

	/**
	 * Obiekt gracza
	 */
	public static Player player;
	/**
	 * Obiekt mapy
	 */
	public static Mapa mapa;
	public static Spritesheet spritesheet;
	public int map_index;

	/**
	 * Obiekt reprezentuj�cy GUI
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
	 * ramka kt�ra zawiera gr�
	 */
	public JFrame frame;

	public String pointsinString;
	/**
	 * sciezka do pliku gdzie znajduje sie mapa, sluzaca do zmieniania mapy po
	 * przejsciu obecnej
	 */
	public static String[] mapaPath;

	/**
	 * Konstruktor przyjmuje String oznaczaj�cy nick gracza, ustawia wst�pne
	 * wymiary planszy gry i tworzy obiekt gracza i mapy
	 * 
	 * @param nicktext
	 *            Nick gracza
	 */

	class TimeListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			repaint();
			mapa.update();
			player.update(nick);
		}
	}

	ActionListener listener = new TimeListener();
	Timer timer = new Timer(1000 / 60, listener);

	public Game(String nicktext, int speedlevel, JFrame frame, String[] mapaPath, int map, int points) {
		Game.points = points;
		Game.mapaPath = mapaPath;
		this.frame = frame;
		this.speedlevel = speedlevel;
		this.map_index = map;
		//Dimension dimension = new Dimension(Config.GameWidth, Config.GameHeight);
		//setPreferredSize(dimension);
		nick = nicktext;
		player = new Player(Config.GameWidth / 2, Config.GameHeight / 2, speedlevel, this, frame);
		mapa = new Mapa(mapaPath[map_index++], speedlevel);
		spritesheet = new Spritesheet("/sprites/terrain.png");

		gui = new GUI();
		addKeyListener(this);
		setFocusable(true);
		timer.start();

	}

	/**
	 * Metoda ko�cz�ca prac� programu
	 */

	public synchronized void stop() {
		timer.stop();
		frame.dispose();
		if (Player.win && map_index != mapaPath.length) {
			nextLevel();
			// Victory victory = new Victory(nick, points);
		} else {
			this.setVisible(false);
			// Defeat defeat = new Defeat(nick, points);
		}
	}

	public void nextLevel() {
		this.setVisible(false);
		Game game2 = new Game(nick, speedlevel, frame, mapaPath, map_index, points);
		frame.add(game2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Metoda paint, rysuj�ca grafik�. Tworzy BufferedImage o wymiarach
	 * pocz�tkowych, na podstawie kt�rego tworzony jest kontekst graficzny,
	 * przekazywany do drugiego bufora, o rozmiarach takich, jak JPanel. W ten
	 * spos�b generowana grafika rozci�gana jest do aktualnych rozmiar�w okna.
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 *            /** Metoda paintComponenet, wywo�ywana jest z metody paint -
	 *            przy ka�dym jej wywo�aniu rysuje t�o planszy, a tak�e wywo�uje
	 *            metody render() jej sk�adowych
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void paintComponent(Graphics g) {
		BufferedImage bufor1 = new BufferedImage(Config.GameWidth, Config.GameHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics dbg = bufor1.getGraphics();

		super.paintComponent(dbg);
		dbg.setColor(Color.black);
		dbg.fillRect(0, 0, Config.GameWidth, Config.GameHeight);
		mapa.render(dbg);
		player.render(dbg);
		gui.render(dbg, nick, points);
		dbg.dispose();
		setFocusable(true);

		BufferedImage bufor2 = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics gg = bufor2.getGraphics();
		gg.drawImage(bufor1, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(bufor2, 0, 0, this);

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
	 * metoda, kt�ra reaguje na zwolnienie klawisza true na false i zatrzymuj�ca
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
