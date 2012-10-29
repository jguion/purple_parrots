package softwaredev.purpleparrots;

import java.util.List;

public class Route {

	private List<String> trainIds;
	private List<String> stops;
	private List<Integer> schedule;
	private Integer transfers;
	private Integer time;
	
	public Route(List<String> trainIds, List<String> stops, List<Integer> schedule, Integer transfers, Integer time) {
		this.trainIds = trainIds;
		this.stops = stops;
		this.schedule = schedule;
		this.transfers = transfers;
		this.time = time;
	}

	public List<String> getTrainIds() {
		return trainIds;
	}

	public void setTrainIds(List<String> trainIds) {
		this.trainIds = trainIds;
	}

	public List<String> getStops() {
		return stops;
	}

	public void setStops(List<String> stops) {
		this.stops = stops;
	}

	public List<Integer> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Integer> schedule) {
		this.schedule = schedule;
	}

	public Integer getTransfers() {
		return transfers;
	}

	public void setTransfers(Integer transfers) {
		this.transfers = transfers;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}
