import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(new GridLayout(4,1));
		
		
		new_game = new JButton("Nowa gra");
		add(new_game);
		new_game.addActionListener(this);
		
		highscore = new JButton("Najlepsze wyniki");
		add(highscore);
		highscore.addActionListener(this);
		
		instruction = new JButton("Instrukcja");
		add(instruction);
		instruction.addActionListener(this);
		
		exit = new JButton("Koniec");
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
		setSize(800, 600);
		setVisible(true);
		
		
	
		
}
	/**
	 * metoda dająca przyciskom reakcje na wolę użytkownika
	 */
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		
		if(source == new_game){
			
			new NickWindow().setVisible(true);
			dispose();
	    }
		
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
		
		else if(source  == instruction){
			try {
				new InstructionWindow().setVisible(true);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			//setVisible(false);
		}
		else if(source  == highscore){
			JOptionPane.showMessageDialog(null,"Ta opcja jeszcze nie jest dostępna");
			
		}
	}
	
	
}
