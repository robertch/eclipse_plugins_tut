package com.ht.clock.util.table;

import java.util.TimeZone;

public class TimeZoneOffsetColumn extends TimeZoneColumn {

	@Override
	public String getText(Object element) {
		if(element instanceof TimeZone){
			TimeZone timeZone = (TimeZone) element;
			int t1 = TimeZone.getDefault().getOffset(System.currentTimeMillis());
			int t2 = timeZone.getOffset(System.currentTimeMillis());
			return String.valueOf((t1-t2)/3600000);
		}else{
			return "";
		}
	}

	@Override
	public String getTitle() {
		return "Przesunięcie";
	}

}
