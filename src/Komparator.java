import java.util.Comparator;

/**
 * Klasa Komparaotr dziedziczaca po Comparator. Umozliwia porownywanie obiektow
 * klasy Records w celu posortowania ich malejaco wzgledem wartosci pola score.
 * 
 * @author Pawel Kowalik
 * @author Adrian Pacholec
 */
public class Komparator implements Comparator<Record> {

	/**
	 * metoda klasy Komparator umozliwiajaca porownanie dwoch obiektow typu
	 * Record w zaleznosci od wartosci ich pola score
	 *
	 * @param r1
	 *            obiekt klasy Record
	 * @param r2
	 *            obiekt klasy Record
	 */
	public int compare(Record r1, Record r2) {
		if (r2 == null)
			return 1;
		if (r1.score > r2.score)
			return -1;
		else if (r1.score < r2.score)
			return 1;
		else
			return 0;
	}

}