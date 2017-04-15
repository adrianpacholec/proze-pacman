import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentująca gracza, dziedziczy po klasie Rectangle
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Player extends Rectangle {
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
	public void update(){
		if(right && movement(x+Config.PacmanSpeed,y))x+=Config.PacmanSpeed;
		if(left && movement(x-Config.PacmanSpeed, y))x-=Config.PacmanSpeed;
		if(up && movement(x, y-Config.PacmanSpeed))y-=Config.PacmanSpeed;
		if(down && movement(x,y-Config.PacmanSpeed))y+=Config.PacmanSpeed;
		
		
	}
	/**
	 * mrtoda sprawdzająca czy pacman nie wchodzi w ścianę, odpowiadająca za ruch pacmana
	 * @param nextx
	 * @param nexty
	 * @return
	 */
	 
	public boolean movement(int nextx,int nexty){
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
