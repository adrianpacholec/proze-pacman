import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Implementacja wczytywania pliku konfiguracyjngeo.
 * <p>
 * Zawiera metodę przyjmującą nazwę pliku konfiguracyjnego i zwracającą
 * obiekt typu Properties zawierający stałe konfiguracyjne.
 *
 
 */
public class ReadConfig {

    /**
     * Metoda służąca do wyczytania wartości z pliku <code>.properties</code>
     * i umieszczenia ich w obiekcie klasy Properties. Metoda zwraca nowo stworzony
     * obiekt.
     *
     * @param propFilePath ścieżka dostepu do pliku <code>.properties</code>
     * @return obiekt Properties zawierający dane z pliku
     * @throws IOException jeĹĽeli nie uda siÄ™ otworzyÄ‡ pliku konfiguracyjnego
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
