import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * 
 * Klasa opisująca okno menu programu. 
 * 
 * @author Pawel Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainWindow() {

		super(Config.ApplicationName);
		Dimension dimension = new Dimension(Config.GameWidth, Config.GameHeight);
		setPreferredSize(dimension);
		Menu menu = new Menu(this);
		setTitle(Config.ApplicationName);
		add(menu);
		pack();
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjść?", "Wyjście",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

	}

}
