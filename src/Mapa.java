import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mapa {

	public int width = 35;
	public int height = 35;

	public Tile[][] tiles;

	public Mapa(String path) {

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
				}
				yy++;
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
	}

	public void render(Graphics g) {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				if (tiles[x][y] != null)
					tiles[x][y].render(g);
			}

	}

}
