import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

/**
 * Klasa opisuj�ca panel Swing, w kt�rym odbywa si� rysowanie grafiki gry.
 * Zmiana? Obs�uguje jednocze�nie cz�� logiki gry zwi�zan� z po��czeniem innych
 * cz�ci w jedno.
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class Game extends Control {
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
	private int start_star;
	public int temat;

	public int map_index;
	/**
	 * zmienna przechowuj�ca liczb� �y� gracza
	 */
	public static boolean win;

	/**
	 * Obiekt reprezentuj�cy GUI
	 */
	public static GUI gui;

	/**
	 * zmienna potrzebna do przekazania predkosci wrogow
	 */
	public int enemyspeed;
	/**
	 * obiekt gry
	 */

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
	class removePoint extends TimerTask {
		public void run() {
			if (points > 0)
				points--;
		}
	}

	public Game(String nicktext, int enemyspeed, JFrame frame, String[] mapaPath, int map, int points, int temat) {
		Game.points = points;
		Game.mapaPath = mapaPath;
		this.frame = frame;
		this.enemyspeed = enemyspeed;
		this.map_index = map;
		this.temat = temat;

		nick = nicktext;
		player = new Player(Config.GameWidth / 2, Config.GameHeight / 2, enemyspeed);
		mapa = new Mapa(mapaPath[map_index++], this.enemyspeed, temat);
		spritesheet = new Spritesheet("/sprites/male.png");
		moneta = new Spritesheet("/sprites/coin.png");
		gui = new GUI();
		gwiazdka=false;
		addKeyListener(this);
		setFocusable(true);
		timer.start();
		Timer point_timer = new Timer();
		point_timer.schedule(new removePoint(), 0, 4000);
	}

	/**
	 * Metoda kończąca pracę programu
	 */

	public synchronized void stop() {
		timer.stop();
		if (win && map_index != mapaPath.length) {
			nextLevel();
		} else {
			End victory = new End(frame, nick, points);
			frame.add(victory);
			frame.remove(this);
			frame.setVisible(true);
		}
	}

	public void nextLevel() {
		Game game2 = new Game(nick, enemyspeed, frame, mapaPath, map_index, points, temat);
		frame.add(game2);
		frame.remove(this);
		frame.setVisible(true);
	}

	@Override
	void updatePlayer() {
		for (int i = 0; i < mapa.stars.size(); i++) {
			if (player.intersects(mapa.stars.get(i))) {
				mapa.stars.remove(i);
				start_star = (int) System.currentTimeMillis();
				points += (2 * enemyspeed);
				gwiazdka = true;
				break;
			}
		}
		for (int i = 0; i < mapa.punkty.size(); i++) {
			if (player.intersects(mapa.punkty.get(i))) {
				mapa.punkty.remove(i);
				points += enemyspeed;
				break;
			}
		}
		if (mapa.punkty.size() == 0 && mapa.stars.size() == 0) {
			win = true;
			stop();
		}
		for (int i = 0; i < mapa.enemies.size(); i++) {
			if (player.intersects(mapa.enemies.get(i))) {
				if (!gwiazdka) {

					life--;
					player.left = false;
					player.right = false;
					player.up = false;
					player.down = false;
					points -= (5 * enemyspeed);
					player.moveTo(player.newx, player.newy);

					if (life == 0) {
						win = false;
						stop();
					}
				} else
					mapa.enemies.remove(i);
				points += (5 * enemyspeed);
			}
		}
		if ((int) System.currentTimeMillis() - start_star >= 5000)
			gwiazdka = false;
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
		if (temat == 0)
			dbg.setColor(KolorTrawy);
		else if (temat == 1)
			dbg.setColor(KolorLak);
		else if (temat == 2)
			dbg.setColor(KolorZboz);

		dbg.fillRect(0, 0, Config.GameWidth, Config.GameHeight);
		mapa.render(dbg);
		player.render(dbg);
		gui.render(dbg, nick, points);
		if (paused)
			gui.pause(dbg);
		dbg.dispose();
		setFocusable(true);

		BufferedImage bufor2 = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics gg = bufor2.getGraphics();
		gg.drawImage(bufor1, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(bufor2, 0, 0, this);

	}

}
