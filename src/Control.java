import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Control extends JPanel implements KeyListener {

	/**
	 * 
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
	public static Spritesheet spritesheet, moneta;
	public boolean paused = false;
	/**
	 * ramka kt�ra zawiera gr�
	 */
	public JFrame frame;

	public Random random;
	public int temat;
	Color KolorTrawy = new Color(56, 106, 48);
	Color KolorLak = new Color(0, 107, 70);
	Color KolorZboz = new Color(98, 53, 28);

	class TimeListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			repaint();
			mapa.update();
			player.move();
			updatePlayer();
		}
	}

	void updatePlayer() {
	};

	ActionListener listener = new TimeListener();
	Timer timer = new Timer(1000 / 30, listener);

	public synchronized void stop() {
	};

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
	 * metoda, kt�ra reaguje na zwolnienie klawisza true na false i zatrzymuj�ca
	 * pacmana
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

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
