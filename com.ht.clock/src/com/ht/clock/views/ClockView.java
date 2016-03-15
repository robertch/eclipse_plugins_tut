package com.ht.clock.views;


import java.util.TimeZone;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;

import com.ht.clock.util.ClockWidget;

public class ClockView extends ViewPart {
	private TableViewer viewer;
	private Combo timezones;
	

	public ClockView() {}

	public void createPartControl(Composite parent) {
		Object[] oo = parent.getDisplay().getDeviceData().objects;
		int c = 0;
		for (int i = 0; i < oo.length; i++) 
				if(oo[i] instanceof Color) c++;
		System.err.println("c = "+c);
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		parent.setLayout(layout);
		final ClockWidget clock1 = new ClockWidget(parent,SWT.NONE,new RGB(255,0,0));
		final ClockWidget clock2 = new ClockWidget(parent,SWT.NONE,new RGB(0,255,0));
		final ClockWidget clock3 = new ClockWidget(parent,SWT.NONE,new RGB(0,0,0));
		
		String[] ids = TimeZone.getAvailableIDs();
		timezones = new Combo(parent, SWT.DROP_DOWN);
		timezones.setVisibleItemCount(5);
		for (String string : ids) {
			timezones.add(string);
		}
		timezones.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				String z = timezones.getText();
				TimeZone tz = z == null ? null : TimeZone.getTimeZone(z);
				TimeZone dt = TimeZone.getDefault();
				int offset = tz == null ? 0 : (tz.getOffset(System.currentTimeMillis()) - dt.getOffset(System.currentTimeMillis())) / 3600000;
				clock3.setOffset(offset);
				clock3.redraw();			
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				clock3.setOffset(0);
				clock3.redraw();
			}
		});
	}

	public void setFocus() {
		timezones.setFocus();
	}
}