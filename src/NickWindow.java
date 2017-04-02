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
	

	private static final long serialVersionUID = 1L;
	private JLabel nick, picklevel;
	private JButton back;
	private JButton ok;
	private JTextField field;
	private ButtonGroup levels;
	private JRadioButton easy,medium,hard;
	private Font Userfont;
	
public NickWindow(){
	super("Nazwa użytkownika");
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	
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
	//setLocation(40,40);
	setLayout(new GridLayout(8,1));	
	setSize(400, 200);
	
	
	nick = new JLabel(Config.ButtonUserName,SwingConstants.CENTER);
	//nick.setPreferredSize(new Dimension(800,800));
	//nick.setBounds(20,20,150,20);
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
	
	easy = new JRadioButton("Łatwy",true);
	levels.add(easy);
	add(easy);
	easy.setHorizontalAlignment(AbstractButton.CENTER);
	easy.addActionListener(this);
	
	medium = new JRadioButton("Średni",false);
	levels.add(medium);
	add(medium);
	medium.setHorizontalAlignment(AbstractButton.CENTER);
	medium.addActionListener(this);
	
	hard = new JRadioButton("Trudny",false);
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
	
	
	String text = field.getText();
	
	if(text.trim().equals("")){
		JOptionPane.showMessageDialog(null, " Musisz wprowadzić nazwę użytkownika ","Ostrzeżenie",JOptionPane.WARNING_MESSAGE);
		field.setText("");
		field.requestFocus();
		
	}
	else {
	if (text.length() > 3 && !text.contains(" ") && !text.contains(",") && !text.contains(".") && !text.contains("!") && !text.contains("@") && !text.contains("#") && !text.contains("$") && !text.contains("*") && !text.contains("/") && !text.contains("+") && !text.contains("-") && !text.contains("]")) {
	    //make sure that its length is not over 1, and that it has no spaces and no commas 
	    
	    JOptionPane.showMessageDialog(null, " Twój nick: " + text ,"Ahoj przygodo!",JOptionPane.INFORMATION_MESSAGE);
	    
	    
	    Game game = new Game();
		JFrame frame = new JFrame();
		frame.setTitle(Game.TITLE);
		frame.add(game);
		//frame.setResizable(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
		dispose();
	     
	} else {
	    //if a space or comma was found no matter how big the text it will execute the else.. 
		JOptionPane.showMessageDialog(null, "Wprowadź nazwę użytkownika jeszcze raz","Ostrzeżenie",JOptionPane.WARNING_MESSAGE);
	    //field.setText("");
		field.requestFocus();
	    field.getText();
	    field.setText("");
	}
	}
	
	
	
	
	
	
}	

}
}
