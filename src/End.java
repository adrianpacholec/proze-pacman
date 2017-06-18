import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Pawel Kowalik
 * @author Adrian Pacholec
 */
public class End extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton mainmenu, end;
	JLabel nicklabel, punktylabel, koniec;
	JFrame frame;

	/**
	 * Constructor klasy Victory
	 * 
	 * @param points
	 * @param nick
	 */
	public End(JFrame frame, String nick, int points) {
		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setVisible(true);
		koniec = new JLabel("Koniec gry" + points, SwingConstants.CENTER);
		koniec.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		punktylabel = new JLabel("Punkty: " + points, SwingConstants.CENTER);
		add(punktylabel);
		punktylabel.setFont(new Font(Font.DIALOG, Font.BOLD, 19));

		nicklabel = new JLabel(Config.ButtonUserName + ": " + nick, SwingConstants.CENTER);
		add(nicklabel);
		nicklabel.setFont(new Font(Font.DIALOG, Font.BOLD, 19));
		JPanel opcje = new JPanel();

		mainmenu = new JButton("Menu główne");
		opcje.add(mainmenu);
		mainmenu.addActionListener(this);

		end = new JButton("Koniec");
		opcje.add(end);
		add(opcje);
		end.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		highscore();
		if (e.getSource() == mainmenu) {
			Menu menu = new Menu(frame);
			frame.add(menu);
			frame.remove(this);
			frame.setVisible(true);
		}

		if (e.getSource() == end) {
			frame.dispose();
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