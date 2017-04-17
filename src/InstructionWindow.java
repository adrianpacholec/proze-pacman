import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Klasa InstructionWindow, która zajmuje się wyświetlaniem okna z instrukscją do gry, dziedzicząca po JFrame
 * 
 * @author Pawel Kowalik
 * @author Adrian PAcholec
 * @version 1.0
 */


public class InstructionWindow extends JFrame{

	/**
	 * nieobowiązkowy identyfikator klasy
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ScrollPane który umożliwia przewijanie okna z instrukcją, jeśli jest ona za długa by zmieścić się na ekranie
	 */
	private JScrollPane scrollPane;
	
	/**
	 * plik z którego będziemy odczytywać instrukcję
	 */
	private static final String FILENAME = "instrukcja.txt";
	
	
	/**
	 * pole tekstowe na którym instrukcja będzie wyświetlona
	 */
	private final JTextArea textArea;
	
	
	
	
	@SuppressWarnings("unused")
	public InstructionWindow() throws FileNotFoundException{
		
		/**
		 * utowrzenie okna i nadanie mu rozmiarów oraz położenia
		 */
		super(Config.FileInstruction);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		/**
		 * ustawienie by po naciśnięciu przycisku zamykającego w prawym górnym oknie nic się z nim nie działo
		 */
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		/**
		 * metoda pytająca użytkownika czy jest pewny, że chce skończyć czytanie insrukcji i zgodnie z jego wolą, albo zamyka oknoz 
		 * instrukcją i powraca to głównego okna albo pozostająca w oknie z instrukcją
		 */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wrócić?","", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(x == JOptionPane.YES_OPTION){
					e.getWindow().dispose();
					new MainWindow();
				}
			}
		});
		
		/**
		 * metody tworzące pole tekstowe wraz z możliwością skrolowania w pionie oraz możliwością skalowania
		 */
		@SuppressWarnings("resource")
		Scanner user_Input = new Scanner(System.in);
		@SuppressWarnings("resource")
		String all = new Scanner(new File(FILENAME)).useDelimiter("\\A").next();
		textArea = new JTextArea(10,20);
		scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setText(all);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		add(scrollPane);
		

	}
	
	
	
}	
	
	
	
	
	

