import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NickWindow extends JFrame implements ActionListener{
	

	private static final long serialVersionUID = 1L;
	private JLabel nick;
	private JButton back;
	private JButton ok;
	private JTextField field;
	
public NickWindow(){
	super("Nazwa użytkownika");
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setLocation(40,40);
	setLayout(new GridLayout(4,1));	
	setSize(400, 200);
	
	
	nick = new JLabel("                                           " + Config.ButtonUserName);
	//nick.setPreferredSize(new Dimension(800,800));
	nick.setBounds(20,20,150,20);
	add(nick);
	
	field = new JTextField("");
	field.setBounds(170,80,50,20);
	
	add(field);
	
	back = new JButton(Config.ButtonBack);
	add(back);
	back.addActionListener(this);
	
	
	ok = new JButton(Config.ButtonOK);
	add(ok);
	ok.addActionListener(this);
	
	
}


public void actionPerformed(ActionEvent e){ 
	

Object source = e.getSource();
if(source == back){

	new MainWindow().setVisible(true);
	dispose();
	
}

else if(source == ok){
	
	Game game = new Game();
	JFrame frame = new JFrame();
	frame.setTitle(Game.TITLE);
	frame.add(game);
	frame.setResizable(false);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	game.start();
	dispose();
}	

}
}
