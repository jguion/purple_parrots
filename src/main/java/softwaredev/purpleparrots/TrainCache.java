package softwaredev.purpleparrots;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class TrainCache {
	private Line line;
	private long updateAt = 15000l;
	private List<Train> trains = new ArrayList<Train>();
	private Calendar lastUpdate = null;
	public TrainCache(Line line) { this.line = line; }
	public List<Train> getTrains() { return getTrains(MyMbta.def); }
	public List<Train> getTrains(String location) {
		if (lastUpdate == null || !isWarm()) {
			update(location);
		}
		return trains;
	}
	private boolean isWarm() {
		return lastUpdate != null &&
				diff(Calendar.getInstance(), lastUpdate) < updateAt;
	}
	private long diff(Calendar a, Calendar b) {
		return a.getTimeInMillis() - b.getTimeInMillis();
	}
	private void update(String location) {
		trains = JsonData.getTrains(line, location);
		Calendar now = Calendar.getInstance();
		lastUpdate = now;
	}
}