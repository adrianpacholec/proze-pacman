import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentuj�ca gwiazdk�, dziedziczy po klasie Rectangle
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Star extends Rectangle {

	/**
	 * Identyfikator wersji klasy
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor obiektu gwiazdki
	 * 
	 * @param x
	 *            Pozycja x licz�c od lewego g�renego rogu panelu
	 * @param y
	 *            Pozycja y licz�c od lewego g�renego rogu panelu
	 * 
	 */
	public Star(int x, int y, int rand) {
		setBounds(x + 6, y + 6, 20, 20);
	}

	/**
	 * Metoda rysuj�ca reprezentacj� gwiazdki
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		Spritesheet sheet = Control.spritesheet;
		g.drawImage(sheet.getSprite(9, 3), x - 4, y - 4, null);
	}
}
