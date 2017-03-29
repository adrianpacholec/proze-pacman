import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
* @author Pawel Kowalik
* @author Adrian Pacholec
*/

public class MainWindow extends JFrame implements ActionListener
{
	
	
	private static final long serialVersionUID = 1L;
	private JButton  new_game, highscore, instruction, exit;
	JMenuItem nimbus;
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
		
	    nimbus = new JMenuItem("Metal");
	    try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			
			e.printStackTrace();
		}
	    SwingUtilities.updateComponentTreeUI(this);
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
		
		else if(source  == exit){
			int answer = JOptionPane.showConfirmDialog(null, "Czy jesteś pewien?","Pytanie",JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION){
				JOptionPane.showMessageDialog(null,"Do Zobaczenia");
				dispose();
				}
			else if (answer==JOptionPane.NO_OPTION)
				JOptionPane.showMessageDialog(null,"Dobra decyzja");
			else if (answer == JOptionPane.CLOSED_OPTION)
				JOptionPane.showMessageDialog(null, "Tak nie robimy","Ostrzeżenie",JOptionPane.WARNING_MESSAGE);	
           }
		
		else if(source  == instruction){
			new InstructionWindow().setVisible(true);
			dispose();
		}
		else if(source  == highscore){
			JOptionPane.showMessageDialog(null,"Ta opcja jeszcze nie jest dostępna");
			
		}
	}
	
	
}
