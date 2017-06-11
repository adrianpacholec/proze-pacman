import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class NickWindow extends JFrame implements ActionListener {

	/**
	 * nieobowi�zowy identyfikator klasy
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * labele informuj�ce u�ytkownika co ma zrobi�, wybra� poziom i wpsia� nick
	 */
	private JLabel nick, picklevel;

	/**
	 * przyciski do poruszania si� po menu
	 */
	private JButton back, ok;

	/**
	 * pole tekstowe do wpisania nicku
	 */
	private JTextField field;

	/**
	 * grupa radiobutton�w
	 */
	private ButtonGroup levels;

	/**
	 * radiobuttony kt�re pozwalaj� wybra� poziom trudno�ci przez u�ytownika
	 */
	private JRadioButton easy, medium, hard;

	/**
	 * zmienna do okre�lenia w��snej czcionki
	 */
	private Font Userfont;

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

	public NickWindow() {
		super(Config.UserName);
		setLayout(new GridLayout(8, 1));
		setSize(500, 300);

		/**
		 * metoda pytaj�ca u�ytkownika czy jest pewny, �e chce sko�czy�
		 * wpisywanie nicku i zgodnie z jego wol�, albo zamyka to okno i wraca
		 * do poprzedniego, albo je�li u�ytkownik zmieni� zdanie pozostaje w tej
		 * cze�ci menu
		 * 
		 */
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wr�ci�?", "",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (x == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
					new MainWindow();
				}
			}
		});

		nick = new JLabel(Config.ButtonUserName, SwingConstants.CENTER);
		add(nick);

		Userfont = new Font("Courier", Font.BOLD, 15);
		field = new JTextField();
		field.setBounds(170, 80, 50, 20);
		field.setFont(Userfont);
		add(field);

		back = new JButton(Config.ButtonBack);
		add(back);
		back.addActionListener(this);

		ok = new JButton(Config.ButtonOK);
		add(ok);
		ok.addActionListener(this);

		picklevel = new JLabel("Wybierz poziom: ", SwingConstants.CENTER);
		add(picklevel);

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

	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == easy) {
			spdlevel = Config.EnemySpeed_Level_1;
		} else if (source == medium) {
			spdlevel = Config.EnemySpeed_Level_2;
		} else if (source == hard) {
			spdlevel = Config.EnemySpeed_Level_3;
		} else if (source == back) {

			new MainWindow().setVisible(true);
			dispose();

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
				JOptionPane.showMessageDialog(null, " Musisz wprowadzi� nazw� u�ytkownika ", "Ostrze�enie",
						JOptionPane.WARNING_MESSAGE);
				field.setText("");
				/**
				 * ustawienie myszki na pole tekstowe
				 */
				field.requestFocus();

			} else {
				if (nicktext.length() > 1 && !nicktext.contains(" ") && !nicktext.contains(",")
						&& !nicktext.contains(".") && !nicktext.contains("!") && !nicktext.contains("@")
						&& !nicktext.contains("#") && !nicktext.contains("$") && !nicktext.contains("*")
						&& !nicktext.contains("/") && !nicktext.contains("+") && !nicktext.contains("-")
						&& !nicktext.contains("]")) {
					// make sure that its length is not over 1, and that it has
					// no spaces and no commas

					JOptionPane.showMessageDialog(null, " Tw�j nick: " + nicktext, "Ahoj przygodo!",
							JOptionPane.INFORMATION_MESSAGE);

					/**
					 * tworzenie okna z gr�
					 */

					JFrame frame = new JFrame();
					String[] mapy = Config.FileMap.split(" ");
					// int map_index = mapy.length;
					int map_index = 0;
					Game game = new Game(nicktext, spdlevel, frame, mapy, map_index, points);
					frame.setTitle(Config.ApplicationName);
					frame.add(game);
					Dimension dimension = new Dimension(Config.GameWidth, Config.GameHeight);
					frame.setPreferredSize(dimension);
					frame.pack();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					// game.run();
					dispose();

				} else {
					// if a space or comma was found no matter how big the text
					// it will execute the else..
					JOptionPane.showMessageDialog(null, "Wprowad� nazw� u�ytkownika jeszcze raz", "Ostrze�enie",
							JOptionPane.WARNING_MESSAGE);
					// field.setText("");

					/**
					 * ustawienie myszki na pole tekstowe
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