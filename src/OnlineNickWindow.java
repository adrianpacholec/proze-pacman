import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OnlineNickWindow extends NickWindow {
	/**
	 * adres z jakim nawiązano połączenie
	 */
	private String adres;
	/**
	 * zmienne przechowujące informacje o połączeniu
	 */
	private int wybor, port;
	/**
	 * nieobowi�zowy identyfikator klasy
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor panelu wyboru nicku i poziomu truności gry online
	 * 
	 * @param frame
	 *            Obiekt okna programu
	 * @param wybor
	 *            Numer wybranej gry
	 * @param port
	 *            Port, z którym nawiązano połączenie
	 */
	public OnlineNickWindow(JFrame frame, int wybor, String adres, int port) {
		super(frame);
		this.wybor = wybor;
		this.port = port;
		this.adres = adres;
	}

	@Override
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

					String[] mapy = { "online_map" + wybor + ".txt" };
					int map_index = 0;

					GameOnline game = new GameOnline(nicktext, spdlevel, frame, mapy, map_index, points, temat, wybor,
							adres, port);
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
