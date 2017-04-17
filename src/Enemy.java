import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;



/**
 * Klasa reprezentująca przeciwnika, dziedziczy po klasie Rectangle
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Enemy extends Rectangle {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private int random = 0,smart=1,find_path = 2;
	
	/**
	 * zmienna pomocnicza 
	 */
	private int state=smart;
	
	/**
	 * zmienne wyliczeniowe określające ruch przeciwnika
	 */
	private int right=0, left=1, up=2, down = 3;
	/**
	 * zmienna okreslajaca kierunek ruchu przeciwnika
	 */
	private int dir= -1;
	
	public Random random2;
	
	/**
	 * zmienna odliczajaca czas
	 */
	private int time = 0;
	/**
	 * zmienna odpowiadajaca za przelaczanie stanu przeciwnikow z random na smart
	 */
	private int TargetTime = 60*4;
	
	private int lastDir = -1;
	
	
	/**
	 * Konstruktor obiektu przeciwnika
	 * 
	 * @param x
	 *            Pozycja x licząc od lewego górenego rogu panelu
	 * @param y
	 *            Pozycja y licząc od lewego górenego rogu panelu
	 * 
	 */
	public Enemy(int x, int y) {
		random2=new Random();
		setBounds(x, y, 32, 32);
		dir=random2.nextInt(4);
	}

	
	/**
	 * metoda odpowiadająca za ruch przeciwników i ich sztuczną inteligencję by poruszały się za pacmanem
	 */
public void tick(){
    	
    	if(state==random){
    		
    		if(dir==right){
    			if(canMove(x+Config.EnemySpeed_Level_1,y)){
    				x+=Config.EnemySpeed_Level_1;
    			}
    			else{
    				dir = random2.nextInt(4);
    			}
    		}
    		
    		if(dir==left){
    			if(canMove(x-Config.EnemySpeed_Level_1,y)){
    				x-=Config.EnemySpeed_Level_1;
    			}
    			else{
    				dir = random2.nextInt(4);
    			}
    		}
    		
    		if(dir==up){
    			if(canMove(x,y-Config.EnemySpeed_Level_1)){
    				y-=Config.EnemySpeed_Level_1;
    			}
    			else{
    				dir = random2.nextInt(4);
    			}
    		}
    		
    		if(dir==down){
    			if(canMove(x,y+Config.EnemySpeed_Level_1)){
    				y+=Config.EnemySpeed_Level_1;
    			}
    			else{
    				dir = random2.nextInt(4);
    			}
    			time++;
    			
    			if(time == TargetTime){
    				state = smart;
    				time = 0;
    			}
    		}
    			/**
    			 * poruszanie się przeciwnika za pacmanem
    			 */
    		}else if(state==smart){
    			
    			boolean move = false;
    			
    			if(x < Game.player.x){
    				if(canMove(x+Config.EnemySpeed_Level_1,y)){
    					x+=Config.EnemySpeed_Level_1;
    					move=true;
    					lastDir = right;
    				}
    			}
    			if(x > Game.player.x){
    				if(canMove(x-Config.EnemySpeed_Level_1,y)){
    					x-=Config.EnemySpeed_Level_1;
    					move=true;
    					lastDir = left;
    				}
    			}
    			if(y < Game.player.y){
    				if(canMove(x,y+Config.EnemySpeed_Level_1)){
    					y+=Config.EnemySpeed_Level_1;
    					move=true;
    					lastDir = down;
    				}
    			}
    			if(y > Game.player.y){
    				if(canMove(x,y-Config.EnemySpeed_Level_1)){
    					y-=Config.EnemySpeed_Level_1;
    					move=true;
    					lastDir = up;
    				}
    			}
    			
    			if(x == Game.player.x && y == Game.player.y)move=true;
    			
    			
    			if(!move){
    				state= find_path;
    			}
    			
    		}else if(state == find_path){
    			if(lastDir == right){
					
					if(y< Game.player.y){
						if(canMove(x,y+Config.EnemySpeed_Level_1)){
							y+=Config.EnemySpeed_Level_1;
							state=smart;
						}
					}else{
						if(canMove(x,y-Config.EnemySpeed_Level_1)){
							y-=Config.EnemySpeed_Level_1;
							state=smart;
					}
				}if(canMove(x+Config.EnemySpeed_Level_1,y)){
					x+=Config.EnemySpeed_Level_1;
					}
				}
				else if (lastDir == left){
					if(y< Game.player.y){
						if(canMove(x,y+Config.EnemySpeed_Level_1)){
							y+=Config.EnemySpeed_Level_1;
							state=smart;
						}
					}else{
						if(canMove(x,y-Config.EnemySpeed_Level_1)){
							y-=Config.EnemySpeed_Level_1;
							state=smart;
					}
				} if(canMove(x-Config.EnemySpeed_Level_1,y)){
					x-=Config.EnemySpeed_Level_1;
				}
				}
				else if (lastDir == up){
					if(x< Game.player.x){
						if(canMove(x+Config.EnemySpeed_Level_1,y)){
							x+=Config.EnemySpeed_Level_1;
							state=smart;
						}
					}else{
						if(canMove(x-Config.EnemySpeed_Level_1,y)){
							x-=Config.EnemySpeed_Level_1;
							state=smart;
					}
				} if(canMove(y-Config.EnemySpeed_Level_1,y)){
					y-=Config.EnemySpeed_Level_1;
				}
				}
				
				else if (lastDir == down){
					if(x< Game.player.x){
						if(canMove(x+Config.EnemySpeed_Level_1,y)){
							x+=Config.EnemySpeed_Level_1;
							state=smart;
						}
					}else{
						if(canMove(x-Config.EnemySpeed_Level_1,y)){
							x-=Config.EnemySpeed_Level_1;
							state=smart;
					}
				} if(canMove(y+Config.EnemySpeed_Level_1,y)){
					y+=Config.EnemySpeed_Level_1;
				}
				}
    		}
    	}
    	
    
	
	
	
	
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
	
	
	/**
	 * Metoda rysująca reprezentację przeciwnika
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 32, 32);
	}

}
