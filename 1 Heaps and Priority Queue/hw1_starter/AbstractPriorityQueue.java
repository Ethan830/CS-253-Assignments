import java.util.Comparator;

public abstract class AbstractPriorityQueue<Key,Value> implements PriorityQueue<Key,Value> {

	protected static class PQEntry<Key,Value> implements Entry<Key,Value> {
		private Key k;
		private Value v;
		private double x;
		private double y;

		public PQEntry(Key k, Value v){
			this.k = k;
			this.v = v;
		}

		//methods of Entry Interface
		public Key getKey() { return k; }
		public Value getValue() {return v; }

		//utilities (not part of Entry interface)
		protected void setKey(Key k){ this.k = k; }
		protected void setValue(Value v){ this.v = v;}
		public String toString(){ return "( " + k + ", " + v + " )";}

		public void setX(double x){
			this.x = x;
		}

		public void setY(double y){
			this.y = y;
		}

		public double getX(){
			return this.x;
		}

		public double getY(){
			return this.y;
		}

	}

	// Instance variable

	private Comparator<Key> comp;

	// Constructors

	protected AbstractPriorityQueue(Comparator<Key> c) { comp = c; }

	protected AbstractPriorityQueue() { this(new DefaultComparator<Key>()); }

	// Define how to compare entries (using their keys)
	protected int compare(Entry<Key,Value> v, Entry<Key,Value> w) {
		return comp.compare(v.getKey(), w.getKey());
	}

	// Is the key valid?
	protected boolean checkKey(Key k) throws IllegalArgumentException {
		try{
			return (comp.compare(k,k) == 0); // can we compare key to itself?
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}

	public boolean isEmpty() {return size() == 0; }

}