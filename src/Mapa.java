import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa reprezentująca mapę gry
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Mapa {
	/**
	 * Liczba rzędów bloków
	 */
	public int width = 35;
	/**
	 * Liczba kolumn bloków
	 */
	public int height = 35;
	
	
	/**
	 * rozmiar mapy
	 */
	static public int MapSize;
	/**
	 * Tablica obiektów typu Tile
	 */
	public Tile[][] tiles;
	/**
	 * Lista obiektów typu Punkt
	 */
	public List<Punkt> punkty;
	/**
	 * Lista obiektów typu Enemy
	 */
	public List<Enemy> enemies;
	/**
	 * Lista obiektów typu Star
	 */
	public List<Star> stars;

	/**
	 * Konstruktor obiektu mapy. Wczytuje informację o pozycji danego obiektu na
	 * planszy z pliku mapa.txt i odpowiednio tworzy obiekty na mapie.
	 * 
	 * @param path
	 *            Ścieżka dostępu do pliku mapa.txt
	 * 
	 */

	public Mapa(String path) {
		punkty = new ArrayList<>();
		enemies = new ArrayList<>();
		stars = new ArrayList<>();
		try {
			Scanner in = new Scanner(new File(path));
			tiles = new Tile[width][height];
			int yy = 0;
			while (in.hasNextLine()) {
				String line = in.nextLine();
				MapSize = line.length();
				for (int xx = 0; xx < line.length(); xx++) {
					if (line.charAt(xx) == '1') {
						tiles[xx][yy] = new Tile(xx * 32, yy * 32);
					} else if (line.charAt(xx) == 'P') {
						Game.player.x = xx * 32;
						Game.player.y = yy * 32;
					} else if (line.charAt(xx) == 'E') {
						enemies.add(new Enemy(xx * 32, yy * 32));
					} else if (line.charAt(xx) == 'S') {
						stars.add(new Star(xx * 32, yy * 32));
					} else {
						punkty.add(new Punkt(xx * 32, yy * 32));
					}
				}
				yy++;
			}
			System.out.println(yy);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
	}

	/**
	 * Metoda render wywołująca metody render poszczególnych obiektów należących
	 * do mapy gry
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				if (tiles[x][y] != null)
					tiles[x][y].render(g);
			}

		for (int i = 0; i < punkty.size(); i++) {
			punkty.get(i).render(g);
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(g);
		}
		for (int i = 0; i < stars.size(); i++) {
			stars.get(i).render(g);
		}
	}

}
