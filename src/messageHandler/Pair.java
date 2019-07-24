package messageHandler;

public class Pair <T, K>{
	T key;
	K value;
	public Pair(T a, K b){
		this.key = a;
		this.value = b;
	}
	
	public T getKey() {
		return key;
	}
	
	public K getValue() {
		return value;
	}
	
	

}
