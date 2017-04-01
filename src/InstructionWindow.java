
import java.io.File;
import java.io.FileNotFoundException;


import java.util.Scanner;


import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InstructionWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private static final String FILENAME = "instrukcja.txt";
	private final JTextArea textArea;
	
	@SuppressWarnings("unused")
	public InstructionWindow() throws FileNotFoundException{
		super("Instrukcja");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//setLayout(null);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		@SuppressWarnings("resource")
		Scanner user_Input = new Scanner(System.in);
		@SuppressWarnings("resource")
		String all = new Scanner(new File(FILENAME)).useDelimiter("\\A").next();
		textArea = new JTextArea(10,20);
		scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setText(all);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		add(scrollPane);
		
		
		//new MainWindow().setVisible(true);
	
		
	}
	
	
	
}	
	
	
	
	
	

