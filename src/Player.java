import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;



/**
 * Klasa reprezentująca gracza, dziedziczy po klasie Rectangle
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Player extends Rectangle {
	/**
	 * punkty gromadzone przez gracza po zebraniu punktów
	 */
	public static int points = 0;
	public static int life = 3;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 * zmienne służące do obsługi ruchu pacmana
 */
	public boolean right, left, down, up;
	
	/**
	 * Konstruktor obiektu gracza
	 * 
	 * @param x
	 *            Pozycja x licząc od lewego górenego rogu panelu
	 * @param y
	 *            Pozycja y licząc od lewego górenego rogu panelu
	 * 
	 */
	public Player(int x, int y) {
		setBounds(x, y, 32, 32);
	}

	/**
	 * Metoda rysująca reprezentację gracza
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
		
		
	}
	
	
	
	/**
	 * metoda 
	 */
	public void update(String nick){
		if(right && canMove(x+Config.PacmanSpeed,y))x+=Config.PacmanSpeed;
		if(left && canMove(x-Config.PacmanSpeed, y))x-=Config.PacmanSpeed;
		if(up && canMove(x, y-Config.PacmanSpeed))y-=Config.PacmanSpeed;
		if(down && canMove(x,y+Config.PacmanSpeed))y+=Config.PacmanSpeed;
		
		
		Mapa mapa = Game.mapa;
		for(int i=0;i<mapa.punkty.size();i++){
			if(this.intersects(mapa.punkty.get(i))){
				mapa.punkty.remove(i);
				points+=5;
				System.out.println(points);
				break;
			}
			
		}
		if (mapa.punkty.size() == 0) {
			Game.player = new Player(0,0);
			Game.mapa = new Mapa(Config.FileMap);
			return;
		}
			
		for(int k=0;k<mapa.enemies.size();k++){	
			if(this.intersects(mapa.enemies.get(k))){
				life--;
				
				//JOptionPane.showMessageDialog(null, "Straciłeś życie");
				//JOptionPane.getRootFrame().dispose();;
				//GUI.render(g, nick);
				if(life == 0){
					//JOptionPane.showMessageDialog(null, "Przegrałeś");
					//dispose();
					//nick2 = nick;
					Defeat defeat  = new Defeat(nick,points);
				}
			}
		}
			
	}
	/**
	 * mrtoda sprawdzająca czy pacman nie wchodzi w ścianę, odpowiadająca za ruch pacmana
	 * @param nextx
	 * @param nexty
	 * @return
	 */
	 
	public boolean canMove(int nextx,int nexty){
		Rectangle bounds = new Rectangle (nextx,nexty,32,32);
		Mapa mapa = Game.mapa;
		
		
		for (int xxx=0; xxx<Mapa.MapSize; xxx++)
            for(int yyy=0;yyy<Mapa.MapSize;yyy++){
				if(mapa.tiles[xxx][yyy] != null){
					if(bounds.intersects(mapa.tiles[xxx][yyy])){
						return false;
						
					}
				}
		}
		
		
		return true;
				
				
	}
	
}
