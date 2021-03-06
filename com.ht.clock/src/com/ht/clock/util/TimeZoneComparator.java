package com.ht.clock.util;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;

public class TimeZoneComparator implements Comparator<TimeZone> {

	@Override
	public int compare(TimeZone o1, TimeZone o2) {
		return o1.getID().compareTo(o2.getID());
	}
	public static Map<String, Set<TimeZone>> getTimeZones(){
		Map<String, Set<TimeZone>> timezones = new TreeMap<String,Set<TimeZone>>();
		for (String string : TimeZone.getAvailableIDs()) {
			String[] parts = string.split("/");
			if(parts.length==2){
				String region = parts[0];
				Set<TimeZone> zones = timezones.get(region);
				if(zones == null){
					zones = new TreeSet<TimeZone>(new TimeZoneComparator());
					timezones.put(region,zones);
				}
				TimeZone timezone = TimeZone.getTimeZone(string);
				zones.add(timezone);
			}
		}
		return timezones;
	}
}
