/* Interface for a key-value pair */
public interface Entry<Key,Value> {
	Key getKey();
	Value getValue();	
	double getX();
	double getY();
	void setX(double x);
	void setY(double y);
}