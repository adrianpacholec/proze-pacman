import java.util.Properties;

/**
 * Klasa zawieraj¹ca sta³e operacyjne, dostêpne dla wszystkich komponentów
 * programu. Nazwy danych s¹ identyczne z nazwami parametrów konfiguracyjnych.
 *
 * @author Pawe³ Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class Config {

	/**
	 * nazwa aplikacji
	 */
	public static String ApplicationName;

	/**
	 * szerokoœæ okna
	 */
	public static int WindowWidth;

	/**
	 * wysokoœæ okna
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
	 * tekst pod przyciskiem s³u¿¹cym przejœciu do menu nowej gry
	 */
	public static String ButtonNewGame;

	/**
	 * tekst pod przyciskiem pod s³u¿¹cym do wyœwietlenia instrukcji
	 */
	public static String ButtonInstruction;

	/**
	 * tekst pod przyciskiem s³u¿¹cym do wyœwietlania wyników
	 */
	public static String ButtonHighscores;

	/**
	 * tekst pod przyciskiem s³u¿¹cym do wyjœcia z gry
	 */
	public static String ButtonEndGame;

	/**
	 * tekst pod przyciekiem s³u¿¹cym do wybrania opcji OK
	 */
	public static String ButtonOK;

	/**
	 * tekst pod przyciskiem s³u¿¹cym do wprowadzenia nazwy u¿ytkownika
	 */
	public static String ButtonUserName;

	/**
	 * tekst pod przyciskiem s³u¿¹cym do ustawienia poziomu trudnoœci na ³atwy
	 */
	public static String GameLevel_1;

	/**
	 * tekst pod przyciskiem s³u¿¹cym do ustawienia poziomu trudnoœci na œredni
	 */
	public static String GameLevel_2;

	/**
	 * tekst pod przyciskiem s³u¿¹cym do ustawienia poziomu trudnoœci na trudny
	 */
	public static String GameLevel_3;
	public static String FileHighscore;

	/**
	 * prêdkoœæ duszków na poziomie ³atwym
	 */
	public static int EnemySpeed_Level_1;

	/**
	 * prêdkoœæ duszków na poziomie œrednim
	 */
	public static int EnemySpeed_Level_2;

	/**
	 * prêdkoœæ duszków na poziomie trudnym
	 */
	public static int EnemySpeed_Level_3;

	/**
	 * nazwa pliku z instrukcj¹
	 */
	public static String FileInstruction;

	/**
	 * szerokoœæ okna z gr¹
	 */
	public static int GameWidth;

	/**
	 * szerokoœæ okna z gr¹
	 */
	public static int GameHeight;

	/**
	 * nazwa pliku z map¹
	 */
	public static String FileMap;

	/**
	 * nazwa pliku z kolejn¹ map¹
	 */
	public static String FileMap2;

	/**
	 * 
	 */
	public static int MapWidth;

	/**
	 * 
	 */
	public static int MapHeight;

	/**
	 * Tytu³ okna NickWindow
	 */

	/**
	 * okreslenie predkosci pacmana
	 */
	public static int PacmanSpeed;
	/**
	 * tekst nazwa uzytkonika gdzie mamy wpisac swoj nick
	 */
	public static String UserName;

	/**
	 * tytu³ okna koniec gry
	 */
	public static String GameOver;

	/**
	 * Metoda parsuj¹ca dane konfiguracyjne, w trybie offline, dane sÄ…
	 * wczytywane z lokalnego pliku konfiguracyjnego
	 * 
	 * @param Config
	 *            obiekt Properties, który przechowuje dane do sparsowania
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
