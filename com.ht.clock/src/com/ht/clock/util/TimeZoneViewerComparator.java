package com.ht.clock.util;

import java.util.TimeZone;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class TimeZoneViewerComparator extends ViewerComparator {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		int compare;
		if(e1 instanceof TimeZone && e2 instanceof TimeZone){
			long time = System.currentTimeMillis();
			compare=((TimeZone)e2).getOffset(time)-((TimeZone)e1).getOffset(time);
		}else{
			compare = e1.toString().compareTo(e2.toString());
		}
		return super.compare(viewer, e1, e2);
	}
	
}
