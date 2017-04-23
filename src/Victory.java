
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;



/**
 * klasa tworz¹ca okno wyswietlaj¹ce siê po zakoñczeniu wygranej, podsumowuj¹ca j¹ i dajaca wybór graczu co chce zrobiæ dalej
 * @author Pawel Kowalik
 * @author Adrian Pacholec
 */
public class Victory extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton mainmenu,end;
	JLabel nicklabel,punktylabel;
	private Font Userfont;
	/**
	 * Constructor klasy Victory
	 * @param points
	 * @param nick
	 */
	public Victory(String nick, int points){
		
	super("Wygrana");
	getContentPane().setBackground(Color.BLUE);
	setLayout(new GridLayout(4,1));	
	setSize(500, 300);
	setVisible(true);
	

	punktylabel = new JLabel("Punkty:"+ points ,SwingConstants.CENTER);
	add(punktylabel);
	punktylabel.setFont(Userfont);
	punktylabel.setForeground(Color.YELLOW);
	
	nicklabel = new JLabel(Config.ButtonUserName+":"+ nick ,SwingConstants.CENTER);
	add(nicklabel);
	nicklabel.setFont(Userfont);
	nicklabel.setForeground(Color.YELLOW);
	
	
	mainmenu = new JButton("Zagraj ponownie");
	add(mainmenu);
	mainmenu.addActionListener(this);
	
	end = new JButton("Koniec");
	add(end);
	end.addActionListener(this);
	
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

