import java.io.IOException;
import java.util.Properties;

/**
 * 
 * Główna klasa gry Pacman
 * 
 * 
 * @author Pawel Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */


public class Main 
{
	static int points;
	/**
     * Wczytanie pliku konfiguracyjnego  Config.properties i utworzenie 
     * na jego podstawie instancji menu głównego
     *
     * @param args argumenty przekazane do aplikacji
     */
	public static void main(String[] args)
	{
		String configPath = "config.properties";
		ReadConfig readConfig = new ReadConfig();
		try{
		Properties properties = readConfig.getProperties(configPath);
		Config.readConstants(properties);

		new MainWindow();
		} catch (IOException e) {
		    System.out.println("Exception when loading properties: " + e);
		}
			
	}
}
