import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	private BufferedImage sheet;

	public Spritesheet(String path) {
		try {
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			System.out.println("Nie zaladowano sprite");
		}
	}
	public BufferedImage getSprite(int xx, int yy){
		return sheet.getSubimage(xx*32, yy*32,32,32);
		
	}
	
}
