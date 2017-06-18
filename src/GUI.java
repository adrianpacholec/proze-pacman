import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Klasa reprezentuj�ca interfejs graficzny u�ytkownika
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class GUI {
	/**
	 * Metoda rysująca elementy interfejsu graficznego
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * @param nick
	 *            Nick u�ytkownika
	 * 
	 */
	
	public void render(Graphics g, String nick, int points) {

		Spritesheet sheet = Control.spritesheet;
		
		g.setColor(Color.pink);
		if(Game.life >= 1) g.drawImage(sheet.getSprite(9,4), 590, 8, null);
		if(Game.life >= 2) g.drawImage(sheet.getSprite(9,4), 550, 8, null);
		if(Game.life >= 3)g.drawImage(sheet.getSprite(9,4), 510, 8, null);
		g.setColor(Color.white);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 19));
		g.drawString("Punkty: " + String.valueOf(points), 15, 25);
		g.drawString("Twój nick to: " + nick, 200, 470);
	}
	
	public void pause(Graphics g) {
		g.setColor(new Color(0,0,0,127));
		g.fillRect(0,0,Config.GameWidth,Config.GameHeight);
		g.setColor(Color.white);
		g.drawString("PAUZA", Config.GameWidth/2-30, Config.GameHeight/2-5);
	}

		
	
	

}
