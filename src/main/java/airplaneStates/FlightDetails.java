package airplaneStates;

import java.util.ArrayList;
import java.util.HashMap;

public class FlightDetails {
	
	
	public static HashMap<Long, ArrayList<States>> timeList = new HashMap<Long, ArrayList<States>>();
	
	public FlightDetails() {
		
	}
	
	public FlightDetails(long time, ArrayList<States> states) {
		timeList.put(time, states);
	}
	
	public void add(long time, ArrayList<States> states) {
		timeList.put(time, states);
	}


}
