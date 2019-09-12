package logic.enum_format;

public enum Temperature {
	
	DEGREES_CELSIUS("Degrees celsius",-273.15),
	DEGREES_KELVIN("Degrees kelvin",0);
	//
	double factor;
	String name;
	Temperature(String name, double factor) {
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
