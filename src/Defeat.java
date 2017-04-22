import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;



/**
 * klasa tworz¹ca okno wyswietlaj¹ce siê po zakoñczeniu rozgrywki, podsumowuj¹ca j¹ i dajaca wybór graczu co chce zrobiæ dalej
 * @author Pawel Kowalik
 * @author Adrian Pacholec
 */
public class Defeat extends JFrame implements ActionListener{
static int points;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton mainmenu,end;
	JLabel nicklabel,punkty;
	/**
	 * Constructor klasy Defeat
	 * @param points
	 * @param nick
	 */
	public Defeat(String nick, int points){
	super(Config.GameOver);
		
	setLayout(new FlowLayout());	
	setSize(500, 300);
	
	nicklabel = new JLabel(Config.ButtonUserName+":"+ nick ,SwingConstants.CENTER);
	add(nicklabel);
	
	punkty = new JLabel("Punkty:"+ points ,SwingConstants.CENTER);
	add(nicklabel);
	
	mainmenu = new JButton("Zacznij od nowa");
	
	end = new JButton("Koniec");
	
	
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mainmenu)
			new MainWindow().setVisible(true);
		dispose();
		
		if(e.getSource() == end)
			dispose();
	}
	
	
	
	
	
	
}
