package softwaredev.purpleparrots;

import java.util.ArrayList;
import java.util.List;

public class Line {
	
	private String name;
	private List<String> stops;
	
	public Line(String name) {
		this.name = name;
		this.stops = new ArrayList<String>();
	}
	
	public Line(String name, List<String> stops) {
		this.name = name;
		this.stops = stops;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getStops() {
		return stops;
	}

	public void setStops(List<String> stops) {
		this.stops = stops;
	}
	
	@Override public boolean equals(Object that) {
		return that != null && that instanceof Line && equals((Line)that);
	}
	
	@Override public int hashCode() {
		return 42 * this.name.hashCode();
	}
	
	private boolean equals(Line that) {
		return that != null && this.name == null ? that.name == null : this.name.equals(that.name);
	}

}