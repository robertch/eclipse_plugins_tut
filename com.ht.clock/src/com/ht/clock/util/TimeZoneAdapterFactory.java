package com.ht.clock.util;

import java.util.TimeZone;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

public class TimeZoneAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if(adapterType == IPropertySource.class && adaptableObject instanceof TimeZone)
			return new TimeZonePropertySource((TimeZone)adaptableObject);
		else 
			return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] {IPropertySource.class};
	}

}
