
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * klasa tworząca okno wyswietlające się po zakończeniu rozgrywki, podsumowująca
 * ją i dajaca wybór graczu co chce zrobić dalej
 * 
 * @author Pawel Kowalik
 * @author Adrian Pacholec
 */
public class Defeat extends JFrame implements ActionListener {

	/**
	 * zmienna do określenia własnej czcionki
	 */
	private Font Userfont;

	/**
	 * 
	 */

	JButton mainmenu, end;
	JLabel nicklabel, punktylabel;

	/**
	 * Constructor klasy Defeat
	 * 
	 * @param points
	 * @param nick
	 */
	public Defeat(String nick, int points) {
		super(Config.GameOver);

		/**
		 * ustawienie tła okna na niebiesko
		 */
		getContentPane().setBackground(Color.BLUE);
		setLayout(new GridLayout(4, 1));
		setSize(500, 300);
		setVisible(true);

		Userfont = new Font("Courier", Font.BOLD, 20);

		// nicklabel.setFont(Userfont);
		nicklabel = new JLabel(Config.ButtonUserName + ":" + nick, SwingConstants.CENTER);
		add(nicklabel);
		nicklabel.setFont(Userfont);
		nicklabel.setForeground(Color.YELLOW);

		// punktylabel.setFont(Userfont);
		punktylabel = new JLabel("Punkty:" + points, SwingConstants.CENTER);
		add(punktylabel);
		punktylabel.setFont(Userfont);
		punktylabel.setForeground(Color.YELLOW);

		// mainmenu.setFont(Userfont);
		mainmenu = new JButton("Zacznij od nowa");
		add(mainmenu);
		mainmenu.addActionListener(this);

		end = new JButton("Koniec");
		add(end);
		end.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		{
			if (source == end)
				this.dispose();
			// new MainWindow().setVisible(true);
		}

		if (source == mainmenu) {
			this.dispose();
			new MainWindow().setVisible(true);
		}

	}

}
