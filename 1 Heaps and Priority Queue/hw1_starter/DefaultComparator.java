import java.util.Comparator;

@SuppressWarnings("unchecked")

/* Generic Comparator that uses the class "E" Comparable Interface implementation */
public class DefaultComparator<E> implements Comparator<E> {
	public int compare(E v, E w) throws ClassCastException{
		return ((Comparable<E>) v).compareTo(w);
	}
}