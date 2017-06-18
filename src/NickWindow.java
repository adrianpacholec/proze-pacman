import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NickWindow extends JPanel implements ActionListener {

	/**
	 * nieobowi�zowy identyfikator klasy
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * przyciski do poruszania si� po menu
	 */
	protected JButton back, ok;

	/**
	 * pole tekstowe do wpisania nicku
	 */
	protected JTextField field;
	/**
	 * obiekt okna programu
	 */
	public JFrame frame;

	/**
	 * grupa radiobutton�w
	 */
	protected ButtonGroup levels;

	/**
	 * radiobuttony kt�re pozwalaj� wybra� poziom trudno�ci przez u�ytownika
	 */
	protected JRadioButton easy, medium, hard;
	/**
	 * zmienna przechowująca temat graficzny
	 */
	protected int temat;
	/**
	 * zmienna zapisuj�ca tekst kt�ry u�ytkownik wpisa� do pola tekstowego
	 */
	public String nicktext;

	/**
	 * zmienna w zaleznosci od wyboru pozioomu gracza, wczytujaca odpwoeidnia
	 * predkosc duchow
	 */
	public int spdlevel;
	/**
	 * konstruktor klasy NickWindow
	 */
	public int points;

	/**
	 * Konstruktor panelu wyboru nicku i poziomu truności
	 * 
	 * @param frame
	 *            Obiekt okna programu
	 */
	public NickWindow(JFrame frame) {
		setLayout(new GridLayout(8, 1));
		this.frame = frame;
		add(new JLabel(Config.ButtonUserName, SwingConstants.CENTER));

		field = new JTextField();
		field.setFont(new Font("Courier", Font.BOLD, 15));
		add(field);

		back = new JButton(Config.ButtonBack);
		add(back);
		back.addActionListener(this);

		ok = new JButton(Config.ButtonOK);
		add(ok);
		ok.addActionListener(this);

		add(new JLabel("Wybierz poziom: ", SwingConstants.CENTER));
		levels = new ButtonGroup();

		easy = new JRadioButton(Config.GameLevel_1, false);
		levels.add(easy);
		add(easy);
		easy.setHorizontalAlignment(AbstractButton.CENTER);
		easy.addActionListener(this);

		medium = new JRadioButton(Config.GameLevel_2, false);
		levels.add(medium);
		add(medium);
		medium.setHorizontalAlignment(AbstractButton.CENTER);
		medium.addActionListener(this);

		hard = new JRadioButton(Config.GameLevel_3, false);
		levels.add(hard);
		add(hard);
		hard.setHorizontalAlignment(AbstractButton.CENTER);
		hard.addActionListener(this);
		field.requestFocus();
	}

	/**
	 * Metoda sterująca zachowaniem programu po wyborze przycisku
	 */
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == easy) {
			spdlevel = Config.EnemySpeed_Level_1;
			temat = 0;
		} else if (source == medium) {
			spdlevel = Config.EnemySpeed_Level_2;
			temat = 1;
		} else if (source == hard) {
			spdlevel = Config.EnemySpeed_Level_3;
			temat = 2;
		} else if (source == back) {
			Menu menu = new Menu(frame);
			frame.add(menu);
			frame.remove(this);
			frame.setVisible(true);

		}

		else if (source == ok) {

			/**
			 * pobieranie nazwy u�ytkownika od u�ytkownika
			 */
			nicktext = field.getText();
			/**
			 * seria sprawdza� maj�ca na celu wpisanie poprawnego nicku
			 */
			if (nicktext.trim().equals("")) {
				JOptionPane.showMessageDialog(null, " Musisz wprowadzić nazwę użytkownika ", "Ostrzeżenie",
						JOptionPane.WARNING_MESSAGE);
				field.setText("");
				/**
				 * ustawienie myszki na pole tekstowe
				 */
				field.requestFocus();

			} else if (levels.isSelected(null)) {
				JOptionPane.showMessageDialog(null, " Musisz wybrać poziom trudności ", "Ostrzeżenie",
						JOptionPane.WARNING_MESSAGE);
			}

			else {
				if (nicktext.length() > 1 && !nicktext.contains(" ") && !nicktext.contains(",")
						&& !nicktext.contains(".") && !nicktext.contains("!") && !nicktext.contains("@")
						&& !nicktext.contains("#") && !nicktext.contains("$") && !nicktext.contains("*")
						&& !nicktext.contains("/") && !nicktext.contains("+") && !nicktext.contains("-")
						&& !nicktext.contains("]")) {

					JOptionPane.showMessageDialog(null, " Twój nick: " + nicktext, "Ahoj przygodo!",
							JOptionPane.INFORMATION_MESSAGE);

					/**
					 * tworzenie okna z grą
					 */

					String[] mapy = Config.FileMap.split(" ");
					int map_index = 0;
					System.out.print("Szybkosc przeciwnikow: " + spdlevel);

					Game game = new Game(nicktext, spdlevel, frame, mapy, map_index, 0, temat);
					Game.life = 3;
					frame.add(game);
					frame.remove(this);
					frame.setVisible(true);

				} else {

					JOptionPane.showMessageDialog(null, "Wprowadź nazwę użytkownika jeszcze raz", "Ostrzeżenie",
							JOptionPane.WARNING_MESSAGE);

					/**
					 * ustawienie kurosra na pole tekstowe
					 */
					field.requestFocus();
					/**
					 * ponowne pobranie tekstu od u�ytkownika
					 */
					field.getText();
					/**
					 * wyczyszczenie pola tekstwoego
					 */
					field.setText("");
				}
			}

		}

	}
}