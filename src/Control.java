import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Abstrakcyjna klasa zawierająca główną mechanikę wyświetlania i sterowania grą, po której
 * dziedziczą klasa gry oraz menu.
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public abstract class Control extends JPanel implements KeyListener {

	/**
	 * Identyfikator wersji klasy
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Obiekt gracza
	 */
	public static Player player;
	/**
	 * Obiekt mapy
	 */
	public static Mapa mapa;
	/**
	 * Obiekt sprite'ów graficznych
	 */
	public static Spritesheet spritesheet, moneta;
	/**
	 * Flaga informująca, czy gra jest wstrzymana
	 */
	public boolean paused = false;
	/**
	 * Ramka, zawierająca grę
	 */
	public JFrame frame;
	/**
	 * Zmienna losowa
	 */
	public Random random;
	/**
	 * Zmienna określająca wybór tematu graficznego
	 */
	public int temat;
	/**
	 * Kolor tła dla łatwego poziomu trudności
	 */
	Color KolorTrawy = new Color(56, 106, 48);
	/**
	 * Kolor tła dla średniego poziomu trudności
	 */
	Color KolorLak = new Color(0, 107, 70);
	/**
	 * Kolor tła dla trudnego poziomu trudności
	 */
	Color KolorZboz = new Color(98, 53, 28);

	/**
	 * Anonimowa klasa timera
	 *
	 */
	class TimeListener implements ActionListener {
		/**
		 * Metoda odświeżająca mapę, wywoływana przez timer
		 */
		public void actionPerformed(ActionEvent e) {
			repaint();
			mapa.update();
			player.move();
			updatePlayer();
		}
	}

	void updatePlayer() {
	};

	/**
	 * Słuchacz timera
	 */
	ActionListener listener = new TimeListener();
	/**
	 * Obiekt timera sterującego mechaniką gry
	 */
	Timer timer = new Timer(1000 / 30, listener);

	public synchronized void stop() {
	};

	/**
	 * Metoda wstrzymująca rozgrywkę
	 */
	public void pause() {
		if (timer.isRunning()) {
			paused = true;
			repaint();
			timer.stop();
		} else {
			paused = false;
			repaint();
			timer.start();
		}
	}

	/**
	 * Metoda odpowiedzialna za reakcję gry na wciśnięcie przycisku
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			player.right = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			player.left = true;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			player.up = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			player.down = true;
		if (e.getKeyCode() == KeyEvent.VK_P)
			pause();
	}

	/**
	 * Metoda odpowiedzialna za reakcję gry na puszczenie przycisku
	 */

	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			player.right = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			player.left = false;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			player.up = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			player.down = false;

	}
	/**
	 * Metoda odpowiedzialna za reakcję gry na naciśnięcie przycisku
	 */
	public void keyTyped(KeyEvent e){
		
	}
	

	
}
