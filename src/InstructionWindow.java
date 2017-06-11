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
 * Klasa InstructionWindow, która zajmuje siê wyœwietlaniem okna z instrukscj¹ do gry, dziedzicz¹ca po JFrame
 * 
 * @author Pawel Kowalik
 * @author Adrian PAcholec
 * @version 1.0
 */


public class InstructionWindow extends JFrame{

	/**
	 * nieobowi¹zkowy identyfikator klasy
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ScrollPane który umo¿liwia przewijanie okna z instrukcj¹, jeœli jest ona za d³uga by zmieœciæ siê na ekranie
	 */
	private JScrollPane scrollPane;
	
	/**
	 * plik z którego bêdziemy odczytywaæ instrukcjê
	 */
	private static final String FILENAME = "instrukcja.txt";
	
	
	/**
	 * pole tekstowe na którym instrukcja bêdzie wyœwietlona
	 */
	private final JTextArea textArea;
	
	
	
	
	@SuppressWarnings("unused")
	public InstructionWindow() throws FileNotFoundException{
		
		/**
		 * utowrzenie okna i nadanie mu rozmiarów oraz po³o¿enia
		 */
		super(Config.FileInstruction);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		/**
		 * ustawienie by po naciœniêciu przycisku zamykaj¹cego w prawym górnym oknie nic siê z nim nie dzia³o
		 */
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		/**
		 * metoda pytaj¹ca u¿ytkownika czy jest pewny, ¿e chce skoñczyæ czytanie insrukcji i zgodnie z jego wol¹, albo zamyka oknoz 
		 * instrukcj¹ i powraca to g³ównego okna albo pozostaj¹ca w oknie z instrukcj¹
		 */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wróciæ?","", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(x == JOptionPane.YES_OPTION){
					e.getWindow().dispose();
					new MainWindow();
				}
			}
		});
		
		/**
		 * metody tworz¹ce pole tekstowe wraz z mo¿liwoœci¹ skrolowania w pionie oraz mo¿liwoœci¹ skalowania
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
	
	
	
	
	

