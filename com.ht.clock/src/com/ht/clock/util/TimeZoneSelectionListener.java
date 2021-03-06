package com.ht.clock.util;

import java.util.TimeZone;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

public class TimeZoneSelectionListener implements ISelectionListener {
	private Viewer viewer;
	private IWorkbenchPart part;

	public TimeZoneSelectionListener(Viewer viewer, IWorkbenchPart part) {
		super();
		this.viewer = viewer;
		this.part = part;
	}

	@Override
	public void selectionChanged(IWorkbenchPart p, ISelection sel) {
		if(p !=this.part){
			Object selected = ((IStructuredSelection)sel).getFirstElement();
			Object current = ((IStructuredSelection)this.viewer.getSelection()).getFirstElement();
			if(selected != current && selected instanceof TimeZone){
				viewer.setSelection(sel);
				if(viewer instanceof StructuredViewer){
					((StructuredViewer)viewer).reveal(selected);
				}
			}
		}
	}

}
