import java.util.Properties;

/**
 * Klasa zawieraj�ca sta�e operacyjne, dost�pne dla wszystkich komponent�w
 * programu. Nazwy danych s� identyczne z nazwami parametr�w konfiguracyjnych.
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class Config {

	/**
	 * nazwa aplikacji
	 */
	public static String ApplicationName;

	/**
	 * szeroko�� okna
	 */
	public static int WindowWidth;

	/**
	 * wysoko�� okna
	 */
	public static int WindowHeight;

	/**
	 * tekst pod przyciskiem anulowania
	 */
	public static String ButtonCancel;

	/**
	 * tekst pod przyciskiem przycisku do powrotu
	 */
	public static String ButtonBack;

	/**
	 * tekst pod przyciskiem s�u��cym przej�ciu do menu nowej gry
	 */
	public static String ButtonNewGame;

	/**
	 * tekst pod przyciskiem pod s�u��cym do wy�wietlenia instrukcji
	 */
	public static String ButtonInstruction;

	/**
	 * tekst pod przyciskiem s�u��cym do wy�wietlania wynik�w
	 */
	public static String ButtonHighscores;

	/**
	 * tekst pod przyciskiem s�u��cym do wyj�cia z gry
	 */
	public static String ButtonEndGame;

	/**
	 * tekst pod przyciekiem s�u��cym do wybrania opcji OK
	 */
	public static String ButtonOK;

	/**
	 * tekst pod przyciskiem s�u��cym do wprowadzenia nazwy u�ytkownika
	 */
	public static String ButtonUserName;

	/**
	 * tekst pod przyciskiem s�u��cym do ustawienia poziomu trudno�ci na �atwy
	 */
	public static String GameLevel_1;

	/**
	 * tekst pod przyciskiem s�u��cym do ustawienia poziomu trudno�ci na �redni
	 */
	public static String GameLevel_2;

	/**
	 * tekst pod przyciskiem s�u��cym do ustawienia poziomu trudno�ci na trudny
	 */
	public static String GameLevel_3;
	/**
	 * tekst pod przyciskiem s�u��cym do przejścia do panelu z najlepszymi wynikami
	 */
	public static String FileHighscore;

	/**
	 * pr�dko�� duszk�w na poziomie �atwym
	 */
	public static int EnemySpeed_Level_1;

	/**
	 * pr�dko�� duszk�w na poziomie �rednim
	 */
	public static int EnemySpeed_Level_2;

	/**
	 * pr�dko�� duszk�w na poziomie trudnym
	 */
	public static int EnemySpeed_Level_3;

	/**
	 * nazwa pliku z instrukcj�
	 */
	public static String FileInstruction;

	/**
	 * szeroko�� okna z gr�
	 */
	public static int GameWidth;

	/**
	 * szeroko�� okna z gr�
	 */
	public static int GameHeight;

	/**
	 * nazwa pliku z map�
	 */
	public static String FileMap;

	/**
	 * nazwa pliku z kolejn� map�
	 */
	public static String FileMap2;

	/**
	 * szerokość mapy
	 */
	public static int MapWidth;

	/**
	 * wysokość mapy
	 */
	public static int MapHeight;

	/**
	 * okreslenie predkosci pacmana
	 */
	public static int PacmanSpeed;
	/**
	 * tekst nazwa uzytkonika gdzie mamy wpisac swoj nick
	 */
	public static String UserName;
	/**
	 * tytu� okna koniec gry
	 */
	public static String GameOver;

	/**
	 * Metoda parsuj�ca dane konfiguracyjne, w trybie offline, dane są
	 * wczytywane z lokalnego pliku konfiguracyjnego
	 * 
	 * @param Config
	 *            obiekt Properties, kt�ry przechowuje dane do sparsowania
	 */

	public static void readConstants(Properties Config) {
		ApplicationName = Config.getProperty("ApplicationName");
		WindowWidth = Integer.parseInt(Config.getProperty("WindowWidth"));
		WindowHeight = Integer.parseInt(Config.getProperty("WindowHeight"));
		ButtonCancel = Config.getProperty("ButtonCancel");
		ButtonBack = Config.getProperty("ButtonBack");
		ButtonNewGame = Config.getProperty("ButtonNewGame");
		ButtonInstruction = Config.getProperty("ButtonInstruction");
		ButtonHighscores = Config.getProperty("ButtonHighscores");
		ButtonEndGame = Config.getProperty("ButtonEndGame");
		ButtonOK = Config.getProperty("ButtonOK");
		ButtonUserName = Config.getProperty("ButtonUserName");
		GameLevel_1 = Config.getProperty("GameLevel_1");
		GameLevel_2 = Config.getProperty("GameLevel_2");
		GameLevel_3 = Config.getProperty("GameLevel_3");
		EnemySpeed_Level_1 = Integer.parseInt(Config.getProperty("EnemySpeed_Level_1"));
		EnemySpeed_Level_2 = Integer.parseInt(Config.getProperty("EnemySpeed_Level_2"));
		EnemySpeed_Level_3 = Integer.parseInt(Config.getProperty("EnemySpeed_Level_3"));
		FileInstruction = Config.getProperty("FileInstruction");
		FileHighscore = Config.getProperty("FileHighscore");
		GameWidth = Integer.parseInt(Config.getProperty("GameWidth"));
		GameHeight = Integer.parseInt(Config.getProperty("GameHeight"));
		FileMap = Config.getProperty("FileMap");
		FileMap2 = Config.getProperty("FileMap2");
		MapWidth = Integer.parseInt(Config.getProperty("MapWidth"));
		MapHeight = Integer.parseInt(Config.getProperty("MapHeight"));
		UserName = Config.getProperty("UserName");
		PacmanSpeed = Integer.parseInt(Config.getProperty("PacmanSpeed"));
		GameOver = Config.getProperty("GameOver");
	}

}
