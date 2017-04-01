import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
	
	private static final long serialVersionUID = 1L;

	public boolean right, left, up, down;
	private int speed = 4; 
	
	
	
	public Player(int x, int y){
		setBounds(x,y,32,32);
	}
	/*
	public void tick(){
		if(right || canMove(x+speed,y))x+=speed;
		if(left || canMove(x-speed,y))x-=speed;
		if(up || canMove(x,y-speed))y-=speed;
		if(down || canMove(x,y+speed))y+=speed;
	}
	*/
	
	public void tick(){
		if(right && canMove(x+speed,y))x+=speed;
		if(left)x-=speed;
		if(up)y-=speed;
		if(down)y+=speed;
	}
		
	private boolean canMove(int nextx, int nexty){
		Rectangle bounds = new Rectangle(nextx,nexty,width,height);
		Mapa mapa = Game.mapa;
		
		for(int xx = 0; xx < mapa.tiles.length; x++){
			for (int yy = 0; yy < mapa.tiles[0].length; yy++){
				if(mapa.tiles[xx][yy] != null){
					if(bounds.intersects(mapa.tiles[xx][yy])){
						return false;
					}
				}
			}
		}
		
		return true;
	}
	

	
	public void render(Graphics g){
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}


	
	
}
