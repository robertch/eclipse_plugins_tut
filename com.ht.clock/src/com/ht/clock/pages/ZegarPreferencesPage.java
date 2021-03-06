package com.ht.clock.pages;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.ht.clock.Activator;

public class ZegarPreferencesPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		addField(new IntegerFieldEditor("launchCount", "Liczba uruchomień wtyczki", getFieldEditorParent()));
		addField(new ComboFieldEditor("colors", "Kolory tagów", new String[][]{{"test1","0"},{"test2","1"}}, getFieldEditorParent()));
	}


}
