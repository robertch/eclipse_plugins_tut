package com.ht.clock.views;

import java.util.TimeZone;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.ht.clock.util.table.TimeZoneIDColumn;
import com.ht.clock.util.table.TimeZoneNameColumn;
import com.ht.clock.util.table.TimeZoneOffsetColumn;

public class TimeZoneTableView extends ViewPart {
	private TableViewer tableViewer;
	
	public TimeZoneTableView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		tableViewer = new TableViewer(parent,SWT.H_SCROLL | SWT.V_SCROLL);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		
		String[] ids = TimeZone.getAvailableIDs();
		TimeZone[] timeZones = new TimeZone[ids.length];
		for(int i=0;i<ids.length;i++){
			timeZones[i] = TimeZone.getTimeZone(ids[i]);
		}
		new TimeZoneIDColumn().addColumnTo(tableViewer);
		new TimeZoneNameColumn().addColumnTo(tableViewer);
		new TimeZoneOffsetColumn().addColumnTo(tableViewer);
		tableViewer.setInput(timeZones);
		getSite().setSelectionProvider(tableViewer);
	}

	@Override
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}

}
