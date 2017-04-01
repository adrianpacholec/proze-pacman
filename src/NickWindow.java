import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	//setLocation(40,40);
	setLayout(new GridLayout(8,1));	
	setSize(400, 200);
	
	
	nick = new JLabel(Config.ButtonUserName,SwingConstants.CENTER);
	//nick.setPreferredSize(new Dimension(800,800));
	//nick.setBounds(20,20,150,20);
	add(nick);
	
	Userfont = new Font("Courier", Font.BOLD, 15);
	field = new JTextField("");
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
	
	
	
	
}


public void actionPerformed(ActionEvent e){ 
	
	Object source = e.getSource();

if(source  == back){

	new MainWindow().setVisible(true);
	dispose();
	
}

else if(source == ok){
	
	Game game = new Game();
	JFrame frame = new JFrame();
	frame.setTitle(Game.TITLE);
	frame.add(game);
	//frame.setResizable(false);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	game.start();
	dispose();
}	

}
}
