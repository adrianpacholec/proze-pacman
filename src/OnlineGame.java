import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class OnlineGame extends JPanel implements ActionListener {

	/**
	 * nieobowi�zowy identyfikator klasy
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * przyciski do poruszania si� po menu
	 */
	private JButton back, ok, connect;

	/**
	 * pole tekstowe do wpisania nicku
	 */
	private JTextField ip_field, port_field;
	private String ip_address;
	JLabel port;

	public JFrame frame;

	/**
	 * zmienna zapisuj�ca tekst kt�ry u�ytkownik wpisa� do pola tekstowego
	 */
	public String nicktext;

	/**
	 * zmienna w zaleznosci od wyboru pozioomu gracza, wczytujaca odpwoeidnia
	 * predkosc duchow
	 */
	public int spdlevel;
	/**
	 * konstruktor klasy NickWindow
	 */
	public int points;
	public int nr_portu;
	int wybor;
	Socket s = null;
	// private static String[] plansze = {"Raz","Dwa","Trzy"};
	private JList<String> list;

	public OnlineGame(JFrame frame) {

		setLayout(new GridLayout(6, 1));
		this.frame = frame;
		JPanel fields = new JPanel();

		fields.setLayout(new GridLayout(2, 2));
		fields.add(new JLabel("Adres IP", SwingConstants.CENTER));
		fields.add(new JLabel("Port", SwingConstants.CENTER));
		ip_field = new JTextField();
		ip_field.setFont(new Font("Courier", Font.BOLD, 15));
		ip_field.setText("192.168.0.59");
		ip_field.setHorizontalAlignment(JTextField.CENTER);
		;
		ip_field.setMinimumSize(getSize());
		fields.add(ip_field);

		port_field = new JTextField();
		port_field.setFont(new Font("Courier", Font.BOLD, 15));
		port_field.setText("8981");
		port_field.setHorizontalAlignment(JTextField.CENTER);
		;
		fields.add(port_field);

		add(fields);
		connect = new JButton("Połącz");
		add(connect);
		connect.addActionListener(this);
		ip_field.requestFocus();
		frame.pack();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == connect) {
			try {
				nr_portu = Integer.parseInt(port_field.getText());
				ip_address = ip_field.getText();
				s = new Socket(ip_address, nr_portu);
				add(new JLabel("Połączono z: " + ip_address + ":" + port_field.getText(), SwingConstants.CENTER));
				InputStream inStream = s.getInputStream();
				OutputStream outStream = s.getOutputStream();
				PrintWriter out = new PrintWriter(outStream, true);
				Scanner in = new Scanner(inStream);
				// żadanie gier
				out.println("GET GAMES");
				// odbierz gry
				updateStatus(in.nextLine().split("%"));
			} catch (IOException e1) {
				System.out.println("Nie udało się nawiązać połączenia.");
			}

		} else if (source == back) {
			Menu menu = new Menu(frame);
			frame.add(menu);
			frame.remove(this);
			frame.setVisible(true);
		} else if (source == ok) {
			try {
				InputStream inStream = s.getInputStream();
				OutputStream outStream = s.getOutputStream();
				PrintWriter out = new PrintWriter(outStream, true);
				Scanner in = new Scanner(inStream);
				out.println("CHOOSE GAME");
				wybor = list.getSelectedIndex() + 1;
				out.println(wybor);

				// żądanie mapy
				out.println("GET MAP");
				System.out.println("klient GET MAP");

				// odbierz mapę
				saveMap("online_map" + wybor + ".txt", in.nextLine().split("%"));

				// żądanie wynikow
				out.println("GET HSCORES");
				System.out.println("klient GET HSCORES");
				saveMap("online_high_scores" + wybor + ".txt", in.nextLine().split("%"));
				// odpal gre
				OnlineNickWindow nickwindow = new OnlineNickWindow(frame, wybor, ip_address, nr_portu);
				this.setVisible(false);
				frame.add(nickwindow);
				frame.setVisible(true);

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}

	public void updateStatus(String[] words) {
		add(new JLabel("Pobrane gry:", SwingConstants.CENTER));
		list = new JList<String>(words);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(list));
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
			}
		});
		JPanel opcje = new JPanel();
		ok = new JButton(Config.ButtonOK);
		opcje.add(ok);
		ok.addActionListener(this);
		back = new JButton(Config.ButtonBack);
		opcje.add(back);
		back.addActionListener(this);
		add(opcje);
		revalidate();
		repaint();
		frame.pack();
	}

	public void saveMap(String fileName, String[] data) {
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			for (String str : data) {
				writer.println(str);
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("error");
		}

	}

}