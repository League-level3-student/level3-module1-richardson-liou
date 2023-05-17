package _07_Meeting_Scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MeetingScheduler {
    /*
     * Your task is to code a method to find a meeting time for two people
     * given their schedules.
     * 
     * Code the method below so that it returns a Schedule object that contains
     * all the times during the week that are in BOTH people's schedules. The
     * Schedule class is included in this package.
     * 
     * Example:
     * person1 availability - Monday at 9, Tuesday at 14, and Friday 10
     * person2 availability - Tuesday at 14, Friday 8, and Monday at 9
     * The returned HashMap should contain: Tuesday 14 and Monday 9
     * 
     * The returned Schedule object represents the times both people are
     * available for a meeting.
     * 
     * Time availability is always at the top of the hour, so 9:30 is not valid
     * Time availability always represents 1 hour
     * Assume both schedules are in the same time zones
     */
	

    public static Schedule getMutualAvailability(Schedule person1, Schedule person2) {
    	Schedule available = new Schedule();
    	ArrayList<Integer> availableTimes = new ArrayList<Integer>();
        for(String i : person1.getSchedule().keySet()) {        	
        		if(person2.getSchedule().containsKey(i)) {
        			String avDay = i;
        			System.out.println(i);
        			ArrayList<Integer> p2av = person2.getSchedule().get(i);
        			ArrayList<Integer> p1av = person1.getSchedule().get(i);
        			for(int k = 0; k< p2av.size(); k ++) {
        				int p2time= p2av.get(k);
        				int p1time = p1av.get(k);
        				
        				if(p1time == p2time) {
        					availableTimes.add(p2time);
        					available.addAvailability(i, p1time);
        					available.getSchedule().put(i, availableTimes);
        				}
        			}
        		}
        	}
        
        return available;
    }
}
