import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InstructionWindow extends JFrame implements ActionListener{

	private JButton back;
	JTextArea notebook;
	JScrollPane scroller;
	
	private static final String FILENAME = "E:\\Workspace\\pacman z gita\\instrukcja.txt";
	public InstructionWindow(){
		super("Instrukcja");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(new GridLayout(2,1));
		setSize(400, 200);
		
		notebook = new JTextArea();
		scroller = new JScrollPane(notebook);
		scroller.setBounds(0, 0, 400, 100);
		
		
			Scanner scanner = new Scanner(FILENAME);
			//while(scanner.hasNextLine())
				//notebook.append(scanner.nextLine() + "|n");
			
		//back = new JButton(Config.ButtonBack);
		//add(back);
		//back.addActionListener(this);
			
		
		

		

			try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					notebook.append(scanner.nextLine() + "|n");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
			
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == back){
			
			new MainWindow().setVisible(true);
			dispose();
			
		}
		
	}
	
	
	
}	
	
	
	
	
	

