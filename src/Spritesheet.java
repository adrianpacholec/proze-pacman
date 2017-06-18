import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * Klasa obiektu arkusza graficznego, wczytująca go z pliku graficznego.
 * 
 * @author Pawel Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Spritesheet {
	/**
	 * obiekt arkusza graficznego
	 */
	private BufferedImage sheet;
	/**
	 * konstruktor obiektu arkusza graficznego
	 * @param path Ścieżka dostępu do pliu graficznego
	 */
	public Spritesheet(String path) {
		try {
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			System.out.println("Nie zaladowano sprite");
		}
	}

	public BufferedImage getSprite(int xx, int yy) {
		return sheet.getSubimage(xx * 32, yy * 32, 32, 32);

	}

}
