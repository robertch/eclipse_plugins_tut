package com.ht.newwizard.tests;

import org.eclipse.jface.wizard.WizardDialog; 
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.ht.newwizard.NewFeedWizard;

public class MainWizard {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		new WizardDialog(shell, new NewFeedWizard()).open();
		display.dispose();
	}

}
