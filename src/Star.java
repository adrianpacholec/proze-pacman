import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Star extends Rectangle{


	private static final long serialVersionUID = 1L;
	public Star(int x, int y){
		setBounds(x+6,y+6,20,20);
	}
	public void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
}
