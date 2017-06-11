import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa reprezentuj¹ca mapê gry
 *
 * @author Pawe³ Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Mapa {
	/**
	 * Liczba rzêdów bloków
	 */
	public int width;

	/**
	 * Liczba kolumn bloków
	 */
	public int height;

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
	 * zmienna potrzebna do przekazania predkosci wrogow
	 */
	public int speedlevel;

	/**
	 * Konstruktor obiektu mapy. Wczytuje informacjê o pozycji danego obiektu na
	 * planszy z pliku mapa.txt i odpowiednio tworzy obiekty na mapie.
	 * 
	 * @param path
	 *            Œcie¿ka dostêpu do pliku mapa.txt
	 * 
	 */

	public Mapa(String mapaPath, int speedlevel) {

		this.speedlevel = speedlevel;
		punkty = new ArrayList<>();
		enemies = new ArrayList<>();
		stars = new ArrayList<>();
		try {
			Scanner in = new Scanner(new File(mapaPath));
			int licznik = 0;
			while (in.hasNextLine()) {
				licznik++;
				width = in.nextLine().length();
				height = licznik;
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		char p[][] = new char[height + 2][width + 2];
		for (int w = 0; w < width + 2; w++) {
			p[0][w] = '1';
			p[height + 1][w] = '1';
		}
		for (int h = 0; h < height + 2; h++) {
			p[h][0] = '1';
			p[h][width + 1] = '1';
		}

		for (int h = 0; h < height + 2; h++) {
			for (int w = 0; w < width + 2; w++) {
				System.out.print(p[h][w]);
			}
			System.out.println();
		}
		System.out.println();
		try {
			Scanner in = new Scanner(new File(mapaPath));
			int j = 0;
			while (in.hasNextLine()) {
				String line = in.nextLine();
				for (int i = 0; i < line.length(); i++)
					p[j + 1][i + 1] = line.charAt(i);
				j++;
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int h = 0; h < height + 2; h++) {
			for (int w = 0; w < width + 2; w++) {
				System.out.print(p[h][w]);
			}
			System.out.println();
		}

		tiles = new Tile[height][width];

		for (int yy = 0; yy < height; yy++) {
			for (int xx = 0; xx < width; xx++) {
				if (p[yy + 1][xx + 1] == '1') {
					int[] a = { p[yy][xx], p[yy][xx + 1], p[yy][xx + 2], p[yy + 1][xx], p[yy + 1][xx + 2],
							p[yy + 2][xx], p[yy + 2][xx + 1], p[yy + 2][xx + 2] };
					tiles[yy][xx] = new Tile(xx * 32, yy * 32, a);
				} else if (p[yy + 1][xx + 1] == 'P') {
					Game.player.x = xx * 32;
					Game.player.y = yy * 32;
					Game.player.newx = xx * 32;
					Game.player.newy = yy * 32;
				} else if (p[yy + 1][xx + 1] == 'E') {
					enemies.add(new Enemy(xx * 32, yy * 32, speedlevel));
				} else if (p[yy + 1][xx + 1] == 'S') {
					stars.add(new Star(xx * 32, yy * 32));
				} else {
					punkty.add(new Punkt(xx * 32, yy * 32));
				}
			}
		}

	}

	public void update() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
		}
	}

	/**
	 * Metoda render wywo³uj¹ca metody render poszczególnych obiektów nale¿¹cych
	 * do mapy gry
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++) {
				if (tiles[y][x] != null)
					tiles[y][x].render(g);
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
