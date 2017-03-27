import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NickWindow extends JFrame //implements ActionListener
{
	

	private static final long serialVersionUID = 1L;
	private JLabel nick;
	//private JButton back,ok;
	private JTextField field;
	
public NickWindow()
{
	super("Nazwa u¿ytkownika");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocation(40,40);
	setLayout(null);	
	setSize(800, 600);
	
	
	nick = new JLabel("Nazwa u¿ytkownika:");
	//nick.setPreferredSize(new Dimension(800,800));
	nick.setBounds(20,20,150,20);
	
	
	add(nick);
	//nick.addActionListener(this);
	
	JTextField field = new JTextField("");
	field.setBounds(170,20,150,20);
	add(field);
	
	//back = new JButton("")
	
}
	
}
