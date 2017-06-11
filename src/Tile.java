import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentuj�ca �cian�, dziedziczy po klasie Rectangle
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Tile extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int pos[] = new int[2];
	int map[] = {2,0};
	/**
	 * Konstruktor obiektu �ciany
	 * 
	 * @param x
	 *            Pozycja x licz�c od lewego g�renego rogu panelu
	 * @param y
	 *            Pozycja y licz�c od lewego g�renego rogu panelu
	 * 
	 */
	public Tile(int x, int y, int[]pozycja) {
		setBounds(x, y, 32, 32);
		okresl_pozycje(pozycja);
	}

	/**
	 * Metoda rysuj�ca reprezentacj� �ciany
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	private void okresl_pozycje(int[]pozycja){
		if (pozycja[0]=='1'&&pozycja[1]=='1'&&pozycja[2]=='1'&&pozycja[3]=='1'&&pozycja[4]=='1'&&pozycja[5]=='1'&&pozycja[6]=='1'&&pozycja[7]=='1') {pos[0]=3;pos[1]=1;}
		else if (pozycja[1]=='1' && pozycja[6]=='1' && pozycja[3]=='1' && pozycja[4]=='1' && pozycja[7]!='1') {pos[0]=0;pos[1]=1;}
		else if (pozycja[1]=='1' && pozycja[6]=='1' && pozycja[3]=='1' && pozycja[4]=='1' && pozycja[5]!='1') {pos[0]=0;pos[1]=2;}
		else if (pozycja[1]=='1' && pozycja[6]=='1' && pozycja[3]=='1' && pozycja[4]=='1' && pozycja[2]!='1') {pos[0]=1;pos[1]=1;}
		else if (pozycja[1]=='1' && pozycja[6]=='1' && pozycja[3]=='1' && pozycja[4]=='1' && pozycja[0]!='1') {pos[0]=1;pos[1]=2;}
		
		else if (pozycja[1]!='1' && pozycja[6]=='1' && pozycja[3]!='1' && pozycja[4]=='1') {pos[0]=2;pos[1]=0;}
		else if (pozycja[1]!='1' && pozycja[6]=='1' && pozycja[3]=='1' && pozycja[4]=='1') {pos[0]=2;pos[1]=1;}
		else if (pozycja[1]!='1' && pozycja[6]=='1' && pozycja[3]=='1' && pozycja[4]!='1') {pos[0]=2;pos[1]=2;}
		
		else if (pozycja[1]=='1' && pozycja[6]=='1' && pozycja[3]!='1' && pozycja[4]=='1') {pos[0]=3;pos[1]=0;}
		else if (pozycja[1]=='1' && pozycja[6]=='1' && pozycja[3]=='1' && pozycja[4]!='1') {pos[0]=3;pos[1]=2;}
		
		else if (pozycja[1]=='1' && pozycja[6]!='1' && pozycja[3]!='1' && pozycja[4]=='1') {pos[0]=4;pos[1]=0;}
		else if (pozycja[1]=='1' && pozycja[6]!='1' && pozycja[3]=='1' && pozycja[4]=='1') {pos[0]=4;pos[1]=1;}
		else if (pozycja[1]=='1' && pozycja[6]!='1' && pozycja[3]=='1' && pozycja[4]!='1') {pos[0]=4;pos[1]=2;}
	
		else {pos[0]=0;pos[1]=0;}		
		
		}
	
	public void render(Graphics g) {
		Spritesheet sheet = Game.spritesheet;
		g.drawImage(sheet.getSprite(pos[1]+map[1]*3,pos[0]+map[0]*6),x,y,null);
	}
}