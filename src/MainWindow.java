import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/*
* @author Pawel Kowalik
* @author Adrian Pacholec
*/

public class MainWindow extends JFrame implements ActionListener
{
	
	
	private static final long serialVersionUID = 1L;
	private JButton  new_game, highscore, instruction, exit;
	public MainWindow(){
		super(Config.ApplicationName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4,1));
		
		
		
		new_game = new JButton("Nowa gra");
		add(new_game);
		new_game.addActionListener(this);
		
		highscore = new JButton("Najlepsze wyniki");
		add(highscore);
		highscore.addActionListener(this);
		
		instruction = new JButton("Instrukcja");
		add(instruction);
		instruction.addActionListener(this);
		
		exit = new JButton("Koniec");
		add(exit);
		exit.addActionListener(this);
		
		
		pack();
		setSize(800, 600);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		
		Object source = e.getSource();
		if(source == new_game){
			
			new NickWindow().setVisible(true);
			dispose();
		}
		else if(source == exit){
			
			dispose();
			
		}
		else if(source == highscore){
			
			
		}
	}
	
	
	
	
	
}
