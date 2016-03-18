package com.ht.clock.util;

import java.util.TimeZone;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;


public class TimeZoneViewerFilter extends ViewerFilter {
	private String pattern;
	
	public TimeZoneViewerFilter(String pattern) {
		super();
		this.pattern = pattern;
	}

	@Override
	public boolean select(Viewer viewer, Object parent, Object element) {
		if(element instanceof TimeZone){
			TimeZone zone = (TimeZone)element;
			return zone.getDisplayName().contains(pattern);
		}else{
			return true;	
		}
	}

}