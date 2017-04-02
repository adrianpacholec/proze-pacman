import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, KeyListener{

	
	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private Thread thread;
	public String nick;
	public static Player player;
	public static Mapa mapa;

	public Game(String nicktext){
		Dimension dimension = new Dimension(Config.GameWidth, Config.GameHeight);
		setPreferredSize(dimension);	
		addKeyListener(this);
		player = new Player(Config.GameWidth/2,Config.GameHeight/2);
		mapa = new Mapa(Config.FileMap);
		nick = nicktext;
	
	}
	public synchronized void start(){
		if(isRunning) return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){	
		if (!isRunning) return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	public void paint(Graphics g)
    {
    	
    	BufferedImage dbImage = new BufferedImage(640,480,BufferedImage.TYPE_INT_ARGB);
		Graphics dbg = dbImage.getGraphics();
		paintComponent(dbg);
		
		BufferedImage scaled=new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D gg=scaled.createGraphics();
		gg.drawImage(dbImage, 0, 0, getWidth(),getHeight(), null);
		g.drawImage(scaled, 0, 0, this);
    }
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	g.setColor(Color.black);
		g.fillRect(0, 0, Config.GameWidth, Config.GameHeight);
		g.setColor(Color.white);
		g.setFont(new Font(Font.DIALOG,Font.BOLD,19));
		player.render(g);
		mapa.render(g);
		g.drawString("Punkty: 0",15,25);
		g.drawString("Twój nick to: " + nick, 200, 470);
		g.setColor(Color.orange);
		g.fillRect(590, 8, 18, 18);
		g.fillRect(560, 8, 18, 18);
		g.fillRect(530, 8, 18, 18);
		//g.dispose();
    }
				
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) player.right = true;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) player.left = true;
		if(e.getKeyCode()==KeyEvent.VK_UP) player.up = true;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) player.down = true;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) player.right = false;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) player.left = false;
		if(e.getKeyCode()==KeyEvent.VK_UP) player.up = false;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) player.down = false;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
