import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Implementacja wczytywania pliku konfiguracyjngeo.
 * <p>
 * Zawiera metodê przyjmuj¹c¹ nazwê pliku konfiguracyjnego i zwracaj¹c¹
 * obiekt typu Properties zawieraj¹cy sta³e konfiguracyjne.
 ** @author Pawe³ Kowalik
 *  @author Adrian Pacholec
 *  @version 1.0
 *
 */
public class ReadConfig {

    /**
     * Metoda s³u¿¹ca do wyczytania wartoœci z pliku <code>.properties</code>
     * i umieszczenia ich w obiekcie klasy Properties. Metoda zwraca nowo stworzony
     * obiekt.
     *
     * @param propFilePath œcie¿ka dostepu do pliku <code>.properties</code>
     * @return obiekt Properties zawieraj¹cy dane z pliku
     * @throws IOException jeÅ¼eli nie uda siÄ™ otworzyÄ‡ pliku konfiguracyjnego
     */
    public final Properties getProperties(String propFilePath) throws IOException {

        Properties prop = new Properties();
        try (InputStream inputStream = new FileInputStream(propFilePath)) {
            prop.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }
}
