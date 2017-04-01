import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mapa {

	public int width = 35;
	public int height = 35;

	public Tile[][] tiles;
	
	public List<Punkt> punkty;
	public List<Enemy> enemies;

	public Mapa(String path) {
		punkty = new ArrayList<>();
		enemies = new ArrayList<>();
		try {
			File file = new File(path);
			Scanner in = new Scanner(file);
			tiles = new Tile[width][height];
			int yy = 0;
			while (in.hasNextLine()) {
				String line = in.nextLine();
				for (int xx = 0; xx < line.length(); xx++) {
					if (line.charAt(xx) == '1') {
						tiles[xx][yy] = new Tile(xx * 32, yy * 32);

					}
					else if (line.charAt(xx) == 'P'){
						Game.player.x = xx*32;
						Game.player.y = yy*32;
					}
					else if (line.charAt(xx) == 'E'){
						enemies.add(new Enemy(xx*32, yy*32));
					}
					else{
						punkty.add(new Punkt(xx*32,yy*32));
					}
				}
				yy++;
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
	}
	public void tick(){
		for(int i=0; i< enemies.size(); i++)
		{
			enemies.get(i).tick();
		}
	}
	
	
	public void render(Graphics g) {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				if (tiles[x][y] != null) tiles[x][y].render(g);
			}

	
	for(int i=0; i < punkty.size();i++)
	{
		punkty.get(i).render(g);
	}
	for(int i=0; i < enemies.size();i++)
	{
		enemies.get(i).render(g);
	}
}
	
}
