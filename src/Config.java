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

	public static String ApplicationName;
	/**
	 * nazwa aplikacji
	 */
	
	public static int WindowWidth;
	/**
	 * szerokość okna
	 */
	
	public static int WindowHeight;
	/**
	 * wysokość okna
	 */
	
	public static String ButtonCancel;
	/**
	 *  tekst pod przyciskiem anulowania
	 */
	
	public static String ButtonBack;
	/**
	 * tekst pod przyciskiem przycisku do powrotu
	 */
	
	public static String ButtonNewGame;
	/**
	 *  tekst pod przyciskiem służącym przejściu do menu nowej gry
	 */
	
	public static String ButtonInstruction;
	/**
	 * tekst pod przyciskiem pod służącym do wyświetlenia instrukcji
	 */
	
	public static String ButtonHighscores;
	/**
	 *  tekst pod przyciskiem służącym do wyświetlania wyników
	 */
	
	public static String ButtonEndGame;
	/**
	 * tekst pod przyciskiem służącym do wyjścia z gry
	 */
	
	public static String ButtonOK;
	/**
	 * tekst pod przyciekiem służącym do wybrania opcji OK
	 */
	
	public static String ButtonUserName;
	/**
	 * tekst pod przyciskiem służącym do wprowadzenia nazwy użytkownika
	 */
	
	
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
        
        
	}
	
	
	
}
