public class SkipListEntry {
  private String key;
  private Integer value;

  public int pos;      // I added this to print the skiplist "nicely"

  public SkipListEntry up, down, left, right;

  public static String negInf = new String("-oo");  // -inf key value
  public static String posInf = new String("+oo");  // +inf key value

  public SkipListEntry(String k, Integer v) { 
     key = k;
     value = v;

     up = down = left = right = null;
  }

  public Integer getValue() { return value; }

  public String getKey() { return key; }

  public Integer setValue(Integer val) {
    Integer oldValue = value;
    value = val;
    return oldValue;
  }

  @Override
  public boolean equals(Object o) {
    SkipListEntry ent;

    try { 
      ent = (SkipListEntry) o;    // Test if o is a SkipListEntry...
    }
    catch (ClassCastException ex) {
    	return false; 
    }

    return (ent.getKey() == key) && (ent.getValue() == value);
  }

  @Override
  public String toString() {
    return "(" + key + "," + value + ")";
  }
}