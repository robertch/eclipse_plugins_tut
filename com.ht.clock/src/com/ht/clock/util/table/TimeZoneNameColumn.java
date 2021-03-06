package com.ht.clock.util.table;

import java.util.TimeZone;

public class TimeZoneNameColumn extends TimeZoneColumn {

	@Override
	public String getText(Object element) {
		if(element instanceof TimeZone)
			return ((TimeZone)element).getDisplayName();
		else
			return "";
	}

	@Override
	public String getTitle() {
		return "Wyświetlana nazwa";
	}

}
