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
 * Klasa InstructionWindow, kt�ra zajmuje si� wy�wietlaniem okna z instrukscj� do gry, dziedzicz�ca po JFrame
 * 
 * @author Pawel Kowalik
 * @author Adrian PAcholec
 * @version 1.0
 */


public class InstructionWindow extends JFrame{

	/**
	 * nieobowi�zkowy identyfikator klasy
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ScrollPane kt�ry umo�liwia przewijanie okna z instrukcj�, je�li jest ona za d�uga by zmie�ci� si� na ekranie
	 */
	private JScrollPane scrollPane;
	
	/**
	 * plik z kt�rego b�dziemy odczytywa� instrukcj�
	 */
	private static final String FILENAME = "instrukcja.txt";
	
	
	/**
	 * pole tekstowe na kt�rym instrukcja b�dzie wy�wietlona
	 */
	private final JTextArea textArea;
	
	
	
	
	@SuppressWarnings("unused")
	public InstructionWindow() throws FileNotFoundException{
		
		/**
		 * utowrzenie okna i nadanie mu rozmiar�w oraz po�o�enia
		 */
		super(Config.FileInstruction);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		/**
		 * ustawienie by po naci�ni�ciu przycisku zamykaj�cego w prawym g�rnym oknie nic si� z nim nie dzia�o
		 */
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		/**
		 * metoda pytaj�ca u�ytkownika czy jest pewny, �e chce sko�czy� czytanie insrukcji i zgodnie z jego wol�, albo zamyka oknoz 
		 * instrukcj� i powraca to g��wnego okna albo pozostaj�ca w oknie z instrukcj�
		 */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wr�ci�?","", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(x == JOptionPane.YES_OPTION){
					e.getWindow().dispose();
					new MainWindow();
				}
			}
		});
		
		/**
		 * metody tworz�ce pole tekstowe wraz z mo�liwo�ci� skrolowania w pionie oraz mo�liwo�ci� skalowania
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
	
	
	
	
	

