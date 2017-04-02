import java.util.Properties;


/**
 * Klasa zawierająca stałe operacyjne, dostępne dla  wszystkich
 * komponentów programu. Nazwy danych są identyczne z nazwami parametrów
 * konfiguracyjnych.
 *
 * @author Paweł Kowalik
 * @author Adrian PAcholec
*/


public class Config {

	
	/**
	 * nazwa aplikacji
	 */
	public static String ApplicationName;
	
	/**
	 * szerokość okna
	 */
	public static int WindowWidth;
	
	/**
	 * wysokość okna
	 */
	public static int WindowHeight;
	
	/**
	 *  tekst pod przyciskiem anulowania
	 */
	public static String ButtonCancel;
	
	/**
	 * tekst pod przyciskiem przycisku do powrotu
	 */
	public static String ButtonBack;
	
	/**
	 *  tekst pod przyciskiem służącym przejściu do menu nowej gry
	 */
	public static String ButtonNewGame;
	
	/**
	 * tekst pod przyciskiem pod służącym do wyświetlenia instrukcji
	 */
	public static String ButtonInstruction;
	
	/**
	 *  tekst pod przyciskiem służącym do wyświetlania wyników
	 */
	public static String ButtonHighscores;
	
	/**
	 * tekst pod przyciskiem służącym do wyjścia z gry
	 */
	public static String ButtonEndGame;
	
	/**
	 * tekst pod przyciekiem służącym do wybrania opcji OK
	 */
	public static String ButtonOK;
	
	/**
	 * tekst pod przyciskiem służącym do wprowadzenia nazwy użytkownika
	 */
	public static String ButtonUserName;
	
	
	/**
	 * tekst pod przyciskiem służącym do ustawienia poziomu trudności na łatwy
	 */
	public static String GameLevel_1;
	
	/**
	 * tekst pod przyciskiem służącym do ustawienia poziomu trudności na średni
	 */
	public static String GameLevel_2;
	
	
	/**
	 * tekst pod przyciskiem służącym do ustawienia poziomu trudności na trudny
	 */
	public static String GameLevel_3;
	
	
	
	/**
	 * prędkość duszków na poziomie łatwym
	 */
	public static int EnemySpeed_Level_1;
	
	
	
	/**
	 * prędkość duszków na poziomie średnim
	 */
	public static int EnemySpeed_Level_2;
	
	
	/**
	 * prędkość duszków na poziomie trudnym
	 */
	public static int EnemySpeed_Level_3;
	
	/**
	 * nazwa pliku z instrukcją
	 */
	public static String FileInstruction;
	
	
	/**
	 * szerokość okna z grą 
	 */
	public static int GameWidth;
	
	
	
	/**
	 * szerokość okna z grą 
	 */
	public static int GameHeight;
	
	
	
	/**
	 * nazwa pliku z mapą
	 */
	public static String FileMap;
	
	/**
	 * 
	 */
	public static int MapWidth;
	
	
	/**
	 * 
	 */
	public static int MapHeight;
	
	
	/**
	 * Tytuł okna NickWindow
	 */
	public static String UserName;
	
	
	/**
	 Metoda parsująca dane konfiguracyjne, w trybie offline,  dane sÄ… wczytywane z lokalnego pliku konfiguracyjnego
    * @param Config obiekt Properties, który przechowuje dane do sparsowania
	*/
	
	public static void readConstants(Properties Config){
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
        GameLevel_3= Config.getProperty("GameLevel_3");
        EnemySpeed_Level_1= Integer.parseInt(Config.getProperty("EnemySpeed_Level_1"));
        EnemySpeed_Level_2 = Integer.parseInt(Config.getProperty("EnemySpeed_Level_2"));
        EnemySpeed_Level_3= Integer.parseInt(Config.getProperty("EnemySpeed_Level_3"));
        FileInstruction= Config.getProperty("FileInstruction");
        GameWidth= Integer.parseInt(Config.getProperty("GameWidth"));
        GameHeight= Integer.parseInt(Config.getProperty("GameHeight"));
        FileMap= Config.getProperty("FileMap");
        MapWidth= Integer.parseInt(Config.getProperty("MapWidth"));
        MapHeight= Integer.parseInt(Config.getProperty("MapHeight"));
        UserName= Config.getProperty("UserName");
	}
	
	
	
}
