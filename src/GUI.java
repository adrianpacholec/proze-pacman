import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Klasa reprezentująca interfejs graficzny użytkownika
 *
 * @author Paweł Kowalik
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
	 *            Nick użytkownika
	 * 
	 */
	public void render(Graphics g, String nick) {
		drawInfo(g, nick);
		g.setColor(Color.pink);
		
		//if(Player.life == 3){
		g.fillRect(590, 8, 18, 18);
		g.fillRect(560, 8, 18, 18);
		g.fillRect(530, 8, 18, 18);
		//}
		
		/*if(Player.life == 2){
			g.fillRect(560, 8, 18, 18);
			g.fillRect(530, 8, 18, 18);
			}
		if(Player.life == 1){
			g.fillRect(530, 8, 18, 18);
			}*/
		
	}

	public void drawInfo(Graphics g, String nick) {
		g.setColor(Color.white);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 19));
		g.drawString("Punkty: ", 15, 25);
		g.drawString("Twój nick to: " + nick, 200, 470);

	}

}
