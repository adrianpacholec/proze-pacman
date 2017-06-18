import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentuj�ca punkt, dziedziczy po klasie Rectangle
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Punkt extends Rectangle {

	/**
	 * Identyfikator wersji klasy
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Zmienna pomocnicza przy animacji
	 */
	private int licznik;
	/**
	 * Konstruktor obiektu punktu
	 * 
	 * @param x
	 *            Pozycja x licz�c od lewego g�renego rogu panelu
	 * @param y
	 *            Pozycja y licz�c od lewego g�renego rogu panelu
	 * 
	 */
	public Punkt(int x, int y, int rand) {
		setBounds(x + 10, y + 10, 8, 8);
		licznik = rand;
	}

	/**
	 * Metoda rysuj�ca reprezentacj� punktu
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		
		Spritesheet sheet = Control.moneta;
		g.drawImage(sheet.getSprite(licznik,1), x-8, y-8, null);
		if (licznik == 7) licznik = 0; else licznik++;
	}
}