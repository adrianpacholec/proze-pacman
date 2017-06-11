import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Klasa reprezentuj¹ca interfejs graficzny u¿ytkownika
 *
 * @author Pawe³ Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class GUI {
	/**
	 * Metoda rysuj¹ca elementy interfejsu graficznego
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * @param nick
	 *            Nick u¿ytkownika
	 * 
	 */
	public void render(Graphics g, String nick, int points) {

		
		g.setColor(Color.pink);
		if(Game.life >= 1) g.fillRect(590, 8, 18, 18);
		if(Game.life >= 2) g.fillRect(560, 8, 18, 18);
		if(Game.life >= 3) g.fillRect(530, 8, 18, 18);
		g.setColor(Color.white);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 19));
		g.drawString("Punkty: " + String.valueOf(points), 15, 25);
		g.drawString("Twój nick to: " + nick, 200, 470);
		//}
		
		/*if(Player.life == 2){
			g.fillRect(560, 8, 18, 18);
			g.fillRect(530, 8, 18, 18);
			}
		if(Player.life == 1){
			g.fillRect(530, 8, 18, 18);
			}*/
		
	}


}
