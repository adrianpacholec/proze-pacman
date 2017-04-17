import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class NickWindow extends JFrame implements ActionListener{
	
/**
 * nieobowiązowy identyfikator klasy
 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * labele informujące użytkownika co am zrobić, wybrać poziom i wpsiać nick
	 */
	private JLabel nick, picklevel;
	
	
	/**
	 * przyciski do poruszania się po menu
	 */
	private JButton back,ok;
	
	/**
	 * pole tekstowe do wpisania nicku
	 */
	private JTextField field;
	
	/**
	 * grupa radiobuttonów
	 */
	private ButtonGroup levels;
	
	/**
	 * radiobuttony które pozwalają wybrać poziom trudności przez użytownika
	 */
	private JRadioButton easy,medium,hard;
	
	
	/**
	 * zmienna do określenia włąsnej czcionki
	 */
	private Font Userfont;
	
	
	/**
	 * zmienna zapisująca tekst który użytkownik wpisał do pola tekstowego
	 */
	public String nicktext;
	
	
	
	/**
	 * konstruktor klasy NickWindow
	 */
public NickWindow(){
	super(Config.UserName);
	setLayout(new GridLayout(8,1));	
	setSize(500, 300);
	
	/**
	 * metoda pytająca użytkownika czy jest pewny, że chce skończyć wpisywanie nicku i zgodnie z jego wolą, albo zamyka to okno
	 * i wraca do poprzedniego, albo jeśli użytkownik zmienił zdanie pozostaje w tej cześci menu 
	 * 
	 */
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e){
			int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wrócić?","", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(x == JOptionPane.YES_OPTION){
				e.getWindow().dispose();
				new MainWindow();
			}
		}
	});
	
	
	
	nick = new JLabel(Config.ButtonUserName,SwingConstants.CENTER);
	add(nick);
	
	Userfont = new Font("Courier", Font.BOLD, 15);
	field = new JTextField();
	field.setBounds(170,80,50,20);
	field.setFont(Userfont);
	add(field);
	
	
	
	back = new JButton(Config.ButtonBack);
	add(back);
	back.addActionListener(this);
	
	
	ok = new JButton(Config.ButtonOK);
	add(ok);
	ok.addActionListener(this);
	
	picklevel = new JLabel("Wybierz poziom: ",SwingConstants.CENTER);
	add(picklevel);
	
	levels = new ButtonGroup();
	
	easy = new JRadioButton(Config.GameLevel_1,true);
	levels.add(easy);
	add(easy);
	easy.setHorizontalAlignment(AbstractButton.CENTER);
	easy.addActionListener(this);
	
	medium = new JRadioButton(Config.GameLevel_2,false);
	levels.add(medium);
	add(medium);
	medium.setHorizontalAlignment(AbstractButton.CENTER);
	medium.addActionListener(this);
	
	hard = new JRadioButton(Config.GameLevel_3,false);
	levels.add(hard);
	add(hard);
	hard.setHorizontalAlignment(AbstractButton.CENTER);
	hard.addActionListener(this);
	
	field.requestFocus();

}


public void actionPerformed(ActionEvent e){ 
	
	Object source = e.getSource();

if(source  == back){

	new MainWindow().setVisible(true);
	dispose();
	
}


else if(source == ok){
	
	/**
	 * pobieranie nazwy użytkownika od użytkownika 
	 */
	nicktext = field.getText();
	/**
	 * seria sprawdzań mająca na celu wpisanie poprawnego nicku
	 */
	if(nicktext.trim().equals("")){
		JOptionPane.showMessageDialog(null, " Musisz wprowadzić nazwę użytkownika ","Ostrzeżenie",JOptionPane.WARNING_MESSAGE);
		field.setText("");
		/**
		 * ustawienie myszki na pole tekstowe
		 */
		field.requestFocus();
		
	}
	else {
	if (nicktext.length() > 1 && !nicktext.contains(" ") && !nicktext.contains(",") && !nicktext.contains(".") && !nicktext.contains("!") && !nicktext.contains("@") && !nicktext.contains("#") && !nicktext.contains("$") && !nicktext.contains("*") && !nicktext.contains("/") && !nicktext.contains("+") && !nicktext.contains("-") && !nicktext.contains("]")) {
	    //make sure that its length is not over 1, and that it has no spaces and no commas 
	    
	    JOptionPane.showMessageDialog(null, " Twój nick: " + nicktext ,"Ahoj przygodo!",JOptionPane.INFORMATION_MESSAGE);
	    
	    /**
	     * tworzenie okna z grą
	     */
	    Game game = new Game(nicktext);
		JFrame frame = new JFrame();
		frame.setTitle(Config.ApplicationName);
		frame.add(game);
		//frame.setResizable(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.run();
		dispose();
	     
	} else {
	    //if a space or comma was found no matter how big the text it will execute the else.. 
		JOptionPane.showMessageDialog(null, "Wprowadź nazwę użytkownika jeszcze raz","Ostrzeżenie",JOptionPane.WARNING_MESSAGE);
	    //field.setText("");
		
		/**
		 * ustawienie myszki na pole tekstowe
		 */
		field.requestFocus();
		/**
		 * ponowne pobranie tekstu od użytkownika
		 */
	    field.getText();
	    /**
	     * wyczyszczenie pola tekstwoego
	     */
	    field.setText("");
	}
	}
	
	
	
	
	
	
}	

}
}
