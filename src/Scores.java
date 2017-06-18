import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Klasa, która zajmuje się wyświetlaniem najlepszych wyników
 * gry offline
 * 
 * @author Pawel Kowalik
 * @author Adrian PAcholec
 * @version 1.0
 */

public class Scores extends JPanel implements ActionListener {

	/**
	 * nieobowiązkowy identyfikator klasy
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * skaner plików
	 */
	public static Scanner file;
	/**
	 * ScrollPane umożliwiający przewijanie wynikow, gdy nie będą mieściły się w
	 * panelu
	 */
	private JScrollPane scrollPane;
	/**
	 * przycisk powrotu
	 */
	private JButton back;
	/**
	 * etykieta wyświetlana w panelu
	 */
	private JLabel wyniki;
	/**
	 * okno programu
	 */
	public JFrame frame;
	/**
	 * pole tekstowe na kt�rym instrukcja b�dzie wy�wietlona
	 */
	private final JTextArea textArea;

	/**
	 * Konstruktor panelu z najlepszymi wynikami
	 *
	 * @param frame
	 *            okno programu
	 */
	public Scores(JFrame frame) {

		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		wyniki = new JLabel("Najlepsze wyniki:", SwingConstants.CENTER);
		wyniki.setAlignmentX(Component.CENTER_ALIGNMENT);
		wyniki.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(wyniki);
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		try {
			file = new Scanner(new File(Config.FileHighscore));
			while (file.hasNextLine())
				textArea.append(file.nextLine() + "\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		add(scrollPane);

		back = new JButton(Config.ButtonBack);
		add(back);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == back) {
			Menu menu = new Menu(frame);
			frame.add(menu);
			frame.remove(this);
			frame.setVisible(true);
		}
	}
}
