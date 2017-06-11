/*	import java.io.File;
	import java.io.FileNotFoundException;
	import java.util.Scanner;
	import javax.swing.JFrame;
	import javax.swing.JOptionPane;

	/**
	 * Klasa ScoreBox dziedziczaca po JFrame
	 * 
	 * umozliwia wyswietlanie listy najlepsych wynikow zdobytych przez graczy w kilku rozgrywkach
	 * 
	 * @author Pawe³ Kowalik
	 * @author Adrian Pacholec
	 */
/*	public class Scores extends JFrame{
		
		/**
		 * zmienna typu Scaner pozwalaj¹ca na odczytywanie z pliku 
		 */
/*		public static Scanner file;
		
		/**
		 * statyczna tablica typu String, z ktorej odczytujemy najlepsze wyniki
		 */
/*		private static String[] wyniki = new String[10]; 
		
		
		/**
		 * metoda pozwalaj¹ca na wyswietlanie najlepszych wynikow
		 * wyniki sa pobierane z pliku tekstowego
		 */
/*		public static void begin() {
			JFrame frame = new JFrame(Config.ButtonHighscores);
			try {
				file = new Scanner(new File(Config.FileHighscore));
				int i=0;
				while (file.hasNextLine()) {

					wyniki[i] = file.nextLine();
					i++;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
			    
			JOptionPane.showMessageDialog(frame, wyniki);

		}
	}
	*/
	
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


	public class Scores extends JFrame{

		/**
		 * nieobowi¹zkowy identyfikator klasy
		 */
		private static final long serialVersionUID = 1L;
		public static Scanner file;
		/**
		 * ScrollPane który umo¿liwia przewijanie okna z instrukcj¹, jeœli jest ona za d³uga by zmieœciæ siê na ekranie
		 */
		private JScrollPane scrollPane;
		private static String[] wyniki = new String[10]; 
		
		/**
		 * plik z którego bêdziemy odczytywaæ instrukcjê
		 */
				
		/**
		 * pole tekstowe na którym instrukcja bêdzie wyœwietlona
		 */
		private final JTextArea textArea;
		
		
		
		
		@SuppressWarnings("unused")
		public Scores() throws FileNotFoundException{
			
			/**
			 * utowrzenie okna i nadanie mu rozmiarów oraz po³o¿enia
			 */
			super(Config.ButtonHighscores);
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
			
			

			textArea = new JTextArea(10,20);
			scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			//textArea.setText(wyniki);
			file = new Scanner(new File(Config.FileHighscore));
			int i=0;
			while (file.hasNextLine()) {

				//wyniki[i] = file.nextLine();
				i++;
				textArea.append(file.nextLine() + "\n");
			}
			textArea.setLineWrap(true);
			textArea.setEditable(false);
			textArea.setWrapStyleWord(true);
			add(scrollPane);
			
	
		}
			
		
		
	}	
		
		
		
		
		



