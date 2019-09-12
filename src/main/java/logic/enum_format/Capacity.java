package logic.enum_format;

public enum Capacity {
	
	CUBIC_MILLIMETER("Cubic millimeter", 1_000_000_000),
	CUBIC_CENTIMETER("Cubic centimeter", 1_000_000 ),
	CUBIC_METER("Cubic meter", 1);
	//
	double factor;
	String name;
	Capacity(String name, double factor) {
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
