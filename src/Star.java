import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentuj¹ca gwiazdkê, dziedziczy po klasie Rectangle
 *
 * @author Pawe³ Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Star extends Rectangle {

	/**
	 * Konstruktor obiektu gwiazdki
	 * 
	 * @param x
	 *            Pozycja x licz¹c od lewego górenego rogu panelu
	 * @param y
	 *            Pozycja y licz¹c od lewego górenego rogu panelu
	 * 
	 */
	public Star(int x, int y) {
		setBounds(x + 6, y + 6, 20, 20);
	}

	/**
	 * Metoda rysuj¹ca reprezentacjê gwiazdki
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
}
