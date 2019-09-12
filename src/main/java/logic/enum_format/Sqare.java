package logic.enum_format;

public enum Sqare {

	SQARE_MILLIMETER("Sqare millimeter", 1_000_000),
	SQARE_SENTIMETER("Sqare sentimeter", 10_000),
	SQARE_METER("Sqare meter", 1);
	//
	double factor;
	String name;
	Sqare(String name, double factor) {
		this.factor = factor;
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
	public double getFactor() {
		return factor;
	}
}
