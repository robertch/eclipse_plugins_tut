package com.ht.clock.util.table;

import java.util.TimeZone;

public class TimeZoneIDColumn extends TimeZoneColumn {

	@Override
	public String getText(Object element) {
		if(element instanceof TimeZone)
			return ((TimeZone)element).getID();
		else
			return "";
	}

	@Override
	public String getTitle() {
		return "ID";
	}

}
