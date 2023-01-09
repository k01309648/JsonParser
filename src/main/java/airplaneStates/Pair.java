package airplaneStates;

import java.util.ArrayList;

public class Pair {
	
	private long first;
	private ArrayList<States>  second;
	
    public Pair(long first, ArrayList<States> second) {

        this.first = first;
        this.second = second;
    }
    
    
    public long returnFirst() {
    	return first;
    }

    public ArrayList<States> returnSecond() {
    	return second;
    }
}
