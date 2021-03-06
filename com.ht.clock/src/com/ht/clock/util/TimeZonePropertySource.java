package com.ht.clock.util;

import java.util.Date;
import java.util.TimeZone;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class TimeZonePropertySource implements IPropertySource {
	private TimeZone timeZone;
	private static final Object ID 		 = new Object();
	private static final Object DAYLIGHT = new Object();
	private static final Object NAME	 = new Object();

	public TimeZonePropertySource(TimeZone timeZone) {
		super();
		this.timeZone = timeZone;
	}

	@Override
	public Object getEditableValue() {
		
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[]{
			new PropertyDescriptor(ID, "Strefa czasowa"),
			new PropertyDescriptor(DAYLIGHT, "Czas letni"),
			new PropertyDescriptor(NAME, "Nazwa")
		};
	}

	@Override
	public Object getPropertyValue(Object id) {
		if(ID.equals(id))
			return timeZone.getID();
		else if(DAYLIGHT.equals(id))
			return timeZone.inDaylightTime(new Date());
		else if(NAME.equals(id)){
			return timeZone.getDisplayName();
		}else{
		return null;
		}
	}

	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {

	}

	@Override
	public void setPropertyValue(Object id, Object value) {

	}

}
