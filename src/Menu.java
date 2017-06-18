import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * Panel menu głównego gry, dziedziczący mechanikę gry z klasy Control.
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class Menu extends Control {
	/**
	 * Identyfikator wersji klasy
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor przyjmuje okno JFrame, do którego zostaje dodany.
	 * 
	 * @param frame
	 *            Okno programu
	 */

	public Menu(JFrame frame) {
		this.frame = frame;
		player = new Player(Config.GameWidth / 2, Config.GameHeight / 2, 4);
		mapa = new Mapa("menu_map.txt", 0, temat);
		spritesheet = new Spritesheet("/sprites/male.png");
		addKeyListener(this);
		setFocusable(true);
		timer.start();

	}

	/**
	 * Metoda odczytująca wybór opcji menu, dokonywany poprzez wejście gracza w
	 * odpowiedni obszar planszy menu.
	 */

	void updatePlayer() {
		if (player.x > mapa.width * 32) {
			if (player.y > (mapa.width * 32) / 2)
				onlineGame();
			else
				nickWindow();
		}
		if (player.x < -32) {
			if (player.y > (mapa.width * 32) / 2)
				frame.dispose();
			else
				highScores();

		}
	};

	/**
	 * Metoda dodająca do okna programu panel z najlepszymi wynikami.
	 */
	public void highScores() {
		timer.stop();
		Scores highscores = new Scores(frame);
		frame.add(highscores);

		frame.remove(this);
		frame.setVisible(true);
	}

	/**
	 * Metoda dodająca do okna programu panel gry online.
	 */
	public void onlineGame() {
		timer.stop();
		OnlineGame online = new OnlineGame(frame);
		frame.add(online);

		frame.remove(this);
		frame.setVisible(true);
	}

	/**
	 * Metoda dodająca do okna programu panel przygotowania gry offline.
	 */
	public void nickWindow() {
		timer.stop();
		NickWindow nickwindow = new NickWindow(frame);
		frame.add(nickwindow);

		frame.remove(this);
		frame.setVisible(true);
	}

	/**
	 * Metoda rysująca na ekranie podpisy opcji menu.
	 */
	private void podpisy(Graphics g) {
		Spritesheet sheet = Control.spritesheet;
		g.setColor(new Color(80, 32, 40));
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

		g.drawString("NAJLEPSZE WYNIKI", 17, 150);
		g.drawImage(sheet.getSprite(9, 1), 15, 155, null);

		g.drawString("NOWA GRA", 520, 150);
		g.drawImage(sheet.getSprite(9, 2), 535, 165, null);

		g.drawString("WYJŚCIE", 15, 372);
		g.drawImage(sheet.getSprite(9, 1), 105, 360, null);

		g.drawString("GRA ONLINE", 500, 372);
		g.drawImage(sheet.getSprite(9, 2), 520, 385, null);
	}

	/**
	 * Metoda paint, rysująca grafikę. Tworzy BufferedImage o wymiarach
	 * początkowych, na podstawie którego tworzony jest kontekst graficzny,
	 * przekazywany do drugiego bufora, o rozmiarach takich, jak JPanel. W ten
	 * sposób generowana grafika rozciągana jest do aktualnych rozmiarów okna.
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void paintComponent(Graphics g) {
		BufferedImage bufor1 = new BufferedImage(Config.GameWidth, Config.GameHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics dbg = bufor1.getGraphics();

		super.paintComponent(dbg);

		if (temat == 0)
			dbg.setColor(KolorTrawy);
		else if (temat == 1)
			dbg.setColor(KolorLak);
		else if (temat == 2)
			dbg.setColor(KolorZboz);

		dbg.fillRect(0, 0, Config.GameWidth, Config.GameHeight);
		mapa.render(dbg);
		podpisy(dbg);
		player.render(dbg);
		dbg.dispose();
		setFocusable(true);

		BufferedImage bufor2 = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics gg = bufor2.getGraphics();
		gg.drawImage(bufor1, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(bufor2, 0, 0, this);

	}

}
