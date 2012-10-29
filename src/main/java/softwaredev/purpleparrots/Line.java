package softwaredev.purpleparrots;

import java.util.List;

public class Line {
	
	private String name;
	private List<String> stops;
	
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

}