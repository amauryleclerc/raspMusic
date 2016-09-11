package fr.aleclerc.rasp.music.api.utils;

public class Tuple<X, Y> {
	public final X a;
	public final Y b;

	private Tuple(X a, Y b) {
		this.a = a;
		this.b = b;
	}

	public static <X, Y> Tuple<X, Y> tuple(X a, Y b) {
		return new Tuple<X, Y>(a, b);
	}

	public X getA() {
		return a;
	}

	public Y getB() {
		return b;
	}

}