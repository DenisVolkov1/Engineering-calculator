package logic.enum_format;

public enum Time {
	
	DAY("Day", 0.0416666666666667),
	HOUR("Hour", 1),
	MINUTE("Minute", 60),
	SECOND("Second", 3600),
	MILLISECONDS("Millisecond", 3_600_000);
	//
	double factor;
	String name;
	Time(String name, double factor) {
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
