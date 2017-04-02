import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * Klasa opisująca i tworząca menu główne gry, w którym są przyciski do rozpoczęcia nowej gry, wyswietlenia instrukcji i najlepszych wyników oraz możliwości zakończenia gry
 * 
* @author Pawel Kowalik
* @author Adrian Pacholec
*/

public class MainWindow extends JFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	/**
	 * nieobowiązkowy identyfikator klasy
	 */
	
	private JButton  new_game, highscore, instruction, exit;
	/**
	 * przyciski z menu głownego
	 */
	
	JMenuItem nimbus;
	/**
	 * zmienna wykorzystywana do zmiany stylu menu z defaultowego na styl nimbus
	 */
	
	
	/**
	 * konstruktor menu glównego
	 */
	public MainWindow(){
		
		super(Config.ApplicationName);
		setSize(800, 600);
		setVisible(true);
		/**
		 * metoda pytająca użytkownika czy jest pewny, że chce zakończyć grę i zgodnie z jego wolą, albo zamyka okno z men
		 *
		 */
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjść?","", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(x == JOptionPane.YES_OPTION){
					e.getWindow().dispose();
				}
			}
		});
		
		/**
		 * ustwienie layoutu gridlayout, 4 przyciski w pionie
		 */
		setLayout(new GridLayout(4,1));
		
		
		new_game = new JButton(Config.ButtonNewGame);
		add(new_game);
		new_game.addActionListener(this);
		
		highscore = new JButton(Config.ButtonHighscores);
		add(highscore);
		highscore.addActionListener(this);
		
		instruction = new JButton(Config.ButtonInstruction);
		add(instruction);
		instruction.addActionListener(this);
		
		exit = new JButton(Config.ButtonEndGame);
		add(exit);
		exit.addActionListener(this);
		
		
		/**
		 * metoda służąca zmianie wyglądu menu 
		 */
	    nimbus = new JMenuItem("Metal");
	    try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			
			e.printStackTrace();
		}
	    SwingUtilities.updateComponentTreeUI(this);
	    
	    
	    
		pack();
		
		
		
	
		
}
	/**
	 * metoda dająca przyciskom reakcje na wolę użytkownika
	 */
	public void actionPerformed(ActionEvent e) 
	{
		/**
		 * stworzenie obiektu który będzie reagował na nasze kliknięcia
		 */
		Object source = e.getSource();
		
		/**
		 * jesli zrodłem jest przycisk nowej gry to tworzone jest okno z wyborem nicku, a to okno porzucane
		 */
		if(source == new_game){
			
			new NickWindow().setVisible(true);
			dispose();
	    }
		
		/**
		 * jesli zrodłem jest przycisk końca to użytkownuk pytany jest czy jest tego pewien i albo zamykane jest menu główne albo nic się z nim nie dzieje 
		 */
		else if(source  == exit){
			int answer = JOptionPane.showConfirmDialog(null, "Czy jesteś pewien ?","Pytanie",JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION){
				JOptionPane.showMessageDialog(null,"Do Zobaczenia");
				dispose();
				}
			else if (answer==JOptionPane.NO_OPTION)
				JOptionPane.showMessageDialog(null,"Dobra decyzja");
			else if (answer == JOptionPane.CLOSED_OPTION)
				JOptionPane.showMessageDialog(null, "Tak nie robimy","OstrzeĹĽenie",JOptionPane.WARNING_MESSAGE);	
           }
		/**
		 * jesli zródlem jest przycisk instrukcji do gry, to jest ona wyswietlana
		 */
		else if(source  == instruction){
			try {
				new InstructionWindow().setVisible(true);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			setVisible(false);
		}
		
		/**
		 * jesli zrodlem jest przycisk najlepszych wynikow to sa one wyswietlane w oddzielnym oknie
		 */
		else if(source  == highscore){
			JOptionPane.showMessageDialog(null,"Ta opcja jeszcze nie jest dostępna");
			
		}
	}
	
	
}
