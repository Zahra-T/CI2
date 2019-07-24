package game;

public class Triple <S, T, K>{
	S a;
	T b;
	K c;
	
	public Triple(S a, T b, K c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public S getFirst() {
		return a;
	}
	public T getSecond() {
		return b;
	}
	public K getThird() {
		return c;
	}
}
