import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentuj�ca �cian�, dziedziczy po klasie Rectangle
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Tile extends Rectangle {
	/**
	 * Identyfikator wersji klasy
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Tablica pomocnicza służąca wyborowi odpowiej grafiki z arkusza
	 * graficznego
	 */
	int pos[] = new int[2];
	/**
	 * Tablica przechowująca współrzędne odpowiedniego pola na arkuszu
	 * graficznym
	 */
	int map[] = { 0, 2 };

	/**
	 * Konstruktor obiektu �ciany
	 * 
	 * @param x
	 *            Pozycja x licz�c od lewego g�renego rogu panelu
	 * @param y
	 *            Pozycja y licz�c od lewego g�renego rogu panelu
	 * @param temat
	 *            Numer odpowiedniego tematu graficznego
	 * 
	 */
	public Tile(int x, int y, int[] pozycja, int temat) {
		setBounds(x, y, 32, 32);
		okresl_pozycje(pozycja);
		map[1] = temat;
	}

	/**
	 * Metoda określająca względną pozycję obiektu ściany w stosunku do
	 * otaczających jej ścian
	 * 
	 * @param pozycja
	 *            Tablica reprezentująca obiekty otaczające analizowany obiekt
	 *            ściany
	 * 
	 * 
	 */
	private void okresl_pozycje(int[] pozycja) {
		if (pozycja[0] == '1' && pozycja[1] == '1' && pozycja[2] == '1' && pozycja[3] == '1' && pozycja[4] == '1'
				&& pozycja[5] == '1' && pozycja[6] == '1' && pozycja[7] == '1') {
			pos[0] = 3;
			pos[1] = 1;
		} else if (pozycja[1] == '1' && pozycja[6] == '1' && pozycja[3] == '1' && pozycja[4] == '1'
				&& pozycja[7] != '1') {
			pos[0] = 0;
			pos[1] = 1;
		} else if (pozycja[1] == '1' && pozycja[6] == '1' && pozycja[3] == '1' && pozycja[4] == '1'
				&& pozycja[5] != '1') {
			pos[0] = 0;
			pos[1] = 2;
		} else if (pozycja[1] == '1' && pozycja[6] == '1' && pozycja[3] == '1' && pozycja[4] == '1'
				&& pozycja[2] != '1') {
			pos[0] = 1;
			pos[1] = 1;
		} else if (pozycja[1] == '1' && pozycja[6] == '1' && pozycja[3] == '1' && pozycja[4] == '1'
				&& pozycja[0] != '1') {
			pos[0] = 1;
			pos[1] = 2;
		}

		else if (pozycja[1] != '1' && pozycja[6] == '1' && pozycja[3] != '1' && pozycja[4] == '1') {
			pos[0] = 2;
			pos[1] = 0;
		} else if (pozycja[1] != '1' && pozycja[6] == '1' && pozycja[3] == '1' && pozycja[4] == '1') {
			pos[0] = 2;
			pos[1] = 1;
		} else if (pozycja[1] != '1' && pozycja[6] == '1' && pozycja[3] == '1' && pozycja[4] != '1') {
			pos[0] = 2;
			pos[1] = 2;
		}

		else if (pozycja[1] == '1' && pozycja[6] == '1' && pozycja[3] != '1' && pozycja[4] == '1') {
			pos[0] = 3;
			pos[1] = 0;
		} else if (pozycja[1] == '1' && pozycja[6] == '1' && pozycja[3] == '1' && pozycja[4] != '1') {
			pos[0] = 3;
			pos[1] = 2;
		}

		else if (pozycja[1] == '1' && pozycja[6] != '1' && pozycja[3] != '1' && pozycja[4] == '1') {
			pos[0] = 4;
			pos[1] = 0;
		} else if (pozycja[1] == '1' && pozycja[6] != '1' && pozycja[3] == '1' && pozycja[4] == '1') {
			pos[0] = 4;
			pos[1] = 1;
		} else if (pozycja[1] == '1' && pozycja[6] != '1' && pozycja[3] == '1' && pozycja[4] != '1') {
			pos[0] = 4;
			pos[1] = 2;
		}

		else {
			pos[0] = 0;
			pos[1] = 0;
		}

	}

	/**
	 * Metoda rysująca reprezentację ściany na podstawie otaczających jej ścian
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		Spritesheet sheet = Control.spritesheet;
		g.drawImage(sheet.getSprite(pos[1] + map[1] * 3, pos[0] + map[0] * 6), x, y, null);
	}
}