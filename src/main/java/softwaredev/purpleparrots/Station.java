package softwaredev.purpleparrots;

import java.util.List;

public class Station {
	
	private String name;
	private List<String> lines;
	
	public Station(String name, List<String> lines) {
		this.name = name;
		this.lines = lines;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

}
