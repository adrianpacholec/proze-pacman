import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * 
 * @author Pawel Kowalik
 * @author Adrian Pacholec
 */
public class OnlineEnd extends JPanel implements ActionListener {

	/**
	 * Identyfikator wersji klasy
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * przyciski do poruszania si� po menu
	 */
	JButton back, end;
	/**
	 * etykiety panelu końca gry online
	 */
	JLabel nicklabel, punktylabel, koniec;
	/**
	 * obiekt okna programu
	 */
	JFrame frame;
	/**
	 * zmienne z nickiem i adresem
	 */
	String nick, adres;
	/**
	 * obiekt pola z suwakiem
	 */
	private JScrollPane scrollPane;
	/**
	 * zmienne przechowujące liczbę punktów i paramtery połączenia
	 */
	int points, online, port;
	/**
	 * Obiekt skanera
	 */
	public static Scanner file;

	/**
	 * Konstruktor panelu kończącego rozgrywkę
	 * 
	 * @param nick
	 *            Nick gracza
	 * @param frame
	 *            Okno programu
	 * @param points
	 *            liczba punktów
	 * @param online
	 *            Numer wybranej gry online
	 * @param adres
	 *            Adres, z którym nawiązano połączenie
	 * @param port
	 *            Numer portu, z którym nawiązano połączenie
	 * 
	 */
	public OnlineEnd(JFrame frame, String nick, int points, int online, String adres, int port) {
		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setVisible(true);
		koniec = new JLabel("Koniec gry", SwingConstants.CENTER);
		add(koniec);
		this.nick = nick;
		this.points = points;
		this.adres = adres;
		this.port = port;
		punktylabel = new JLabel("Punkty:" + points, SwingConstants.CENTER);
		add(punktylabel);
		punktylabel.setFont(new Font(Font.DIALOG, Font.BOLD, 19));
		this.online = online;
		nicklabel = new JLabel(Config.ButtonUserName + ": " + nick, SwingConstants.CENTER);
		add(nicklabel);
		nicklabel.setFont(new Font(Font.DIALOG, Font.BOLD, 19));
		add(new JLabel("Najlepsze wyniki:", SwingConstants.CENTER));
		wyslijWyniki(nick, adres, points);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == back) {
			frame.remove(this);
			OnlineGame online = new OnlineGame(frame);
			frame.add(online);
			frame.remove(this);
			frame.setVisible(true);
		}

		if (e.getSource() == end) {
			frame.dispose();
		}
	}

	/**
	 * Metoda odpowiedzialna za podsumowanie i wysłanie do serwera uzyskanego
	 * wyniku, a następnie uzyskanie odpowiedzi w postaci zaktualizowanej listy
	 * najlepszych wyników danej gry online, zapisanie jej do pliku i
	 * wyświtlenie na ekranie.
	 * 
	 * @param nick
	 *            Nick gracza
	 * @param adres
	 *            Adres, z którym nawiązano połączenie
	 * @param points
	 *            Zdobyta liczba punktów
	 */
	public void wyslijWyniki(String nick, String adres, int points) {
		try {
			Socket s = new Socket(adres, port);
			OutputStream outStream = s.getOutputStream();
			InputStream inStream = s.getInputStream();
			PrintWriter out = new PrintWriter(outStream, true);
			Scanner in = new Scanner(inStream);
			out.println("SENDING SCORE");
			System.out.println("klient wysylam score");
			out.println(nick + "%" + points + "%" + online);
			System.out.println("klient wyslalem score");
			out.println("GET HSCORES");
			save("online_high_scores" + online + ".txt", in.nextLine().split("%"));

			JTextArea textArea = new JTextArea();
			scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			try {
				file = new Scanner(new File("online_high_scores" + online + ".txt"));
				while (file.hasNextLine())
					textArea.append(file.nextLine() + "\n");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			textArea.setLineWrap(true);
			textArea.setEditable(false);
			textArea.setWrapStyleWord(true);
			add(scrollPane);

			JPanel opcje = new JPanel();

			back = new JButton("Powrót");
			opcje.add(back);
			back.addActionListener(this);

			end = new JButton("Koniec");
			opcje.add(end);
			add(opcje);
			end.addActionListener(this);
			revalidate();
			repaint();
			frame.pack();
			in.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda zapisująca otrzymaną odpowiedz serwera do pliku
	 * 
	 * @param fileName
	 *            Nazwa zapisywanego pliku
	 * @param data
	 *            Tablica zawierająca dane odebrane z serwera
	 */
	public void save(String fileName, String[] data) {
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			for (String str : data) {
				writer.println(str);
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("error");
		}

	}

}