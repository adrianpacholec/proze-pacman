import javax.swing.JFrame;

/**
 * Klasa opisuj�ca panel Swing, w kt�rym odbywa si� rysowanie grafiki gry.
 * Zmiana? Obs�uguje jednocze�nie cz�� logiki gry zwi�zan� z po��czeniem innych
 * cz�ci w jedno.
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class GameOnline extends Game {
	private int port;
	private String adres;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
int online;
	public GameOnline(String nicktext, int enemyspeed, JFrame frame, String[] mapaPath, int map, int points,
			int temat, int online, String adres, int port) {
		super(nicktext, enemyspeed, frame, mapaPath, map, points, temat);
		
		
		// TODO Auto-generated constructor stub
		this.online=online;
		this.adres=adres;
		this.port=port;
	}
	
	@Override
	public synchronized void stop() {
		timer.stop();
		if (win && map_index != mapaPath.length) {
			nextLevel();
		} else {
			OnlineEnd end = new OnlineEnd(frame,nick,points,online,adres,port);
			frame.add(end);
			frame.remove(this);
			frame.setVisible(true);
		}
	}
}
