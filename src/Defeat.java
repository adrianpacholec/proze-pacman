import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * zmienna do określenia własnej czcionki
	 */
	private Font Userfont;

	/**
	 * 
	 */
	public static Komparator komp;
	// public List<Record> records;
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
		highscore();
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

	public void highscore() {
		ArrayList<Record> records = new ArrayList<>();

			
			System.out.println(Game.nick + Game.points);
			try {
				Scanner file = new Scanner(new File(Config.FileHighscore));
				while (file.hasNextLine()) {

					String[] czesci = file.nextLine().split(" ");
					records.add(new Record(czesci[0], Integer.parseInt(czesci[1])));
					System.out.println("rozmiar records:" + records.size());
					
				}
				file.close();
			} catch (Exception e1) {
				System.out.println("brak pliku");
			}
			records.add(new Record(Game.nick, Game.points));
			Komparator komp = new Komparator();
			Collections.sort(records, komp);
			System.out.println(records.get(0).name);
			System.out.println(records.size());
			System.out.println(records.get(records.size() - 1).name);
			records.remove(records.size() - 1);
			System.out.println(records.size());

			try {

				PrintWriter writer = new PrintWriter(Config.FileHighscore);

				for (int i = 0; i < records.size(); i++) {
					writer.println(records.get(i).name + " " + records.get(i).score);

				}

				writer.close();
			} catch (Exception e1) {
				System.out.print("brak pliku");
			}
		
	}
}