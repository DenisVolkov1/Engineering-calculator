package logic.enum_format;

public enum Weight {
	
	MILLIGRAM("Miligram",1_000_000),
	GRAM("Gram",1000),
	KILOGRAM("Kilogram",1);
	//
	double factor;
	String name;
	Weight(String name, double factor) {
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
