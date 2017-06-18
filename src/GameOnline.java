import javax.swing.JFrame;

/**
 * Klasa reprezentująca grę online, dziedzicząca po klasie gry offline.
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class GameOnline extends Game {
	/**
	 * Numer portu, z którym nawiązano połączenie
	 */
	private int port;
	/**
	 * Adres, z którym nawiązano połączenie
	 */
	private String adres;
	/**
	 * Identyfikator wersji klasy
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Zmienna przechowująca numer wybranej gry online
	 */
	int online;

	/**
	 * Konstruktor przyjmuje parametry gry offline plus dodatkowe parametry gry
	 * online
	 * 
	 * @param nicktext
	 *            Nick gracza
	 * @param enemyspeed
	 *            Prędkość przeciwników
	 * @param frame
	 *            Okno programu
	 * @param mapaPath
	 *            Tablica ścieżek do map
	 * @param map
	 *            Indeks bieżącej mapy w tablicy map
	 * @param points
	 *            liczba punktów
	 * @param online
	 *            Numer wybranej gry online
	 * @param adres
	 *            Adres, z którym nawiązano połączenie
	 * @param port
	 *            Numer portu, z którym nawiązano połączenie
	 * @param temat
	 *            temat graficzny
	 * 
	 */
	public GameOnline(String nicktext, int enemyspeed, JFrame frame, String[] mapaPath, int map, int points, int temat,
			int online, String adres, int port) {
		super(nicktext, enemyspeed, frame, mapaPath, map, points, temat);

		// TODO Auto-generated constructor stub
		this.online = online;
		this.adres = adres;
		this.port = port;
	}

	@Override
	/**
	 * Metoda zatrzymująca grę
	 */
	public synchronized void stop() {
		timer.stop();
		if (win && map_index != mapaPath.length) {
			nextLevel();
		} else {
			OnlineEnd end = new OnlineEnd(frame, nick, points, online, adres, port);
			frame.add(end);
			frame.remove(this);
			frame.setVisible(true);
		}
	}
}
